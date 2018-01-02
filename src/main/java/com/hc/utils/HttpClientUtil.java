package com.hc.utils;

import java.io.IOException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class HttpClientUtil {

	/**
	 * 日志
	 */
	private static Log log = LogFactory.getLog(HttpClientUtil.class);
	
    /**
     * http请求
     *
     * @param requestURL     请求地址
     * @param requestData    请求数据
     * @param connectTimeOut 连接超时
     * @param timeOut        读取超时
     * @return
     * @throws Exception
     */
    public static String post(String requestURL,
                              Map<String, String> requestData, String charset,
                              int connectTimeOut, int timeOut) throws Exception {
        String result = "";
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        // 请求超时
        defaultHttpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, connectTimeOut);
        // 读取超时
        defaultHttpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, timeOut);
        // 建立HttpPost对象
        try {
            if (requestURL.startsWith("https")) {
                TrustManager easyTrustManager = new X509TrustManager() {
                    public void checkClientTrusted(java.security.cert.X509Certificate[] x509Certificates, String s)
                            throws java.security.cert.CertificateException {
                    }

                    public void checkServerTrusted(java.security.cert.X509Certificate[] x509Certificates, String s)
                            throws java.security.cert.CertificateException {
                    }

                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                };
                //这个好像是HOST验证
        		X509HostnameVerifier hostnameVerifier = new X509HostnameVerifier() {
        			@Override
        			public boolean verify(String arg0, SSLSession arg1) {
        				return true;
        			}
        			public void verify(String arg0, SSLSocket arg1) throws IOException {}
        			public void verify(String arg0, String[] arg1, String[] arg2) throws SSLException {}
        			public void verify(String arg0, X509Certificate arg1) throws SSLException {}
        		};
        		
                SSLContext sslcontext = SSLContext.getInstance("TLS");
                sslcontext.init(null, new TrustManager[]{easyTrustManager}, null);
                SSLSocketFactory sf = new SSLSocketFactory(sslcontext);
                sf.setHostnameVerifier(hostnameVerifier);
                Scheme sch = new Scheme("https", 443, sf);
                defaultHttpClient.getConnectionManager().getSchemeRegistry().register(sch);
            }
            HttpPost httpPost = new HttpPost(StringUtils.trimToEmpty(requestURL));
            // 建立一个NameValuePair数组，用于存储欲传送的参数
            List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
            log.info("requestURL" + requestURL);
            for (String key : requestData.keySet()) {
                // 添加参数
                params.add(new BasicNameValuePair(key, requestData.get(key)));
                log.info("key----->" + key + " value---->" + requestData.get(key));
            }
            // 设置编码
            httpPost.setEntity(new UrlEncodedFormEntity(params, charset));
            // 发送Post,并返回一个HttpResponse对象
            HttpResponse response = defaultHttpClient.execute(httpPost);
            // Header header = response.getFirstHeader("Content-Length");
            // String Length=header.getValue();
            // 上面两行可以得到指定的Header
            // 如果状态码为200,就是正常返回
            if (response.getStatusLine().getStatusCode() == 200) {
                result = new String(EntityUtils.toString(response.getEntity()));
                log.info("result " + result);
                // 得到返回的字符串
                // 打印输出
                // 如果是下载文件,可以用response.getEntity().getContent()返回InputStream
            } else {
                log.error("request error" + String.valueOf(response.getStatusLine().getStatusCode()));
            }
        } finally {
            defaultHttpClient.getConnectionManager().shutdown();
        }
        return result.trim();
    }

    public static String httpsPost(String requestURL,
                                   Map<String, String> requestData, String charset,
                                   int connectTimeOut, int timeOut) throws Exception {
        log.info("requestURL----->" + requestURL);
        String result = "";
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        // 请求超时
        defaultHttpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, connectTimeOut);
        // 读取超时
        defaultHttpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, timeOut);
        try {
            TrustManager easyTrustManager = new X509TrustManager() {
                public void checkClientTrusted(java.security.cert.X509Certificate[] x509Certificates, String s)
                        throws java.security.cert.CertificateException {
                }

                public void checkServerTrusted(java.security.cert.X509Certificate[] x509Certificates, String s)
                        throws java.security.cert.CertificateException {
                }

                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };

            SSLContext sslcontext = SSLContext.getInstance("TLS");
            sslcontext.init(null, new TrustManager[]{easyTrustManager}, null);
            SSLSocketFactory sf = new SSLSocketFactory(sslcontext);
            Scheme sch = new Scheme("https", 443, sf);
            // 建立HttpPost对象
            HttpPost httpPost = new HttpPost(requestURL);
            // httpPost.addHeader(name, value)
            // 建立一个NameValuePair数组，用于存储欲传送的参数
            List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
            defaultHttpClient.getConnectionManager().getSchemeRegistry().register(sch);
            for (String key : requestData.keySet()) {
                // 添加参数
                params.add(new BasicNameValuePair(key, requestData.get(key)));
                log.info("key----->" + key + " value---->" + requestData.get(key));
            }
            // 设置编码
            httpPost.setEntity(new UrlEncodedFormEntity(params, charset));
            // 发送Post,并返回一个HttpResponse对象
            HttpResponse response = defaultHttpClient.execute(httpPost);
            // Header header = response.getFirstHeader("Content-Length");
            // String Length=header.getValue();
            // 上面两行可以得到指定的Header
            // 如果状态码为200,就是正常返回
            if (response.getStatusLine().getStatusCode() == 200) {
                result = new String(EntityUtils.toString(response.getEntity()));
                log.info("result " + result);
                // 得到返回的字符串
                // 打印输出
                // 如果是下载文件,可以用response.getEntity().getContent()返回InputStream
            } else {
                log.error("request error" + String.valueOf(response.getStatusLine().getStatusCode()));
            }
        } finally {
            defaultHttpClient.getConnectionManager().shutdown();
        }
        return result.trim();
    }
    
    /**
     * http请求
     *
     * @param requestURL     请求地址
     * @param requestData    请求数据
     * @param connectTimeOut 连接超时
     * @param timeOut        读取超时
     * @return
     * @throws Exception
     */
    public static String post(String requestURL,
    		String requestParams, String charset,
                              int connectTimeOut, int timeOut) throws Exception {
        String result = "";
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        // 请求超时
        defaultHttpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, connectTimeOut);
        // 读取超时
        defaultHttpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, timeOut);
        // 建立HttpPost对象
        try {
            if (requestURL.startsWith("https")) {
                TrustManager easyTrustManager = new X509TrustManager() {
                    public void checkClientTrusted(java.security.cert.X509Certificate[] x509Certificates, String s)
                            throws java.security.cert.CertificateException {
                    }

                    public void checkServerTrusted(java.security.cert.X509Certificate[] x509Certificates, String s)
                            throws java.security.cert.CertificateException {
                    }

                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                };
                //这个好像是HOST验证
        		X509HostnameVerifier hostnameVerifier = new X509HostnameVerifier() {
        			@Override
        			public boolean verify(String arg0, SSLSession arg1) {
        				return true;
        			}
        			public void verify(String arg0, SSLSocket arg1) throws IOException {}
        			public void verify(String arg0, String[] arg1, String[] arg2) throws SSLException {}
        			public void verify(String arg0, X509Certificate arg1) throws SSLException {}
        		};
        		
                SSLContext sslcontext = SSLContext.getInstance("TLS");
                sslcontext.init(null, new TrustManager[]{easyTrustManager}, null);
                SSLSocketFactory sf = new SSLSocketFactory(sslcontext);
                sf.setHostnameVerifier(hostnameVerifier);
                Scheme sch = new Scheme("https", 443, sf);
                defaultHttpClient.getConnectionManager().getSchemeRegistry().register(sch);
            }
            HttpPost httpPost = new HttpPost(StringUtils.trimToEmpty(requestURL));
            
            log.info("requestURL:" + requestURL);
            log.info("requestParams:" + requestParams);
            StringEntity entityParams = new StringEntity(requestParams,"utf-8");
            httpPost.setEntity(entityParams);
            // 设置头编码
            httpPost.setHeader("Content-Type", "text/xml;charset=ISO-8859-1");
            // 发送Post,并返回一个HttpResponse对象
            HttpResponse response = defaultHttpClient.execute(httpPost);
            // 上面两行可以得到指定的Header
            // 如果状态码为200,就是正常返回
            if (response.getStatusLine().getStatusCode() == 200) {
                result = new String(EntityUtils.toString(response.getEntity()));
                log.info("result:" + result);
            } else {
                log.error("request error" + String.valueOf(response.getStatusLine().getStatusCode()));
            }
        } finally {
            defaultHttpClient.getConnectionManager().shutdown();
        }
        return result.trim();
    }
    
    /**
     * http请求
     *
     * @param requestURL     请求地址
     * @param requestData    请求数据
     * @param connectTimeOut 连接超时
     * @param timeOut        读取超时
     * @return
     * @throws Exception
     */
    public static String jsonPost(String requestURL,
    		String requestParams, String charset,
                              int connectTimeOut, int timeOut) throws Exception {
        String result = "";
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        // 请求超时
        defaultHttpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, connectTimeOut);
        // 读取超时
        defaultHttpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, timeOut);
        // 建立HttpPost对象
        try {
            if (requestURL.startsWith("https")) {
                TrustManager easyTrustManager = new X509TrustManager() {
                    public void checkClientTrusted(java.security.cert.X509Certificate[] x509Certificates, String s)
                            throws java.security.cert.CertificateException {
                    }

                    public void checkServerTrusted(java.security.cert.X509Certificate[] x509Certificates, String s)
                            throws java.security.cert.CertificateException {
                    }

                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                };
                //这个好像是HOST验证
        		X509HostnameVerifier hostnameVerifier = new X509HostnameVerifier() {
        			@Override
        			public boolean verify(String arg0, SSLSession arg1) {
        				return true;
        			}
        			public void verify(String arg0, SSLSocket arg1) throws IOException {}
        			public void verify(String arg0, String[] arg1, String[] arg2) throws SSLException {}
        			public void verify(String arg0, X509Certificate arg1) throws SSLException {}
        		};
        		
                SSLContext sslcontext = SSLContext.getInstance("TLS");
                sslcontext.init(null, new TrustManager[]{easyTrustManager}, null);
                SSLSocketFactory sf = new SSLSocketFactory(sslcontext);
                sf.setHostnameVerifier(hostnameVerifier);
                Scheme sch = new Scheme("https", 443, sf);
                defaultHttpClient.getConnectionManager().getSchemeRegistry().register(sch);
            }
            HttpPost httpPost = new HttpPost(StringUtils.trimToEmpty(requestURL));
            
            log.info("requestURL:" + requestURL);
            log.info("requestParams:" + requestParams);
            StringEntity entityParams = new StringEntity(requestParams,"utf-8");
            entityParams.setContentEncoding("UTF-8");    
            entityParams.setContentType("application/json");
            httpPost.setEntity(entityParams);
            // 设置头编码
            httpPost.setHeader("charset", "UTF-8");
            // 发送Post,并返回一个HttpResponse对象
            HttpResponse response = defaultHttpClient.execute(httpPost);
            // 上面两行可以得到指定的Header
            // 如果状态码为200,就是正常返回
            if (response.getStatusLine().getStatusCode() == 200) {
                result = new String(EntityUtils.toString(response.getEntity()));
                log.info("result:" + result);
            } else {
                log.error("request error" + String.valueOf(response.getStatusLine().getStatusCode()));
            }
        } finally {
            defaultHttpClient.getConnectionManager().shutdown();
        }
        return result.trim();
    }
    
    /**
     * http请求
     *
     * @param requestURL     请求地址
     * @param requestData    请求数据
     * @param connectTimeOut 连接超时
     * @param timeOut        读取超时
     * @return
     * @throws Exception
     */
    public static String postTLS(String requestURL,
                              Map<String, String> requestData, String charset,
                              int connectTimeOut, int timeOut) throws Exception {
        String result = "";
        HttpClientTLS12 defaultHttpClient = new HttpClientTLS12();
        // 请求超时
        defaultHttpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, connectTimeOut);
        // 读取超时
        defaultHttpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, timeOut);
        // 建立HttpPost对象
        try {
            HttpPost httpPost = new HttpPost(StringUtils.trimToEmpty(requestURL));
            // 建立一个NameValuePair数组，用于存储欲传送的参数
            List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
            for (String key : requestData.keySet()) {
                // 添加参数
                params.add(new BasicNameValuePair(key, requestData.get(key)));
            }
            // 设置编码
            httpPost.setEntity(new UrlEncodedFormEntity(params, charset));
            // 发送Post,并返回一个HttpResponse对象
            HttpResponse response = defaultHttpClient.execute(httpPost);
            // Header header = response.getFirstHeader("Content-Length");
            // String Length=header.getValue();
            // 上面两行可以得到指定的Header
            // 如果状态码为200,就是正常返回
            if (response.getStatusLine().getStatusCode() == 200) {
                result = new String(EntityUtils.toString(response.getEntity()));
                // 得到返回的字符串
                // 打印输出
                // 如果是下载文件,可以用response.getEntity().getContent()返回InputStream
            } else {
            }
        } finally {
            defaultHttpClient.getConnectionManager().shutdown();
        }
        return result.trim();
    }
    
    public static String getDocumentString(String str){
    	String strArr[] = str.split("&");
    	Document document = DocumentHelper.createDocument();
    	Element root = document.addElement("moboAccount");
    	Element resp = root.addElement("respData");
    	for(String a : strArr){
    		String key = a.substring(0, a.indexOf('='));
    		String value = a.substring(a.indexOf("=") + 1, a.length());
    		resp.addElement(key).addText(value);
    	}
    	return document.asXML();
    }
    
    public static void main(String[] args) throws Exception {
    	String url = "http://127.0.0.1:8080/SystemSet/appService/10003.do";
    	//私钥
    	String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBANFoDbdHlURqxb1ORx0Pc14qGiF9thgzti4EQpSf/RMJiJx58wnh13Q2P//ELCdWl6zKsrtQfAHGv6W39s0TIjDqUVCV103ScJVZSitYN+MdgPBVIAL5RXZ2rBUSf2A8gd8y3p6ZuwV8JNYojrS5d/zVNjEDz9z3qoUCmIfqHU6PAgMBAAECgYBw7rz26xQFIdAil+SiN4LwdaRCC5n402qcxo9huCaz7aBPQzVfTvzzH47EUPe+/2QtO+PIQU+fZaBVrzDayEbXQMIQEFFy2hjD9aIgrCXEjnZRrb7k9SyHlrFX5jdjwQFKW2QgldyN/KQvXq4BBLsyruCK3R03fZpe19TKuVPogQJBAOtocK7sUtzwmSifupkFlQFznmuZFtwyZHzWEnvMluPi8Eqj2BhR4kMs8Dl5TGkq2Z5gH4xr5PkEOjGRJW4a/8MCQQDjuVqcnypj28ihf4P/PJ07Q3B6RNh/r4tnonMQCjX9YRTxZVYFMqRBwp4tc4MQeTzeY51crWjK8wlwWcN/YTVFAkBB7k9Y/oE6tfv/X7UQNHsGHA6rjgXUCB0SxZYJrEQ6cQ3hqb/VF+Hp9IUXY7yd2811SPCtEG7hu2hdB4zXKKKnAkBrBoUYkRGqJvRvOm/9+C3pWXO2cz7RG97H0SiExFfFgugF39uCy9847M/Mirpxdplt56FgmjtjDsBD5lwkNLqFAkBSmlm+VFpcvI8qi9e1wDsDKTdwo2g5qADzqPj1upDstvzQUAH+wYgGFP+4GiCyL3gKhj1otjhXvRpZ4W2ITwLJ";
    	
    	//参数
		Map<String, String> reqMap = new HashMap<String, String>();
		reqMap.put("bankCardNo",SysUtil.base64Encoder(RsaUtils.encryptByPrivateKey("441302198102127116".getBytes("UTF-8"), privateKey)));// 收款人卡号
		reqMap.put("aa","dfdfdf");
		reqMap.put("times", DateUtil.getTimeNotModifier());
		// 签名准备
		reqMap = RsaUtils.paraFilter(reqMap);// 空值和签名参数
		String sign = RsaUtils.buildRequestByRSA(reqMap, privateKey, "UTF-8");
		reqMap.put("sign", SysUtil.base64Encoder(sign));// 签名 不可空
    	
    	
    	String result = post(url, reqMap, "UTF-8", 10000, 10000);
    	//System.out.println(result);
	}
}