<?php echo $this->element('filter'); ?>

<?php echo $this->element('actions_header'); ?>

<?php $isRefView = isset($isRefView) && $isRefView; ?>

<!-- view content -->
<div class="row">
	<div class="col-sm-12">

		<p><?php
			echo __('Es %s %s %s gespeichert.',
							(count($people) == 1) ? 'ist' : 'sind',
							(empty($people)) ? 'keine' : count($people),
							(count($people) == 1) ? (($isRefView) ? 'Schiedsrichter_in' : 'Person') : (($isRefView) ? 'Schiedsrichter_innen' : 'Personen')
							);
		?></p>
		<?php
			if (!empty($people)) {
		?>

			<?php if ($isRefView) { ?>

				<p><?php echo __('Legende:'); ?></p>
				<?php

					$ulout = array();
					foreach ($statustypes as $statustype) {
						$ulout[] = $this->Html->tag('span',
																				($statustype['StatusType']['remark']) ? h($statustype['StatusType']['remark']) : h($statustype['StatusType']['title']),
																				array(
																							'class' => $statustype['StatusType']['sid'],
																							'style' => $this->Html->style(Configure::read(sprintf('RefMan.statustypes.%s', $statustype['StatusType']['sid'])))
																							)
																				);
					}
					echo $this->Html->nestedList($ulout, array('class' => 'legend'));
				?>

				<p>
					<?php echo $this->Html->link('Export als Excel-Datei', array('action' => 'export', $season['Season']['year_start'], 'excel')); ?>
					|
					<?php echo $this->Html->link('Export als PDF', array('action' => 'export', $season['Season']['year_start'], 'pdf')); ?>
					<?php if ($isEditor) { ?>
						|
						<?php echo $this->Html->link('Datenübersicht (zip)', array('action' => 'export', $season['Season']['year_start'], 'person-data')); ?>
					<?php } ?>
				</p>
			<?php } ?>


			<table class="table table-striped table-bordered table-condensed table-responsive">
				<caption><?php echo $title_for_layout; ?> <?php echo $season['Season']['title_season']; ?></caption>

				<?php
					include_once('columns.php');

					$cells = array();
						foreach ($columns['index'] as $column) {
							$cells[] = $column['title'];
						}
						$cells[] = __('Aktionen');

					echo $this->Html->tag('thead', $this->Html->tableHeaders($cells));
					echo $this->Html->tag('tfoot', $this->Html->tableHeaders($cells));

				?>

				<tbody>
					<?php
						/*foreach ($people as $person) {

							$tmpFormat = null;
							if ($isRefView) {
								//$tmpStatus = $this->People->getRefereeStatus($person, $season);
								if (($tmpStatus !== null) && (array_key_exists($tmpStatus['status_type_id'], $statustypes))) {
									$tmpFormat = $this->Html->style(Configure::read(sprintf('RefMan.statustypes.%s', $statustypes[$tmpStatus['status_type_id']]['StatusType']['sid'])));
								}
							}

							$cells = array();
								foreach ($columns['index'] as $column) {
									$cells[] = array($column['content'],
																	 (empty($tmpFormat)) ? null : array('style' => $tmpFormat));
								}
								$cells[] = $this->element('actions_table', array('id' => $person['Person']['id']));

							echo $this->Template->replaceRefereeData(
																											 $this->Html->tableCells(array($cells)),
																											 $person,
																											 'text',
																											 'html'
																											 );
						}*/
					?>
				</tbody>
			</table>

			<?php if (false) { ?>
			<?php /*if ($isRefView) {*/ ?>

				<h2><?php echo __('Zusatzinformationen'); ?></h2>

				<?php
					foreach ($statustypes as $outstatustype) {
				?>
					<div class="panel panel-default">
						<div class="panel-heading">
							<?php
								echo ($outstatustype['StatusType']['remark']) ? h($outstatustype['StatusType']['remark']) : h($outstatustype['StatusType']['title']);
							?>
						</div>
						<div class="panel-body">
							<?php
								$arrNames = array();
								foreach ($outstatustype['referees'] as $referee) {
									$arrNames[] = $this->RefereeFormat->formatPerson($referee, 'fullname');
								}
								echo $this->RefereeFormat->formatMultiline($arrNames, ', ');
							?>
						</div>
					</div>

			<?php
					}
				}
			?>

		<?php
			}
		?>

		<?php
			//debug($people);
		?>

	</div>
</div>

