package com.hc.trr.app;

import com.hc.utils.DateUtil;
import com.hc.utils.HttpClientUtil;
import com.hc.utils.JacksonUtils;
import com.hc.utils.SysUtil;
import junit.framework.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class WarningTest {

    @Test
    public void select() throws Exception {

        String url = Const.pathUrl + "301001.do";
        // 参数
        Map<String, String> reqMap = new HashMap<String, String>();

        reqMap.put("token", Const.token);//token

        reqMap.put("infoId", "9fda4ee4e6c542e78e6721433f006c5a");

        reqMap.put("times", DateUtil.getTimeNotModifier());
        // 签名准备
        reqMap.put("sign", SysUtil.base64Encoder(Const.getSign(reqMap, Const.key, "utf-8")));// 签名 不可空

        String result = HttpClientUtil.post(url, reqMap, "UTF-8", 10000, 10000);

        Map<String, Object>  resultMap = JacksonUtils.json2map(result);

        Assert.assertEquals("0000", resultMap.get("code"));

        System.out.println(result);
    }

    @Test
    public void insert() throws Exception {

        String url = Const.pathUrl + "301002.do";
        // 参数
        Map<String, String> reqMap = new HashMap<String, String>();

        reqMap.put("token", Const.token);//token

        reqMap.put("infoId", "88edb81fbeba41db9af467fd6d20f7d0");

        reqMap.put("times", DateUtil.getTimeNotModifier());
        // 签名准备
        reqMap.put("sign", SysUtil.base64Encoder(Const.getSign(reqMap, Const.key, "utf-8")));// 签名 不可空

        String result = HttpClientUtil.post(url, reqMap, "UTF-8", 10000, 10000);

        Map<String, Object>  resultMap = JacksonUtils.json2map(result);

        Assert.assertEquals("0000", resultMap.get("code"));

        System.out.println(result);
    }

    @Test
    public void delete() throws Exception {

        String url = Const.pathUrl + "301003.do";
        // 参数
        Map<String, String> reqMap = new HashMap<String, String>();

        reqMap.put("token", Const.token);//token

        reqMap.put("id", "b09c5f6395614454819bfb7483d349a0");

        reqMap.put("times", DateUtil.getTimeNotModifier());
        // 签名准备
        reqMap.put("sign", SysUtil.base64Encoder(Const.getSign(reqMap, Const.key, "utf-8")));// 签名 不可空

        String result = HttpClientUtil.post(url, reqMap, "UTF-8", 10000, 10000);

        Map<String, Object>  resultMap = JacksonUtils.json2map(result);

        Assert.assertEquals("0000", resultMap.get("code"));

        System.out.println(result);
    }

    @Test
    public void w301004() throws Exception {

        String url = Const.pathUrl + "301004.do";
        // 参数
        Map<String, String> reqMap = new HashMap<String, String>();

        reqMap.put("token", Const.token);//token

        reqMap.put("taskId", "4a3a99097bbb40d69a8b582158c0cab1");

        reqMap.put("times", DateUtil.getTimeNotModifier());
        // 签名准备
        reqMap.put("sign", SysUtil.base64Encoder(Const.getSign(reqMap, Const.key, "utf-8")));// 签名 不可空

        String result = HttpClientUtil.post(url, reqMap, "UTF-8", 10000, 10000);

        Map<String, Object>  resultMap = JacksonUtils.json2map(result);

        Assert.assertEquals("0000", resultMap.get("code"));

        System.out.println(result);
    }
    
    @Test
    public void w301005() throws Exception {
    	
    	String url = Const.pathUrl + "301005.do";
    	// 参数
    	Map<String, String> reqMap = new HashMap<String, String>();
    	
    	reqMap.put("token", Const.token);//token
    	
    	reqMap.put("id", "81f3690dfbfa4f43bc46d90999773fe5");
    	
    	reqMap.put("times", DateUtil.getTimeNotModifier());
    	// 签名准备
    	reqMap.put("sign", SysUtil.base64Encoder(Const.getSign(reqMap, Const.key, "utf-8")));// 签名 不可空
    	
    	String result = HttpClientUtil.post(url, reqMap, "UTF-8", 10000, 10000);
    	
    	Map<String, Object>  resultMap = JacksonUtils.json2map(result);
    	
    	Assert.assertEquals("0000", resultMap.get("code"));
    	
    	System.out.println(result);
    }


}
