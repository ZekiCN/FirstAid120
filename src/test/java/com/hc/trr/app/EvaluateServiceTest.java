package com.hc.trr.app;

import com.hc.utils.DateUtil;
import com.hc.utils.HttpClientUtil;
import com.hc.utils.JacksonUtils;
import com.hc.utils.SysUtil;
import junit.framework.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class EvaluateServiceTest {

	/**
	 * 测试300201接口。创伤评估模板
	 * @throws Exception
	 */
	@Test
	public void getCurrentTemplate() throws Exception {
		
		String url = Const.pathUrl + "300201.do";
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
	 * 测试300202接口。录入创伤评估
	 * @throws Exception
	 */
	@Test
	public void insertEcaluate() throws Exception {
		
		String url = Const.pathUrl + "300202.do";
		// 参数
		Map<String, String> reqMap = new HashMap<String, String>();
		
		reqMap.put("token", Const.token);//token
		
		reqMap.put("infoId", "faab35c0ea424142b4d2693320b261f9");
		reqMap.put("source", "1");
		reqMap.put("items", "0cfb0228477e4e1db66eb07629edc936,7071999d86b9451b9fcf5f0dd23ca7be");
		
		reqMap.put("times", DateUtil.getTimeNotModifier());
		// 签名准备
		reqMap.put("sign", SysUtil.base64Encoder(Const.getSign(reqMap, Const.key, "utf-8")));// 签名 不可空

		String result = HttpClientUtil.post(url, reqMap, "UTF-8", 10000, 10000);
		
		Map<String, Object>  resultMap = JacksonUtils.json2map(result);
		
		Assert.assertEquals("0000", resultMap.get("code"));
		
		System.out.println(result);
	}
	
	/**
	 * 测试300203接口。选择创伤评估建议的医院列表
	 * @throws Exception
	 */
	@Test
	public void selectAdiviseHospital() throws Exception {
		
		String url = Const.pathUrl + "300203.do";
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

}
