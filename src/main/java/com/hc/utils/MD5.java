package com.hc.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/** 
 * 说明：MD5处理
 * 创建人：FH Q313596790
 * 修改时间：2014年9月20日
 * @version
 */
public class MD5 {

	public static String md5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			str = buf.toString();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return str;
	}

	
	/**
	 * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
	 *
	 * @param params
	 *            需要排序并参与字符拼接的参数组
	 * @param encode
	 *            是否需要urlEncode
	 * @return 拼接后字符串
	 */
	public static String createLinkString(Map<String, String> params, boolean encode, String charset) {

		params = paraFilter(params);
		
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);

		String prestr = "";

		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key);
			if (encode) {
				try {
					value = URLEncoder.encode(value, charset);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}

			if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
				prestr = prestr + key + "=" + value;
			} else {
				prestr = prestr + key + "=" + value + "&";
			}
		}

		return prestr;
	}
	
	/**
	 * 除去数组中不需要验证的数据
	 *
	 * @param sArray
	 *            签名参数组
	 * @return 去掉空值与签名参数后的新签名参数组
	 */
	public static Map<String, String> paraFilter(Map<String, String> sArray) {

		Map<String, String> result = new HashMap<String, String>();

		if (sArray == null || sArray.size() <= 0) {
			return result;
		}

		for (String key : sArray.keySet()) {
			String value = sArray.get(key);
			if (!key.equalsIgnoreCase("uid")) {
				if (value == null || value.equals("") || key.equalsIgnoreCase("sign")
						|| key.equalsIgnoreCase("sign_type")) {
					continue;
				}
			}

			if(key.equals("times") || key.equals("token")){
				result.put(key, value);
			}
		}

		return result;
	}
	
	/**
	 * 
	 * @param text
	 * @param sign
	 * @param publicKey
	 * @param charset
	 * @return
	 * @throws Exception 
	 */
    public static boolean verify(String text, String sign, String key, String charset) throws Exception {  
    	//生成签名
    	String textSign = signByMD5(text, key, charset);
    	
    	if(textSign.equals(sign)){
    		return true;
    	}
    	
    	return false;
    } 
    
    /**
     * md5签名
     * @param sourceData
     * @param key
     * @param charset
     * @return
     * @throws Exception
     */
	public static String signByMD5(String sourceData, String key, String charset) throws Exception {
		String data = sourceData + key;
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		byte[] sign = md5.digest(data.getBytes(charset));

		return Bytes2HexString(sign).toUpperCase();
	}
	
	/**
	 * 将byte数组转成十六进制的字符串
	 * 
	 * @param b
	 *            byte[]
	 * @return String
	 */
	public static String Bytes2HexString(byte[] b) {
		StringBuffer ret = new StringBuffer(b.length);
		String hex = "";
		for (int i = 0; i < b.length; i++) {
			hex = Integer.toHexString(b[i] & 0xFF);

			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			ret.append(hex.toUpperCase());
		}
		return ret.toString();
	}
	
	public static void main(String[] args) throws Exception {
		String key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKB";
		
		// 加签*********************************************
		Map<String, String> reqMap = new HashMap<String, String>();
		reqMap.put("aa","dfdfdf");
		reqMap.put("times", DateUtil.getTimeNotModifier());
		reqMap.put("token", "sdfjlskdjflskjdflksdjf");
		// 签名准备
		String sign = MD5.signByMD5(MD5.createLinkString(MD5.paraFilter(MD5.paraFilter(reqMap)), true, "utf-8"), key, "UTF-8");
		
		reqMap.put("sign", SysUtil.base64Encoder(sign));// 签名 不可空
		
		 // 验签************************************/
		sign = SysUtil.base64Decoder(reqMap.get("sign"));
		String prestr = MD5.createLinkString(MD5.paraFilter(reqMap), true, "utf-8");
		if (!MD5.verify(prestr, sign, key, "utf-8")) {
			//System.out.println("验签失败");
		}else{
			//System.out.println("验签成功");
		}
		
	}
}
