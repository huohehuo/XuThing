package com.fangzuo.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.fangzuo.assist.Dao.InStoreType;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "IN_STORE_TYPE".
*/
public class InStoreTypeDao extends AbstractDao<InStoreType, Void> {

    public static final String TABLENAME = "IN_STORE_TYPE";

    /**
     * Properties of entity InStoreType.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property FID = new Property(0, String.class, "FID", false, "FID");
        public final static Property FName = new Property(1, String.class, "FName", false, "FNAME");
    }


    public InStoreTypeDao(DaoConfig config) {
        super(config);
    }
    
    public InStoreTypeDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"IN_STORE_TYPE\" (" + //
                "\"FID\" TEXT," + // 0: FID
                "\"FNAME\" TEXT);"); // 1: FName
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"IN_STORE_TYPE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, InStoreType entity) {
        stmt.clearBindings();
 
        String FID = entity.getFID();
        if (FID != null) {
            stmt.bindString(1, FID);
        }
 
        String FName = entity.getFName();
        if (FName != null) {
            stmt.bindString(2, FName);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, InStoreType entity) {
        stmt.clearBindings();
 
        String FID = entity.getFID();
        if (FID != null) {
            stmt.bindString(1, FID);
        }
 
        String FName = entity.getFName();
        if (FName != null) {
            stmt.bindString(2, FName);
        }
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public InStoreType readEntity(Cursor cursor, int offset) {
        InStoreType entity = new InStoreType( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // FID
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1) // FName
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, InStoreType entity, int offset) {
        entity.setFID(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setFName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(InStoreType entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(InStoreType entity) {
        return null;
    }

    @Override
    public boolean hasKey(InStoreType entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
