package com.hc.trr.app;

import com.hc.utils.DateUtil;
import com.hc.utils.HttpClientUtil;
import com.hc.utils.JacksonUtils;
import com.hc.utils.SysUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class InfoTransferApplyAppServiceTest {

	/**
	 * 测试300101接口。添加转院申请
	 * @throws Exception
	 */
	@Test
	public void insertApply() throws Exception {

		String url = Const.pathUrl + "300101.do";
		// 参数
		Map<String, String> reqMap = new HashMap<String, String>();

		reqMap.put("token", Const.token);//token

		reqMap.put("source", "1");
		reqMap.put("infoId", "ebb3289e6806481fbc767183e49abd49");
		reqMap.put("pursuant", "1");
		reqMap.put("hospitalId", "37a22ed0ec9548ebae49244eba7648d6");

		reqMap.put("outTime", "");

		reqMap.put("times", DateUtil.getTimeNotModifier());
		// 签名准备
		reqMap.put("sign", SysUtil.base64Encoder(Const.getSign(reqMap, Const.key, "utf-8")));// 签名 不可空

		String result = HttpClientUtil.post(url, reqMap, "UTF-8", 20000, 20000);

		Map<String, Object>  resultMap = JacksonUtils.json2map(result);

		Assert.assertEquals("0000", resultMap.get("code"));

		System.out.println(result);
	}

	/**
	 * 测试300102接口。转院申请列表
	 * @throws Exception
	 */
	@Test
	public void selectApply() throws Exception {

		String url = Const.pathUrl + "300102.do";
		// 参数
		Map<String, String> reqMap = new HashMap<String, String>();

		reqMap.put("token", Const.token);//token

		reqMap.put("infoId", "81e79cfbaa26492e979f6e019e02b032");
		reqMap.put("status", "0,1,2,3");//状态；0：未确认；1：确认接收；2：拒绝接收；3：撤销。	逗号分隔查询多个。

		reqMap.put("page", "1");
		reqMap.put("rows", "20");

		reqMap.put("times", DateUtil.getTimeNotModifier());
		// 签名准备
		reqMap.put("sign", SysUtil.base64Encoder(Const.getSign(reqMap, Const.key, "utf-8")));// 签名 不可空

		String result = HttpClientUtil.post(url, reqMap, "UTF-8", 10000, 10000);

		Map<String, Object>  resultMap = JacksonUtils.json2map(result);

		Assert.assertEquals("0000", resultMap.get("code"));

		System.out.println(result);
	}

	/**
	 * 测试300103接口。撤销转院
	 * @throws Exception
	 */
	@Test
	public void cancleApply() throws Exception {

		String url = Const.pathUrl + "300103.do";
		// 参数
		Map<String, String> reqMap = new HashMap<String, String>();

		reqMap.put("token", Const.token);//token

		reqMap.put("id", "98d7319fdf8443ae95dbc45eb902aa9d");
		reqMap.put("status", "3");


		reqMap.put("times", DateUtil.getTimeNotModifier());
		// 签名准备
		reqMap.put("sign", SysUtil.base64Encoder(Const.getSign(reqMap, Const.key, "utf-8")));// 签名 不可空

		String result = HttpClientUtil.post(url, reqMap, "UTF-8", 10000, 10000);

		Map<String, Object>  resultMap = JacksonUtils.json2map(result);

		Assert.assertEquals("0000", resultMap.get("code"));

		System.out.println(result);
	}

	@Test
	public void w300104() throws Exception {

		String url = Const.pathUrl + "300104.do";
		// 参数
		Map<String, String> reqMap = new HashMap<String, String>();

		reqMap.put("token", Const.token);//token

		reqMap.put("taskId", "e6ebae2a232149d58b2359e7786da119");

		reqMap.put("times", DateUtil.getTimeNotModifier());
		// 签名准备
		reqMap.put("sign", SysUtil.base64Encoder(Const.getSign(reqMap, Const.key, "utf-8")));// 签名 不可空

		String result = HttpClientUtil.post(url, reqMap, "UTF-8", 10000, 10000);

		Map<String, Object>  resultMap = JacksonUtils.json2map(result);

		junit.framework.Assert.assertEquals("0000", resultMap.get("code"));

		System.out.println(result);
	}

	@Test
	public void w300105() throws Exception {

		String url = Const.pathUrl + "300105.do";
		// 参数
		Map<String, String> reqMap = new HashMap<String, String>();

		reqMap.put("token", Const.token);//token

		reqMap.put("id", "ce1e3e354fa1492a8a511a9609bfb5d0");

		reqMap.put("times", DateUtil.getTimeNotModifier());
		// 签名准备
		reqMap.put("sign", SysUtil.base64Encoder(Const.getSign(reqMap, Const.key, "utf-8")));// 签名 不可空

		String result = HttpClientUtil.post(url, reqMap, "UTF-8", 10000, 10000);

		Map<String, Object>  resultMap = JacksonUtils.json2map(result);

		junit.framework.Assert.assertEquals("0000", resultMap.get("code"));

		System.out.println(result);
	}
}
