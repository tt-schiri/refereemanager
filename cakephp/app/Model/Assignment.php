<?php

App::uses('AppModel', 'Model');

/**
 * Assignment Model
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.1
 * @since 0.1
 */
class Assignment extends AppModel {

	/**
	 * Declare virtual display field in constructor to be alias-safe.
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public function __construct($id = false, $table = null, $ds = null) {
		parent::__construct($id, $table, $ds);
		// I can't get this one working with game number and season, so I program a simple (but possibly bad) workaround
		$this->virtualFields['display_assignment'] = sprintf(
			'CONCAT(%1$s.id, "-", %1$s.start)',
			$this->alias
		);
	}

	/**
	 * Model name.
	 *
	 * Good practice to include the model name.
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $name = 'Assignment';

	/**
	 * Display field
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $displayField = 'display_assignment';


	/**
	 * Validation rules
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $validate = array(
		'id' => array(
			'uuid' => array(
				'rule' => array('uuid'),
			),
		),
		'start' => array(
			'notempty' => array(
				'rule' => array('notempty'),
			),
			'datetime' => array(
				'rule' => array('datetime'),
			),
		),
		'end' => array(
			'datetime' => array(
				'rule' => array('datetime'),
			),
		),
	);

	/**
	 * hasMany associations
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $hasMany = array('LeagueGame', 'RefereeAssignment', 'TournamentGame');

}

/* EOF */

