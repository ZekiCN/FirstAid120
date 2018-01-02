package com.hc.trr.app;

import com.hc.utils.MD5;

import java.util.Map;

public class Const {
	public static String token = "FD02B0FDEA9E81872C6EC67EA3B273B5";
	
//	public static String pathUrl = "http://172.16.0.144:8570/SystemSet/appService/";
	public static String pathUrl = "http://127.0.0.1:8080/SystemSet/appService/";
	//public static String pathUrl = "http://125.94.45.151:19080/SystemSet/appService/";
	public  static String key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKB";
	
	public static String getSign(Map reqMap, String key, String charset) throws Exception{
		return MD5.signByMD5(MD5.createLinkString(MD5.paraFilter(MD5.paraFilter(reqMap)), true, "utf-8"), key, "UTF-8");
	}
}
