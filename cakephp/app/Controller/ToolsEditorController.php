<?php

App::uses('AppController', 'Controller');

/**
 * Tools editor Controller
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.1
 * @since 0.1
 */
class ToolsEditorController extends AppController {

	/** Models. */
	public $uses = array('Season');

	/**
	 * Defines actions to perform before the action method is executed.
	 */
	public function beforeFilter() {
		parent::beforeFilter();

		if (!$this->viewVars['isEditor']) {
			throw new ForbiddenException(__('Nur für Editoren!'));
		}

		$this->Season->recursive = -1;
	}

	/**
	 * Index method.
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public function index() {
		$this->set('title_for_layout', __('Editor-Werkzeuge'));
	}

	/**
	 * Message method.
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public function message() {
		$this->set('title_for_layout', __('Nachricht'));

		$seasonarray = $this->Season->find('list');
		asort($seasonarray, SORT_LOCALE_STRING);
		$this->set('seasonarray', $seasonarray);
		$this->set('result', false);

		// create messages
		if (!empty($this->request->data)) {
			$this->set('result', true);
			$this->set('data', $this->request->data);

			$referees = array();
			switch ($this->request->data['ToolsEditor']['recipient']) {
				case 'a':
					// $referees = all referees in season
					break;
				case 'm':
					// $referees = all referees in season with an email address
					break;
				case 's':
					// $referees = all referees in season with an snail mail address without an email address
					break;
			}

			// now an if because switch is too exclusive for 'a'
			if (($this->request->data['ToolsEditor']['recipient'] === 'a') || ($this->request->data['ToolsEditor']['recipient'] === 'm')) {
				//if (referee has email address) {
					$addresslist = '';
					$more = false;
					foreach ($referees as $referee) {
						if ($more) {
							$addresslist .= '; ';
						}
						$more = true;
						//$addresslist .= sprintf('%s <%s>', $this->RefereeFormat->formatPerson($referee['Person'], 'name_title'), $this->RefereeFormat->formatEMail($email, 'text'));
					}
				//}
			}

		}

	}

}

/* EOF */
