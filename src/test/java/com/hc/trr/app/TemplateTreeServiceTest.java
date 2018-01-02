package com.hc.trr.app;

import com.hc.utils.DateUtil;
import com.hc.utils.HttpClientUtil;
import com.hc.utils.JacksonUtils;
import com.hc.utils.SysUtil;
import junit.framework.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TemplateTreeServiceTest {

	/**
	 * 测试300701接口。
	 * @throws Exception
	 */
	@Test
	public void get() throws Exception {
		
		String url = Const.pathUrl + "300701.do";
		// 参数
		Map<String, String> reqMap = new HashMap<String, String>();
		
		reqMap.put("token", Const.token);//token
		
		reqMap.put("code", "25");
		
		reqMap.put("times", DateUtil.getTimeNotModifier());
		// 签名准备
		reqMap.put("sign", SysUtil.base64Encoder(Const.getSign(reqMap, Const.key, "utf-8")));// 签名 不可空

		String result = HttpClientUtil.post(url, reqMap, "UTF-8", 10000, 10000);
		
		Map<String, Object>  resultMap = JacksonUtils.json2map(result);
		
		Assert.assertEquals("0000", resultMap.get("code"));
		
		System.out.println(result);
	}

}
