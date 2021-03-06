package de.edgesoft.refereemanager.controller;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

/**
 * Controller for progress dialog scene.
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
 * @version 0.14.0
 * @since 0.10.0
 */
public class ProgressDialogController {

	/**
	 * Title label
	 *
	 * @version 0.14.0
	 * @since 6.0.0
	 */
	@FXML
	private Label lblTitle;

	/**
	 * Message label.
	 *
	 * @version 0.14.0
	 * @since 0.10.0
	 */
	@FXML
	private Label lblMessage;

	/**
	 * Progress bar.
	 *
	 * @version 0.14.0
	 * @since 0.10.0
	 */
	@FXML
	private ProgressBar progBar;

	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 *
	 * @version 0.14.0
	 * @since 0.10.0
	 */
	@FXML
	private void initialize() {

		progBar.setProgress(0);
		progBar.progressProperty().unbind();
		lblMessage.textProperty().unbind();

		}

	/**
	 * Initializes the controller with things, that cannot be done during {@link #initialize()}.
	 *
	 * @param theTitle title
	 * @param theTask task to bind
	 *
	 * @version 0.14.0
	 * @since 0.10.0
	 */
	public void initController(final String theTitle, final Task<?> theTask) {
		lblTitle.setText(theTitle);
		progBar.progressProperty().bind(theTask.progressProperty());
		lblMessage.textProperty().bind(theTask.messageProperty());
		}

}

/* EOF */
