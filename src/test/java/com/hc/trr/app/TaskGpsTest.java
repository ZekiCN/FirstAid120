package com.hc.trr.app;

import com.hc.utils.DateUtil;
import com.hc.utils.HttpClientUtil;
import com.hc.utils.JacksonUtils;
import com.hc.utils.SysUtil;
import junit.framework.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TaskGpsTest {

    @Test
    public void insertGps() throws Exception {

        String url = Const.pathUrl + "300901.do";
        // 参数
        Map<String, String> reqMap = new HashMap<String, String>();

        reqMap.put("token", Const.token);//token

        reqMap.put("emergencyTaskId", "f74e662aefac489f9bc158184074083a");
        reqMap.put("plateId", "564582d6da824e8d91f5376e8b9ad7e3");
        reqMap.put("longitude", "114.416678");
        reqMap.put("latitude", "23.119249");
        reqMap.put("latitude", "23.119249");
        reqMap.put("gpsTime", "2017-11-16 14:24:40");

        reqMap.put("times", DateUtil.getTimeNotModifier());
        // 签名准备
        reqMap.put("sign", SysUtil.base64Encoder(Const.getSign(reqMap, Const.key, "utf-8")));// 签名 不可空

        String result = HttpClientUtil.post(url, reqMap, "UTF-8", 10000, 10000);

        Map<String, Object>  resultMap = JacksonUtils.json2map(result);

        Assert.assertEquals("0000", resultMap.get("code"));

        System.out.println(result);
    }

    @Test
    public void selectGps() throws Exception {

        String url = Const.pathUrl + "300902.do";
        // 参数
        Map<String, String> reqMap = new HashMap<String, String>();

        reqMap.put("token", Const.token);//token

        reqMap.put("taskId", "f74e662aefac489f9bc158184074083a");

        reqMap.put("times", DateUtil.getTimeNotModifier());
        // 签名准备
        reqMap.put("sign", SysUtil.base64Encoder(Const.getSign(reqMap, Const.key, "utf-8")));// 签名 不可空

        String result = HttpClientUtil.post(url, reqMap, "UTF-8", 10000, 10000);

        Map<String, Object>  resultMap = JacksonUtils.json2map(result);

        Assert.assertEquals("0000", resultMap.get("code"));

        System.out.println(result);
    }
}
