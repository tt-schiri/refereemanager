/**
 * This class is generated by jOOQ
 */
package de.edgesoft.refereemanager.jooq.tables.records;


import de.edgesoft.refereemanager.jooq.tables.TeamSeasons;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.UInteger;


/**
 * Association of teams in a specified season.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.0"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TeamSeasonsRecord extends UpdatableRecordImpl<TeamSeasonsRecord> implements Record4<UInteger, UInteger, UInteger, UInteger> {

    private static final long serialVersionUID = -784444908;

    /**
     * Setter for <code>refereemanager.rfrmgr_team_seasons.id</code>.
     */
    public void setId(UInteger value) {
        set(0, value);
    }

    /**
     * Getter for <code>refereemanager.rfrmgr_team_seasons.id</code>.
     */
    public UInteger getId() {
        return (UInteger) get(0);
    }

    /**
     * Setter for <code>refereemanager.rfrmgr_team_seasons.team_id</code>.
     */
    public void setTeamId(UInteger value) {
        set(1, value);
    }

    /**
     * Getter for <code>refereemanager.rfrmgr_team_seasons.team_id</code>.
     */
    public UInteger getTeamId() {
        return (UInteger) get(1);
    }

    /**
     * Setter for <code>refereemanager.rfrmgr_team_seasons.season_id</code>.
     */
    public void setSeasonId(UInteger value) {
        set(2, value);
    }

    /**
     * Getter for <code>refereemanager.rfrmgr_team_seasons.season_id</code>.
     */
    public UInteger getSeasonId() {
        return (UInteger) get(2);
    }

    /**
     * Setter for <code>refereemanager.rfrmgr_team_seasons.league_id</code>.
     */
    public void setLeagueId(UInteger value) {
        set(3, value);
    }

    /**
     * Getter for <code>refereemanager.rfrmgr_team_seasons.league_id</code>.
     */
    public UInteger getLeagueId() {
        return (UInteger) get(3);
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
    public Row4<UInteger, UInteger, UInteger, UInteger> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row4<UInteger, UInteger, UInteger, UInteger> valuesRow() {
        return (Row4) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field1() {
        return TeamSeasons.TEAM_SEASONS.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field2() {
        return TeamSeasons.TEAM_SEASONS.TEAM_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field3() {
        return TeamSeasons.TEAM_SEASONS.SEASON_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field4() {
        return TeamSeasons.TEAM_SEASONS.LEAGUE_ID;
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
        return getTeamId();
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
        return getLeagueId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TeamSeasonsRecord value1(UInteger value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TeamSeasonsRecord value2(UInteger value) {
        setTeamId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TeamSeasonsRecord value3(UInteger value) {
        setSeasonId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TeamSeasonsRecord value4(UInteger value) {
        setLeagueId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TeamSeasonsRecord values(UInteger value1, UInteger value2, UInteger value3, UInteger value4) {
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
     * Create a detached TeamSeasonsRecord
     */
    public TeamSeasonsRecord() {
        super(TeamSeasons.TEAM_SEASONS);
    }

    /**
     * Create a detached, initialised TeamSeasonsRecord
     */
    public TeamSeasonsRecord(UInteger id, UInteger teamId, UInteger seasonId, UInteger leagueId) {
        super(TeamSeasons.TEAM_SEASONS);

        set(0, id);
        set(1, teamId);
        set(2, seasonId);
        set(3, leagueId);
    }
}
