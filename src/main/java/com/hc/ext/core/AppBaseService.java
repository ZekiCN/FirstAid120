package com.hc.ext.core;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.workSpace.core.frame.BaseService;
import org.workSpace.core.jedisSupport.jedisClientProxy.JedisClientProxy;
import org.workSpace.utils.JsonUtils;

import com.hc.utils.Constant;
import com.hc.utils.Tools;
import com.hc.utils.UuidUtil;

/**
 * 对BaseService的功能拓展.
 * 其他Service的类都实现本类.
 *
 * @Date 2016/04/20
 * @Author lidong
 */
public abstract class AppBaseService extends BaseService {
    public static Logger logger = LoggerFactory.getLogger(AppBaseService.class);

/*    private final String APPDBSOURCE = "appDBSource";
    private final String REDISPROXY = "jedisShareClientProxy";*/

    public AppBaseService() {
    }

    public SqlSession getSqlSession() throws Exception {
        return this.getSqlSession("sqlSessionFactory");
    }

/*    public JedisClientProxy getRedisClient() throws Exception {
        return this.getRedisClient(getConfProperty("app", "base.RedisDataSource"));
    }*/

    public String getPrjCode() throws Exception {
        return getConfProperty("app", "prjCode");
    }
/*    *//**
     * 从redis缓存中读取信息
     * @param prjCode
     * @param Id
     * @param keyFlag
     * @return
     * @throws Exception
     *//*
    public  String getDataFromRedis(String prjCode, String Id, String keyFlag) throws Exception{
    	
    	return getRedisClient().get(prjCode + Constant.SPLIT+ keyFlag +Constant.SPLIT+Id);
    }

    *//**
     * 从redis缓存中读取信息列表
     * @param prjCode
     * @param Id[]
     * @param keyFlag
     * @return
     * @throws Exception
     *//*
    public  List<String> getDataFromRedis(String prjCode, String[] Id, String keyFlag) throws Exception{
    	List<String> reList = null;
    	if (Tools.notEmpty(prjCode) && Tools.notEmpty(keyFlag) && Id != null){
    		String[] idKey = new String[Id.length];
        	for(int i = 0; i < Id.length; i++){
        		idKey[i] = prjCode + Constant.SPLIT+ keyFlag +Constant.SPLIT+Id[i];
        	}
        	reList = getRedisClient().mget(idKey);
    	}
    	return reList;
    }
    
    *//**
     * 加载数据到信息到redis中
     * @param prjCode
     * @param id
     * @param reSelectList
     * @param keyFlag
     * @param msg
     * @throws Exception
     *//*
	public <T> void loadDataToRedis(String prjCode, String id, T reSelectList,
			String keyFlag, String msg) throws Exception {
        try {
            getRedisClient().set(prjCode + Constant.SPLIT + keyFlag + Constant.SPLIT + id, JsonUtils.genUpdateDataReturnJsonStr(true, msg, reSelectList));
            //设置组织机构缓存数据的超时时间为10小时
            getRedisClient().expire(prjCode + Constant.SPLIT + keyFlag + Constant.SPLIT + id,Constant.REDIS_INFO_LIFETIME);
        } catch (Exception x) {
            logger.error("Class AppBaseService,Method getDataFromRedis,redis Execution exception", x);
            throw x;
        }
	}
    *//**
     * 从redis缓存删除数据
     * @param prjCode
     * @throws Exception
     *//*
    public void delDataFromRedis(String prjCode, String Id, String keyFlag) throws Exception{
        try{
            getRedisClient().del(prjCode + Constant.SPLIT + keyFlag + Constant.SPLIT + Id);
        }catch (Exception x) {
            logger.error("Class B_areaService,Method delProDataFromRedis,redis Execution exception", x);
            throw x;
        }
    }
    
    *//**Redis前缀模糊删除
	 * @return
	 *//*
    public void deleteByPrexRedis(String prex) {
    	try { 
     		Set<String> keys = getRedisClient().keys(prex + "*");
			if(keys == null || keys.size() < 1) {
				return;
			}
			String[] keyArray = keys.toArray(new String[0]);	 
		    getRedisClient().del(keyArray);
    	} catch (Exception e) {		
			e.printStackTrace();
		}
    	
    }*/
    /**得到32位的uuid
	 * @return
	 */
	public String get32UUID(){
		return UuidUtil.get32UUID();
	}


}