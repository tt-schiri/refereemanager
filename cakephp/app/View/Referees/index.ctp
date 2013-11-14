<?php echo $this->element('actions_header');	?>
<!-- header actions -->

<!-- view filters -->
<div class="filter">
	<p>Saison: <?php echo $season['title_season']; ?></p>
</div>

<!-- view content -->
<?php
	if (empty($referees)) {
?>
	<p><?php echo __('Es sind keine Schiedsrichter_innen gespeichert.'); ?></p>
<?php
	} else {
?>
	<p><?php echo __('Legende:'); ?></p>
	<ul class="legend">
		<?php
			// compute different styles
			foreach ($statustypes as &$statustype) {

				$style = '';

				if ($statustype['style'] || $statustype['color'] || $statustype['bgcolor']) {

					switch ($statustype['style']) {
						case 'normal':
						case 'italic':
						case 'oblique':
							$style .= sprintf('font-style: %s; ', $statustype['style']);
							break;
						case 'normal':
						case 'bold':
						case 'bolder':
						case 'lighter':
							$style .= sprintf('font-weight: %s; ', $statustype['style']);
							break;
					}

					if ($statustype['color']) {
						$style .= sprintf('color: #%s; ', $statustype['color']);
					}

					if ($statustype['bgcolor']) {
						$style .= sprintf('background-color: #%s; ', $statustype['bgcolor']);
					}

				}
				$statustype['outputstyle'] = $style;

				if (($statustype['sid'] == StatusType::SID_MANY) ||
						($statustype['sid'] == StatusType::SID_INACTIVESEASON) ||
						($statustype['sid'] == StatusType::SID_OTHER)) {
		?>
			<li style="<?php echo $statustype['outputstyle']; ?>"><?php echo ($statustype['remark']) ? h($statustype['remark']) : h($statustype['title']); ?></li>
		<?php
				}
			}
		?>
	</ul>

	<p><?php echo $this->Html->link('Export to Excel', array('controller' => 'referees', 'action' => 'export', 'excel')); ?></p>

	<table>
		<?php
			$columns = array();
				if ($isReferee) {
					$columns[] = __('Bild');
				}
				$columns[] = __('Name');
				$columns[] = __('Vorname');
				$columns[] = __('Mitglied');
				if ($hasreffor) {
					$columns[] = __('Schiedst für');
				}
				if ($isReferee) {
					$columns[] = __('E-Mail');
					$columns[] = __('Telefon');
				}
				if ($isEditor) {
					$columns[] = __('Adresse');
					$columns[] = __('Geschlecht');
					$columns[] = __('Geburtstag');
				}
				$columns[] = __('Ausbildung');
				if ($isEditor) {
					$columns[] = __('Letzte Fortbildung');
					$columns[] = __('Nächste Fortbildung');
					$columns[] = __('Anmerkung');
				}
				$columns[] = __('Aktionen');
			$table = array('thead', 'tfoot');

			foreach ($table as $tabletag) {
				echo sprintf('<%s><tr>', $tabletag);
				foreach ($columns as $column) {
					echo sprintf('<th>%s</th>', $column);
				}
				echo sprintf('</tr></%s>', $tabletag);
			}
		?>
		<tbody>
			<?php
				foreach ($referees as $referee) {
			?>
					<tr>
						<?php if ($isReferee) { ?>
							<td data-title="<?php echo __('Bild'); ?>" style="<?php echo $statustypes[$referee['RefereeStatus']['sid']]['outputstyle']; ?>"><?php
								if (!empty($referee['Picture'])) {
									echo $this->Html->image($referee['Picture']['url'], array('width' => '50', 'alt' => __('Bild von %s %s', $referee['Person']['first_name'], $referee['Person']['name']), 'title' => __('%s %s', $referee['Person']['first_name'], $referee['Person']['name'])));
								}
							?></td>
						<?php } ?>

						<td data-title="<?php echo __('Name'); ?>" style="<?php echo $statustypes[$referee['RefereeStatus']['sid']]['outputstyle']; ?>"><?php echo $this->Html->link($this->RefereeFormat->formatPerson($referee['Person'], 'name'), array('controller' => 'referees', 'action' => 'view', $referee['Referee']['id']), array('style' => $statustypes[$referee['RefereeStatus']['sid']]['outputstyle']));  ?></td>

						<td data-title="<?php echo __('Vorname'); ?>" style="<?php echo $statustypes[$referee['RefereeStatus']['sid']]['outputstyle']; ?>"><?php echo $this->Html->link($this->RefereeFormat->formatPerson($referee['Person'], 'first_name'), array('controller' => 'referees', 'action' => 'view', $referee['Referee']['id']), array('style' => $statustypes[$referee['RefereeStatus']['sid']]['outputstyle'])); ?></td>

						<td data-title="<?php echo __('Mitglied'); ?>" style="<?php echo $statustypes[$referee['RefereeStatus']['sid']]['outputstyle']; ?>"><?php
							if (!empty($referee['RefereeRelation']['member'])) {
								echo $this->Html->link($referee['RefereeRelation']['member']['Club']['name'], array('controller' => 'clubs', 'action' => 'view', $referee['RefereeRelation']['member']['Club']['id']), array('style' => $statustypes[$referee['RefereeStatus']['sid']]['outputstyle']));
							}
						?></td>

						<?php if ($hasreffor) { ?>
							<td data-title="<?php echo __('Schiedst für'); ?>" style="<?php echo $statustypes[$referee['RefereeStatus']['sid']]['outputstyle']; ?>"><?php
								if (!empty($referee['RefereeRelation']['reffor'])) {
									echo $this->Html->link($referee['RefereeRelation']['reffor']['Club']['name'], array('controller' => 'clubs', 'action' => 'view', $referee['RefereeRelation']['reffor']['Club']['id']), array('style' => $statustypes[$referee['RefereeStatus']['sid']]['outputstyle']));
								}
							?></td>
						<?php } ?>

						<?php if ($isReferee) { ?>
							<td data-title="<?php echo __('E-Mail'); ?>" style="<?php echo $statustypes[$referee['RefereeStatus']['sid']]['outputstyle']; ?>"><?php
								$text = '';
								if (array_key_exists('Contact', $referee) && array_key_exists('Email', $referee['Contact'])) {
									$hasMore = false;
									$printType = (count($referee['Contact']['Email']) > 1);
									foreach ($referee['Contact']['Email'] as $contacttype => $emailkind) {
										$printType |= (count($emailkind) > 1);
										foreach ($emailkind as $email) {
											if ($hasMore) {
												$text .= '<br />';
											}
											if ($printType || ($contacttype != Configure::read('RefMan.defaultcontacttypeid'))) {
												$text .= __('%s: ', $contacttypes[$contacttype]['abbreviation']);
											}
											$text .= $this->RefereeFormat->formatEMail($email, 'link');
											$hasMore = true;
										}
									}
								}
								echo $text;
							?></td>

							<td data-title="<?php echo __('Telefon'); ?>" style="<?php echo $statustypes[$referee['RefereeStatus']['sid']]['outputstyle']; ?>"><?php
								$text = '';
								if (array_key_exists('Contact', $referee) && array_key_exists('PhoneNumber', $referee['Contact'])) {
									$hasMore = false;
									$printType = (count($referee['Contact']['PhoneNumber']) > 1);
									foreach ($referee['Contact']['PhoneNumber'] as $contacttype => $phonekind) {
										$printType |= (count($phonekind) > 1);
										foreach ($phonekind as $phone) {
											if ($hasMore) {
												$text .= '<br />';
											}
											if ($printType || ($contacttype != Configure::read('RefMan.defaultcontacttypeid'))) {
												$text .= __('%s: ', $contacttypes[$contacttype]['abbreviation']);
											}
											$text .= $this->RefereeFormat->formatPhone($phone, 'normal');
											$hasMore = true;
										}
									}
								}
								echo $text;
							?></td>
						<?php } ?>

						<?php if ($isEditor) { ?>
							<td data-title="<?php echo __('Adresse'); ?>" style="<?php echo $statustypes[$referee['RefereeStatus']['sid']]['outputstyle']; ?>"><?php
								$text = '';
								if (array_key_exists('Contact', $referee) && array_key_exists('Address', $referee['Contact'])) {
									$hasMore = false;
									$printType = (count($referee['Contact']['Address']) > 1);
									foreach ($referee['Contact']['Address'] as $contacttype => $addresskind) {
										$printType |= (count($addresskind) > 1);
										foreach ($addresskind as $address) {
											if ($hasMore) {
												$text .= '<br />';
											}
											if ($printType || ($contacttype != Configure::read('RefMan.defaultcontacttypeid'))) {
												$text .= __('%s: ', $contacttypes[$contacttype]['abbreviation']);
											}
											$text .= $this->RefereeFormat->formatAddress($address, 'fulladdress');
											$hasMore = true;
										}
									}
								}
								echo $text;
							?></td>

							<td data-title="<?php echo __('Geschlecht'); ?>" style="<?php echo $statustypes[$referee['RefereeStatus']['sid']]['outputstyle']; ?>"><?php
								$text = '';
								$text .= __($sextypes[$referee['Person']['sex_type_id']]['title']);
								echo $text;
							?></td>

							<td data-title="<?php echo __('Geburtstag'); ?>" style="<?php echo $statustypes[$referee['RefereeStatus']['sid']]['outputstyle']; ?>"><?php
								$text = '';
								if (!empty($referee['Person']['birthday'])) {
									$text .= $this->RefereeFormat->formatPerson($referee['Person'], 'birthday');
								}
								echo $text;
							?></td>
						<?php } ?>

						<td data-title="<?php echo __('Ausbildung'); ?>" style="<?php echo $statustypes[$referee['RefereeStatus']['sid']]['outputstyle']; ?>"><?php
							$text = '';
							if (!empty($referee['TrainingLevelInfo'])) {
								$text .= $this->Html->link($referee['TrainingLevelInfo']['abbreviation'], array('controller' => 'trainingleveltype', 'action' => 'view', $referee['TrainingLevelInfo']['id']), array('style' => $statustypes[$referee['RefereeStatus']['sid']]['outputstyle']));
							}
							echo $text;
						?></td>

						<?php if ($isEditor) { ?>
							<td data-title="<?php echo __('Letzte Fortbildung'); ?>" style="<?php echo $statustypes[$referee['RefereeStatus']['sid']]['outputstyle']; ?>"><?php
								$text = '';
								if (!empty($referee['TrainingLevelInfo']) && !empty($referee['TrainingLevelInfo']['lastupdate'])) {
									$text .= $this->RefereeFormat->formatDate($referee['TrainingLevelInfo']['lastupdate'], 'date');
								}
								echo $text;
							?></td>

							<td data-title="<?php echo __('Nächste Fortbildung'); ?>" style="<?php echo $statustypes[$referee['RefereeStatus']['sid']]['outputstyle']; ?>"><?php
								$text = '';
								if (!empty($referee['TrainingLevelInfo']) && !empty($referee['TrainingLevelInfo']['nextupdate'])) {
									$text .= $this->RefereeFormat->formatDate($referee['TrainingLevelInfo']['nextupdate'], 'year');
								}
								echo $text;
							?></td>

							<td data-title="<?php echo __('Anmerkung'); ?>" style="<?php echo $statustypes[$referee['RefereeStatus']['sid']]['outputstyle']; ?>"><?php
								if (!empty($referee['Person']['remark'])) {
									echo h($referee['Person']['remark']);
								}
							?></td>
						<?php } ?>

						<td class="actions" data-title="<?php echo __('Aktionen'); ?>">
							<?php echo $this->element('actions_table', array('id' => $referee['Referee']['id']));	?>
						</td>
					</tr>
			<?php
				}
			?>
		</tbody>
	</table>

	<h3><?php echo __('Zusatzinformationen'); ?></h3>
	<?php
		foreach ($statustypes as $outstatustype) {
			if (($outstatustype['sid'] == StatusType::SID_MANY) ||
					($outstatustype['sid'] == StatusType::SID_INACTIVESEASON) ||
					($outstatustype['sid'] == StatusType::SID_OTHER)) {
	?>
		<p><strong><?php echo ($outstatustype['remark']) ? h($outstatustype['remark']) : h($outstatustype['title']); ?></strong></p>
		<p>
			<?php
				$hasMore = false;
				$text = '';
				foreach ($outstatustype['referees'] as $referee) {
					if ($hasMore) {
						$text .= ', ';
					}
					$text .= __($this->RefereeFormat->formatPerson($referee, 'fullname'));
					$hasMore = true;
				}
				echo $text;
			?>
		</p>
	<?php
			}
		}
	?>

<?php
	}
?>

<!--?php pr($statustypes); ?-->
<?php pr($referees); ?>

