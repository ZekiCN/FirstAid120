package com.hc.utils;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.NameValuePair;

/**
 *
 * <p>
 * RSA签名,加解密处理核心文件，注意：密钥长度1024
 * </p>
 * 
 * @author leelun
 * @version $Id: RSA.java, v 0.1 2013-11-15 下午2:33:53 lilun Exp $
 */
public class RsaUtils {

	/**
	 * 签名算法
	 */
	public static final String SIGNATURE_ALGORITHM = "SHA1withRSA";
	/**
	 * 加密算法RSA
	 */
	public static final String KEY_ALGORITHM = "RSA";
	/**
	 * RSA最大加密明文大小
	 */
	private static final int MAX_ENCRYPT_BLOCK = 117;

	/**
	 * RSA最大解密密文大小
	 */
	private static final int MAX_DECRYPT_BLOCK = 128;

	/**
	 * 获取公钥的key
	 */
	private static final String PUBLIC_KEY = "RSAPublicKey";

	/**
	 * 获取私钥的key
	 */
	private static final String PRIVATE_KEY = "RSAPrivateKey";

	/**
	 * <p>
	 * 生成密钥(公钥和私钥)
	 * </p>
	 *
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> genKeyPair() throws Exception {
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
		keyPairGen.initialize(1024);
		KeyPair keyPair = keyPairGen.generateKeyPair();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		Map<String, Object> keyMap = new HashMap<String, Object>(2);
		keyMap.put(PUBLIC_KEY, publicKey);
		keyMap.put(PRIVATE_KEY, privateKey);
		return keyMap;

	}

	/**
	 * 签名字符
	 *
	 * @param text
	 *            要签名的字符
	 * @param privateKey
	 *            私钥(BASE64编码)
	 *
	 * @param input_charset
	 *            编码格式
	 * @return 签名结果(BASE64编码)
	 */
	public static String sign(String text, String privateKey, String charset) throws Exception {

		byte[] keyBytes = Base64.decodeBase64(privateKey);
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initSign(privateK);
		signature.update(getContentBytes(text, charset));
		byte[] result = signature.sign();
		return Base64.encodeBase64String(result);

	}

	/**
	 * 签名字符
	 *
	 * @param text
	 *            要签名的字符
	 * @param sign
	 *            客户签名结果
	 * @param publicKey
	 *            公钥(BASE64编码)
	 * @param input_charset
	 *            编码格式
	 * @return 验签结果
	 */
	public static boolean verify(String text, String sign, String publicKey, String charset) throws Exception {
		byte[] keyBytes = Base64.decodeBase64(publicKey);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PublicKey publicK = keyFactory.generatePublic(keySpec);

		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initVerify(publicK);
		signature.update(getContentBytes(text, charset));
		return signature.verify(Base64.decodeBase64(sign));

	}

	/**
	 * <P>
	 * 私钥解密
	 * </p>
	 *
	 * @param encryptedData
	 *            已加密数
	 * @param privateKey
	 *            私钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey) throws Exception {
		byte[] keyBytes = Base64.decodeBase64(privateKey);
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateK);
		int inputLen = encryptedData.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段解�?
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
				cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_DECRYPT_BLOCK;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return decryptedData;

	}

	/**
	 * <p>
	 * 公钥解密
	 * </p>
	 *
	 * @param encryptedData
	 *            已加密数
	 * @param publicKey
	 *            公钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPublicKey(byte[] encryptedData, String publicKey) throws Exception {
		byte[] keyBytes = Base64.decodeBase64(publicKey);
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key publicK = keyFactory.generatePublic(x509KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, publicK);
		int inputLen = encryptedData.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段解�?
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
				cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_DECRYPT_BLOCK;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return decryptedData;

	}

	/**
	 * <p>
	 * 公钥加密
	 * </p>
	 *
	 * @param data
	 *            源数
	 * @param publicKey
	 *            公钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPublicKey(byte[] data, String publicKey) throws Exception {
		byte[] keyBytes = Base64.decodeBase64(publicKey);
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key publicK = keyFactory.generatePublic(x509KeySpec);
		// 对数据加�?
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicK);
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段加�?
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_ENCRYPT_BLOCK;
		}
		byte[] encryptedData = out.toByteArray();
		out.close();
		return encryptedData;

	}

	/**
	 * <p>
	 * 私钥加密
	 * </p>
	 *
	 * @param data
	 *            源数
	 * @param privateKey
	 *            私钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPrivateKey(byte[] data, String privateKey) throws Exception {
		byte[] keyBytes = Base64.decodeBase64(privateKey);
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, privateK);
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段加�?
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_ENCRYPT_BLOCK;
		}
		byte[] encryptedData = out.toByteArray();
		out.close();
		return encryptedData;

	}

	/**
	 * @param content
	 * @param charset
	 * @return
	 * @throws SignatureException
	 * @throws UnsupportedEncodingException
	 */
	private static byte[] getContentBytes(String content, String charset) {
		if (charset == null || "".equals(charset)) {
			return content.getBytes();
		}
		try {
			return content.getBytes(charset);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
		}
	}

