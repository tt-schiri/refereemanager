<?php

App::uses('AppController', 'Controller');

/**
 * Assignments Controller
 *
 * @property Assignment $Assignment
 */
class AssignmentsController extends AppController {

	/** Helpers that are used within this class and the associated views. */
	public $helpers = array('RefereeFormat');

	/** Singleton containing the available referee roles. */
	private $refereeroles = NULL;

	/**
	 * Index method: show all assignments of a specific season, current season if none given.
	 *
	 * @param $season season to show assignments for
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
			$this->fillAssignment($assignment);
		endforeach;

		// pass information to view
		$this->set('assignments', $assignments);
		$this->set('season', $season);
		$this->set('refereeroles', $this->getRefereeRoles());

		// set title
		$this->set('title_for_layout', __('Übersicht der Schiedsrichtereinsätze'));
	}

	/**
	 * View method: show the assignment with the given id.
	 *
	 * @param $id id of assignment
	 * @return void
	 */
	public function view($id = null) {

		// get and fill assignment
		$this->Assignment->id = $id;
		if (!$this->Assignment->exists()) {
			throw new NotFoundException(__('Schiedsrichtereinsatz nicht vorhanden'));
		}
		$assignment = $this->Assignment->read(null, $id);
		$this->fillAssignment($assignment);

		// get changes
		$changes = $this->getChanges($assignment);;

		// get venue
		$venue = $this->getVenue($assignment);;

		// pass information to view
		$this->set('assignment', $assignment);
		$this->set('changes', $changes);
		$this->set('venue', $venue);
		$this->set('refereeroles', $this->getRefereeRoles());
	}

	/**
	 * Add method: add new assignment.
	 *
	 * @return void
	 */
	public function add() {
		if ($this->request->is('post')) {
			$this->Assignment->create();
			if ($this->Assignment->save($this->request->data)) {
				$this->Session->setFlash(__('Der Schiedsrichtereinsatz wurde gespeichert.'));
				$this->redirect(array('action' => 'index'));
			} else {
				$this->Session->setFlash(__('Der Schiedsrichtereinsatz konnte nicht gespeichert werden.') . ' ' . __('Bitte versuchen Sie es noch einmal.'));
			}
		}

		$this->prepareAndSetAddEdit();
	}

	/**
	 * Edit method: edit the assignment with the given id.
	 *
	 * @param $id id of assignment
	 * @return void
	 */
	public function edit($id = null) {
		$this->Assignment->id = $id;

		if (!$this->Assignment->exists()) {
			throw new NotFoundException(__('Schiedsrichtereinsatz nicht vorhanden'));
		}

		if ($this->request->is('post') || $this->request->is('put')) {
			if ($this->Assignment->save($this->request->data)) {
				$this->Session->setFlash(__('Der Schiedsrichtereinsatz wurde gespeichert.'));
				$this->redirect(array('action' => 'index'));
			} else {
				$this->Session->setFlash(__('Der Schiedsrichtereinsatz konnte nicht gespeichert werden.') . ' ' . __('Bitte versuchen Sie es noch einmal.'));
			}
		} else {
			$this->request->data = $this->Assignment->read(null, $id);
		}

		$this->prepareAndSetAddEdit();
	}

	/**
	 * Delete method: show the assignment with the given id and ask for deletion or delete assignment.
	 *
	 * @param $id id of assignment
	 * @return void
	 */
	public function delete($id = null) {

		$this->Assignment->id = $id;
		if (!$this->Assignment->exists()) {
			throw new NotFoundException(__('Schiedsrichtereinsatz nicht vorhanden'));
		}

		if ($this->request->is('post') || $this->request->is('put')) {
//		if ($this->Assignment->delete()) {
			if (false) {
				$this->Session->setFlash(__('Der Schiedsrichtereinsatz wurde gelöscht.'));
				$this->redirect(array('action' => 'index'));
			} else {
				$this->Session->setFlash(__('Der Schiedsrichtereinsatz konnte nicht gelöscht werden.') . ' ' . __('Bitte versuchen Sie es noch einmal.'));
			}
		}

		$assignment = $this->Assignment->read(null, $id);
		$this->fillAssignment($assignment);

		// get changes
		$changes = $this->getChanges($assignment);;

		// get venue
		$venue = $this->getVenue($assignment);;

		// pass information to view
		$this->set('assignment', $assignment);
		$this->set('changes', $changes);
		$this->set('venue', $venue);
		$this->set('refereeroles', $this->getRefereeRoles());
	}

