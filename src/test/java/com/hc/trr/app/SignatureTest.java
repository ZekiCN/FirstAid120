package com.hc.trr.app;

import com.hc.utils.DateUtil;
import com.hc.utils.HttpClientUtil;
import com.hc.utils.JacksonUtils;
import com.hc.utils.SysUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by qiuruihao on 2017/9/27.
 */
public class SignatureTest {

    @Test
    public void test300801() throws Exception{
        String url = Const.pathUrl + "300801.do";
        // 参数
        Map<String, String> reqMap = new HashMap<String, String>();

        reqMap.put("token", Const.token);//token

        reqMap.put("infoId", "4b30d6d76d074bcfbb58ce125ebf96a2");
        reqMap.put("type", "4");

        reqMap.put("times", DateUtil.getTimeNotModifier());
        // 签名准备
        reqMap.put("sign", SysUtil.base64Encoder(Const.getSign(reqMap, Const.key, "utf-8")));// 签名 不可空

        String result = HttpClientUtil.post(url, reqMap, "UTF-8", 10000, 10000);

        Map<String, Object> resultMap = JacksonUtils.json2map(result);

        Assert.assertEquals("0000", resultMap.get("code"));

        System.out.println(result);
    }

    @Test
    public void test300802() throws Exception{
        String url = Const.pathUrl + "300802.do";
        // 参数
        Map<String, String> reqMap = new HashMap<String, String>();

        reqMap.put("token", Const.token);//token

        reqMap.put("infoId", "4b30d6d76d074bcfbb58ce125ebf96a2");

        reqMap.put("times", DateUtil.getTimeNotModifier());
        // 签名准备
        reqMap.put("sign", SysUtil.base64Encoder(Const.getSign(reqMap, Const.key, "utf-8")));// 签名 不可空

        String result = HttpClientUtil.post(url, reqMap, "UTF-8", 10000, 10000);

        Map<String, Object> resultMap = JacksonUtils.json2map(result);

        Assert.assertEquals("0000", resultMap.get("code"));

        System.out.println(result);
    }
}
