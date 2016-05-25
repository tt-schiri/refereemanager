/**
 * This class is generated by jOOQ
 */
package de.edgesoft.refereemanager.jooq.tables;


import de.edgesoft.refereemanager.jooq.Keys;
import de.edgesoft.refereemanager.jooq.Refereemanager;
import de.edgesoft.refereemanager.jooq.tables.records.WishTypesRecord;

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
 * Wish types of a referee: prefer, avoid.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.0"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class WishTypes extends TableImpl<WishTypesRecord> {

    private static final long serialVersionUID = 542449059;

    /**
     * The reference instance of <code>refereemanager.rfrmgr_wish_types</code>
     */
    public static final WishTypes WISH_TYPES = new WishTypes();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<WishTypesRecord> getRecordType() {
        return WishTypesRecord.class;
    }

    /**
     * The column <code>refereemanager.rfrmgr_wish_types.id</code>.
     */
    public final TableField<WishTypesRecord, UInteger> ID = createField("id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>refereemanager.rfrmgr_wish_types.sid</code>.
     */
    public final TableField<WishTypesRecord, String> SID = createField("sid", org.jooq.impl.SQLDataType.VARCHAR.length(20).nullable(false), this, "");

    /**
     * The column <code>refereemanager.rfrmgr_wish_types.title</code>.
     */
    public final TableField<WishTypesRecord, String> TITLE = createField("title", org.jooq.impl.SQLDataType.VARCHAR.length(100).nullable(false), this, "");

    /**
     * The column <code>refereemanager.rfrmgr_wish_types.remark</code>.
     */
    public final TableField<WishTypesRecord, String> REMARK = createField("remark", org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * Create a <code>refereemanager.rfrmgr_wish_types</code> table reference
     */
    public WishTypes() {
        this("rfrmgr_wish_types", null);
    }

    /**
     * Create an aliased <code>refereemanager.rfrmgr_wish_types</code> table reference
     */
    public WishTypes(String alias) {
        this(alias, WISH_TYPES);
    }

    private WishTypes(String alias, Table<WishTypesRecord> aliased) {
        this(alias, aliased, null);
    }

    private WishTypes(String alias, Table<WishTypesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "Wish types of a referee: prefer, avoid.");
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
    public Identity<WishTypesRecord, UInteger> getIdentity() {
        return Keys.IDENTITY_WISH_TYPES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<WishTypesRecord> getPrimaryKey() {
        return Keys.KEY_RFRMGR_WISH_TYPES_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<WishTypesRecord>> getKeys() {
        return Arrays.<UniqueKey<WishTypesRecord>>asList(Keys.KEY_RFRMGR_WISH_TYPES_PRIMARY, Keys.KEY_RFRMGR_WISH_TYPES_ID_UNIQUE, Keys.KEY_RFRMGR_WISH_TYPES_SID_UNIQUE, Keys.KEY_RFRMGR_WISH_TYPES_TITLE_UNIQUE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WishTypes as(String alias) {
        return new WishTypes(alias, this);
    }

    /**
     * Rename this table
     */
    public WishTypes rename(String name) {
        return new WishTypes(name, null);
    }
}