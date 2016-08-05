package de.edgesoft.refereemanager.model;

import java.util.function.Predicate;

import de.edgesoft.refereemanager.Prefs;
import de.edgesoft.refereemanager.jaxb.Contact;
import de.edgesoft.refereemanager.utils.PrefKey;

/**
 * Contact model, additional methods for jaxb model class.
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
 * @since 0.6.0
 */
public class ContactModel extends Contact {

	/** Filter predicate for primary contacts. */
	public static final Predicate<Contact> ISPRIMARY = (contact -> (contact.isIsPrimary() == null) || contact.isIsPrimary());
	
	/** 
	 * Use private data only?
	 * 
	 * This was introduced, because it is far too difficult to provide
	 * the getter methods with a parameter.
	 * 
	 * @todo check if there is a better way of implementing this
	 * 
	 * @version 0.8.0
	 * @since 0.8.0
	 */
	public static boolean isPrivateOnly = false;
	
	/**
	 * Is contact private?
	 * 
	 * @return is contact private
	 * 
	 * @version 0.8.0
	 * @since 0.8.0
	 */
    public boolean isPrivate() {
    	
    	return (getContactType() == null) || ((getContactType() != null) && getContactType().getId().equals(Prefs.get(PrefKey.CONTACT_PRIVATE)));
    	
    }
    
}

/* EOF */
