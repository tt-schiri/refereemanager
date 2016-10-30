package de.edgesoft.refereemanager.controller;

import java.io.File;
import java.nio.file.Paths;

import de.edgesoft.refereemanager.utils.AlertUtils;
import de.edgesoft.refereemanager.utils.PrefKey;
import de.edgesoft.refereemanager.utils.Prefs;
import de.edgesoft.refereemanager.utils.Resources;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 * Controller for preferences edit dialog scene.
 *
 * ## Legal stuff
 *
 * Copyright 2016-2016 Ekkart Kleinod <ekleinod@edgesoft.de>
 *
 * This file is part of refereemanager.
 *
 * refereemanager is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * refereemanager is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with refereemanager.  If not, see <http://www.gnu.org/licenses/>.
 *
 * @author Ekkart Kleinod
 * @version 0.10.0
 * @since 0.10.0
 */
public class PreferencesDialogController {

	/**
	 * Checkbox: full path in title.
	 *
	 * @version 6.0.0
	 * @since 6.0.0
	 */
	@FXML
	private CheckBox chkTitleFullpath;

	/**
	 * Template path.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private TextField txtTemplatePath;

	/**
	 * Template path button.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private Button btnTemplatePath;

	/**
	 * Image path.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private TextField txtImagePath;

	/**
	 * Image path button.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private Button btnImagePath;

	/**
	 * OK button.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private Button btnOK;

	/**
	 * Cancel button.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private Button btnCancel;

	/**
	 * Tab display.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private Tab tabDisplay;

	/**
	 * Tab paths.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private Tab tabPaths;

	/**
	 * Reference to dialog stage.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	private Stage dialogStage;

	/**
	 * OK clicked?.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	private boolean okClicked;


	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private void initialize() {

        chkTitleFullpath.setSelected(Boolean.parseBoolean(Prefs.get(PrefKey.TITLE_FULLPATH)));
		txtTemplatePath.setText(Prefs.get(PrefKey.TEMPLATE_PATH));
		txtImagePath.setText(Prefs.get(PrefKey.IMAGE_PATH));

		// icons
		btnTemplatePath.setGraphic(new ImageView(Resources.loadImage("icons/actions/folder-open-16.png")));
		btnImagePath.setGraphic(new ImageView(Resources.loadImage("icons/actions/folder-open-16.png")));

		btnOK.setGraphic(new ImageView(Resources.loadImage("icons/actions/dialog-ok-16.png")));
		btnCancel.setGraphic(new ImageView(Resources.loadImage("icons/actions/dialog-cancel-16.png")));

		tabDisplay.setGraphic(new ImageView(Resources.loadImage("icons/actions/view-list-details.png")));
		tabPaths.setGraphic(new ImageView(Resources.loadImage("icons/actions/view-list-details.png")));

    }

	/**
	 * Sets dialog stage.
	 *
	 * @param theStage dialog stage
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	public void setDialogStage(final Stage theStage) {
        dialogStage = theStage;
    }

	/**
	 * Returns if user clicked ok.
	 *
	 * @return did user click ok?
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	public boolean isOkClicked() {
        return okClicked;
    }

	/**
	 * Validates input, stores ok click, and closes dialog; does nothing for invalid input.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
    private void handleOk() {
        okClicked = false;
        if (isInputValid()) {

        	Prefs.put(PrefKey.TITLE_FULLPATH, Boolean.toString(chkTitleFullpath.isSelected()));

        	Prefs.put(PrefKey.TEMPLATE_PATH, txtTemplatePath.getText());
        	Prefs.put(PrefKey.IMAGE_PATH, txtImagePath.getText());

            okClicked = true;
            dialogStage.close();
        }
    }

	/**
	 * Stores non-ok click and closes dialog.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
    private void handleCancel() {
        okClicked = false;
        dialogStage.close();
    }

	/**
	 * Validates input, shows error message for invalid input.
	 *
	 * @return is input valid?
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	private boolean isInputValid() {

        StringBuilder sbErrorMessage = new StringBuilder();

        if (!txtTemplatePath.getText().isEmpty()) {
	        File flePath = Paths.get(txtTemplatePath.getText()).toFile();
	        if (!flePath.exists() || !flePath.isDirectory()) {
	            sbErrorMessage.append("Der Template-Pfad existiert nicht oder ist kein Verzeichnis.\n");
	        }
        }

        if (!txtImagePath.getText().isEmpty()) {
	        File flePath = Paths.get(txtImagePath.getText()).toFile();
	        if (!flePath.exists() || !flePath.isDirectory()) {
	            sbErrorMessage.append("Der Bilder-Pfad existiert nicht oder ist kein Verzeichnis.\n");
	        }
        }

        if (sbErrorMessage.length() == 0) {
            return true;
        }

        // Show the error message.
        AlertUtils.createAlert(AlertType.ERROR, dialogStage,
        		"Ungültige Eingaben",
        		"Bitte korrigieren Sie die fehlerhaften Eingaben.",
        		sbErrorMessage.toString())
        .showAndWait();

        return false;

    }

	/**
	 * Set template path.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private void handleTemplatePath() {

		DirectoryChooser dirChooser = new DirectoryChooser();

		dirChooser.setTitle("Template-Pfad auswählen");
        if (!Prefs.get(PrefKey.TEMPLATE_PATH).isEmpty()) {
        	dirChooser.setInitialDirectory(new File(Prefs.get(PrefKey.TEMPLATE_PATH)));
        }

        File dir = dirChooser.showDialog(dialogStage);

        if (dir != null) {
            txtTemplatePath.setText(dir.getPath());
        }

	}

	/**
	 * Set image path.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private void handleImagePath() {

		DirectoryChooser dirChooser = new DirectoryChooser();

		dirChooser.setTitle("Bilder-Pfad auswählen");
        if (!Prefs.get(PrefKey.IMAGE_PATH).isEmpty()) {
        	dirChooser.setInitialDirectory(new File(Prefs.get(PrefKey.IMAGE_PATH)));
        }

        File dir = dirChooser.showDialog(dialogStage);

        if (dir != null) {
            txtImagePath.setText(dir.getPath());
        }

	}

}

/* EOF */
