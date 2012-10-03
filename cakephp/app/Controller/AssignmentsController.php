<?php
App::uses('AppController', 'Controller');
/**
 * Assignments Controller
 *
 * @property Assignment $Assignment
 */
class AssignmentsController extends AppController {

	private $refereeroles = NULL;

	/**
	 * Index method: show all assignments of a specific season, current season if none given.
	 *
	 * @param string $season
	 * @return void
	 */
	public function index($season = null) {

		// select season
		if ($season == null) {
			$season = sprintf('%d', ((idate('m') < 8) ? (idate('Y') - 1) : idate('Y')));
		}
		$options['conditions'] = array(
			'Season.year_start' => $season
		);

		// find matching assignments
		$assignments = $this->Assignment->find('all', $options);

		// reconfigure array for easy access of values
		foreach ($assignments as &$assignment):
			$this->fillAssignment(&$assignment);
		endforeach;

		// pass information to view
		$this->set('assignments', $assignments);
		$this->set('season', $season);
		$this->set('refereeroles', $this->getRefereeRoles());
	}

	/**
	 * view method
	 *
	 * @throws NotFoundException
	 * @param string $id
	 * @return void
	 */
	public function view($id = null) {

		// get and fill assignment
		$this->Assignment->id = $id;
		if (!$this->Assignment->exists()) {
			throw new NotFoundException(__('Invalid assignment'));
		}
		$assignment = $this->Assignment->read(null, $id);
		$this->fillAssignment(&$assignment);

		// get changes
		$changes = $this->getChangesForAssignment($assignment);;

		// pass information to view
		$this->set('assignment', $assignment);
		$this->set('changes', $changes);
		$this->set('refereeroles', $this->getRefereeRoles());
	}

/**
 * add method
 *
 * @return void
 */
	public function add() {
		if ($this->request->is('post')) {
			$this->Assignment->create();
			if ($this->Assignment->save($this->request->data)) {
				$this->Session->setFlash(__('The assignment has been saved'));
				$this->redirect(array('action' => 'index'));
			} else {
				$this->Session->setFlash(__('The assignment could not be saved. Please, try again.'));
			}
		}
		$seasons = $this->Assignment->Season->find('list');
		$this->set(compact('seasons'));
	}

/**
 * edit method
 *
 * @throws NotFoundException
 * @param string $id
 * @return void
 */
	public function edit($id = null) {
		$this->Assignment->id = $id;
		if (!$this->Assignment->exists()) {
			throw new NotFoundException(__('Invalid assignment'));
		}
		if ($this->request->is('post') || $this->request->is('put')) {
			if ($this->Assignment->save($this->request->data)) {
				$this->Session->setFlash(__('The assignment has been saved'));
				$this->redirect(array('action' => 'index'));
			} else {
				$this->Session->setFlash(__('The assignment could not be saved. Please, try again.'));
			}
		} else {
			$this->request->data = $this->Assignment->read(null, $id);
		}
		$seasons = $this->Assignment->Season->find('list');
		$this->set(compact('seasons'));
	}

/**
 * delete method
 *
 * @throws MethodNotAllowedException
 * @throws NotFoundException
 * @param string $id
 * @return void
 */
	public function delete($id = null) {
		if (!$this->request->is('post')) {
			throw new MethodNotAllowedException();
		}
		$this->Assignment->id = $id;
		if (!$this->Assignment->exists()) {
			throw new NotFoundException(__('Invalid assignment'));
		}
		if ($this->Assignment->delete()) {
			$this->Session->setFlash(__('Assignment deleted'));
			$this->redirect(array('action' => 'index'));
		}
		$this->Session->setFlash(__('Assignment was not deleted'));
		$this->redirect(array('action' => 'index'));
	}

	/**
	 * Returns available referee roles.
	 *
	 */
	private function getRefereeRoles() {
		if ($this->refereeroles == NULL) {
			$this->loadModel('RefereeAssignmentRole');
			$wholerefereeroles = $this->RefereeAssignmentRole->find('all');
			$this->refereeroles = array();
			foreach ($wholerefereeroles as $refereerole):
				$this->refereeroles[] = $refereerole['RefereeAssignmentRole'];
			endforeach;
		}
		return $this->refereeroles;
	}

	/**
	 * Fills assignment array with missing values, resp. gives readable names for easy access in views.
	 *
	 */
	private function fillAssignment($assignment) {

		// load Person model
		$this->loadModel('Person');

		// home and road team
		foreach ($assignment['Team'] as $team):
			if ($team['TeamAssignment']['home']) {
				$assignment['HomeTeam'] = $team;
			} else {
				$assignment['RoadTeam'] = $team;
			}
		endforeach;

		// referees
		foreach ($assignment['Referee'] as &$referee):
			// person data
			$referee['Person'] = $this->Person->findById($referee['person_id']);

			// assignment roles
			foreach ($this->getRefereeRoles() as $refereerole):
				if ($referee['RefereeAssignment']['referee_assignment_role_id'] == $refereerole['id']) {
					if (!array_key_exists($refereerole['code'], $assignment)) {
						$assignment[$refereerole['code']] = array();
					}
					$assignment[$refereerole['code']][] = $referee;
				}
			endforeach;
		endforeach;
	}

	/**
	 * Returns changes for the selected assignment.
	 *
	 */
	private function getChangesForAssignment($assignment) {

		// initialize return array
		$changes = array();

		// load ActivityLog model
		$this->loadModel('ActivityLog');

		// tables that contain changes for referee assignments
		$changes_tables = array('addresses', // address change of home team
														'leagues', // change of umpire report link
														'umpire_report_recipients',
														'clubs', // address change of home team
														'teams', // name, address
														'team_spokespeople',
														'assignments',
														'referee_assignments',
														'team_assignments');

		// change of assignment
		$logs = $this->ActivityLog->find('all',
			array(
				'conditions' => array(
					'ActivityLog.table_name' => 'assignments',
					'ActivityLog.row_id' => $assignment['Assignment']['id']
				)
			)
		);
		foreach ($logs as $log) {
			if (!array_key_exists($log['ActivityLog']['created'], $changes)) {
				$changes[$log['ActivityLog']['created']] = array();
			}
			switch ($log['ActivityLog']['column_name']) {
				case 'datetime':
					$old_value = $log['ActivityLog']['old_value'];
					$new_value = $log['ActivityLog']['new_value'];
					$type = 'datetime';
					break;
				default:
					$old_value = $log['ActivityLog']['old_value'];
					$new_value = $log['ActivityLog']['new_value'];
					break;
			}
			$change = array(
				'datetime' => $log['ActivityLog']['created'],
				'description' => $log['ActivityLog']['description'],
				'type' => $type,
				'old_value' => $old_value,
				'new_value' => $new_value
			);
			$changes[$log['ActivityLog']['created']][] = $change;
		}

		// return results
		return $changes;
	}

}

/* EOF*/

