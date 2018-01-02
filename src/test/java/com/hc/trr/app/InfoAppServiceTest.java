package com.hc.trr.app;

import com.hc.utils.DateUtil;
import com.hc.utils.HttpClientUtil;
import com.hc.utils.JacksonUtils;
import com.hc.utils.SysUtil;
import junit.framework.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class InfoAppServiceTest {
	
	
	/**
	 * 测试300001接口。添加伤者信息
	 * @throws Exception
	 */
	@Test
	public void insertInfo() throws Exception {
		
		String url = "http://172.16.0.144:8570/SystemSet/appService/" + "300001.do";
		// 参数
		Map<String, String> reqMap = new HashMap<String, String>();
		
		reqMap.put("token", Const.token);//token
		
		reqMap.put("taskId", "94a7e8bbcbcb41bd973a0b6aa72fe4e5");
		reqMap.put("name", "程程");
		reqMap.put("age", "20");
		reqMap.put("phone", "1382498827");
		reqMap.put("gender", "女");
		reqMap.put("nation", "汉族");
		reqMap.put("nationality", "中国");
		
		reqMap.put("times", DateUtil.getTimeNotModifier());
		// 签名准备
		reqMap.put("sign", SysUtil.base64Encoder(Const.getSign(reqMap, Const.key, "utf-8")));// 签名 不可空

		String result = HttpClientUtil.post(url, reqMap, "UTF-8", 10000, 10000);
		
		Map<String, Object>  resultMap = JacksonUtils.json2map(result);
		
		Assert.assertEquals("0000", resultMap.get("code"));
		
		System.out.println(result);
	}
	
	/**
	 * 测试300002接口。删除伤者信息
	 * @throws Exception
	 */
	@Test
	public void deleteInfo() throws Exception {
		
		String url = Const.pathUrl + "300002.do";
		// 参数
		Map<String, String> reqMap = new HashMap<String, String>();
		
		reqMap.put("token", Const.token);//token
		
		reqMap.put("infoId", "b72774c10d5643a1b12759eb22c2cc62");
		
		reqMap.put("times", DateUtil.getTimeNotModifier());
		// 签名准备
		reqMap.put("sign", SysUtil.base64Encoder(Const.getSign(reqMap, Const.key, "utf-8")));// 签名 不可空

		String result = HttpClientUtil.post(url, reqMap, "UTF-8", 10000, 10000);
		
		Map<String, Object>  resultMap = JacksonUtils.json2map(result);
		
		Assert.assertEquals("0000", resultMap.get("code"));
		
		System.out.println(result);
	}
	
	
	/**
	 * 测试300003接口。更新伤者信息
	 * @throws Exception
	 */
	@Test
	public void updateInfo() throws Exception {
		
		String url = Const.pathUrl + "300003.do";
		// 参数
		Map<String, String> reqMap = new HashMap<String, String>();
		
		reqMap.put("token", Const.token);//token
		
		reqMap.put("infoId", "fa5a4990245647c98efd5723948897e0");
//		reqMap.put("name", "测试");
//		reqMap.put("age", "78");
//		reqMap.put("phone", "138266498827");
//		reqMap.put("gender", "女");
//		reqMap.put("nation", "汉族");
//		reqMap.put("nationality", "中国");
//		reqMap.put("situationWhenArrive", "昏迷");
//		reqMap.put("pupilLeft", "10");
//		reqMap.put("pupilRight", "10");
//		reqMap.put("lightReflexLeft", "反射消失");
//		reqMap.put("lightReflexRight", "反射消失");
		reqMap.put("allergies", "test1");
		reqMap.put("medicationsCurrentlyUsed", "test2");
		reqMap.put("pastIllness", "test3");
		reqMap.put("lastMeal", "test4");
		reqMap.put("eventRelatedToTheInjury", "test5");

		reqMap.put("times", DateUtil.getTimeNotModifier());
		// 签名准备
		reqMap.put("sign", SysUtil.base64Encoder(Const.getSign(reqMap, Const.key, "utf-8")));// 签名 不可空

		String result = HttpClientUtil.post(url, reqMap, "UTF-8", 10000, 10000);
		
		Map<String, Object>  resultMap = JacksonUtils.json2map(result);
		
		Assert.assertEquals("0000", resultMap.get("code"));
		
		System.out.println(result);
	}
	
	
	/**
	 * 测试300004接口。获得伤者信息
	 * @throws Exception
	 */
	@Test
	public void selectInfo() throws Exception {
		
		String url = Const.pathUrl + "300004.do";
		// 参数
		Map<String, String> reqMap = new HashMap<String, String>();
		
		reqMap.put("token", Const.token);//token
		
		reqMap.put("taskId", "38f0c77cba56448fa84a0049cd65bfe9");
		reqMap.put("infoId", "d1bbf87bcb2946ffb5c0ad570f442232");
		
		reqMap.put("times", DateUtil.getTimeNotModifier());
		// 签名准备
		reqMap.put("sign", SysUtil.base64Encoder(Const.getSign(reqMap, Const.key, "utf-8")));// 签名 不可空

		String result = HttpClientUtil.post(url, reqMap, "UTF-8", 10000, 10000);
		
		Map<String, Object>  resultMap = JacksonUtils.json2map(result);
		
		Assert.assertEquals("0000", resultMap.get("code"));
		
		System.out.println(result);
	}
	
	/**
	 * 测试300004接口。获得伤者信息
	 * @throws Exception
	 */
	@Test
	public void selectPageInfo() throws Exception {
		
		String url = Const.pathUrl + "300007.do";
		// 参数
		Map<String, String> reqMap = new HashMap<String, String>();
		
		reqMap.put("emergencyInfoNo", "060120171115001");
		//reqMap.put("receiptHospitalId", "d1bbf87bcb2946ffb5c0ad570f442232");
		
		reqMap.put("times", DateUtil.getTimeNotModifier());
		// 签名准备
		reqMap.put("sign", SysUtil.base64Encoder(Const.getSign(reqMap, Const.key, "utf-8")));// 签名 不可空

		String result = HttpClientUtil.post(url, reqMap, "UTF-8", 10000, 10000);
		
		Map<String, Object>  resultMap = JacksonUtils.json2map(result);
		
		Assert.assertEquals("0000", resultMap.get("code"));
		
		System.out.println(result);
	}

	/**
	 * 测试300401接口
	 * @throws Exception
	 */
	@Test
	public void selectInfoById() throws Exception {
		String url = Const.pathUrl + "300401.do";
		// 参数
		Map<String, String> reqMap = new HashMap<String, String>();

		reqMap.put("token", Const.token);//token
		reqMap.put("id", "fa5a4990245647c98efd5723948897e0");

		reqMap.put("times", DateUtil.getTimeNotModifier());
		// 签名准备
		reqMap.put("sign", SysUtil.base64Encoder(Const.getSign(reqMap, Const.key, "utf-8")));// 签名 不可空

		String result = HttpClientUtil.post(url, reqMap, "UTF-8", 10000, 10000);

		Map<String, Object>  resultMap = JacksonUtils.json2map(result);

		Assert.assertEquals("0000", resultMap.get("code"));

		System.out.println(result);

	}

	/**
	 * 测试300402接口
	 * @throws Exception
	 */
	@Test
	public void insertDrug() throws Exception {
		String url = Const.pathUrl + "300402.do";
		// 参数
		Map<String, String> reqMap = new HashMap<String, String>();

		reqMap.put("token", Const.token);//token
		reqMap.put("drugId", "0537b2a25e7946b6bff6832921e29f48");
		reqMap.put("emergencyInfoId", "37a19c3b7e734035bd90c795d2453f2f");
		reqMap.put("source", "1");
		reqMap.put("dose", "2");

		reqMap.put("times", DateUtil.getTimeNotModifier());
		// 签名准备
		reqMap.put("sign", SysUtil.base64Encoder(Const.getSign(reqMap, Const.key, "utf-8")));// 签名 不可空

		String result = HttpClientUtil.post(url, reqMap, "UTF-8", 10000, 10000);

		Map<String, Object>  resultMap = JacksonUtils.json2map(result);

		Assert.assertEquals("0000", resultMap.get("code"));

		System.out.println(result);

	}

	/**
	 * 测试300403接口
	 * @throws Exception
	 */
	@Test
	public void deleteDrug() throws Exception {
		String url = Const.pathUrl + "300403.do";
		// 参数
		Map<String, String> reqMap = new HashMap<String, String>();

		reqMap.put("token", Const.token);//token
		reqMap.put("id", "2696011e61264d2ca817aacf705f621c");

		reqMap.put("times", DateUtil.getTimeNotModifier());
		// 签名准备
		reqMap.put("sign", SysUtil.base64Encoder(Const.getSign(reqMap, Const.key, "utf-8")));// 签名 不可空

		String result = HttpClientUtil.post(url, reqMap, "UTF-8", 10000, 10000);

		Map<String, Object>  resultMap = JacksonUtils.json2map(result);

		Assert.assertEquals("0000", resultMap.get("code"));

		System.out.println(result);
	}

	/**
	 * 测试300404接口
	 * @throws Exception
	 */
	@Test
	public void insertDiagnose() throws Exception {
		String url = Const.pathUrl + "300404.do";
		// 参数
		Map<String, String> reqMap = new HashMap<String, String>();

		reqMap.put("token", Const.token);//token
		reqMap.put("emergencyInfoId", "08889b581c1241409f498cb6043cfbf9");
		reqMap.put("source", "1");
		reqMap.put("data", "W3sidHlwZSI6IuW+queOryIsInR5cGVTdWIiOiLliJvkvKToh7Tlv4Pot7PlgZzmraIifSx7InR5cGUiOiLlvqrnjq8iLCJ0eXBlU3ViIjoi5LyR5YWLL+WIm+S8pOaAp+S8keWFiyJ9XQ==");
		reqMap.put("isUnknown", "1");

		reqMap.put("times", DateUtil.getTimeNotModifier());
		// 签名准备
		reqMap.put("sign", SysUtil.base64Encoder(Const.getSign(reqMap, Const.key, "utf-8")));// 签名 不可空

		String result = HttpClientUtil.post(url, reqMap, "UTF-8", 10000, 10000);

		Map<String, Object>  resultMap = JacksonUtils.json2map(result);

		Assert.assertEquals("0000", resultMap.get("code"));

		System.out.println(result);
	}

	/**
	 * 测试300405接口
	 * @throws Exception
	 */
	@Test
	public void deleteDiagnose() throws Exception {
		String url = Const.pathUrl + "300405.do";
		// 参数
		Map<String, String> reqMap = new HashMap<String, String>();

		reqMap.put("token", Const.token);//token
		reqMap.put("id", "0f0b3e10318249dbbaa3b24af93f0582");

		reqMap.put("times", DateUtil.getTimeNotModifier());
		// 签名准备
		reqMap.put("sign", SysUtil.base64Encoder(Const.getSign(reqMap, Const.key, "utf-8")));// 签名 不可空

		String result = HttpClientUtil.post(url, reqMap, "UTF-8", 10000, 10000);

		Map<String, Object>  resultMap = JacksonUtils.json2map(result);

		Assert.assertEquals("0000", resultMap.get("code"));

		System.out.println(result);
	}

	/**
	 * 测试300406接口
	 * @throws Exception
	 */
	@Test
	public void insertCureMeasur() throws Exception {
		String url = Const.pathUrl + "300406.do";
		// 参数
		Map<String, String> reqMap = new HashMap<String, String>();

		reqMap.put("token", Const.token);//token
		reqMap.put("emergencyInfoId", "6b6620e9107646f88eaa926a365b0217");
		reqMap.put("source", "1");
		reqMap.put("data", "W3sidHlwZSI6Iua1i+ivleexu+WIqyIsInR5cGVTdWIiOiLmtYvor5XnsbvliKsxIiwidHlwZVN1YlN1YiI6Iua1i+ivleexu+WIqzIifV0=");
		reqMap.put("isUnknown", "1");

		reqMap.put("times", DateUtil.getTimeNotModifier());
		// 签名准备
		reqMap.put("sign", SysUtil.base64Encoder(Const.getSign(reqMap, Const.key, "utf-8")));// 签名 不可空

		String result = HttpClientUtil.post(url, reqMap, "UTF-8", 10000, 10000);

		Map<String, Object>  resultMap = JacksonUtils.json2map(result);

		Assert.assertEquals("0000", resultMap.get("code"));

		System.out.println(result);
	}

	/**
	 * 测试300408接口
	 * @throws Exception
	 */
	@Test
	public void insertSystmicManifest() throws Exception {
		String url = Const.pathUrl + "300408.do";
		// 参数
		Map<String, String> reqMap = new HashMap<String, String>();

		reqMap.put("token", Const.token);//token
		reqMap.put("emergencyInfoId", "5b26193772a54b65be76eb8ec4e0b4cd");
		reqMap.put("source", "1");
		reqMap.put("gcs", "5");
		reqMap.put("eyeStates", "无反应");
		reqMap.put("eyeStatesScore", "1");
		reqMap.put("languageStates", "仅能发音，不能说话");
		reqMap.put("languageStatesScore", "2");
		reqMap.put("motionStates", "刺痛时，四肢过度伸张");
		reqMap.put("motionStatesScore", "2");

		reqMap.put("times", DateUtil.getTimeNotModifier());
		// 签名准备
		reqMap.put("sign", SysUtil.base64Encoder(Const.getSign(reqMap, Const.key, "utf-8")));// 签名 不可空

		String result = HttpClientUtil.post(url, reqMap, "UTF-8", 10000, 10000);

		Map<String, Object>  resultMap = JacksonUtils.json2map(result);

		Assert.assertEquals("0000", resultMap.get("code"));

		System.out.println(result);
	}

	@Test
	public void test300410() throws Exception{
		String url = Const.pathUrl + "300410.do";
		// 参数
		Map<String, String> reqMap = new HashMap<String, String>();

		reqMap.put("token", Const.token);//token
		reqMap.put("infoId", "6b6620e9107646f88eaa926a365b0217");

		reqMap.put("times", DateUtil.getTimeNotModifier());
		// 签名准备
		reqMap.put("sign", SysUtil.base64Encoder(Const.getSign(reqMap, Const.key, "utf-8")));// 签名 不可空

		String result = HttpClientUtil.post(url, reqMap, "UTF-8", 10000, 10000);

		Map<String, Object>  resultMap = JacksonUtils.json2map(result);

		Assert.assertEquals("0000", resultMap.get("code"));

		System.out.println(result);

	}

	@Test
	public void test300411() throws Exception{
		String url = Const.pathUrl + "300411.do";
		// 参数
		Map<String, String> reqMap = new HashMap<String, String>();

		reqMap.put("token", Const.token);//token
		reqMap.put("infoId", "6b6620e9107646f88eaa926a365b0217");

		reqMap.put("times", DateUtil.getTimeNotModifier());
		// 签名准备
		reqMap.put("sign", SysUtil.base64Encoder(Const.getSign(reqMap, Const.key, "utf-8")));// 签名 不可空

		String result = HttpClientUtil.post(url, reqMap, "UTF-8", 10000, 10000);

		Map<String, Object>  resultMap = JacksonUtils.json2map(result);

		Assert.assertEquals("0000", resultMap.get("code"));

		System.out.println(result);

	}

	@Test
	public void test300412() throws Exception{
		String url = Const.pathUrl + "300412.do";
		// 参数
		Map<String, String> reqMap = new HashMap<String, String>();

		reqMap.put("token", Const.token);//token
		reqMap.put("infoId", "6b6620e9107646f88eaa926a365b0217");

		reqMap.put("times", DateUtil.getTimeNotModifier());
		// 签名准备
		reqMap.put("sign", SysUtil.base64Encoder(Const.getSign(reqMap, Const.key, "utf-8")));// 签名 不可空

		String result = HttpClientUtil.post(url, reqMap, "UTF-8", 10000, 10000);

		Map<String, Object>  resultMap = JacksonUtils.json2map(result);

		Assert.assertEquals("0000", resultMap.get("code"));

		System.out.println(result);

	}

	@Test
	public void test300413() throws Exception{
		String url = Const.pathUrl + "300413.do";
		// 参数
		Map<String, String> reqMap = new HashMap<String, String>();

		reqMap.put("token", Const.token);//token
		reqMap.put("infoId", "6b6620e9107646f88eaa926a365b0217");
		reqMap.put("page", "1");
		reqMap.put("rows", "5");

		reqMap.put("times", DateUtil.getTimeNotModifier());
		// 签名准备
		reqMap.put("sign", SysUtil.base64Encoder(Const.getSign(reqMap, Const.key, "utf-8")));// 签名 不可空

		String result = HttpClientUtil.post(url, reqMap, "UTF-8", 10000, 10000);

		Map<String, Object>  resultMap = JacksonUtils.json2map(result);

		Assert.assertEquals("0000", resultMap.get("code"));

		System.out.println(result);

	}

	@Test
	public void test300414() throws Exception{
		String url = Const.pathUrl + "300414.do";
		// 参数
		Map<String, String> reqMap = new HashMap<String, String>();

		reqMap.put("token", Const.token);//token
		reqMap.put("infoId", "5b26193772a54b65be76eb8ec4e0b4cd");

		reqMap.put("times", DateUtil.getTimeNotModifier());
		// 签名准备
		reqMap.put("sign", SysUtil.base64Encoder(Const.getSign(reqMap, Const.key, "utf-8")));// 签名 不可空

		String result = HttpClientUtil.post(url, reqMap, "UTF-8", 10000, 10000);

		Map<String, Object>  resultMap = JacksonUtils.json2map(result);

		Assert.assertEquals("0000", resultMap.get("code"));

		System.out.println(result);

	}
	/**
	 * 测试300415接口
	 * @throws Exception
	 */
	@Test
	public void test300415() throws Exception {
		String url = Const.pathUrl + "300415.do";
		// 参数
		Map<String, String> reqMap = new HashMap<String, String>();

		reqMap.put("token", Const.token);//token
		reqMap.put("emergencyInfoId", "08889b581c1241409f498cb6043cfbf9");
		reqMap.put("source", "1");
		reqMap.put("data", "W3sidHlwZSI6IuW+queOryIsInR5cGVTdWIiOiLliJvkvKToh7Tlv4Pot7PlgZzmraIifSx7InR5cGUiOiLlvqrnjq8iLCJ0eXBlU3ViIjoi5LyR5YWLL+WIm+S8pOaAp+S8keWFiyJ9XQ==");

		reqMap.put("times", DateUtil.getTimeNotModifier());
		// 签名准备
		reqMap.put("sign", SysUtil.base64Encoder(Const.getSign(reqMap, Const.key, "utf-8")));// 签名 不可空

		String result = HttpClientUtil.post(url, reqMap, "UTF-8", 10000, 10000);

		Map<String, Object>  resultMap = JacksonUtils.json2map(result);

		Assert.assertEquals("0000", resultMap.get("code"));

		System.out.println(result);
	}

	/**
	 * 测试300416接口
	 * @throws Exception
	 */
	@Test
	public void test300416() throws Exception {
		String url = Const.pathUrl + "300416.do";
		// 参数
		Map<String, String> reqMap = new HashMap<String, String>();

		reqMap.put("token", Const.token);//token
		reqMap.put("id", "78be1d277fb14df483a158fafd536733");

		reqMap.put("times", DateUtil.getTimeNotModifier());
		// 签名准备
		reqMap.put("sign", SysUtil.base64Encoder(Const.getSign(reqMap, Const.key, "utf-8")));// 签名 不可空

		String result = HttpClientUtil.post(url, reqMap, "UTF-8", 10000, 10000);

		Map<String, Object>  resultMap = JacksonUtils.json2map(result);

		Assert.assertEquals("0000", resultMap.get("code"));

		System.out.println(result);
	}

	@Test
	public void test300417() throws Exception{
		String url = Const.pathUrl + "300417.do";
		// 参数
		Map<String, String> reqMap = new HashMap<String, String>();

		reqMap.put("token", Const.token);//token
		reqMap.put("infoId", "08889b581c1241409f498cb6043cfbf9");

		reqMap.put("times", DateUtil.getTimeNotModifier());
		// 签名准备
		reqMap.put("sign", SysUtil.base64Encoder(Const.getSign(reqMap, Const.key, "utf-8")));// 签名 不可空

		String result = HttpClientUtil.post(url, reqMap, "UTF-8", 10000, 10000);

		Map<String, Object>  resultMap = JacksonUtils.json2map(result);

		Assert.assertEquals("0000", resultMap.get("code"));

		System.out.println(result);

	}

	@Test
	public void test300005() throws Exception{
		String url = Const.pathUrl + "300005.do";
		// 参数
		Map<String, String> reqMap = new HashMap<String, String>();

		reqMap.put("token", Const.token);//token
		reqMap.put("infoId", "d1bbf87bcb2946ffb5c0ad570f442232");

		reqMap.put("times", DateUtil.getTimeNotModifier());
		// 签名准备
		reqMap.put("sign", SysUtil.base64Encoder(Const.getSign(reqMap, Const.key, "utf-8")));// 签名 不可空

		String result = HttpClientUtil.post(url, reqMap, "UTF-8", 10000, 10000);

		Map<String, Object>  resultMap = JacksonUtils.json2map(result);

		Assert.assertEquals("0000", resultMap.get("code"));

		System.out.println(result);

	}

	@Test
	public void test300006() throws Exception{
		String url = Const.pathUrl + "300006.do";
		// 参数
		Map<String, String> reqMap = new HashMap<String, String>();

		reqMap.put("token", Const.token);//token
		reqMap.put("infoId", "d1bbf87bcb2946ffb5c0ad570f442232");

		reqMap.put("times", DateUtil.getTimeNotModifier());
		// 签名准备
		reqMap.put("sign", SysUtil.base64Encoder(Const.getSign(reqMap, Const.key, "utf-8")));// 签名 不可空

		String result = HttpClientUtil.post(url, reqMap, "UTF-8", 10000, 10000);

		Map<String, Object>  resultMap = JacksonUtils.json2map(result);

		Assert.assertEquals("0000", resultMap.get("code"));

		System.out.println(result);

	}

	@Test
	public void test300008() throws Exception{
		String url = Const.pathUrl + "300008.do";
		// 参数http://172.16.0.144:8570/SystemSet/appService/300008.do?infoId=dad8f621fa0c4d67a93cf6d4e974305f&sign=NEUxMDcyQzIyQzY1NEM1REY4MTc4RkUzQjU2RTM1Q0Y=&times=20171128173246 
		Map<String, String> reqMap = new HashMap<String, String>();

		reqMap.put("token", Const.token);//token
		reqMap.put("infoId", "fcabcd7c11db4d02b830626a6378bb32");

		reqMap.put("times", DateUtil.getTimeNotModifier());
		// 签名准备
		reqMap.put("sign", SysUtil.base64Encoder(Const.getSign(reqMap, Const.key, "utf-8")));// 签名 不可空

		String result = HttpClientUtil.post(url, reqMap, "UTF-8", 10000, 10000);

		Map<String, Object>  resultMap = JacksonUtils.json2map(result);

		Assert.assertEquals("0000", resultMap.get("code"));

		System.out.println(result);

	}
}

