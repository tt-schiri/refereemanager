/**
 * This class is generated by jOOQ
 */
package de.edgesoft.refereemanager.jooq.tables;


import de.edgesoft.refereemanager.jooq.Keys;
import de.edgesoft.refereemanager.jooq.Refereemanager;
import de.edgesoft.refereemanager.jooq.tables.records.RefereeAssignmentsRecord;

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
 * Referee's assignments with their role.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.0"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RefereeAssignments extends TableImpl<RefereeAssignmentsRecord> {

    private static final long serialVersionUID = -1107550767;

    /**
     * The reference instance of <code>refereemanager.rfrmgr_referee_assignments</code>
     */
    public static final RefereeAssignments REFEREE_ASSIGNMENTS = new RefereeAssignments();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<RefereeAssignmentsRecord> getRecordType() {
        return RefereeAssignmentsRecord.class;
    }

    /**
     * The column <code>refereemanager.rfrmgr_referee_assignments.id</code>.
     */
    public final TableField<RefereeAssignmentsRecord, UInteger> ID = createField("id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>refereemanager.rfrmgr_referee_assignments.assignment_id</code>.
     */
    public final TableField<RefereeAssignmentsRecord, UInteger> ASSIGNMENT_ID = createField("assignment_id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>refereemanager.rfrmgr_referee_assignments.referee_assignment_type_id</code>.
     */
    public final TableField<RefereeAssignmentsRecord, UInteger> REFEREE_ASSIGNMENT_TYPE_ID = createField("referee_assignment_type_id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>refereemanager.rfrmgr_referee_assignments.referee_id</code>.
     */
    public final TableField<RefereeAssignmentsRecord, UInteger> REFEREE_ID = createField("referee_id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>refereemanager.rfrmgr_referee_assignments.referee_assignment_status_type_id</code>.
     */
    public final TableField<RefereeAssignmentsRecord, UInteger> REFEREE_ASSIGNMENT_STATUS_TYPE_ID = createField("referee_assignment_status_type_id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED, this, "");

    /**
     * The column <code>refereemanager.rfrmgr_referee_assignments.referee_assignment_remark_type_id</code>.
     */
    public final TableField<RefereeAssignmentsRecord, UInteger> REFEREE_ASSIGNMENT_REMARK_TYPE_ID = createField("referee_assignment_remark_type_id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED, this, "");

    /**
     * The column <code>refereemanager.rfrmgr_referee_assignments.remark</code>.
     */
    public final TableField<RefereeAssignmentsRecord, String> REMARK = createField("remark", org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * Create a <code>refereemanager.rfrmgr_referee_assignments</code> table reference
     */
    public RefereeAssignments() {
        this("rfrmgr_referee_assignments", null);
    }

    /**
     * Create an aliased <code>refereemanager.rfrmgr_referee_assignments</code> table reference
     */
    public RefereeAssignments(String alias) {
        this(alias, REFEREE_ASSIGNMENTS);
    }

    private RefereeAssignments(String alias, Table<RefereeAssignmentsRecord> aliased) {
        this(alias, aliased, null);
    }

    private RefereeAssignments(String alias, Table<RefereeAssignmentsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "Referee's assignments with their role.");
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
    public Identity<RefereeAssignmentsRecord, UInteger> getIdentity() {
        return Keys.IDENTITY_REFEREE_ASSIGNMENTS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<RefereeAssignmentsRecord> getPrimaryKey() {
        return Keys.KEY_RFRMGR_REFEREE_ASSIGNMENTS_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<RefereeAssignmentsRecord>> getKeys() {
        return Arrays.<UniqueKey<RefereeAssignmentsRecord>>asList(Keys.KEY_RFRMGR_REFEREE_ASSIGNMENTS_PRIMARY, Keys.KEY_RFRMGR_REFEREE_ASSIGNMENTS_ID_UNIQUE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<RefereeAssignmentsRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<RefereeAssignmentsRecord, ?>>asList(Keys.FK_RFRMGR_REFEREE_ASSIGNMENTS_RFRMGR_ASSIGNMENTS1, Keys.FK_RFRMGR_REFEREE_ASSIGNMENTS_RFRMGR_REFEREE_ASSIGNMENT_ROLES1, Keys.FK_RFRMGR_REFEREE_ASSIGNMENTS_RFRMGR_REFEREES1, Keys.FK_RFRMGR_REFEREE_ASSIGNMENTS_RFRMGR_ASSIGNMENT_STATUS_TYPES1, Keys.FK_RFRMGR_REFEREE_ASSIGNMENTS_RFRMGR_REFEREE_ASSIGNMENT_REMAR1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RefereeAssignments as(String alias) {
        return new RefereeAssignments(alias, this);
    }

    /**
     * Rename this table
     */
    public RefereeAssignments rename(String name) {
        return new RefereeAssignments(name, null);
    }
}