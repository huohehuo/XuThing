package com.lins.xuthing.gen;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.lins.xuthing.Beans.SendOrderListBean;

import com.lins.xuthing.gen.SendOrderListBeanDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig sendOrderListBeanDaoConfig;

    private final SendOrderListBeanDao sendOrderListBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        sendOrderListBeanDaoConfig = daoConfigMap.get(SendOrderListBeanDao.class).clone();
        sendOrderListBeanDaoConfig.initIdentityScope(type);

        sendOrderListBeanDao = new SendOrderListBeanDao(sendOrderListBeanDaoConfig, this);

        registerDao(SendOrderListBean.class, sendOrderListBeanDao);
    }
    
    public void clear() {
        sendOrderListBeanDaoConfig.clearIdentityScope();
    }

    public SendOrderListBeanDao getSendOrderListBeanDao() {
        return sendOrderListBeanDao;
    }

}
