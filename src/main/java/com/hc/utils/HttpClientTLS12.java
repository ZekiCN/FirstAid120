package com.hc.utils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;

public class HttpClientTLS12 extends DefaultHttpClient {
	public HttpClientTLS12() throws Exception {
		super();
		SSLContext ctx = SSLContext.getInstance("TLSv1.2");
		X509TrustManager tm = new X509TrustManager() {
			
			@Override
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void checkServerTrusted(java.security.cert.X509Certificate[] chain,
					String authType) throws java.security.cert.CertificateException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void checkClientTrusted(java.security.cert.X509Certificate[] chain,
					String authType) throws java.security.cert.CertificateException {
				// TODO Auto-generated method stub
				
			}
		};
		ctx.init(null, new TrustManager[] { tm }, null);
		org.apache.http.conn.ssl.SSLSocketFactory ssf = new org.apache.http.conn.ssl.SSLSocketFactory(ctx,
				org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		ClientConnectionManager ccm = this.getConnectionManager();
		SchemeRegistry sr = ccm.getSchemeRegistry();
		sr.register(new Scheme("https", 443, ssf));
	}
}