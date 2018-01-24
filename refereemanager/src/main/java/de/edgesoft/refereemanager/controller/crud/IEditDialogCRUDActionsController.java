package de.edgesoft.refereemanager.controller.crud;

import de.edgesoft.edgeutils.commons.ext.ModelClassExt;
import javafx.collections.ObservableList;

/**
 * Interface for CRUD actions that open a dialog.
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
public interface IEditDialogCRUDActionsController<T extends ModelClassExt> extends ICRUDActionsController {

	/**
	 * Sets edit dialog's fxml filename and noun, shown in title ("edit <noun>").
	 *
	 * @param theFXMLFilename fxml filename of the edit view
	 * @param theTitleNoun title noun of the edit view ("edit <noun>")
	 */
	public void initEditDialogFXMLFilename(
			final String theFXMLFilename,
			final String theTitleNoun
	);

	/**
	 * Handles add action.
	 *
	 * @param theData data element
	 * @param theDataList data list to add data to
	 */
	public void handleAdd(
			T theData,
			ObservableList<T> theDataList
	);

	/**
	 * Handles edit action.
	 */
	public void handleEdit();

	/**
	 * Handles delete action.
	 *
	 * @param theDataList data list to delete data from
	 */
	public void handleDelete(
			ObservableList<T> theDataList
	);

	/**
	 * Opens the data edit dialog.
	 *
	 * If the user clicks OK, the changes are saved into the provided event object and true is returned.
	 *
	 * @param theData the data to be edited
	 * @return true if the user clicked OK, false otherwise.
	 */
	public boolean showEditDialog(
			T theData
	);

}

/* EOF */
