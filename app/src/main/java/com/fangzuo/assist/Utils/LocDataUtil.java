package com.fangzuo.assist.Utils;

import com.fangzuo.assist.Activity.Crash.App;
import com.fangzuo.assist.Dao.NoteBean;
import com.fangzuo.greendao.gen.AddrBeanDao;
import com.fangzuo.greendao.gen.BuyBeanDao;
import com.fangzuo.greendao.gen.DaoSession;
import com.fangzuo.greendao.gen.NoteBeanDao;

public class LocDataUtil {

    //删除盘点方案
    public static void deletePd(String fid){
        if (null==fid || "".equals(fid))return;
        DaoSession daoSession = GreenDaoManager.getmInstance(App.getContext()).getDaoSession();
        String sql="DELETE  FROM PDSUB WHERE FID = ?";
        daoSession.getDatabase().execSQL(sql,new String[]{fid});
    }
    public static boolean checkBuyBean(String string){
        DaoSession daoSession = GreenDaoManager.getmInstance(App.getContext()).getDaoSession();
        BuyBeanDao dao = daoSession.getBuyBeanDao();
        return dao.queryBuilder().where(BuyBeanDao.Properties.FName.eq(string)).count()>0;
    }
    public static boolean checkAddrBean(String string){
        DaoSession daoSession = GreenDaoManager.getmInstance(App.getContext()).getDaoSession();
        AddrBeanDao dao = daoSession.getAddrBeanDao();
        return dao.queryBuilder().where(AddrBeanDao.Properties.FName.eq(string)).count()>0;
    }
    public static long checkNoteBean(String string){
        DaoSession daoSession = GreenDaoManager.getmInstance(App.getContext()).getDaoSession();
        NoteBeanDao dao = daoSession.getNoteBeanDao();
        return dao.queryBuilder().where(NoteBeanDao.Properties.NBuyName.eq(string)).count();
    }
    public static long checkNoteBean4Addr(String string){
        DaoSession daoSession = GreenDaoManager.getmInstance(App.getContext()).getDaoSession();
        NoteBeanDao dao = daoSession.getNoteBeanDao();
        return dao.queryBuilder().where(NoteBeanDao.Properties.NAddrName.eq(string)).count();
    }


}
