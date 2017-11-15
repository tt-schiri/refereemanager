package de.edgesoft.refereemanager.controller;

import de.edgesoft.edgeutils.commons.ext.ModelClassExt;
import de.edgesoft.edgeutils.javafx.FontUtils;
import de.edgesoft.edgeutils.javafx.LabelUtils;
import de.edgesoft.refereemanager.jaxb.TitledIDType;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.FontWeight;

/**
 * Controller for the details part: title id.
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
public class DetailsTitledIDController implements IDetailsController {

	/**
	 * Heading label.
	 */
	@FXML
	private Label lblHeading;

	/**
	 * ID label for output.
	 */
	@FXML
	private Label lblID;

	/**
	 * "ID" label.
	 */
	@FXML
	private Label lblLabelID;

	/**
	 * Title label for output.
	 */
	@FXML
	private Label lblTitle;

	/**
	 * "Title" label.
	 */
	@FXML
	private Label lblLabelTitle;

	/**
	 * Short title label for output.
	 */
	@FXML
	private Label lblShorttitle;

	/**
	 * "Short title" label.
	 */
	@FXML
	private Label lblLabelShorttitle;

	/**
	 * Remark label for output.
	 */
	@FXML
	private Label lblRemark;

	/**
	 * "Remark" label.
	 */
	@FXML
	private Label lblLabelRemark;

	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		lblHeading.setFont(FontUtils.getDerived(lblHeading.getFont(), FontWeight.BOLD));
	}

	/**
	 * Shows detail data.
	 *
	 * @param theDetailData (null if no data to show)
	 */
	@Override
	public <T extends ModelClassExt> void showDetails(final T theDetailData) {

		if ((theDetailData == null) || !(theDetailData instanceof TitledIDType)) {

			LabelUtils.setText(lblID, null);

			LabelUtils.setText(lblTitle, null);
			LabelUtils.setText(lblShorttitle, null);
			LabelUtils.setText(lblRemark, null);

		} else {

			TitledIDType theData = (TitledIDType) theDetailData;

			lblID.setText(theData.getId());

			LabelUtils.setText(lblTitle, theData.getTitle());
			LabelUtils.setText(lblShorttitle, theData.getShorttitle());
			LabelUtils.setText(lblRemark, theData.getRemark());

		}

	}

}

/* EOF */