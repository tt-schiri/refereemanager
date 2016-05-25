/**
 * This class is generated by jOOQ
 */
package de.edgesoft.refereemanager.jooq.tables.records;


import de.edgesoft.refereemanager.jooq.tables.LeagueTypes;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.UInteger;


/**
 * All league types.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.0"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class LeagueTypesRecord extends UpdatableRecordImpl<LeagueTypesRecord> implements Record4<UInteger, String, UInteger, String> {

    private static final long serialVersionUID = 1559155060;

    /**
     * Setter for <code>refereemanager.rfrmgr_league_types.id</code>.
     */
    public void setId(UInteger value) {
        set(0, value);
    }

    /**
     * Getter for <code>refereemanager.rfrmgr_league_types.id</code>.
     */
    public UInteger getId() {
        return (UInteger) get(0);
    }

    /**
     * Setter for <code>refereemanager.rfrmgr_league_types.title</code>.
     */
    public void setTitle(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>refereemanager.rfrmgr_league_types.title</code>.
     */
    public String getTitle() {
        return (String) get(1);
    }

    /**
     * Setter for <code>refereemanager.rfrmgr_league_types.sex_type_id</code>.
     */
    public void setSexTypeId(UInteger value) {
        set(2, value);
    }

    /**
     * Getter for <code>refereemanager.rfrmgr_league_types.sex_type_id</code>.
     */
    public UInteger getSexTypeId() {
        return (UInteger) get(2);
    }

    /**
     * Setter for <code>refereemanager.rfrmgr_league_types.remark</code>.
     */
    public void setRemark(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>refereemanager.rfrmgr_league_types.remark</code>.
     */
    public String getRemark() {
        return (String) get(3);
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
    // Record4 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row4<UInteger, String, UInteger, String> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row4<UInteger, String, UInteger, String> valuesRow() {
        return (Row4) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field1() {
        return LeagueTypes.LEAGUE_TYPES.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return LeagueTypes.LEAGUE_TYPES.TITLE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field3() {
        return LeagueTypes.LEAGUE_TYPES.SEX_TYPE_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return LeagueTypes.LEAGUE_TYPES.REMARK;
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
    public UInteger value3() {
        return getSexTypeId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getRemark();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LeagueTypesRecord value1(UInteger value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LeagueTypesRecord value2(String value) {
        setTitle(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LeagueTypesRecord value3(UInteger value) {
        setSexTypeId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LeagueTypesRecord value4(String value) {
        setRemark(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LeagueTypesRecord values(UInteger value1, String value2, UInteger value3, String value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached LeagueTypesRecord
     */
    public LeagueTypesRecord() {
        super(LeagueTypes.LEAGUE_TYPES);
    }

    /**
     * Create a detached, initialised LeagueTypesRecord
     */
    public LeagueTypesRecord(UInteger id, String title, UInteger sexTypeId, String remark) {
        super(LeagueTypes.LEAGUE_TYPES);

        set(0, id);
        set(1, title);
        set(2, sexTypeId);
        set(3, remark);
    }
}