/**
 * This class is generated by jOOQ
 */
package de.edgesoft.refereemanager.jooq.tables.records;


import de.edgesoft.refereemanager.jooq.tables.LeaguePlannedReferees;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.UInteger;


/**
 * Planned numbers of referees by assignment type.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.0"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class LeaguePlannedRefereesRecord extends UpdatableRecordImpl<LeaguePlannedRefereesRecord> implements Record5<UInteger, UInteger, UInteger, UInteger, UInteger> {

    private static final long serialVersionUID = 716142227;

    /**
     * Setter for <code>refereemanager.rfrmgr_league_planned_referees.id</code>.
     */
    public void setId(UInteger value) {
        set(0, value);
    }

    /**
     * Getter for <code>refereemanager.rfrmgr_league_planned_referees.id</code>.
     */
    public UInteger getId() {
        return (UInteger) get(0);
    }

    /**
     * Setter for <code>refereemanager.rfrmgr_league_planned_referees.league_id</code>.
     */
    public void setLeagueId(UInteger value) {
        set(1, value);
    }

    /**
     * Getter for <code>refereemanager.rfrmgr_league_planned_referees.league_id</code>.
     */
    public UInteger getLeagueId() {
        return (UInteger) get(1);
    }

    /**
     * Setter for <code>refereemanager.rfrmgr_league_planned_referees.season_id</code>.
     */
    public void setSeasonId(UInteger value) {
        set(2, value);
    }

    /**
     * Getter for <code>refereemanager.rfrmgr_league_planned_referees.season_id</code>.
     */
    public UInteger getSeasonId() {
        return (UInteger) get(2);
    }

    /**
     * Setter for <code>refereemanager.rfrmgr_league_planned_referees.referee_assignment_type_id</code>.
     */
    public void setRefereeAssignmentTypeId(UInteger value) {
        set(3, value);
    }

    /**
     * Getter for <code>refereemanager.rfrmgr_league_planned_referees.referee_assignment_type_id</code>.
     */
    public UInteger getRefereeAssignmentTypeId() {
        return (UInteger) get(3);
    }

    /**
     * Setter for <code>refereemanager.rfrmgr_league_planned_referees.quantity</code>.
     */
    public void setQuantity(UInteger value) {
        set(4, value);
    }

    /**
     * Getter for <code>refereemanager.rfrmgr_league_planned_referees.quantity</code>.
     */
    public UInteger getQuantity() {
        return (UInteger) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<UInteger> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<UInteger, UInteger, UInteger, UInteger, UInteger> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<UInteger, UInteger, UInteger, UInteger, UInteger> valuesRow() {
        return (Row5) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field1() {
        return LeaguePlannedReferees.LEAGUE_PLANNED_REFEREES.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field2() {
        return LeaguePlannedReferees.LEAGUE_PLANNED_REFEREES.LEAGUE_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field3() {
        return LeaguePlannedReferees.LEAGUE_PLANNED_REFEREES.SEASON_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field4() {
        return LeaguePlannedReferees.LEAGUE_PLANNED_REFEREES.REFEREE_ASSIGNMENT_TYPE_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field5() {
        return LeaguePlannedReferees.LEAGUE_PLANNED_REFEREES.QUANTITY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger value2() {
        return getLeagueId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger value3() {
        return getSeasonId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger value4() {
        return getRefereeAssignmentTypeId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger value5() {
        return getQuantity();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LeaguePlannedRefereesRecord value1(UInteger value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LeaguePlannedRefereesRecord value2(UInteger value) {
        setLeagueId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LeaguePlannedRefereesRecord value3(UInteger value) {
        setSeasonId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LeaguePlannedRefereesRecord value4(UInteger value) {
        setRefereeAssignmentTypeId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LeaguePlannedRefereesRecord value5(UInteger value) {
        setQuantity(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LeaguePlannedRefereesRecord values(UInteger value1, UInteger value2, UInteger value3, UInteger value4, UInteger value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached LeaguePlannedRefereesRecord
     */
    public LeaguePlannedRefereesRecord() {
        super(LeaguePlannedReferees.LEAGUE_PLANNED_REFEREES);
    }

    /**
     * Create a detached, initialised LeaguePlannedRefereesRecord
     */
    public LeaguePlannedRefereesRecord(UInteger id, UInteger leagueId, UInteger seasonId, UInteger refereeAssignmentTypeId, UInteger quantity) {
        super(LeaguePlannedReferees.LEAGUE_PLANNED_REFEREES);

        set(0, id);
        set(1, leagueId);
        set(2, seasonId);
        set(3, refereeAssignmentTypeId);
        set(4, quantity);
    }
}