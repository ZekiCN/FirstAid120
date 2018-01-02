package com.hc.trr.app;

import com.hc.utils.DateUtil;
import com.hc.utils.HttpClientUtil;
import com.hc.utils.JacksonUtils;
import com.hc.utils.SysUtil;
import junit.framework.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TaskAppServiceTest {
	
	/**
	 * 测试200001接口。获得任务列表
	 * @throws Exception
	 */
	@Test
	public void getTaskList() throws Exception {
		
		String url = Const.pathUrl + "200001.do";
		// 参数
		Map<String, String> reqMap = new HashMap<String, String>();
		
		reqMap.put("token", Const.token);//token
		
		reqMap.put("ambulanceId", "564582d6da824e8d91f5376e8b9ad7e3");//01146ddbcd104e4abad40efe0d875bed
//		reqMap.put("status", "1,2,3,4,5");
//		reqMap.put("page", "1");// 当前页
//		reqMap.put("rows", "999");// 每页行数
//		reqMap.put("startTime", "2017-09-30 00:00:00");
//		reqMap.put("endTime", "2017-10-01 00:00:00");
//		reqMap.put("isFinished", "0");
		reqMap.put("dispatchDoctorName", "移动工作站医生");
		
		reqMap.put("times", DateUtil.getTimeNotModifier());
		// 签名准备
		reqMap.put("sign", SysUtil.base64Encoder(Const.getSign(reqMap, Const.key, "utf-8")));// 签名 不可空

		String result = HttpClientUtil.post(url, reqMap, "UTF-8", 10000, 10000);
		
		Map<String, Object>  resultMap = JacksonUtils.json2map(result);
		
		Assert.assertEquals("0000", resultMap.get("code"));
		
		System.out.println(result);
	}
	
	/**
	 * 测试200002接口。获得当前任务
	 * @throws Exception
	 */
	@Test
	public void getTaskCurrent() throws Exception {
		
		String url = Const.pathUrl + "200002.do";
		// 参数
		Map<String, String> reqMap = new HashMap<String, String>();
		
		reqMap.put("token", Const.token);//token
		
		reqMap.put("ambulanceId", "a7b252719bd34adf9a601a84e498149d");//01146ddbcd104e4abad40efe0d875bed
		
		reqMap.put("times", DateUtil.getTimeNotModifier());
		// 签名准备
		reqMap.put("sign", SysUtil.base64Encoder(Const.getSign(reqMap, Const.key, "utf-8")));// 签名 不可空

		String result = HttpClientUtil.post(url, reqMap, "UTF-8", 10000, 10000);
		
		Map<String, Object>  resultMap = JacksonUtils.json2map(result);
		
		Assert.assertEquals("0000", resultMap.get("code"));
		
		System.out.println(result);
	}
	
	/**
	 * 测试200003接口。派车确认，接诊确认
	 * @throws Exception
	 */
	@Test
	public void updateTaskStatus() throws Exception {
		
		String url = Const.pathUrl + "200003.do";
		// 参数
		Map<String, String> reqMap = new HashMap<String, String>();
		
		reqMap.put("token", Const.token);//token
		
		reqMap.put("taskId", "df9f7ead51d24141b0f34ecfe6f4cf67");
		reqMap.put("status", "5");//2：派车确认（接诊中），3：接诊确认（接到患者），4：接诊取消(未接到患者)，5：已完成(抵达医院完成此次任务)

		
		reqMap.put("times", DateUtil.getTimeNotModifier());
		// 签名准备
		reqMap.put("sign", SysUtil.base64Encoder(Const.getSign(reqMap, Const.key, "utf-8")));// 签名 不可空

		String result = HttpClientUtil.post(url, reqMap, "UTF-8", 10000, 10000);
		
		Map<String, Object>  resultMap = JacksonUtils.json2map(result);
		
		Assert.assertEquals("0000", resultMap.get("code"));
		
		System.out.println(result);
	}

	@Test
	public void test200004() throws Exception {

		String url = Const.pathUrl + "200004.do";
		// 参数
		Map<String, String> reqMap = new HashMap<String, String>();

		reqMap.put("token", Const.token);//token

		reqMap.put("taskId", "45eeb4f9f68c437d8d2a54cbab77d130");
		reqMap.put("isFinished", "2");


		reqMap.put("times", DateUtil.getTimeNotModifier());
		// 签名准备
		reqMap.put("sign", SysUtil.base64Encoder(Const.getSign(reqMap, Const.key, "utf-8")));// 签名 不可空

		String result = HttpClientUtil.post(url, reqMap, "UTF-8", 10000, 10000);

		Map<String, Object>  resultMap = JacksonUtils.json2map(result);

		Assert.assertEquals("0000", resultMap.get("code"));

		System.out.println(result);
	}

	@Test
	public void test200005() throws Exception {

		String url = Const.pathUrl + "200005.do";
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
