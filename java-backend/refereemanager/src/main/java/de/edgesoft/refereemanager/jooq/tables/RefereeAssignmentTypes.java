/**
 * This class is generated by jOOQ
 */
package de.edgesoft.refereemanager.jooq.tables;


import de.edgesoft.refereemanager.jooq.Keys;
import de.edgesoft.refereemanager.jooq.Refereemanager;
import de.edgesoft.refereemanager.jooq.tables.records.RefereeAssignmentTypesRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Identity;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;
import org.jooq.types.UInteger;


/**
 * Possible types of referee's assignments such as umpire, stan / * comment 
 * truncated * / / *dby umpire, referee, racket control.* /
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.0"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RefereeAssignmentTypes extends TableImpl<RefereeAssignmentTypesRecord> {

    private static final long serialVersionUID = 1944585402;

    /**
     * The reference instance of <code>refereemanager.rfrmgr_referee_assignment_types</code>
     */
    public static final RefereeAssignmentTypes REFEREE_ASSIGNMENT_TYPES = new RefereeAssignmentTypes();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<RefereeAssignmentTypesRecord> getRecordType() {
        return RefereeAssignmentTypesRecord.class;
    }

    /**
     * The column <code>refereemanager.rfrmgr_referee_assignment_types.id</code>.
     */
    public final TableField<RefereeAssignmentTypesRecord, UInteger> ID = createField("id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>refereemanager.rfrmgr_referee_assignment_types.sid</code>.
     */
    public final TableField<RefereeAssignmentTypesRecord, String> SID = createField("sid", org.jooq.impl.SQLDataType.VARCHAR.length(20).nullable(false), this, "");

    /**
     * The column <code>refereemanager.rfrmgr_referee_assignment_types.title</code>.
     */
    public final TableField<RefereeAssignmentTypesRecord, String> TITLE = createField("title", org.jooq.impl.SQLDataType.VARCHAR.length(100).nullable(false), this, "");

    /**
     * The column <code>refereemanager.rfrmgr_referee_assignment_types.abbreviation</code>.
     */
    public final TableField<RefereeAssignmentTypesRecord, String> ABBREVIATION = createField("abbreviation", org.jooq.impl.SQLDataType.VARCHAR.length(10).nullable(false), this, "");

    /**
     * The column <code>refereemanager.rfrmgr_referee_assignment_types.remark</code>.
     */
    public final TableField<RefereeAssignmentTypesRecord, String> REMARK = createField("remark", org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * Create a <code>refereemanager.rfrmgr_referee_assignment_types</code> table reference
     */
    public RefereeAssignmentTypes() {
        this("rfrmgr_referee_assignment_types", null);
    }

    /**
     * Create an aliased <code>refereemanager.rfrmgr_referee_assignment_types</code> table reference
     */
    public RefereeAssignmentTypes(String alias) {
        this(alias, REFEREE_ASSIGNMENT_TYPES);
    }

    private RefereeAssignmentTypes(String alias, Table<RefereeAssignmentTypesRecord> aliased) {
        this(alias, aliased, null);
    }

    private RefereeAssignmentTypes(String alias, Table<RefereeAssignmentTypesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "Possible types of referee's assignments such as umpire, stan /* comment truncated */ /*dby umpire, referee, racket control.*/");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Refereemanager.REFEREEMANAGER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<RefereeAssignmentTypesRecord, UInteger> getIdentity() {
        return Keys.IDENTITY_REFEREE_ASSIGNMENT_TYPES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<RefereeAssignmentTypesRecord> getPrimaryKey() {
        return Keys.KEY_RFRMGR_REFEREE_ASSIGNMENT_TYPES_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<RefereeAssignmentTypesRecord>> getKeys() {
        return Arrays.<UniqueKey<RefereeAssignmentTypesRecord>>asList(Keys.KEY_RFRMGR_REFEREE_ASSIGNMENT_TYPES_PRIMARY, Keys.KEY_RFRMGR_REFEREE_ASSIGNMENT_TYPES_ID_UNIQUE, Keys.KEY_RFRMGR_REFEREE_ASSIGNMENT_TYPES_SID_UNIQUE, Keys.KEY_RFRMGR_REFEREE_ASSIGNMENT_TYPES_TITLE_UNIQUE, Keys.KEY_RFRMGR_REFEREE_ASSIGNMENT_TYPES_ABBREVIATION_UNIQUE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RefereeAssignmentTypes as(String alias) {
        return new RefereeAssignmentTypes(alias, this);
    }

    /**
     * Rename this table
     */
    public RefereeAssignmentTypes rename(String name) {
        return new RefereeAssignmentTypes(name, null);
    }
}
