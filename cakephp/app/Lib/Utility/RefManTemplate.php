<?php

/**
 * Template Helper library.
 *
 * Methods for using and filling templates.
 *
 * @package       Lib.Utility
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.3
 * @since 0.3
 */
class RefManTemplate {

	/** Zip archive. */
	private static $zip = null;

	/** Merge file. */
	private static $merge = null;

	/** Referee relations. */
	private static $refereerelationtypes = null;

	/**
	 * Returns template text.
	 *
	 * @param $id template id
	 * @return template content
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public static function getTemplate($id) {
		return file_get_contents(sprintf('%s%s%s',
																		 WWW_ROOT,
																		 Configure::read('RefMan.template.path'),
																		 Configure::read(sprintf('RefMan.template.%s', $id))));
	}

	/**
	 * Returns general token.
	 *
	 * @param $text text
	 * @return token with text
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public static function getToken($text) {
		return sprintf('**%s**', $text);
	}

	/**
	 * Returns replace token.
	 *
	 * @param $id id
	 * @return replace token with id
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public static function getReplaceToken($id) {
		return RefManTemplate::getToken(sprintf('generated %s', $id));
	}

	/**
	 * Returns if token.
	 *
	 * @param $condition condition
	 * @param $id id
	 * @param $text text
	 * @return if token
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public static function getIfToken($condition, $id, $text) {
		return sprintf('%s%s%s', RefManTemplate::getToken(sprintf('if%s %s', $condition, $id)), $text, RefManTemplate::getToken(sprintf('end%s %s', $condition, $id)));
	}

	/**
	 * Returns empty token.
	 *
	 * @param $id id
	 * @param $text empty text
	 * @return empty token
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public static function getEmptyToken($id, $text) {
		return RefManTemplate::getIfToken('empty', $id, $text);
	}

	/**
	 * Returns empty token.
	 *
	 * @param $id id
	 * @param $text not empty text
	 * @return not empty token
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public static function getNotEmptyToken($id, $text) {
		return RefManTemplate::getIfToken('notempty', $id, $text);
	}

	/**
	 * Replaces token in text with value.
	 *
	 * @param $text text
	 * @param $token token
	 * @param $value value
	 * @return replaced text
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public static function replace($text, $token, $value) {

		$txtReturn = $text;

		// resolve conditions
		$conditions = array();
		$conditions['empty'] = sprintf('/%s/', str_replace('(.\*)', '(.*)', str_replace('*', '\*', RefManTemplate::getIfToken('empty', $token, '(.*)'))));
		$conditions['notempty'] = sprintf('/%s/', str_replace('(.\*)', '(.*)', str_replace('*', '\*', RefManTemplate::getIfToken('notempty', $token, '(.*)'))));

		$replacements = array();
		if (empty($value)) {
			$replacements['empty'] = '$1';
			$replacements['notempty'] = '';
		} else {
			$replacements['empty'] = '';
			$replacements['notempty'] = '$1';
		}

		ksort($conditions);
		ksort($replacements);
		$txtReturn = preg_replace($conditions, $replacements, $txtReturn);

		// replace token with value
		$txtReturn = str_replace(RefManTemplate::getReplaceToken($token), $value, $txtReturn);

		return $txtReturn;
	}

	/**
	 * Replaces all referee tokens in text.
	 *
	 * @param $text text
	 * @param $referee referee
	 * @param $type format type
	 * @param $export export type
	 * @return replaced text
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public static function replaceRefereeData($text, $referee, $type, $export) {
		$txtReturn = $text;

		$txtReturn = RefManTemplate::replacePersonData($txtReturn, $referee, $type, $export);

		// id
		$txtReturn = RefManTemplate::replace($txtReturn, 'referee_id', $referee['Referee']['id']);

		// referee relation types
		if (empty(RefManTemplate::$refereerelationtypes)) {
			$model = ClassRegistry::init('RefereeRelationType');
			$model->recursive = -1;
			RefManTemplate::$refereerelationtypes = $model->find('all');
		}
		foreach (RefManTemplate::$refereerelationtypes as $relationtype) {
			$sid = $relationtype['RefereeRelationType']['sid'];
			$txtReturn = RefManTemplate::replace($txtReturn, sprintf('referee_relation_%s', $sid),
																					 RefManRefereeFormat::formatRelations(RefManPeople::getRelations($referee, $sid), $type, $export));
		}

		// training
		$traininglevel = RefManPeople::getTrainingLevel($referee);
		$txtReturn = RefManTemplate::replace($txtReturn, 'traininglevel',
																				 (empty($traininglevel) || empty($traininglevel['TrainingLevelType']['abbreviation'])) ? '' : $traininglevel['TrainingLevelType']['abbreviation']);
		$txtReturn = RefManTemplate::replace($txtReturn, 'traininglevelsince',
																				 (empty($traininglevel) || empty($traininglevel['TrainingLevel']['since'])) ? '' : RefManRefereeFormat::formatDate($traininglevel['TrainingLevel']['since'], 'date'));
		$txtReturn = RefManTemplate::replace($txtReturn, 'lasttrainingupdate',
																				 (empty($traininglevel) || empty($traininglevel['lasttrainingupdate'])) ? '' : RefManRefereeFormat::formatDate($traininglevel['lasttrainingupdate'], 'date'));
		$txtReturn = RefManTemplate::replace($txtReturn, 'nexttrainingupdate',
																				 (empty($traininglevel) || empty($traininglevel['nexttrainingupdate'])) ? '' : RefManRefereeFormat::formatDate($traininglevel['nexttrainingupdate'], 'year'));

		// status
		foreach ($referee['RefereeStatus'] as $refereestatus) {
			$txtReturn = RefManTemplate::replace($txtReturn, sprintf('refereestatus_%s_remark', $refereestatus['season_id']),
																					 (empty($refereestatus['remark'])) ? '' : $refereestatus['remark']);
		}

		return $txtReturn;
	}

	/**
	 * Replaces all person tokens in text.
	 *
	 * @param $text text
	 * @param $person person
	 * @param $type format type
	 * @param $export export type
	 * @return replaced text
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public static function replacePersonData($text, $person, $type, $export) {
		$txtReturn = $text;

		// id
		$txtReturn = RefManTemplate::replace($txtReturn, 'person_id', $person['Person']['id']);

		// names
		$txtReturn = RefManTemplate::replace($txtReturn, 'fullname',
																				 (empty($person['Person']['name'])) ? '' : RefManRefereeFormat::formatPerson($person, 'fullname'));
		$txtReturn = RefManTemplate::replace($txtReturn, 'first_name',
																				 (empty($person['Person']['first_name'])) ? '' : $person['Person']['first_name']);
		$txtReturn = RefManTemplate::replace($txtReturn, 'name_title',
																				 (empty($person['Person']['name'])) ? '' : RefManRefereeFormat::formatPerson($person, 'name_title'));
		$txtReturn = RefManTemplate::replace($txtReturn, 'tablename',
																				 (empty($person['Person']['name'])) ? '' : RefManRefereeFormat::formatPerson($person, 'tablename'));

		// dates
		$txtReturn = RefManTemplate::replace($txtReturn, 'birthday',
																				 (empty($person['Person']['birthday'])) ? '' : RefManRefereeFormat::formatDate($person['Person']['birthday'], 'date'));
		$txtReturn = RefManTemplate::replace($txtReturn, 'dayofdeath',
																				 (empty($person['Person']['dayofdeath'])) ? '' : sprintf('†&nbsp;%s', RefManRefereeFormat::formatDate($person['Person']['dayofdeath'], 'date')));

		// contacts
		$txtReturn = RefManTemplate::replace($txtReturn, 'emails',
																				 RefManRefereeFormat::formatContacts(RefManPeople::getContacts($person, 'Email'), $type, 'Email', $export));
		$txtReturn = RefManTemplate::replace($txtReturn, 'phone_numbers_national',
																				 RefManRefereeFormat::formatContacts(RefManPeople::getContacts($person, 'PhoneNumber'), 'national', 'PhoneNumber', $export));
		$txtReturn = RefManTemplate::replace($txtReturn, 'addresses_fulladdress',
																				 RefManRefereeFormat::formatContacts(RefManPeople::getContacts($person, 'Address'), 'fulladdress', 'Address', $export));
		$txtReturn = RefManTemplate::replace($txtReturn, 'urls',
																				 RefManRefereeFormat::formatContacts(RefManPeople::getContacts($person, 'Url'), $type, 'Url', $export));

		// other
		$txtReturn = RefManTemplate::replace($txtReturn, 'sextype',
																				 (empty($person['SexType']['title'])) ? '' : $person['SexType']['title']);
		$txtReturn = RefManTemplate::replace($txtReturn, 'picture_url',
																				 (empty($person['Picture']['url'])) ? '' : $person['Picture']['url']);
		$txtReturn = RefManTemplate::replace($txtReturn, 'remark',
																				 (empty($person['Person']['remark'])) ? '' : $person['Person']['remark']);
		$txtReturn = RefManTemplate::replace($txtReturn, 'internal_remark',
																				 (empty($person['Person']['internal_remark'])) ? '' : $person['Person']['internal_remark']);

		return $txtReturn;
	}

	/**
	 * Replaces all season tokens in text.
	 *
	 * @param $text text
	 * @param $season season
	 * @param $type format type
	 * @param $export export type
	 * @return replaced text
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public static function replaceSeasonData($text, $season, $type, $export) {
		$txtReturn = $text;

		// all simple replacements
		debug($season);
		//$txtReturn = RefManTemplate::replace($txtReturn, 'referee_id', $referee['Referee']['id']);

		return $txtReturn;
	}

	/**
	 * Replaces special characters in filenames.
	 *
	 * @param $filename filename
	 * @return cleaned filename
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public static function fileName($filename) {
		$txtReturn = $filename;

		$txtReturn = str_replace('ä', 'ae', $txtReturn);
		$txtReturn = str_replace('Ä', 'Ae', $txtReturn);
		$txtReturn = str_replace('ö', 'oe', $txtReturn);
		$txtReturn = str_replace('Ö', 'Oe', $txtReturn);
		$txtReturn = str_replace('ü', 'ue', $txtReturn);
		$txtReturn = str_replace('Ü', 'Ue', $txtReturn);
		$txtReturn = str_replace('ß', 'ss', $txtReturn);
		$txtReturn = str_replace('.', '_', $txtReturn);
		$txtReturn = str_replace('-', '_', $txtReturn);

		return $txtReturn;
	}

	/**
	 * Open zip archive.
	 *
	 * @param $filename filename
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public static function openZip($filename) {
		RefManTemplate::$zip = new ZipArchive();
		$zipfile = sprintf('%s%s.zip', TMP, $filename);
		if (RefManTemplate::$zip->open($zipfile, ZipArchive::OVERWRITE) !== TRUE) {
			throw new CakeException(__('Zip-Archiv "%s" konnte nicht angelegt werden.', $zipfile));
		}
		RefManTemplate::$merge = RefManTemplate::getTemplate('merge');
	}

	/**
	 * Adds file to zip archive.
	 *
	 * @param $directory directory
	 * @param $filename filename
	 * @param $content content
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public static function addToZip($directory, $filename, $content) {
		RefManTemplate::$zip->addFromString(sprintf('%s/%s.mmd', $directory, $filename), $content);
		RefManTemplate::$merge = str_replace('#includepdf#',
																				 sprintf("\t\\includepdf{generated/%s.pdf}\n#includepdf#", $filename),
																				 RefManTemplate::$merge);
	}

	/**
	 * Close zip archive.
	 *
	 * @return filename
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public static function closeZip() {
		RefManTemplate::$merge = str_replace('#includepdf#', '', RefManTemplate::$merge);
		RefManTemplate::$zip->addFromString(Configure::read('RefMan.template.merge'), RefManTemplate::$merge);

		RefManTemplate::$zip->addFile(sprintf('%s%s%s',
																					WWW_ROOT,
																					Configure::read('RefMan.template.path'),
																					Configure::read('RefMan.template.build')),
																	Configure::read('RefMan.template.build'));

		$zipfile = RefManTemplate::$zip->filename;
		RefManTemplate::$zip->close();

		return $zipfile;
	}

}

/* EOF */

