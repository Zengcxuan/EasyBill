package finalhomework.tcl.com.finalhomework.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import finalhomework.tcl.com.finalhomework.pojo.BPay;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "BPAY".
*/
public class BPayDao extends AbstractDao<BPay, Long> {

    public static final String TABLENAME = "BPAY";

    /**
     * Properties of entity BPay.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property PayName = new Property(1, String.class, "payName", false, "PAY_NAME");
        public final static Property PayImg = new Property(2, String.class, "payImg", false, "PAY_IMG");
        public final static Property Income = new Property(3, float.class, "income", false, "INCOME");
        public final static Property Outcome = new Property(4, float.class, "outcome", false, "OUTCOME");
    }


    public BPayDao(DaoConfig config) {
        super(config);
    }
    
    public BPayDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"BPAY\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"PAY_NAME\" TEXT," + // 1: payName
                "\"PAY_IMG\" TEXT," + // 2: payImg
                "\"INCOME\" REAL NOT NULL ," + // 3: income
                "\"OUTCOME\" REAL NOT NULL );"); // 4: outcome
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"BPAY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, BPay entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String payName = entity.getPayName();
        if (payName != null) {
            stmt.bindString(2, payName);
        }
 
        String payImg = entity.getPayImg();
        if (payImg != null) {
            stmt.bindString(3, payImg);
        }
        stmt.bindDouble(4, entity.getIncome());
        stmt.bindDouble(5, entity.getOutcome());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, BPay entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String payName = entity.getPayName();
        if (payName != null) {
            stmt.bindString(2, payName);
        }
 
        String payImg = entity.getPayImg();
        if (payImg != null) {
            stmt.bindString(3, payImg);
        }
        stmt.bindDouble(4, entity.getIncome());
        stmt.bindDouble(5, entity.getOutcome());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public BPay readEntity(Cursor cursor, int offset) {
        BPay entity = new BPay( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // payName
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // payImg
            cursor.getFloat(offset + 3), // income
            cursor.getFloat(offset + 4) // outcome
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, BPay entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setPayName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setPayImg(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setIncome(cursor.getFloat(offset + 3));
        entity.setOutcome(cursor.getFloat(offset + 4));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(BPay entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(BPay entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(BPay entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
