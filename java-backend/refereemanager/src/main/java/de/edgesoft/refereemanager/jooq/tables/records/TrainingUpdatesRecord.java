/**
 * This class is generated by jOOQ
 */
package de.edgesoft.refereemanager.jooq.tables.records;


import de.edgesoft.refereemanager.jooq.tables.TrainingUpdates;

import java.sql.Date;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.UInteger;


/**
 * Updates of the training levels.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.0"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TrainingUpdatesRecord extends UpdatableRecordImpl<TrainingUpdatesRecord> implements Record3<UInteger, UInteger, Date> {

    private static final long serialVersionUID = 991511571;

    /**
     * Setter for <code>refereemanager.rfrmgr_training_updates.id</code>.
     */
    public void setId(UInteger value) {
        set(0, value);
    }

    /**
     * Getter for <code>refereemanager.rfrmgr_training_updates.id</code>.
     */
    public UInteger getId() {
        return (UInteger) get(0);
    }

    /**
     * Setter for <code>refereemanager.rfrmgr_training_updates.training_level_id</code>.
     */
    public void setTrainingLevelId(UInteger value) {
        set(1, value);
    }

    /**
     * Getter for <code>refereemanager.rfrmgr_training_updates.training_level_id</code>.
     */
    public UInteger getTrainingLevelId() {
        return (UInteger) get(1);
    }

    /**
     * Setter for <code>refereemanager.rfrmgr_training_updates.update</code>.
     */
    public void setUpdate(Date value) {
        set(2, value);
    }

    /**
     * Getter for <code>refereemanager.rfrmgr_training_updates.update</code>.
     */
    public Date getUpdate() {
        return (Date) get(2);
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
    // Record3 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row3<UInteger, UInteger, Date> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row3<UInteger, UInteger, Date> valuesRow() {
        return (Row3) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field1() {
        return TrainingUpdates.TRAINING_UPDATES.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field2() {
        return TrainingUpdates.TRAINING_UPDATES.TRAINING_LEVEL_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Date> field3() {
        return TrainingUpdates.TRAINING_UPDATES.UPDATE;
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
        return getTrainingLevelId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date value3() {
        return getUpdate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TrainingUpdatesRecord value1(UInteger value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TrainingUpdatesRecord value2(UInteger value) {
        setTrainingLevelId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TrainingUpdatesRecord value3(Date value) {
        setUpdate(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TrainingUpdatesRecord values(UInteger value1, UInteger value2, Date value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached TrainingUpdatesRecord
     */
    public TrainingUpdatesRecord() {
        super(TrainingUpdates.TRAINING_UPDATES);
    }

    /**
     * Create a detached, initialised TrainingUpdatesRecord
     */
    public TrainingUpdatesRecord(UInteger id, UInteger trainingLevelId, Date update) {
        super(TrainingUpdates.TRAINING_UPDATES);

        set(0, id);
        set(1, trainingLevelId);
        set(2, update);
    }
}