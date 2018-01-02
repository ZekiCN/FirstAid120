package com.hc.trr.app;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.hc.utils.DateUtil;
import com.hc.utils.HttpClientUtil;
import com.hc.utils.JacksonUtils;
import com.hc.utils.MD5;
import com.hc.utils.RsaUtils;
import com.hc.utils.SysUtil;

import junit.framework.Assert;

public class DictAppServiceTest {
	
	
	/**
	 * 测试100101接口。获得救护车药品信息
	 * @throws Exception
	 */
	@Test
	public void getDrug() throws Exception {
		
		String url = Const.pathUrl + "100101.do";
		// 参数
		Map<String, String> reqMap = new HashMap<String, String>();
		
//		reqMap.put("page", "1");// 当前页
//		reqMap.put("rows", "999");// 每页行数
		
		reqMap.put("times", DateUtil.getTimeNotModifier());
		
		// 签名准备
		reqMap.put("sign", SysUtil.base64Encoder(Const.getSign(reqMap, Const.key, "utf-8")));// 签名 不可空

		String result = HttpClientUtil.post(url, reqMap, "UTF-8", 10000, 10000);
		
		Map<String, Object>  resultMap = JacksonUtils.json2map(result);
		
		Assert.assertEquals("0000", resultMap.get("code"));
		
		System.out.println(result);
	}
	
	/**
	 * 测试100102接口。获得手术器械字典
	 * @throws Exception
	 */
	@Test
	public void getEquipment() throws Exception {
		
		String url = Const.pathUrl + "100102.do";
		// 参数
		Map<String, String> reqMap = new HashMap<String, String>();
		
		reqMap.put("page", "1");// 当前页
		reqMap.put("rows", "1");// 每页行数
		
		reqMap.put("times", DateUtil.getTimeNotModifier());
		// 签名准备
		reqMap.put("sign", SysUtil.base64Encoder(Const.getSign(reqMap, Const.key, "utf-8")));// 签名 不可空

		String result = HttpClientUtil.post(url, reqMap, "UTF-8", 10000, 10000);
		
		Map<String, Object>  resultMap = JacksonUtils.json2map(result);
		
		Assert.assertEquals("0000", resultMap.get("code"));
		
		System.out.println(result);
	}
	
	/**
	 * 测试100103接口。获得医院列表
	 * @throws Exception
	 */
	@Test
	public void getHospital() throws Exception {
		
		String url = Const.pathUrl + "100103.do";
		// 参数
		Map<String, String> reqMap = new HashMap<String, String>();
		
		reqMap.put("page", "1");// 当前页
		reqMap.put("rows", "1");// 每页行数
		
		reqMap.put("times", DateUtil.getTimeNotModifier());
		// 签名准备
		reqMap.put("sign", SysUtil.base64Encoder(Const.getSign(reqMap, Const.key, "utf-8")));// 签名 不可空

		String result = HttpClientUtil.post(url, reqMap, "UTF-8", 10000, 10000);
		
		Map<String, Object>  resultMap = JacksonUtils.json2map(result);
		
		Assert.assertEquals("0000", resultMap.get("code"));
		
		System.out.println(result);
	}
	
	/**
	 * 测试100104接口。获得字典列表
	 * @throws Exception
	 */
	@Test
	public void getdict() throws Exception {
		
		String url = Const.pathUrl + "100104.do";
		// 参数
		Map<String, String> reqMap = new HashMap<String, String>();
		
		reqMap.put("dictKey", "0001");
		
		reqMap.put("times", DateUtil.getTimeNotModifier());
		
		// 签名准备
		reqMap.put("sign", SysUtil.base64Encoder(Const.getSign(reqMap, Const.key, "utf-8")));// 签名 不可空

		String result = HttpClientUtil.post(url, reqMap, "UTF-8", 10000, 10000);
		
		Map<String, Object>  resultMap = JacksonUtils.json2map(result);
		
		Assert.assertEquals("0000", resultMap.get("code"));
		
		System.out.println(result);
	}

}