	/**
	 * Prepares data for add/edit view and sets it.
	 *
	 */
	private function prepareAndSetAddEdit() {
		$seasons = $this->Assignment->Season->find('list');
		asort($seasons, SORT_LOCALE_STRING);
		$leagues = $this->Assignment->League->find('list');
		asort($leagues, SORT_LOCALE_STRING);
		$addresses = $this->Assignment->Address->find('list');
		asort($addresses, SORT_LOCALE_STRING);

		// prepare teams
		$teamobjects = $this->Assignment->Team->find('all');
		$teams = array();
		foreach ($teamobjects as $team):
			$teams[$team['Team']['id']] = $this->Assignment->Team->getTitle($team);
		endforeach;
		asort($teams, SORT_LOCALE_STRING);
		$hometeamid = -1;
		$offteamid = -1;
		if ($this->Assignment->data) {
			foreach ($this->Assignment->data['Team'] as $team):
				if ($team['TeamAssignment']['home']) {
					$hometeamid = $team['id'];
				} else {
					$offteamid = $team['id'];
				}
			endforeach;
		}

		// prepare referees
		$refereeobjects = $this->Assignment->Referee->find('all');
		$referees = array();
		foreach ($refereeobjects as $referee):
			$referees[$referee['Referee']['id']] = $this->Assignment->Referee->getTitle($referee);
		endforeach;
		asort($referees, SORT_LOCALE_STRING);

		// get selected referees
		$selectedreferees = array();
		if ($this->Assignment->data) {
			foreach ($this->Assignment->data['Referee'] as $referee):
				if (!array_key_exists($referee['RefereeAssignment']['referee_assignment_role_id'], $selectedreferees)) {
					$selectedreferees[$referee['RefereeAssignment']['referee_assignment_role_id']] = array();
				}
				$selectedreferees[$referee['RefereeAssignment']['referee_assignment_role_id']][] = $referee['id'];
			endforeach;
		}

		// data and time
		$datetime = ($this->Assignment->data) ? $this->Assignment->data['Assignment']['datetime'] : null;

		// pass information to view
		$this->set(compact('seasons'));
		$this->set(compact('leagues'));
		$this->set(compact('addresses'));
		$this->set(compact('teams'));
		$this->set(compact('referees'));
		$this->set('hometeamid', $hometeamid);
		$this->set('offteamid', $offteamid);
		$this->set('datetime', $datetime);
		$this->set('refereeroles', $this->getRefereeRoles());
		$this->set('selectedreferees', $selectedreferees);

		$this->set('assignment', $this->Assignment->data);
	}

	/**
	 * Returns available referee roles.
	 *
	 * @return array with all available referee assignment roles
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
	 * @param $assignment assignment to fill (is changed within function)
	 */
	private function fillAssignment(&$assignment) {

		// load needed models
		$this->loadModel('Person');
		$this->loadModel('Team');

		// home and road team
		foreach ($assignment['Team'] as $team):
			// hack for team name
			$fullteam = $this->Team->findById($team['id']);
			$fullteam['Team']['title_team'] = $this->Team->getTitle($fullteam);
			if ($team['TeamAssignment']['home']) {
				$assignment['HomeTeam'] = $fullteam;
			} else {
				$assignment['RoadTeam'] = $fullteam;
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
	 * @param $assignment assignment to get changes for
	 * @return array with changes
	 */
	private function getChanges($assignment) {

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

	/**
	 * Returns venue address for the selected assignment.
	 *
	 * @param $assignment assignment to get venue for
	 * @return venue address (NULL if none found)
	 */
	private function getVenue($assignment) {

		// direct address
		if ($assignment['Assignment']['address_id'] > 0) {
			return $assignment['Address'];
		}

		// address of team
		if ($assignment['HomeTeam']['Team']['address_id'] > 0) {
			return $assignment['HomeTeam']['Address'];
		}

		// address of club
		if ($assignment['HomeTeam']['Club']['address_id'] > 0) {
			$this->loadModel('Address');
			$address = $this->Address->findById($assignment['HomeTeam']['Club']['address_id']);
			return $address['Address'];
		}

		// nothing found
		return NULL;
	}

}

/* EOF*/
