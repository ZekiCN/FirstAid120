package com.hc.utils;

import junit.framework.Assert;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class testMain {

	public static void main(String[] args) {

		long startTime = System.currentTimeMillis();
		String imageFilePath = "E:/BaiduYunDownload/DSC0030.JPG";

		String actionUrl = "http://localhost:8080/SystemSet/FileController/uploadFile.do";
		try {
			URL url = new URL(actionUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			con.setDoInput(true);
			con.setDoOutput(true);
			con.setUseCaches(false);

			con.setRequestMethod("POST");
			
			// 参数
			Map<String, String> reqMap = new HashMap<String, String>();
			
/*			reqMap.put("token", Const.token);//token
			
			reqMap.put("infoId", "3042bb9c2d62479d878f9b13624ec90b");
			
			reqMap.put("times", DateUtil.getTimeNotModifier());*/
			
			//传递文件信息
/*			con.setRequestProperty("sign", SysUtil.base64Encoder(Const.getSign(reqMap, Const.key, "utf-8")));
			con.setRequestProperty("token", reqMap.get("token"));
			con.setRequestProperty("times", reqMap.get("times"));
			
			con.setRequestProperty("patientId", "3042bb9c2d62479d878f9b13624ec90b");//患者ID
			con.setRequestProperty("type", "mp4");//文件类型
			con.setRequestProperty("projectId", "projectId");//项目ID
			con.setRequestProperty("source", "1");//来源，0：平台端；1：移动工作站端
*/			
			DataOutputStream ds = new DataOutputStream(con.getOutputStream());
			File file = new File(imageFilePath);

			FileInputStream fStream = new FileInputStream(file);
			int bufferSize = 1024;
			byte[] buffer = new byte[bufferSize];

			int length = -1;

			while ((length = fStream.read(buffer)) != -1) {

				ds.write(buffer, 0, length);
			}

			fStream.close();
			ds.flush();

			InputStream is = con.getInputStream();
			int ch;
			StringBuffer b = new StringBuffer();
			while ((ch = is.read()) != -1) {
				b.append((char) ch);
			}
			ds.close();
            // 获得服务器响应结果和状态[码  
            int responseCode = con.getResponseCode();  
            if (responseCode == 200) {
                // 取回响应的结果  
            	String result = changeInputStream(con.getInputStream(), "UTF-8");
        		Map<String, Object>  resultMap = JacksonUtils.json2map(result);
        		Assert.assertEquals("0000", resultMap.get("code"));
        		//System.out.println(result);
            }  
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.print("client time = "+(System.currentTimeMillis()-startTime));
		
	}
    /**  
     * 将一个输入流转换成指定编码的字符串  
     *   
     * @param inputStream  
     * @param encode  
     * @return  
     */  
    private static String changeInputStream(InputStream inputStream,  
            String encode) {  
  
        // 内存流  
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();  
        byte[] data = new byte[1024];  
        int len = 0;  
        String result = null;  
        if (inputStream != null) {  
            try {  
                while ((len = inputStream.read(data)) != -1) {  
                    byteArrayOutputStream.write(data, 0, len);  
                }  
                result = new String(byteArrayOutputStream.toByteArray(), encode);  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return result;  
    }  
}
