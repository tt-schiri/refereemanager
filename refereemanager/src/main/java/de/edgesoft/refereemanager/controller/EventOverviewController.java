package de.edgesoft.refereemanager.controller;

import java.text.MessageFormat;
import java.util.Map;
import java.util.Objects;

import de.edgesoft.refereemanager.jaxb.LeagueGame;
import de.edgesoft.refereemanager.jaxb.OtherEvent;
import de.edgesoft.refereemanager.jaxb.Tournament;
import de.edgesoft.refereemanager.model.AppModel;
import de.edgesoft.refereemanager.model.ContentModel;
import de.edgesoft.refereemanager.model.EventDateModel;
import de.edgesoft.refereemanager.model.LeagueGameModel;
import de.edgesoft.refereemanager.model.OtherEventModel;
import de.edgesoft.refereemanager.model.TournamentModel;
import de.edgesoft.refereemanager.utils.AlertUtils;
import de.edgesoft.refereemanager.utils.PrefKey;
import de.edgesoft.refereemanager.utils.Prefs;
import de.edgesoft.refereemanager.utils.Resources;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Controller for the event overview scene.
 *
 * ## Legal stuff
 *
 * Copyright 2016-2017 Ekkart Kleinod <ekleinod@edgesoft.de>
 *
 * This file is part of TT-Schiri: Referee Manager.
 *
 * TT-Schiri: Referee Manager is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * TT-Schiri: Referee Manager is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with TT-Schiri: Referee Manager. If not, see <http://www.gnu.org/licenses/>.
 *
 * @author Ekkart Kleinod
 * @version 0.15.0
 * @since 0.15.0
 */
public class EventOverviewController {

	/**
	 * Heading label.
	 */
	@FXML
	private Label lblHeading;

	/**
	 * Number label.
	 */
	@FXML
	private Label lblNumber;

	/**
	 * Number label label.
	 */
	@FXML
	private Label lblNumberLabel;

	/**
	 * Date label.
	 */
	@FXML
	private Label lblDate;

	/**
	 * Date label label.
	 */
	@FXML
	private Label lblDateLabel;

	/**
	 * Time label.
	 */
	@FXML
	private Label lblTime;

	/**
	 * Time label label.
	 */
	@FXML
	private Label lblTimeLabel;

	/**
	 * Teams label.
	 */
	@FXML
	private Label lblTeams;

	/**
	 * Teams label label.
	 */
	@FXML
	private Label lblTeamsLabel;

	/**
	 * League label.
	 */
	@FXML
	private Label lblLeague;

	/**
	 * League label label.
	 */
	@FXML
	private Label lblLeagueLabel;

	/**
	 * Club label.
	 */
	@FXML
	private Label lblClub;

	/**
	 * Club label label.
	 */
	@FXML
	private Label lblClubLabel;

	/**
	 * Organizer label.
	 */
	@FXML
	private Label lblOrganizer;

	/**
	 * Organizer label label.
	 */
	@FXML
	private Label lblOrganizerLabel;

	/**
	 * Venue label.
	 */
	@FXML
	private Label lblVenue;

	/**
	 * Venue label label.
	 */
	@FXML
	private Label lblVenueLabel;

	/**
	 * Remark label.
	 */
	@FXML
	private Label lblRemark;

	/**
	 * Remark label label.
	 */
	@FXML
	private Label lblRemarkLabel;

	/**
	 * Referee report anchor pane.
	 */
	@FXML
	private AnchorPane pneRefereeReport;

	/**
	 * Referee report label.
	 */
	@FXML
	private Label lblRefereeReport;

	/**
	 * Referee report label label.
	 */
	@FXML
	private Label lblRefereeReportLabel;

	/**
	 * Referee report copy button.
	 */
	@FXML
	private Button btnRefereeReportCopy;


	/**
	 * Add button.
	 */
	@FXML
	private Button btnAdd;

	/**
	 * Edit button.
	 */
	@FXML
	private Button btnEdit;

	/**
	 * Delete button.
	 */
	@FXML
	private Button btnDelete;

	/**
	 * Split pane.
	 */
	@FXML
	private SplitPane pneSplit;


	/**
	 * Main app controller.
	 */
	private AppLayoutController appController = null;


