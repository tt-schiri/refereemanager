package de.edgesoft.refereemanager.model;

import java.util.Comparator;

import de.edgesoft.refereemanager.jaxb.TrainingLevel;
import javafx.beans.property.StringProperty;

/**
 * TrainingLevel model, additional methods for jaxb model class.
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
 * @since 0.5.0
 */
public class TrainingLevelModel extends TrainingLevel {

	/**
	 * Comparator rank.
	 *
	 * @version 0.14.0
	 * @since 0.5.0
	 */
	public static final Comparator<TrainingLevel> RANK = Comparator.comparingInt(tl -> tl.getType().getRank().get());

	/**
	 * Returns display text.
	 *
	 * @return display text
	 * @version 0.14.0
	 */
	@Override
	public StringProperty getDisplayText() {
		return getType().getDisplayTitleShort();
	}

}

/* EOF */
