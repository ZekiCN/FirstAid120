package com.hc.trr.app;

import com.hc.utils.DateUtil;
import com.hc.utils.HttpClientUtil;
import com.hc.utils.JacksonUtils;
import com.hc.utils.SysUtil;
import junit.framework.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class AttachmentServiceTest {

	/**
	 * 测试300501接口。伤者附件
	 * @throws Exception
	 */
	@Test
	public void getAttachs() throws Exception {
		
		String url = Const.pathUrl + "300501.do";
		// 参数
		Map<String, String> reqMap = new HashMap<String, String>();
		
		reqMap.put("token", Const.token);//token
		
		reqMap.put("infoId", "3042bb9c2d62479d878f9b13624ec90b");
		
		reqMap.put("times", DateUtil.getTimeNotModifier());
		// 签名准备
		reqMap.put("sign", SysUtil.base64Encoder(Const.getSign(reqMap, Const.key, "utf-8")));// 签名 不可空

		String result = HttpClientUtil.post(url, reqMap, "UTF-8", 10000, 10000);
		
		Map<String, Object>  resultMap = JacksonUtils.json2map(result);
		
		Assert.assertEquals("0000", resultMap.get("code"));
		
		System.out.println(result);
	}

	/**
	 * 测试300502接口。删除附件
	 * @throws Exception
	 */
	@Test
	public void deleteAttachs() throws Exception {

		String url = Const.pathUrl + "300502.do";
		// 参数
		Map<String, String> reqMap = new HashMap<String, String>();

		reqMap.put("token", Const.token);//token

		reqMap.put("id", "7db2ebb4556d42118cdb83ff35d0f480");

		reqMap.put("times", DateUtil.getTimeNotModifier());
		// 签名准备
		reqMap.put("sign", SysUtil.base64Encoder(Const.getSign(reqMap, Const.key, "utf-8")));// 签名 不可空

		String result = HttpClientUtil.post(url, reqMap, "UTF-8", 10000, 10000);

		Map<String, Object>  resultMap = JacksonUtils.json2map(result);

		Assert.assertEquals("0000", resultMap.get("code"));

		System.out.println(result);
	}


}
