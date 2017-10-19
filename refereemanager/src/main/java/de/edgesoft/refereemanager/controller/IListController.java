package de.edgesoft.refereemanager.controller;

import de.edgesoft.edgeutils.commons.ext.ModelClassExt;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView.TableViewSelectionModel;

/**
 * Interface for the list scene controller.
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
public interface IListController {

	/**
	 * Sets table items.
	 */
	public void setItems();

	/**
	 * Handles filter change events.
	 */
	public void handleFilterChange();

	/**
	 * Sets selection mode.
	 *
	 * @param theSelectionMode selection mode
	 */
	public void setSelectionMode(final SelectionMode theSelectionMode);

	/**
	 * Returns selection model of data table.
	 *
	 * @return selection model
	 */
	public TableViewSelectionModel<? extends ModelClassExt> getSelectionModel();

	/**
	 * Returns selection from table as sorted list.
	 *
	 * @return sorted selection from table
	 */
	public ObservableList<? extends ModelClassExt> getSelection();

}

/* EOF */