	/**
	 * <p>
	 * 获取私钥
	 * </p>
	 *
	 * @param keyMap
	 *            密钥
	 * @return
	 * @throws Exception
	 */
	public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
		Key key = (Key) keyMap.get(PRIVATE_KEY);
		return Base64.encodeBase64String(key.getEncoded());
	}

	/**
	 * <p>
	 * 获取公钥
	 * </p>
	 *
	 * @param keyMap
	 *            密钥
	 * @return
	 * @throws Exception
	 */
	public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
		Key key = (Key) keyMap.get(PUBLIC_KEY);
		return Base64.encodeBase64String(key.getEncoded());
	}

	/**
	 * MAP类型数组转换成NameValuePair类型
	 *
	 * @param properties
	 *            MAP类型数组
	 * @return NameValuePair类型数组
	 */
	public static NameValuePair[] generatNameValuePair(Map<String, String> properties, String charset)
			throws Exception {
		NameValuePair[] nameValuePair = new NameValuePair[properties.size()];
		int i = 0;
		for (Map.Entry<String, String> entry : properties.entrySet()) {
			// nameValuePair[i++] = new NameValuePair(entry.getKey(),
			// URLEncoder.encode(entry.getValue(),charset));
			nameValuePair[i++] = new NameValuePair(entry.getKey(), entry.getValue());
		}

		return nameValuePair;
	}

	/**
	 * 生成请求参数数组
	 *
	 * @param sParaTemp
	 *            请求前的参数数组
	 * @return 要请求的参数数组
	 */
	public static Map<String, String> buildRequestPara(Map<String, String> sParaTemp, String signType, String key,
			String inputCharset) throws Exception {
		// 除去数组中的空值和签名参数
		Map<String, String> sPara = paraFilter(sParaTemp);
		// 生成签名结果
		String mysign = "";
		if ("RSA".equalsIgnoreCase(signType)) {
			mysign = buildRequestByRSA(sPara, key, inputCharset);
		}

		// 签名结果与签名方式加入请求提交参数组中
		sPara.put("sign", mysign);
		sPara.put("sign_type", signType);

		return sPara;
	}

	/**
	 * 生成RSA签名结果
	 *
	 * @param sPara
	 *            要签名的数组
	 * @return 签名结果字符串
	 */
	public static String buildRequestByRSA(Map<String, String> sPara, String privateKey, String inputCharset)
			throws Exception {
		String prestr = createLinkString(sPara, false); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
		String mysign = "";
		//System.out.println(prestr);
		mysign = RsaUtils.sign(prestr, privateKey, inputCharset);
		return mysign;
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
	public static Map<String, String> createLinkRequestParas(Map<String, String> params) {
		Map<String, String> encodeParamsValueMap = new HashMap<String, String>();
		List<String> keys = new ArrayList<String>(params.keySet());
		String charset = params.get("_input_charset");
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value;
			try {
				value = URLEncoder.encode(params.get(key), charset);
				encodeParamsValueMap.put(key, value);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return encodeParamsValueMap;
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
	public static String createLinkString(Map<String, String> params, boolean encode) {

		// params = paraFilter(params);

		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);

		String prestr = "";

		String charset = params.get("_input_charset");
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
	 * 除去数组中的空值和签名参数
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
			// try {
			// value = URLEncoder.encode(value,charset);
			// } catch (UnsupportedEncodingException e) {
			// e.printStackTrace();
			// }
			result.put(key, value);
		}

		return result;
	}
	
	

	public static void main(String[] args) throws Exception {
		Map keyMap = genKeyPair();
		// 生成公钥私钥
		String publicKey = getPublicKey(keyMap);
		//System.out.println("publicKey:" + publicKey);
		String privateKey = getPrivateKey(keyMap);
		//System.out.println("privateKey:" + privateKey);

		privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBANFoDbdHlURqxb1ORx0Pc14qGiF9thgzti4EQpSf/RMJiJx58wnh13Q2P//ELCdWl6zKsrtQfAHGv6W39s0TIjDqUVCV103ScJVZSitYN+MdgPBVIAL5RXZ2rBUSf2A8gd8y3p6ZuwV8JNYojrS5d/zVNjEDz9z3qoUCmIfqHU6PAgMBAAECgYBw7rz26xQFIdAil+SiN4LwdaRCC5n402qcxo9huCaz7aBPQzVfTvzzH47EUPe+/2QtO+PIQU+fZaBVrzDayEbXQMIQEFFy2hjD9aIgrCXEjnZRrb7k9SyHlrFX5jdjwQFKW2QgldyN/KQvXq4BBLsyruCK3R03fZpe19TKuVPogQJBAOtocK7sUtzwmSifupkFlQFznmuZFtwyZHzWEnvMluPi8Eqj2BhR4kMs8Dl5TGkq2Z5gH4xr5PkEOjGRJW4a/8MCQQDjuVqcnypj28ihf4P/PJ07Q3B6RNh/r4tnonMQCjX9YRTxZVYFMqRBwp4tc4MQeTzeY51crWjK8wlwWcN/YTVFAkBB7k9Y/oE6tfv/X7UQNHsGHA6rjgXUCB0SxZYJrEQ6cQ3hqb/VF+Hp9IUXY7yd2811SPCtEG7hu2hdB4zXKKKnAkBrBoUYkRGqJvRvOm/9+C3pWXO2cz7RG97H0SiExFfFgugF39uCy9847M/Mirpxdplt56FgmjtjDsBD5lwkNLqFAkBSmlm+VFpcvI8qi9e1wDsDKTdwo2g5qADzqPj1upDstvzQUAH+wYgGFP+4GiCyL3gKhj1otjhXvRpZ4W2ITwLJ";
		publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDRaA23R5VEasW9TkcdD3NeKhohfbYYM7YuBEKUn/0TCYicefMJ4dd0Nj//xCwnVpesyrK7UHwBxr+lt/bNEyIw6lFQlddN0nCVWUorWDfjHYDwVSAC+UV2dqwVEn9gPIHfMt6embsFfCTWKI60uXf81TYxA8/c96qFApiH6h1OjwIDAQAB";

		//用公钥加密，私钥解密
		String s = SysUtil.base64Encoder(encryptByPublicKey("441302198102127116".getBytes("UTF-8"), publicKey));
		System.err.println("加密: " + s);
		byte[] decodedData = decryptByPrivateKey(SysUtil.base64DecoderToBytes(s), privateKey);
		//System.out.println("解密后: " + new String(decodedData));
		
		//用私钥加密公钥解密
		s = SysUtil.base64Encoder(encryptByPrivateKey("441302198102127116".getBytes("UTF-8"), privateKey));
		System.err.println("加密: " + s);
		decodedData = decryptByPublicKey(SysUtil.base64DecoderToBytes(s), publicKey);
		//System.out.println("解密后: " + new String(decodedData));
		
		// 加签*********************************************
		Map<String, String> reqMap = new HashMap<String, String>();
		reqMap.put("bankCardNo",SysUtil.base64Encoder(encryptByPrivateKey("441302198102127116".getBytes("UTF-8"), privateKey)));// 收款人卡号
		reqMap.put("aa","dfdfdf");
		reqMap.put("times", DateUtil.getTimeNotModifier());
		// 签名准备
		reqMap = RsaUtils.paraFilter(reqMap);// 空值和签名参数
		String sign = RsaUtils.buildRequestByRSA(reqMap, privateKey, "UTF-8");
		reqMap.put("sign", SysUtil.base64Encoder(sign));// 签名 不可空

//		Set<Entry<String, String>> entries = reqMap.entrySet();
//
//		for (Entry<String, String> entry : entries) {
//			//System.out.println(entry.getKey() + ":" + entry.getValue());
//		}

		// 验签**********************************
		try {
			sign = SysUtil.base64Decoder(reqMap.get("sign"));
			String prestr = RsaUtils.createLinkString(RsaUtils.paraFilter(reqMap), false);
			//System.out.println(prestr);
			//System.out.println(verify(prestr, sign , publicKey, "utf-8"));
		} catch (Exception e) {
			//System.out.println("验签出错： " + e);
		}
		
	}
}