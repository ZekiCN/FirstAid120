package com.hc.trr.platform.aid.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hc.ext.core.AppBaseService;

@Service("aidService")
public class AidService extends AppBaseService{

    //初始化日志输出方法
    public static Logger logger = LoggerFactory.getLogger(AidService.class);

    /**
     * ------------- 生成代码 -------------
     * 慎用 会导致没有where 项时 查出全部数据，
     * 使用时要在业务逻辑中增加查询项是否为空的验证验证标准
     *
     * @param Aid p
     * @return List<Aid> reSelectList
     * @throws Exception
     */
    public List<Map<String,String>> select(Map<String,String> map) throws Exception {
        SqlSession ss = null;
        List<Map<String,String>> reSelectList = null;
        try {
            ss = getSqlSession();
            reSelectList = ss.selectList("Aid.select", map);
        } catch (Exception x) {
            logger.error("Class AidService,Method select,sqlSession Execution exception", x);
            throw x;
        } finally {
            if (ss != null) {
                ss.close();
            }
        }
        return reSelectList;
    }
}
