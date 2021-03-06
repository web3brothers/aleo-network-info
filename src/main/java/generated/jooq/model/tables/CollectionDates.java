/*
 * This file is generated by jOOQ.
 */
package generated.jooq.model.tables;


import generated.jooq.model.Public;
import generated.jooq.model.tables.records.CollectionDatesRecord;

import java.time.OffsetDateTime;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row1;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CollectionDates extends TableImpl<CollectionDatesRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.collection_dates</code>
     */
    public static final CollectionDates COLLECTION_DATES = new CollectionDates();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CollectionDatesRecord> getRecordType() {
        return CollectionDatesRecord.class;
    }

    /**
     * The column <code>public.collection_dates.collection_date</code>.
     */
    public final TableField<CollectionDatesRecord, OffsetDateTime> COLLECTION_DATE = createField(DSL.name("collection_date"), SQLDataType.TIMESTAMPWITHTIMEZONE(6), this, "");

    private CollectionDates(Name alias, Table<CollectionDatesRecord> aliased) {
        this(alias, aliased, null);
    }

    private CollectionDates(Name alias, Table<CollectionDatesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.collection_dates</code> table reference
     */
    public CollectionDates(String alias) {
        this(DSL.name(alias), COLLECTION_DATES);
    }

    /**
     * Create an aliased <code>public.collection_dates</code> table reference
     */
    public CollectionDates(Name alias) {
        this(alias, COLLECTION_DATES);
    }

    /**
     * Create a <code>public.collection_dates</code> table reference
     */
    public CollectionDates() {
        this(DSL.name("collection_dates"), null);
    }

    public <O extends Record> CollectionDates(Table<O> child, ForeignKey<O, CollectionDatesRecord> key) {
        super(child, key, COLLECTION_DATES);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public CollectionDates as(String alias) {
        return new CollectionDates(DSL.name(alias), this);
    }

    @Override
    public CollectionDates as(Name alias) {
        return new CollectionDates(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public CollectionDates rename(String name) {
        return new CollectionDates(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public CollectionDates rename(Name name) {
        return new CollectionDates(name, null);
    }

    // -------------------------------------------------------------------------
    // Row1 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row1<OffsetDateTime> fieldsRow() {
        return (Row1) super.fieldsRow();
    }
}
