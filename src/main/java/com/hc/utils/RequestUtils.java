package com.hc.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ling
 * 
 */
public class RequestUtils {
	protected static final Logger log = LoggerFactory
			.getLogger(RequestUtils.class);

	public static boolean isMultipart(HttpServletRequest req) {
		return ((req.getContentType() != null) && (req.getContentType()
				.toLowerCase().startsWith("multipart")));
	}

	public static String getStringParameter(HttpServletRequest request,
			String attr, String defaultValue) {

		String str = request.getParameter(attr);
		return (str == null || str.equals("")) ? defaultValue : str.trim();
	}

	@SuppressWarnings("deprecation")
	public static String getURLDecoderParameter(HttpServletRequest request,
			String attr, String defaultValue)
			throws UnsupportedEncodingException {

		String str = request.getParameter(attr);
		return (str == null || str.equals("")) ? URLDecoder.decode(
				defaultValue, "utf-8") : URLDecoder.decode(str, "utf-8");
	}

	public static int getIntParameter(HttpServletRequest request, String attr,
			int defaultValue) {

		try {
			String str = request.getParameter(attr);
			return ((str == null) || str.equals("")) ? defaultValue : Integer
					.parseInt(str.trim());
		} catch (Exception e) {
			log.error(e.getMessage());
			return defaultValue;
		}
	}

	/**
	 * 判断手机是否支持某种类型的格式
	 * 
	 * @param req
	 * @param contentType
	 * @return
	 */
	public static boolean support(HttpServletRequest req, String contentType) {
		String accept = getHeader(req, "accept");
		if (accept != null) {
			accept = accept.toLowerCase();
			return accept.indexOf(contentType.toLowerCase()) != -1;
		}
		return false;
	}

	/**
	 * 获取header信息，名字大小写无关
	 * 
	 * @param req
	 * @param name
	 * @return
	 */
	public static String getHeader(HttpServletRequest req, String name) {
		String value = req.getHeader(name);
		if (value != null)
			return value;
		Enumeration<?> names = req.getHeaderNames();
		while (names.hasMoreElements()) {
			String n = (String) names.nextElement();
			if (n.equalsIgnoreCase(name)) {
				return req.getHeader(n);
			}
		}
		return null;
	}

	/**
	 * 获取访问的URL全路径
	 * 
	 * @param req
	 * @return
	 */
	public static String getRequestURL(HttpServletRequest req) {
		StringBuffer url = new StringBuffer(req.getRequestURI());
		String param = req.getQueryString();
		if (param != null) {
			url.append('?');
			url.append(param);
		}
		String path = url.toString();
		return path.substring(req.getContextPath().length());
	}

	/**
	 * 获取访问的IP地址
	 * 
	 * @param req
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		// 去掉阿里云的高防IP代理转发导致的IP增多问题,电信：180.97.88.0/24 联通：218.60.120.0/24
		if (ip.indexOf(",") > 0) {
			// 按照网络协议，原则上是取第一个IP才是真实的
			ip = ip.substring(0, ip.indexOf(","));
		}
		return ip;
	}

	/**
	 * 获取访问一级域名
	 * 
	 * @param req
	 * @return
	 */
	public static String getAreaName(HttpServletRequest request, String name) {
		String areaName = getHeader(request, name);
		return getFirstAreaName(areaName);
	}
	/**
	 * 获取一级域名
	 */
	public static String getFirstAreaName(String areaName) {
		String firstAreaName = "";
		if ("".equals(areaName) || areaName == null) {
			return firstAreaName;
		}
		//log.info("areaName===" + areaName);
		areaName = areaName.replace("http://", "").replace("https://", "");
		areaName = areaName.split("/")[0];
		areaName = areaName.split(":")[0];
		String[] array = areaName.split("\\.");
		if (StringUtils.isIPAddr(areaName)) {
			firstAreaName = array[0] + "." + array[1] + "." + array[2] + "."
					+ array[3];
		} else {
			if (array.length == 2) {
				firstAreaName = areaName;
			} else if (array.length > 2) {
				firstAreaName = array[array.length - 2] + "."
						+ array[array.length - 1];
				if ("com.cn".equals(firstAreaName)
						|| "net.cn".equals(firstAreaName)
						|| "org.cn".equals(firstAreaName)
						|| "gov.cn".equals(firstAreaName)) {
					firstAreaName = array[array.length - 3] + "."
							+ array[array.length - 2] + "."
							+ array[array.length - 1];
				}
			}
		}
		return firstAreaName;
	}
	
	/**
	 * 获取请求渠道
	 * 
	 * @param request
	 * @return pc电脑请求，mobile手机请求
	 */
	public static String getRequestType(HttpServletRequest request) {
		String requestType = "pc";
		String header = request.getHeader("Accept");
		log.info("Accept=====" + header);
		if(null == header ){
			return "none";
		}
		if (header.indexOf("wap") > -1 || header.indexOf("wml") > -1) {
			requestType = "mobile";
		}
		return requestType;
	}
}
