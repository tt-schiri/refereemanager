/**
 * This class is generated by jOOQ
 */
package de.edgesoft.refereemanager.jooq.tables.records;


import de.edgesoft.refereemanager.jooq.tables.Leagues;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.UInteger;


/**
 * All leagues.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.0"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class LeaguesRecord extends UpdatableRecordImpl<LeaguesRecord> implements Record5<UInteger, String, String, UInteger, String> {

    private static final long serialVersionUID = -641910364;

    /**
     * Setter for <code>refereemanager.rfrmgr_leagues.id</code>.
     */
    public void setId(UInteger value) {
        set(0, value);
    }

    /**
     * Getter for <code>refereemanager.rfrmgr_leagues.id</code>.
     */
    public UInteger getId() {
        return (UInteger) get(0);
    }

    /**
     * Setter for <code>refereemanager.rfrmgr_leagues.title</code>.
     */
    public void setTitle(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>refereemanager.rfrmgr_leagues.title</code>.
     */
    public String getTitle() {
        return (String) get(1);
    }

    /**
     * Setter for <code>refereemanager.rfrmgr_leagues.abbreviation</code>.
     */
    public void setAbbreviation(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>refereemanager.rfrmgr_leagues.abbreviation</code>.
     */
    public String getAbbreviation() {
        return (String) get(2);
    }

    /**
     * Setter for <code>refereemanager.rfrmgr_leagues.league_type_id</code>.
     */
    public void setLeagueTypeId(UInteger value) {
        set(3, value);
    }

    /**
     * Getter for <code>refereemanager.rfrmgr_leagues.league_type_id</code>.
     */
    public UInteger getLeagueTypeId() {
        return (UInteger) get(3);
    }

    /**
     * Setter for <code>refereemanager.rfrmgr_leagues.remark</code>.
     */
    public void setRemark(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>refereemanager.rfrmgr_leagues.remark</code>.
     */
    public String getRemark() {
        return (String) get(4);
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
    public Row5<UInteger, String, String, UInteger, String> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<UInteger, String, String, UInteger, String> valuesRow() {
        return (Row5) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field1() {
        return Leagues.LEAGUES.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Leagues.LEAGUES.TITLE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return Leagues.LEAGUES.ABBREVIATION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field4() {
        return Leagues.LEAGUES.LEAGUE_TYPE_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return Leagues.LEAGUES.REMARK;
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
    public String value2() {
        return getTitle();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getAbbreviation();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger value4() {
        return getLeagueTypeId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getRemark();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LeaguesRecord value1(UInteger value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LeaguesRecord value2(String value) {
        setTitle(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LeaguesRecord value3(String value) {
        setAbbreviation(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LeaguesRecord value4(UInteger value) {
        setLeagueTypeId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LeaguesRecord value5(String value) {
        setRemark(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LeaguesRecord values(UInteger value1, String value2, String value3, UInteger value4, String value5) {
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
     * Create a detached LeaguesRecord
     */
    public LeaguesRecord() {
        super(Leagues.LEAGUES);
    }

    /**
     * Create a detached, initialised LeaguesRecord
     */
    public LeaguesRecord(UInteger id, String title, String abbreviation, UInteger leagueTypeId, String remark) {
        super(Leagues.LEAGUES);

        set(0, id);
        set(1, title);
        set(2, abbreviation);
        set(3, leagueTypeId);
        set(4, remark);
    }
}