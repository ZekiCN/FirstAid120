package com.hc.trr.app;

import com.hc.utils.DateUtil;
import com.hc.utils.HttpClientUtil;
import com.hc.utils.JacksonUtils;
import com.hc.utils.SysUtil;
import junit.framework.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by qiuruihao on 2017/8/23.
 */
public class PlanServiceTest {
    @Test
    public void insertPlan() throws Exception{
        String url = Const.pathUrl + "300601.do";
        // 参数
        Map<String, String> reqMap = new HashMap<String, String>();

        reqMap.put("token", Const.token);//token

        reqMap.put("infoId", "4886e5ba9c5745af805dc6968d6fc4ec");
        reqMap.put("source", "1");
        reqMap.put("isGreenChannel", "1");
        reqMap.put("isOper", "1");
        reqMap.put("isTransfusion", "1");
        reqMap.put("isConsultant", "1");
        reqMap.put("remark", "这是备注这是备注这是备注这是备注这是备注这是备注这是备注这是备注这是备注这是备注这是备注这是备注这是备注这是备注这是备注这是备注这是备注这是备注");
        reqMap.put("opers", "[{\"instrumentId\":\"324fe462c28a436bacd1bffdcd78e80e\",\"instrumentName\":\"X光机\"},{\"instrumentId\":\"cba5dd7be9534cd2a56570945a1c8968\",\"instrumentName\":\"核磁共振\"}]");

        reqMap.put("times", DateUtil.getTimeNotModifier());
        // 签名准备
        reqMap.put("sign", SysUtil.base64Encoder(Const.getSign(reqMap, Const.key, "utf-8")));// 签名 不可空

        String result = HttpClientUtil.post(url, reqMap, "UTF-8", 10000, 10000);

        Map<String, Object>  resultMap = JacksonUtils.json2map(result);

        Assert.assertEquals("0000", resultMap.get("code"));

        System.out.println(result);
    }

    @Test
    public void updatePlan() throws Exception{
        String url = Const.pathUrl + "300602.do";
        // 参数
        Map<String, String> reqMap = new HashMap<String, String>();

        reqMap.put("token", Const.token);//token

        reqMap.put("id", "eea3cbc1a22b41b58ca8472d7cd7f3bc");
        reqMap.put("source", "1");
        reqMap.put("drugs", "[{\"name\":\"233\",\"usages\":\"1\",\"dose\":\"2\",\"doseUnit\":\"3\"}]");

        reqMap.put("times", DateUtil.getTimeNotModifier());
        // 签名准备
        reqMap.put("sign", SysUtil.base64Encoder(Const.getSign(reqMap, Const.key, "utf-8")));// 签名 不可空

        String result = HttpClientUtil.post(url, reqMap, "UTF-8", 10000, 10000);

        Map<String, Object>  resultMap = JacksonUtils.json2map(result);

        Assert.assertEquals("0000", resultMap.get("code"));

        System.out.println(result);
    }

    @Test
    public void selectPlan() throws Exception{
        String url = Const.pathUrl + "300602.do";
        // 参数
        Map<String, String> reqMap = new HashMap<String, String>();

        reqMap.put("token", Const.token);//token

        reqMap.put("infoId", "4886e5ba9c5745af805dc6968d6fc4ec");

        reqMap.put("times", DateUtil.getTimeNotModifier());
        // 签名准备
        reqMap.put("sign", SysUtil.base64Encoder(Const.getSign(reqMap, Const.key, "utf-8")));// 签名 不可空

        String result = HttpClientUtil.post(url, reqMap, "UTF-8", 10000, 10000);

        Map<String, Object>  resultMap = JacksonUtils.json2map(result);

        Assert.assertEquals("0000", resultMap.get("code"));

        System.out.println(result);
    }
}
