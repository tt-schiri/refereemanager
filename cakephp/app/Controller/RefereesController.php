<?php

App::uses('AppController', 'Controller');
App::uses('RefManPeople', 'Utility');
App::uses('RefManRefereeFormat', 'Utility');
App::uses('RefManTemplate', 'Utility');

/**
 * Referees Controller
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.3
 * @since 0.1
 */
class RefereesController extends AppController {

	/** Helper classes. */
	public $helpers = array('PHPExcel', 'RefereeFormat', 'RefereeForm');

	/** Models. */
	public $uses = array('Person', 'Referee', 'RefereeRelationType', 'Season', 'SexType', 'StatusType');

	/**
	 * Defines actions to perform before the action method is executed.
	 */
	public function beforeFilter() {
		parent::beforeFilter();

		$this->StatusType->recursive = -1;
		$this->RefereeRelationType->recursive = -1;
	}

	/**
	 * Index method.
	 *
	 * @param season season (default: null == current season)
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	public function index($season = null) {

		$this->setAndGetStandardIndexExport($season);

		$this->set('title_for_layout', __('Übersicht der Schiedsrichter_innen'));
	}

	/**
	 * Export method.
	 *
	 * @param season season to use (default: null == current season)
	 * @param type export type (default: excel)
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	public function export($season = null, $type = 'excel') {

		$this->setAndGetStandardIndexExport($season);

		$this->set('type', $type);

		$this->set('title_for_layout', __('Export der Schiedsrichter_innen'));

		if ($type === 'pdf') {
			$this->layout = 'pdf';
			$this->render();
		}

	}

	/**
	 * View method: show the referee with the given id.
	 *
	 * @param $id id of referee
	 * @return void
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	public function view($id = null) {

		$this->Referee->id = $id;
		if (!$this->Referee->exists()) {
			throw new NotFoundException(__('Schiedsrichter_in mit der ID \'%s\' existiert nicht.', $id));
		}
		$referee = $this->Referee->read(null, $id);

		$this->setAndGetStandardNewAddView($referee);
		$this->set('title_for_layout', __('Detailanzeige Schiedsrichter%s %s', ($referee['Person']['sex_type_sid'] === 'f') ? 'in' : '', RefManRefereeFormat::formatPerson($referee['Person'], 'fullname')));
		$this->render('/Generic/view');
	}



	/**
	 * Set and get standard values for new, add, view.
	 *
	 * @param $referee referee
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	private function setAndGetStandardNewAddView(&$referee) {

		$sextypes = $this->SexType->getSexTypes();
		$referee['Person']['sex_type_sid'] = $sextypes[$referee['Person']['sex_type_id']]['SexType']['sid'];

		// pass information to view
		$this->set('referee', $referee);
		$this->set('sextypes', $sextypes);
		$this->set('sextypearray', $this->SexType->getSexTypeList());
		$this->set('contacttypes', $this->getContactTypes());
		$this->set('refereerelationtypes', $this->getRefereeRelationTypes());
		$this->set('clubarray', $this->getClubArray());

		$this->set('id', $referee['Referee']['id']);
	}

	/**
	 * Set and get standard values for index and export.
	 *
	 * @param season season (default: null == current season)
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	private function setAndGetStandardIndexExport($season = null) {

		$theSeason = $this->getSeason($season);
		$this->setAndGetStandard();

		$referees = $this->Referee->getReferees($theSeason, $this->viewVars['isEditor']);
		$this->set('people', $referees);

		$this->set('statustypes', $this->getUsedStatusTypes($referees, $theSeason));
		$this->set('refereerelationtypes', $this->getUsedRefereeRelationTypes($referees));

		$this->set('isRefView', true);
	}

	/**
	 * Set and get standard values.
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	private function setAndGetStandard() {

		$this->set('seasonarray', $this->Season->getSeasonList($this->viewVars['isEditor']));

		$this->set('controller', 'Referees');
	}

	/**
	 * Returns the status types used by the referees.
	 *
	 * @param $referees referees
	 * @param $season season
	 * @return array of status types
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	private function getUsedStatusTypes($referees, $season) {
		$arrTemp = array();
		$badIDs = array();

		foreach ($this->StatusType->find('all') as $statustype) {
			$arrTemp[$statustype['StatusType']['id']] = $statustype;
			if ($statustype['StatusType']['sid'] === StatusType::SID_NORMAL) {
				$badIDs[] = $statustype['StatusType']['id'];
			}
		}

		foreach ($referees as $referee) {

			$theStatus = RefManPeople::getRefereeStatus($referee, $season);

			if (($theStatus !== null) && !in_array($theStatus['status_type_id'], $badIDs)) {
				$arrTemp[$theStatus['status_type_id']]['referees'][] = $referee;
			}

		}

		usort($arrTemp, array('StatusType', 'compareTo'));

		$statustypes = array();
		foreach ($arrTemp as $statustype) {
			if (array_key_exists('referees', $statustype)) {
				$statustypes[$statustype['StatusType']['id']] = $statustype;
			}
		}

		return $statustypes;
	}

	/**
	 * Returns used referee relation types of referees.
	 *
	 * @param $referees array of referees
	 * @return used referee relation types
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	private function getUsedRefereeRelationTypes($referees) {

		$arrTemp = array();
		foreach ($referees as $referee) {
			foreach ($referee['RefereeRelation'] as $relation) {
				$arrTemp[$relation['referee_relation_type_id']] = true;
			}
		}

		$refereerelationtypes = array();
		foreach ($arrTemp as $id => $dummy) {
			$tpeTemp = $this->RefereeRelationType->findById($id);
			$refereerelationtypes[$tpeTemp['RefereeRelationType']['sid']] = $tpeTemp['RefereeRelationType'];
		}

		return $refereerelationtypes;
	}












	/**
	 * Edit method: edit the referee with the given id.
	 *
	 * @param $id id of referee
	 * @return void
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public function edit($id = null) {

		$this->Referee->id = $id;
		if (!$this->Referee->exists()) {
			throw new NotFoundException(__('Schiedsrichter_in mit der ID \'%s\' existiert nicht.', $id));
		}
		$referee = $this->Referee->read(null, $id);

		$this->setAndGetStandardNewAddView($referee);

		$this->set('title_for_layout', __('Schiedsrichter%s %s editieren', ($referee['Person']['sex_type_sid'] === 'f') ? 'in' : '', RefManRefereeFormat::formatPerson($referee['Person'], 'fullname')));
		$this->render('/Generic/edit');
	}

	/**
	 * Returns all referee relation types.
	 *
	 * @return all referee relation types
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	private function getAllRefereeRelationTypes() {
		$allrefereerelationtypes = array();
		foreach ($this->RefereeRelationType->find('all') as $refereerelationtype) {
			$allrefereerelationtypes[$refereerelationtype['RefereeRelationType']['sid']] = $refereerelationtype['RefereeRelationType'];
		}
		return $allrefereerelationtypes;
	}

	/**
	 * Returns the contact types.
	 *
	 * @return array of contact types
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	private function getContactTypes() {
		$contacttypes = array();
		foreach ($this->ContactType->find('all') as $contacttype) {
			$contacttypes[$contacttype['ContactType']['id']] = $contacttype['ContactType'];
		}
		return $contacttypes;
	}

	/**
	 * Returns the club array for use in select fields.
	 *
	 * @return array of club
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	private function getClubArray() {
		$clubarray = $this->Club->find('list');
		asort($clubarray, SORT_LOCALE_STRING);

		return $clubarray;
	}

}

/* EOF */

