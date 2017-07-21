package de.edgesoft.refereemanager.controller;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import de.edgesoft.refereemanager.jaxb.League;
import de.edgesoft.refereemanager.jaxb.LeagueGame;
import de.edgesoft.refereemanager.jaxb.OtherEvent;
import de.edgesoft.refereemanager.jaxb.Tournament;
import de.edgesoft.refereemanager.model.AppModel;
import de.edgesoft.refereemanager.model.ContentModel;
import de.edgesoft.refereemanager.model.EventDateModel;
import de.edgesoft.refereemanager.model.LeagueGameModel;
import de.edgesoft.refereemanager.model.OtherEventModel;
import de.edgesoft.refereemanager.model.TitledIDTypeModel;
import de.edgesoft.refereemanager.model.TournamentModel;
import de.edgesoft.refereemanager.utils.TableUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.layout.HBox;

/**
 * Controller for the event list scene.
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
public class EventListController {

	/**
	 * Text for empty table: no data.
	 */
	private static final String TABLE_NO_DATA = "Es wurden noch keine {0} eingegeben.";

	/**
	 * Text for empty table: filtered.
	 */
	private static final String TABLE_FILTERED = "Die Filterung schließt alle {0} aus.";

	/**
	 * Tab pane content.
	 */
	@FXML
	private TabPane tabPaneContent;

	/**
	 * Tab league games.
	 */
	@FXML
	private Tab tabLeagueGames;

	/**
	 * Table view league games.
	 */
	@FXML
	private TableView<LeagueGame> tblLeagueGames;

	/**
	 * ID column.
	 */
	@FXML
	private TableColumn<LeagueGameModel, String> colLeagueGameID;

	/**
	 * Number column.
	 */
	@FXML
	private TableColumn<LeagueGameModel, String> colLeagueGameNumber;

	/**
	 * Date column.
	 */
	@FXML
	private TableColumn<LeagueGameModel, LocalDateTime> colLeagueGameDate;

	/**
	 * Time column.
	 */
	@FXML
	private TableColumn<LeagueGameModel, LocalDateTime> colLeagueGameTime;

	/**
	 * League column.
	 */
	@FXML
	private TableColumn<LeagueGameModel, String> colLeagueGameLeague;

	/**
	 * Teams column.
	 */
	@FXML
	private TableColumn<LeagueGameModel, String> colLeagueGameTeams;

	/**
	 * List of league games.
	 */
	private FilteredList<LeagueGame> lstLeagueGame;

	/**
	 * Label filter.
	 */
	@FXML
	private Label lblLeagueGameFilter;

	/**
	 * HBox filter league.
	 */
	@FXML
	private HBox boxFilterLeague;

	/**
	 * Filter storage.
	 */
	@FXML
	private Map<CheckBox, League> mapChkLeagues;


	/**
	 * Tab tournaments.
	 */
	@FXML
	private Tab tabTournaments;

	/**
	 * Table view tournaments.
	 */
	@FXML
	private TableView<Tournament> tblTournaments;

	/**
	 * ID column.
	 */
	@FXML
	private TableColumn<TournamentModel, String> colTournamentID;

	/**
	 * Start date column.
	 */
	@FXML
	private TableColumn<TournamentModel, LocalDateTime> colTournamentDateStart;

	/**
	 * End date column.
	 */
	@FXML
	private TableColumn<TournamentModel, LocalDateTime> colTournamentDateEnd;

	/**
	 * Title column.
	 */
	@FXML
	private TableColumn<TournamentModel, String> colTournamentTitle;

	/**
	 * List of tournaments.
	 */
	private FilteredList<Tournament> lstTournaments;


	/**
	 * Tab other events.
	 */
	@FXML
	private Tab tabOtherEvents;

	/**
	 * Table other events.
	 */
	@FXML
	private TableView<OtherEvent> tblOtherEvents;

	/**
	 * ID column.
	 */
	@FXML
	private TableColumn<OtherEventModel, String> colOtherEventID;

	/**
	 * Start date column.
	 */
	@FXML
	private TableColumn<OtherEventModel, LocalDateTime> colOtherEventDateStart;

	/**
	 * End date column.
	 */
	@FXML
	private TableColumn<OtherEventModel, LocalDateTime> colOtherEventDateEnd;

	/**
	 * Start time column.
	 */
	@FXML
	private TableColumn<OtherEventModel, LocalDateTime> colOtherEventTimeStart;

	/**
	 * Start time column.
	 */
	@FXML
	private TableColumn<OtherEventModel, LocalDateTime> colOtherEventTimeEnd;

	/**
	 * Title column.
	 */
	@FXML
	private TableColumn<OtherEventModel, String> colOtherEventTitle;

	/**
	 * List of other events.
	 */
	private FilteredList<OtherEvent> lstOtherEvents;


	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {

		// hook data to columns (league games)
		colLeagueGameID.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
		colLeagueGameID.setVisible(false);

		colLeagueGameNumber.setCellValueFactory(cellData -> cellData.getValue().getGameNumberString());
		colLeagueGameDate.setCellValueFactory(cellData -> cellData.getValue().getStart());
		colLeagueGameTime.setCellValueFactory(cellData -> cellData.getValue().getStart());
		colLeagueGameLeague.setCellValueFactory(cellData -> cellData.getValue().getHomeTeam().getLeague().getDisplayTitleShort());
		colLeagueGameTeams.setCellValueFactory(cellData -> cellData.getValue().getTeamText());

		// hook data to columns (tournaments)
		colTournamentID.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
		colTournamentID.setVisible(false);

		colTournamentDateStart.setCellValueFactory(cellData -> cellData.getValue().getStart());
		colTournamentDateEnd.setCellValueFactory(cellData -> cellData.getValue().getEnd());
		colTournamentTitle.setCellValueFactory(cellData -> cellData.getValue().getDisplayTitleShort());

		// hook data to columns (other events)
		colOtherEventID.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
		colOtherEventID.setVisible(false);

		colOtherEventDateStart.setCellValueFactory(cellData -> cellData.getValue().getStart());
		colOtherEventDateEnd.setCellValueFactory(cellData -> cellData.getValue().getEnd());
		colOtherEventTimeStart.setCellValueFactory(cellData -> cellData.getValue().getStart());
		colOtherEventTimeEnd.setCellValueFactory(cellData -> cellData.getValue().getEnd());
		colOtherEventTitle.setCellValueFactory(cellData -> cellData.getValue().getDisplayTitleShort());

		// format date columns
		colLeagueGameDate.setCellFactory(column -> TableUtils.getTableCellLeagueGameDate());
		colLeagueGameTime.setCellFactory(column -> TableUtils.getTableCellLeagueGameTime());

		colTournamentDateStart.setCellFactory(column -> TableUtils.getTableCellTournamentDate());
		colTournamentDateEnd.setCellFactory(column -> TableUtils.getTableCellTournamentDate());

		colOtherEventDateStart.setCellFactory(column -> TableUtils.getTableCellOtherEventDate());
		colOtherEventDateEnd.setCellFactory(column -> TableUtils.getTableCellOtherEventDate());
		colOtherEventTimeStart.setCellFactory(column -> TableUtils.getTableCellOtherEventTime());
		colOtherEventTimeEnd.setCellFactory(column -> TableUtils.getTableCellOtherEventTime());

		// listen to tab changes
		tabPaneContent.getSelectionModel().selectedItemProperty().addListener((event, oldTab, newTab) -> {
	        handleTabChange();
	    });

		// fill filter
		mapChkLeagues = new HashMap<>();
		AppModel.getData().getContent().getLeague().stream().sorted(TitledIDTypeModel.SHORTTITLE_TITLE).forEach(
				league -> {
					CheckBox chkTemp = new CheckBox(league.getDisplayTitleShort().getValue());
					chkTemp.setOnAction(e -> handleFilterChange());
					boxFilterLeague.getChildren().add(chkTemp);
					mapChkLeagues.put(chkTemp, league);
				}
		);

		setItems();

	}

	/**
	 * Sets table items.
	 */
	public void setItems() {

		if (AppModel.getData() == null) {
			lstLeagueGame = null;
			lstTournaments = null;
			lstOtherEvents = null;
		} else {
			lstLeagueGame = new FilteredList<>(((ContentModel) AppModel.getData().getContent()).getObservableLeagueGames(), leaguegame -> true);
			lstTournaments = new FilteredList<>(((ContentModel) AppModel.getData().getContent()).getObservableTournaments(), tournament -> true);
			lstOtherEvents = new FilteredList<>(((ContentModel) AppModel.getData().getContent()).getObservableOtherEvents(), otherevent -> true);
		}

		SortedList<LeagueGame> lstSortedLeagueGames = new SortedList<>(lstLeagueGame);
		lstSortedLeagueGames.comparatorProperty().bind(tblLeagueGames.comparatorProperty());
		tblLeagueGames.setItems(lstSortedLeagueGames);

		SortedList<Tournament> lstSortedTournaments = new SortedList<>(lstTournaments);
		lstSortedTournaments.comparatorProperty().bind(tblTournaments.comparatorProperty());
		tblTournaments.setItems(lstSortedTournaments);

		SortedList<OtherEvent> lstSortedOtherEvents = new SortedList<>(lstOtherEvents);
		lstSortedOtherEvents.comparatorProperty().bind(tblOtherEvents.comparatorProperty());
		tblOtherEvents.setItems(lstSortedOtherEvents);

		// set "empty data" text
		Label lblPlaceholder = new Label(MessageFormat.format(((lstLeagueGame == null) || lstLeagueGame.isEmpty()) ? TABLE_NO_DATA : TABLE_FILTERED, "Ligaspiele"));
		lblPlaceholder.setWrapText(true);
		tblLeagueGames.setPlaceholder(lblPlaceholder);

		lblPlaceholder = new Label(MessageFormat.format(((lstTournaments == null) || lstTournaments.isEmpty()) ? TABLE_NO_DATA : TABLE_FILTERED, "Turniere"));
		lblPlaceholder.setWrapText(true);
		tblTournaments.setPlaceholder(lblPlaceholder);

		lblPlaceholder = new Label(MessageFormat.format(((lstOtherEvents == null) || lstOtherEvents.isEmpty()) ? TABLE_NO_DATA : TABLE_FILTERED, "sonstigen Termine"));
		lblPlaceholder.setWrapText(true);
		tblOtherEvents.setPlaceholder(lblPlaceholder);

		handleFilterChange();

	}

	/**
	 * Handles filter change events.
	 */
	@SuppressWarnings("unchecked")
	private void handleFilterChange() {

		// filter for referees
		if (lstLeagueGame == null) {
			lblLeagueGameFilter.setText("Filter");
		} else {

			lstLeagueGame.setPredicate(LeagueGameModel.ALL);

			for (Entry<CheckBox, League> entryChkLeague : mapChkLeagues.entrySet()) {

				if (entryChkLeague.getKey().isSelected()) {
					lstLeagueGame.setPredicate(((Predicate<LeagueGame>) lstLeagueGame.getPredicate()).and(LeagueGameModel.getLeaguePredicate(entryChkLeague.getValue())));
				}

			}

			lblLeagueGameFilter.setText(MessageFormat.format("Filter ({0} angezeigt)", lstLeagueGame.size()));
		}

		tblLeagueGames.refresh();
		tblTournaments.refresh();
		tblOtherEvents.refresh();

	}

	/**
	 * Handles tab change events.
	 */
	@FXML
	private void handleTabChange() {
		handleFilterChange();
	}

	/**
	 * Sets selection mode.
	 *
	 * @param theSelectionMode selection mode
	 */
	public void setSelectionMode(final SelectionMode theSelectionMode) {
		tblLeagueGames.getSelectionModel().setSelectionMode(theSelectionMode);
		tblTournaments.getSelectionModel().setSelectionMode(theSelectionMode);
		tblOtherEvents.getSelectionModel().setSelectionMode(theSelectionMode);
	}

	/**
	 * Returns selection model of league games table.
	 *
	 * @return selection model
	 */
	public TableViewSelectionModel<LeagueGame> getLeagueGamesSelectionModel() {
		return tblLeagueGames.getSelectionModel();
	}

	/**
	 * Returns selection model of tournaments table.
	 *
	 * @return selection model
	 */
	public TableViewSelectionModel<Tournament> getTournamentsSelectionModel() {
		return tblTournaments.getSelectionModel();
	}

	/**
	 * Returns selection model of other events table.
	 *
	 * @return selection model
	 */
	public TableViewSelectionModel<OtherEvent> getOtherEventsSelectionModel() {
		return tblOtherEvents.getSelectionModel();
	}

	/**
	 * Returns tab pane.
	 *
	 * @return tab pane
	 */
	public TabPane getContentTab() {
		return tabPaneContent;
	}

	/**
	 * Returns league games tab.
	 *
	 * @return league games tab
	 */
	public Tab getTabLeagueGames() {
		return tabLeagueGames;
	}

	/**
	 * Returns tournaments tab.
	 *
	 * @return tournaments tab
	 */
	public Tab getTabTournaments() {
		return tabTournaments;
	}

	/**
	 * Returns other events tab.
	 *
	 * @return other events tab
	 */
	public Tab getTabOtherEvents() {
		return tabOtherEvents;
	}

	/**
	 * Returns selection from all tabs as sorted list.
	 *
	 * @return sorted selection from all tabs
	 */
	public ObservableList<EventDateModel> getAllTabSelection() {

		List<EventDateModel> lstReturn = new ArrayList<>();

		getTabLeagueGamesSelection().forEach(event -> lstReturn.add(event));
		getTabTournamentsSelection().forEach(event -> lstReturn.add(event));
		getTabOtherEventsSelection().forEach(event -> lstReturn.add(event));

		return FXCollections.observableList(lstReturn.stream().sorted(EventDateModel.RANK_START).collect(Collectors.toList()));
	}

	/**
	 * Returns selection from visible tab as sorted list.
	 *
	 * @return sorted selection from visible tabs
	 */
	public ObservableList<EventDateModel> getVisibleTabSelection() {

		if (tabLeagueGames.isSelected()) {
			return getTabLeagueGamesSelection();
		}

		if (tabTournaments.isSelected()) {
			return getTabTournamentsSelection();
		}

		if (tabOtherEvents.isSelected()) {
			return getTabOtherEventsSelection();
		}

		return FXCollections.observableList(new ArrayList<>());
	}

	/**
	 * Returns selection from league games tab as sorted list.
	 *
	 * @return sorted selection from league games tab
	 */
	public ObservableList<EventDateModel> getTabLeagueGamesSelection() {
		return FXCollections.observableList(tblLeagueGames.getSelectionModel().getSelectedItems().stream().sorted(EventDateModel.RANK_START).collect(Collectors.toList()));
	}

	/**
	 * Returns selection from tournaments tab as sorted list.
	 *
	 * @return sorted selection from tournaments tab
	 */
	public ObservableList<EventDateModel> getTabTournamentsSelection() {
		return FXCollections.observableList(tblTournaments.getSelectionModel().getSelectedItems().stream().sorted(EventDateModel.RANK_START).collect(Collectors.toList()));
	}

	/**
	 * Returns selection from other events tab as sorted list.
	 *
	 * @return sorted selection from other events tab
	 */
	public ObservableList<EventDateModel> getTabOtherEventsSelection() {
		return FXCollections.observableList(tblOtherEvents.getSelectionModel().getSelectedItems().stream().sorted(EventDateModel.RANK_START).collect(Collectors.toList()));
	}

}

/* EOF */
