/*
 * This file is generated by jOOQ.
 */
package generated.jooq.model.tables.records;


import generated.jooq.model.tables.CollectionDates;

import java.time.OffsetDateTime;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Row1;
import org.jooq.impl.TableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CollectionDatesRecord extends TableRecordImpl<CollectionDatesRecord> implements Record1<OffsetDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.collection_dates.collection_date</code>.
     */
    public CollectionDatesRecord setCollectionDate(OffsetDateTime value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.collection_dates.collection_date</code>.
     */
    public OffsetDateTime getCollectionDate() {
        return (OffsetDateTime) get(0);
    }

    // -------------------------------------------------------------------------
    // Record1 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row1<OffsetDateTime> fieldsRow() {
        return (Row1) super.fieldsRow();
    }

    @Override
    public Row1<OffsetDateTime> valuesRow() {
        return (Row1) super.valuesRow();
    }

    @Override
    public Field<OffsetDateTime> field1() {
        return CollectionDates.COLLECTION_DATES.COLLECTION_DATE;
    }

    @Override
    public OffsetDateTime component1() {
        return getCollectionDate();
    }

    @Override
    public OffsetDateTime value1() {
        return getCollectionDate();
    }

    @Override
    public CollectionDatesRecord value1(OffsetDateTime value) {
        setCollectionDate(value);
        return this;
    }

    @Override
    public CollectionDatesRecord values(OffsetDateTime value1) {
        value1(value1);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CollectionDatesRecord
     */
    public CollectionDatesRecord() {
        super(CollectionDates.COLLECTION_DATES);
    }

    /**
     * Create a detached, initialised CollectionDatesRecord
     */
    public CollectionDatesRecord(OffsetDateTime collectionDate) {
        super(CollectionDates.COLLECTION_DATES);

        setCollectionDate(collectionDate);
    }
}
