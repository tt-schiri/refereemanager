package de.edgesoft.refereemanager;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Objects;

import de.edgesoft.edgeutils.commandline.AbstractMainClass;
import de.edgesoft.edgeutils.files.JAXBFiles;
import de.edgesoft.refereemanager.jaxb.ObjectFactory;
import de.edgesoft.refereemanager.model.SeasonModel;
import de.edgesoft.refereemanager.utils.ArgumentDBOperation;
import de.edgesoft.refereemanager.utils.PrefKey;
import de.edgesoft.refereemanager.utils.Prefs;

/**
 * Database operations.
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
 * @version 0.8.0
 * @since 0.7.0
 */
public class DBOperations extends AbstractMainClass {
	
	/**
	 * Command line entry point.
	 * 
	 * @param args command line arguments
	 * 
	 * @version 0.7.0
	 * @since 0.7.0
	 */
	public static void main(String[] args) {
		new DBOperations().executeOperation(args);
	}

	/**
	 * Programmatic entry point, initializing and executing main functionality.
	 * 
	 * - set description setDescription("...");
	 * - add options addOption("short", "long", "description", argument?, required?);
	 * - call init(args);
	 * - call operation execution with arguments
	 * 
	 * @version 0.8.0
	 * @since 0.7.0
	 */
	@Override
	public void executeOperation(final String[] args) {
		
		setDescription("Database operations.");
		
		addOption("p", "path", MessageFormat.format("input path of data (default: {0}).", Prefs.get(PrefKey.PATH_DATABASE)), true, false);
		addOption("d", "database", MessageFormat.format("database file name pattern (default: {0}).", Prefs.get(PrefKey.FILENAME_PATTERN_DATABASE)), true, false);
		addOption("s", "season", "season (empty for current season).", true, false);
		addOption("o", "output", "output file (empty for database file + '.db.xml').", true, false);
		addOption("r", "dboperation", "database operation (removeclubs, save, sort).", true, true);
		
		init(args);
		
		dbOperation(getOptionValue("p"), getOptionValue("d"), getOptionValue("s"), getOptionValue("o"), getOptionValue("r"));
		
	}

	/**
	 * Executes database operation.
	 * 
	 * @param theDBPath input path
	 * @param theDBFile db filename (null = {@link RefereeManager#DATAFILENAMEPATTERN})
	 * @param theSeason season (null = current season)
	 * @param theOutputfile output file (null = database + ".db.xml")
	 * @param theDBOperation database operation
	 * 
	 * @version 0.8.0
	 * @since 0.7.0
	 */
	public void dbOperation(final String theDBPath, final String theDBFile, final String theSeason, final String theOutputfile, final String theDBOperation) {
		
		RefereeManager.logger.debug("start.");
		
		Objects.requireNonNull(theDBOperation, "database  operation must not be null");
		
		Integer iSeason = (theSeason == null) ? SeasonModel.getCurrentStartYear() : Integer.valueOf(theSeason);

		Path pathDBFile = Paths.get((theDBPath == null) ? Prefs.get(PrefKey.PATH_DATABASE) : theDBPath,
				String.format(((theDBFile == null) ? Prefs.get(PrefKey.FILENAME_PATTERN_DATABASE) : theDBFile), iSeason));
		
		Path sOutFile = Paths.get((theOutputfile == null) ? String.format("%s.db.xml", pathDBFile) : theOutputfile);
		
		ArgumentDBOperation argDBOperation = ArgumentDBOperation.fromValue(theDBOperation);
		
		dbOperation(pathDBFile, sOutFile, argDBOperation);
		
		RefereeManager.logger.debug("stop");
		
	}
	
	/**
	 * Executes database operation.
	 * 
	 * @param theDBPath database filename with path
	 * @param theOutputPath output file
	 * @param theDBOperation database operation
	 * 
	 * @version 0.8.0
	 * @since 0.7.0
	 */
	public void dbOperation(final Path theDBPath, final Path theOutputPath, final ArgumentDBOperation theDBOperation) {
		
		Objects.requireNonNull(theDBPath, "database file path must not be null");
		Objects.requireNonNull(theOutputPath, "output file path must not be null");
		Objects.requireNonNull(theDBOperation, "db operation must not be null");
		
		try {
			
			RefereeManager.logger.debug(String.format("read database '%s'.", theDBPath.toString()));
			
			final de.edgesoft.refereemanager.jaxb.RefereeManager mgrData = JAXBFiles.unmarshal(theDBPath.toString(), de.edgesoft.refereemanager.jaxb.RefereeManager.class);
			
			RefereeManager.logger.debug(String.format("execute operation '%s'.", theDBOperation.value()));
			
			// do something
			switch (theDBOperation) {
				case REMOVECLUBS:
					de.edgesoft.refereemanager.utils.DBOperations.removeClubs(mgrData);
					break;
				case SAVE:
					break;
				case SORT:
					de.edgesoft.refereemanager.utils.DBOperations.sortDB(mgrData);
					break;
			}
			
			// update info block
			mgrData.getInfo().setModified(LocalDateTime.now());
			mgrData.getInfo().setDocversion(RefereeManager.VERSION);
			mgrData.getInfo().setAppversion(RefereeManager.VERSION);
			mgrData.getInfo().setCreator(this.getClass().getName());
			
			RefereeManager.logger.debug(String.format("write database '%s'.", theOutputPath.toString()));
			
			JAXBFiles.marshal(new ObjectFactory().createRefereemanager(mgrData), theOutputPath.toString(), "../../../git/refereemanager/java-backend/refereemanager/src/main/jaxb/refereemanager.xsd");
			
		} catch (Exception e) {
			RefereeManager.logger.error(e);
			e.printStackTrace();
		}
		
	}
	
}

/* EOF */