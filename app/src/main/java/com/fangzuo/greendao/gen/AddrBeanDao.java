package com.fangzuo.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.fangzuo.assist.Dao.AddrBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ADDR_BEAN".
*/
public class AddrBeanDao extends AbstractDao<AddrBean, Long> {

    public static final String TABLENAME = "ADDR_BEAN";

    /**
     * Properties of entity AddrBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property FID = new Property(1, String.class, "FID", false, "FID");
        public final static Property FName = new Property(2, String.class, "FName", false, "FNAME");
        public final static Property FCreateData = new Property(3, String.class, "FCreateData", false, "FCREATE_DATA");
        public final static Property FIsCloud = new Property(4, String.class, "FIsCloud", false, "FIS_CLOUD");
        public final static Property FUseNum = new Property(5, String.class, "FUseNum", false, "FUSE_NUM");
        public final static Property FMapID = new Property(6, String.class, "FMapID", false, "FMAP_ID");
    }


    public AddrBeanDao(DaoConfig config) {
        super(config);
    }
    
    public AddrBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ADDR_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"FID\" TEXT," + // 1: FID
                "\"FNAME\" TEXT," + // 2: FName
                "\"FCREATE_DATA\" TEXT," + // 3: FCreateData
                "\"FIS_CLOUD\" TEXT," + // 4: FIsCloud
                "\"FUSE_NUM\" TEXT," + // 5: FUseNum
                "\"FMAP_ID\" TEXT);"); // 6: FMapID
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ADDR_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, AddrBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String FID = entity.getFID();
        if (FID != null) {
            stmt.bindString(2, FID);
        }
 
        String FName = entity.getFName();
        if (FName != null) {
            stmt.bindString(3, FName);
        }
 
        String FCreateData = entity.getFCreateData();
        if (FCreateData != null) {
            stmt.bindString(4, FCreateData);
        }
 
        String FIsCloud = entity.getFIsCloud();
        if (FIsCloud != null) {
            stmt.bindString(5, FIsCloud);
        }
 
        String FUseNum = entity.getFUseNum();
        if (FUseNum != null) {
            stmt.bindString(6, FUseNum);
        }
 
        String FMapID = entity.getFMapID();
        if (FMapID != null) {
            stmt.bindString(7, FMapID);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, AddrBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String FID = entity.getFID();
        if (FID != null) {
            stmt.bindString(2, FID);
        }
 
        String FName = entity.getFName();
        if (FName != null) {
            stmt.bindString(3, FName);
        }
 
        String FCreateData = entity.getFCreateData();
        if (FCreateData != null) {
            stmt.bindString(4, FCreateData);
        }
 
        String FIsCloud = entity.getFIsCloud();
        if (FIsCloud != null) {
            stmt.bindString(5, FIsCloud);
        }
 
        String FUseNum = entity.getFUseNum();
        if (FUseNum != null) {
            stmt.bindString(6, FUseNum);
        }
 
        String FMapID = entity.getFMapID();
        if (FMapID != null) {
            stmt.bindString(7, FMapID);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public AddrBean readEntity(Cursor cursor, int offset) {
        AddrBean entity = new AddrBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // FID
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // FName
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // FCreateData
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // FIsCloud
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // FUseNum
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6) // FMapID
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, AddrBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setFID(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setFName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setFCreateData(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setFIsCloud(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setFUseNum(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setFMapID(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(AddrBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(AddrBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(AddrBean entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