	/**
	 * Event list controller.
	 */
	private EventListController ctlEventList;

	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {

		// list
		Map.Entry<Pane, FXMLLoader> pneLoad = Resources.loadPane("EventList");
		AnchorPane refList = (AnchorPane) pneLoad.getKey();

		// add referee list to split pane
		pneSplit.getItems().add(0, refList);

		// store referee table controller
		ctlEventList = pneLoad.getValue().getController();

		// clear event details
		showDetails(null);

		// listen to selection changes, show person
		ctlEventList.getLeagueGamesSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showDetails(newValue));
		ctlEventList.getTournamentsSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showDetails(newValue));
		ctlEventList.getOtherEventsSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showDetails(newValue));
		ctlEventList.getTabLeagueGames().selectedProperty().addListener((event, oldTab, newTab) -> showDetails());
		ctlEventList.getTabTournaments().selectedProperty().addListener((event, oldTab, newTab) -> showDetails());
		ctlEventList.getTabOtherEvents().selectedProperty().addListener((event, oldTab, newTab) -> showDetails());

		// enabling report copy button
		btnRefereeReportCopy.disableProperty().bind(lblRefereeReport.textProperty().isEmpty());

		// enabling edit/delete buttons only with selection
		ObservableBooleanValue isOneItemSelected = ctlEventList.getTabLeagueGames().selectedProperty().not().or(ctlEventList.getLeagueGamesSelectionModel().selectedItemProperty().isNull())
				.and(ctlEventList.getTabTournaments().selectedProperty().not().or(ctlEventList.getTournamentsSelectionModel().selectedItemProperty().isNull()))
				.and(ctlEventList.getTabOtherEvents().selectedProperty().not().or(ctlEventList.getOtherEventsSelectionModel().selectedItemProperty().isNull()));
		btnEdit.disableProperty().bind(isOneItemSelected);
		btnDelete.disableProperty().bind(isOneItemSelected);

		// disabling labels
		ObservableBooleanValue isLeagueGame = ctlEventList.getTabLeagueGames().selectedProperty();
		lblNumberLabel.visibleProperty().bind(isLeagueGame);
		lblNumberLabel.managedProperty().bind(isLeagueGame);
		lblNumber.visibleProperty().bind(isLeagueGame);
		lblNumber.managedProperty().bind(isLeagueGame);
		lblTeamsLabel.visibleProperty().bind(isLeagueGame);
		lblTeamsLabel.managedProperty().bind(isLeagueGame);
		lblTeams.visibleProperty().bind(isLeagueGame);
		lblTeams.managedProperty().bind(isLeagueGame);
		lblLeagueLabel.visibleProperty().bind(isLeagueGame);
		lblLeagueLabel.managedProperty().bind(isLeagueGame);
		lblLeague.visibleProperty().bind(isLeagueGame);
		lblLeague.managedProperty().bind(isLeagueGame);

		ObservableBooleanValue isTournament = ctlEventList.getTabTournaments().selectedProperty();
		lblClubLabel.visibleProperty().bind(isTournament);
		lblClubLabel.managedProperty().bind(isTournament);
		lblClub.visibleProperty().bind(isTournament);
		lblClub.managedProperty().bind(isTournament);
		lblOrganizerLabel.visibleProperty().bind(isTournament);
		lblOrganizerLabel.managedProperty().bind(isTournament);
		lblOrganizer.visibleProperty().bind(isTournament);
		lblOrganizer.managedProperty().bind(isTournament);

		ObservableBooleanValue isLeagueGameOrTournament = ctlEventList.getTabLeagueGames().selectedProperty().or(ctlEventList.getTabTournaments().selectedProperty());
		lblRefereeReportLabel.visibleProperty().bind(isLeagueGameOrTournament);
		lblRefereeReportLabel.managedProperty().bind(isLeagueGameOrTournament);
		pneRefereeReport.visibleProperty().bind(isLeagueGameOrTournament);
		pneRefereeReport.managedProperty().bind(isLeagueGameOrTournament);

		// set divider position
		pneSplit.setDividerPositions(Double.parseDouble(Prefs.get(PrefKey.EVENT_OVERVIEW_SPLIT)));

		// if changed, save divider position to preferences
		pneSplit.getDividers().get(0).positionProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
			Prefs.put(PrefKey.EVENT_OVERVIEW_SPLIT, Double.toString(newValue.doubleValue()));
		});

		// icons
		btnRefereeReportCopy.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit-copy.png")));
		btnAdd.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/list-add.png")));
		btnEdit.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit.png")));
		btnDelete.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/list-remove.png")));

	}

	/**
	 * Initializes the controller with things that cannot be done during {@link #initialize()}.
	 *
	 * @param theAppController app controller
	 */
	public void initController(final AppLayoutController theAppController) {

		appController = theAppController;

		ctlEventList.setItems();

	}

	/**
	 * Shows selected data in detail window.
	 *
	 * Checks if an item is selected in visible tab, uses this.
	 */
	private void showDetails() {

		ObservableList<EventDateModel> lstSelected = ctlEventList.getVisibleTabSelection();

		if (lstSelected.size() == 1) {
			showDetails(lstSelected.get(0));
		} else {
			showDetails(null);
		}

	}

	/**
	 * Shows selected data in detail window.
	 *
	 * @param theDetailData event (null if none is selected)
	 */
	private void showDetails(final EventDateModel theDetailData) {

		if (theDetailData == null) {

			lblHeading.setText("Details");

			lblNumber.setText("");
			lblDate.setText("");
			lblTime.setText("");
			lblTeams.setText("");
			lblLeague.setText("");
			lblClub.setText("");
			lblOrganizer.setText("");
			lblVenue.setText("");
			lblRefereeReport.setText("");
			lblRemark.setText("");

		} else {

			lblHeading.setText(
					(theDetailData.getDisplayTitle() == null) ?
							null :
							theDetailData.getDisplayTitle().getValueSafe());

			lblDate.setText(
					(theDetailData.getDateText() == null) ?
							null :
							theDetailData.getDateText().getValueSafe());

			lblTime.setText(
					(theDetailData.getTimeText() == null) ?
							null :
							theDetailData.getTimeText().getValueSafe());

			lblVenue.setText(
					(theDetailData.getVenue() == null) ?
							null :
							theDetailData.getVenue().getDisplayTitle().getValueSafe());

			lblRemark.setText(
					(theDetailData.getRemark() == null) ?
							null :
							theDetailData.getRemark().getValueSafe());

			if (theDetailData instanceof LeagueGameModel) {

				LeagueGameModel mdlTemp = (LeagueGameModel) theDetailData;

				lblNumber.setText(
						(mdlTemp.getGameNumber() == null) ?
								null :
								mdlTemp.getGameNumberString().getValueSafe());

				lblTeams.setText(
						(mdlTemp.getTeamText() == null) ?
								null :
								mdlTemp.getTeamText().getValueSafe());

				lblLeague.setText(
						(mdlTemp.getHomeTeam() == null) ?
								null :
								mdlTemp.getHomeTeam().getLeague().getDisplayTitle().getValueSafe());

				lblRefereeReport.setText(
						(mdlTemp.getRefereeReportFilename() == null) ?
								null :
								mdlTemp.getRefereeReportFilename().getValueSafe());

				if (mdlTemp.existsRefereeReportFile()) {
					lblRefereeReportLabel.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
				} else {
					lblRefereeReportLabel.setBackground(new Background(new BackgroundFill(Color.INDIANRED, CornerRadii.EMPTY, Insets.EMPTY)));
				}

			}

			if (theDetailData instanceof TournamentModel) {

				TournamentModel mdlTemp = (TournamentModel) theDetailData;

				lblClub.setText(
						(mdlTemp.getOrganizingClub() == null) ?
								null :
								mdlTemp.getOrganizingClub().getDisplayTitleShort().getValueSafe());

				lblOrganizer.setText(
						(mdlTemp.getOrganizer() == null) ?
								null :
								mdlTemp.getOrganizer().getDisplayText().getValueSafe());

				lblRefereeReport.setText(
						(mdlTemp.getRefereeReportFilename() == null) ?
								null :
								mdlTemp.getRefereeReportFilename().getValueSafe());

				System.out.println(mdlTemp.existsRefereeReportFile());

			}

		}

	}

	/**
	 * Copies referee report filename to clipboard.
	 */
	@FXML
	private void handleRefereeReportCopy() {
		ClipboardContent clpContent = new ClipboardContent();
		clpContent.putString(lblRefereeReport.getText());
		Clipboard.getSystemClipboard().setContent(clpContent);
	}

	/**
	 * Opens edit dialog for new data.
	 */
	@FXML
	private void handleAdd() {

		EventDateModel newEventDate = null;

		if (ctlEventList.getTabLeagueGames().isSelected()) {
			newEventDate = new LeagueGameModel();
		}

		if (ctlEventList.getTabTournaments().isSelected()) {
			newEventDate = new TournamentModel();
		}

		if (ctlEventList.getTabOtherEvents().isSelected()) {
			newEventDate = new OtherEventModel();
		}

		if (showEditDialog(newEventDate)) {

			if (ctlEventList.getTabLeagueGames().isSelected()) {
				((ContentModel) AppModel.getData().getContent()).getObservableLeagueGames().add((LeagueGame) newEventDate);
				ctlEventList.getLeagueGamesSelectionModel().select((LeagueGame) newEventDate);
			}

			if (ctlEventList.getTabTournaments().isSelected()) {
				((ContentModel) AppModel.getData().getContent()).getObservableTournaments().add((Tournament) newEventDate);
				ctlEventList.getTournamentsSelectionModel().select((Tournament) newEventDate);
			}

			if (ctlEventList.getTabOtherEvents().isSelected()) {
				((ContentModel) AppModel.getData().getContent()).getObservableOtherEvents().add((OtherEvent) newEventDate);
				ctlEventList.getOtherEventsSelectionModel().select((OtherEvent) newEventDate);
			}

			AppModel.setModified(true);
			appController.setAppTitle();
		}

	}

	/**
	 * Opens edit dialog for editing selected data.
	 */
	@FXML
	private void handleEdit() {

		ObservableList<EventDateModel> lstSelected = ctlEventList.getVisibleTabSelection();

		if (lstSelected.size() == 1) {
			if (showEditDialog(lstSelected.get(0))) {
				showDetails(lstSelected.get(0));
				AppModel.setModified(true);
				appController.setAppTitle();
			}
		}

	}

	/**
	 * Deletes selected data from list.
	 */
	@FXML
	private void handleDelete() {

		ObservableList<EventDateModel> lstSelected = ctlEventList.getVisibleTabSelection();

		if (lstSelected.size() == 1) {

			Alert alert = AlertUtils.createAlert(AlertType.CONFIRMATION, appController.getPrimaryStage(),
					"Löschbestätigung",
					MessageFormat.format("Soll ''{0}'' gelöscht werden?", lstSelected.get(0).getDisplayTitle().get()),
					null);

			alert.showAndWait()
					.filter(response -> response == ButtonType.OK)
					.ifPresent(response -> {
						if (ctlEventList.getTabLeagueGames().isSelected()) {
							((ContentModel) AppModel.getData().getContent()).getObservableLeagueGames().remove(lstSelected.get(0));
						}

						if (ctlEventList.getTabTournaments().isSelected()) {
							((ContentModel) AppModel.getData().getContent()).getObservableTournaments().remove(lstSelected.get(0));
						}

						if (ctlEventList.getTabOtherEvents().isSelected()) {
							((ContentModel) AppModel.getData().getContent()).getObservableOtherEvents().remove(lstSelected.get(0));
						}
						AppModel.setModified(true);
						appController.setAppTitle();
						});

		}

	}

	/**
	 * Opens the data edit dialog.
	 *
	 * If the user clicks OK, the changes are saved into the provided event object and true is returned.
	 *
	 * @param theEventDate the data to be edited
	 * @return true if the user clicked OK, false otherwise.
	 */
	private boolean showEditDialog(EventDateModel theEventDate) {

		Objects.requireNonNull(theEventDate);

		Map.Entry<Pane, FXMLLoader> pneLoad = Resources.loadPane("EventEditDialog");

		AnchorPane editDialog = (AnchorPane) pneLoad.getKey();

		// Create the dialog Stage.
		Stage dialogStage = new Stage();
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(appController.getPrimaryStage());
		dialogStage.setTitle(String.format("%s editieren",
				ctlEventList.getTabLeagueGames().isSelected() ? "Ligaspiel" :
					ctlEventList.getTabTournaments().isSelected() ? "Turnier" :
						"Sonstigen Termin"));

		Scene scene = new Scene(editDialog);
		dialogStage.setScene(scene);

		// Set the referee
//		EventEditDialogController editController = pneLoad.getValue().getController();
//		editController.setDialogStage(dialogStage);
//		editController.setEvent(theEventDate);

		// Show the dialog and wait until the user closes it
		dialogStage.showAndWait();

//		return editController.isOkClicked();
		return false;

	}

}

/* EOF */
