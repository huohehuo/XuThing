package com.fangzuo.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.fangzuo.assist.Dao.WaveHouse;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "WAVE_HOUSE".
*/
public class WaveHouseDao extends AbstractDao<WaveHouse, Void> {

    public static final String TABLENAME = "WAVE_HOUSE";

    /**
     * Properties of entity WaveHouse.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property FSPID = new Property(0, String.class, "FSPID", false, "FSPID");
        public final static Property FSPGroupID = new Property(1, String.class, "FSPGroupID", false, "FSPGROUP_ID");
        public final static Property FNumber = new Property(2, String.class, "FNumber", false, "FNUMBER");
        public final static Property FName = new Property(3, String.class, "FName", false, "FNAME");
        public final static Property FFullName = new Property(4, String.class, "FFullName", false, "FFULL_NAME");
        public final static Property FLevel = new Property(5, String.class, "FLevel", false, "FLEVEL");
        public final static Property FDetail = new Property(6, String.class, "FDetail", false, "FDETAIL");
        public final static Property FParentID = new Property(7, String.class, "FParentID", false, "FPARENT_ID");
        public final static Property FClassTypeID = new Property(8, String.class, "FClassTypeID", false, "FCLASS_TYPE_ID");
        public final static Property FDefaultSPID = new Property(9, String.class, "FDefaultSPID", false, "FDEFAULT_SPID");
    }


    public WaveHouseDao(DaoConfig config) {
        super(config);
    }
    
    public WaveHouseDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"WAVE_HOUSE\" (" + //
                "\"FSPID\" TEXT," + // 0: FSPID
                "\"FSPGROUP_ID\" TEXT," + // 1: FSPGroupID
                "\"FNUMBER\" TEXT," + // 2: FNumber
                "\"FNAME\" TEXT," + // 3: FName
                "\"FFULL_NAME\" TEXT," + // 4: FFullName
                "\"FLEVEL\" TEXT," + // 5: FLevel
                "\"FDETAIL\" TEXT," + // 6: FDetail
                "\"FPARENT_ID\" TEXT," + // 7: FParentID
                "\"FCLASS_TYPE_ID\" TEXT," + // 8: FClassTypeID
                "\"FDEFAULT_SPID\" TEXT);"); // 9: FDefaultSPID
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"WAVE_HOUSE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, WaveHouse entity) {
        stmt.clearBindings();
 
        String FSPID = entity.getFSPID();
        if (FSPID != null) {
            stmt.bindString(1, FSPID);
        }
 
        String FSPGroupID = entity.getFSPGroupID();
        if (FSPGroupID != null) {
            stmt.bindString(2, FSPGroupID);
        }
 
        String FNumber = entity.getFNumber();
        if (FNumber != null) {
            stmt.bindString(3, FNumber);
        }
 
        String FName = entity.getFName();
        if (FName != null) {
            stmt.bindString(4, FName);
        }
 
        String FFullName = entity.getFFullName();
        if (FFullName != null) {
            stmt.bindString(5, FFullName);
        }
 
        String FLevel = entity.getFLevel();
        if (FLevel != null) {
            stmt.bindString(6, FLevel);
        }
 
        String FDetail = entity.getFDetail();
        if (FDetail != null) {
            stmt.bindString(7, FDetail);
        }
 
        String FParentID = entity.getFParentID();
        if (FParentID != null) {
            stmt.bindString(8, FParentID);
        }
 
        String FClassTypeID = entity.getFClassTypeID();
        if (FClassTypeID != null) {
            stmt.bindString(9, FClassTypeID);
        }
 
        String FDefaultSPID = entity.getFDefaultSPID();
        if (FDefaultSPID != null) {
            stmt.bindString(10, FDefaultSPID);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, WaveHouse entity) {
        stmt.clearBindings();
 
        String FSPID = entity.getFSPID();
        if (FSPID != null) {
            stmt.bindString(1, FSPID);
        }
 
        String FSPGroupID = entity.getFSPGroupID();
        if (FSPGroupID != null) {
            stmt.bindString(2, FSPGroupID);
        }
 
        String FNumber = entity.getFNumber();
        if (FNumber != null) {
            stmt.bindString(3, FNumber);
        }
 
        String FName = entity.getFName();
        if (FName != null) {
            stmt.bindString(4, FName);
        }
 
        String FFullName = entity.getFFullName();
        if (FFullName != null) {
            stmt.bindString(5, FFullName);
        }
 
        String FLevel = entity.getFLevel();
        if (FLevel != null) {
            stmt.bindString(6, FLevel);
        }
 
        String FDetail = entity.getFDetail();
        if (FDetail != null) {
            stmt.bindString(7, FDetail);
        }
 
        String FParentID = entity.getFParentID();
        if (FParentID != null) {
            stmt.bindString(8, FParentID);
        }
 
        String FClassTypeID = entity.getFClassTypeID();
        if (FClassTypeID != null) {
            stmt.bindString(9, FClassTypeID);
        }
 
        String FDefaultSPID = entity.getFDefaultSPID();
        if (FDefaultSPID != null) {
            stmt.bindString(10, FDefaultSPID);
        }
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public WaveHouse readEntity(Cursor cursor, int offset) {
        WaveHouse entity = new WaveHouse( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // FSPID
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // FSPGroupID
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // FNumber
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // FName
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // FFullName
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // FLevel
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // FDetail
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // FParentID
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // FClassTypeID
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9) // FDefaultSPID
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, WaveHouse entity, int offset) {
        entity.setFSPID(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setFSPGroupID(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setFNumber(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setFName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setFFullName(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setFLevel(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setFDetail(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setFParentID(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setFClassTypeID(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setFDefaultSPID(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(WaveHouse entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(WaveHouse entity) {
        return null;
    }

    @Override
    public boolean hasKey(WaveHouse entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
