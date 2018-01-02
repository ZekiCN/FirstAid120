package com.hc.ext.core;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.workSpace.core.frame.AppBase;
import org.workSpace.core.jedisSupport.jedisClientProxy.JedisClientProxy;

import java.sql.Connection;

/**
 * Created by liduo.wang on 2016/7/29.
 */
public class BaseService extends AppBase {
    public static Logger logger = LoggerFactory.getLogger("appsLoger");

    public BaseService() {
    }

    public SqlSession getSqlSession(String var1) throws Exception {
        return this.getSqlSession(var1, true);
    }

    public SqlSession getSqlSession(String var1, boolean var2) throws Exception {
        SqlSessionFactory var3 = (SqlSessionFactory)this.getBean(var1);
        SqlSession var4 = var3.openSession(var2);
        return var4;
    }

    public Connection getConnection(String var1) throws Exception {
        SqlSession var2 = this.getSqlSession(var1);
        return var2.getConnection();
    }

    public JedisClientProxy getRedisClient(String var1) throws Exception {
        JedisClientProxy var2 = (JedisClientProxy)this.getBean(var1);
        return var2;
    }
}
