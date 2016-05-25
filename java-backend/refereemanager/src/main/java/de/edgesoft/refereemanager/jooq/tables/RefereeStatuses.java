/**
 * This class is generated by jOOQ
 */
package de.edgesoft.refereemanager.jooq.tables;


import de.edgesoft.refereemanager.jooq.Keys;
import de.edgesoft.refereemanager.jooq.Refereemanager;
import de.edgesoft.refereemanager.jooq.tables.records.RefereeStatusesRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;
import org.jooq.types.UInteger;


/**
 * Referee's status.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.0"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RefereeStatuses extends TableImpl<RefereeStatusesRecord> {

    private static final long serialVersionUID = 817941911;

    /**
     * The reference instance of <code>refereemanager.rfrmgr_referee_statuses</code>
     */
    public static final RefereeStatuses REFEREE_STATUSES = new RefereeStatuses();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<RefereeStatusesRecord> getRecordType() {
        return RefereeStatusesRecord.class;
    }

    /**
     * The column <code>refereemanager.rfrmgr_referee_statuses.id</code>.
     */
    public final TableField<RefereeStatusesRecord, UInteger> ID = createField("id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>refereemanager.rfrmgr_referee_statuses.referee_id</code>.
     */
    public final TableField<RefereeStatusesRecord, UInteger> REFEREE_ID = createField("referee_id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>refereemanager.rfrmgr_referee_statuses.status_type_id</code>.
     */
    public final TableField<RefereeStatusesRecord, UInteger> STATUS_TYPE_ID = createField("status_type_id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>refereemanager.rfrmgr_referee_statuses.season_id</code>.
     */
    public final TableField<RefereeStatusesRecord, UInteger> SEASON_ID = createField("season_id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>refereemanager.rfrmgr_referee_statuses.remark</code>.
     */
    public final TableField<RefereeStatusesRecord, String> REMARK = createField("remark", org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * Create a <code>refereemanager.rfrmgr_referee_statuses</code> table reference
     */
    public RefereeStatuses() {
        this("rfrmgr_referee_statuses", null);
    }

    /**
     * Create an aliased <code>refereemanager.rfrmgr_referee_statuses</code> table reference
     */
    public RefereeStatuses(String alias) {
        this(alias, REFEREE_STATUSES);
    }

    private RefereeStatuses(String alias, Table<RefereeStatusesRecord> aliased) {
        this(alias, aliased, null);
    }

    private RefereeStatuses(String alias, Table<RefereeStatusesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "Referee's status.");
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
    public Identity<RefereeStatusesRecord, UInteger> getIdentity() {
        return Keys.IDENTITY_REFEREE_STATUSES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<RefereeStatusesRecord> getPrimaryKey() {
        return Keys.KEY_RFRMGR_REFEREE_STATUSES_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<RefereeStatusesRecord>> getKeys() {
        return Arrays.<UniqueKey<RefereeStatusesRecord>>asList(Keys.KEY_RFRMGR_REFEREE_STATUSES_PRIMARY, Keys.KEY_RFRMGR_REFEREE_STATUSES_ID_UNIQUE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<RefereeStatusesRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<RefereeStatusesRecord, ?>>asList(Keys.FK_RFRMGR_REFEREE_STATUS_RFRMGR_REFEREES1, Keys.FK_RFRMGR_REFEREE_STATUS_RFRMGR_STATUS_TYPES1, Keys.FK_RFRMGR_REFEREE_STATUS_RFRMGR_SEASONS1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RefereeStatuses as(String alias) {
        return new RefereeStatuses(alias, this);
    }

    /**
     * Rename this table
     */
    public RefereeStatuses rename(String name) {
        return new RefereeStatuses(name, null);
    }
}