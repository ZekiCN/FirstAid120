package com.hc.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @author ling
 * 
 */
public class SysUtil {
	private static String hexString = "0123456789ABCDEF ";

	public static boolean stringIsNull(String s) {
		return (null == s) || ("").equals(s);
	}

	/**
	 * 格式化十六进制的字符串数据，3C3F->0x3C,0x3F
	 * 
	 * @param hexData
	 */
	public static String formatHexString(String hexData) {

		if (SysUtil.stringIsNull(hexData))
			return null;
		int dLen = hexData.length();
		StringBuffer temData = new StringBuffer();
		for (int i = 0; i < dLen;) {
			temData.append("0x").append(hexData.substring(i, i + 2));
			i += 2;
			if (i < dLen)
				temData.append(",");

			if (i != 0 && i % 11 == 0)
				temData.append("\r\n");
		}
		return temData.toString();

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

	/**
	 * 将两个ASCII字符合成一个字节； 如："EF"--> 0xEF
	 * 
	 * @param src0
	 *            byte
	 * @param src1
	 *            byte
	 * @return byte
	 */
	public static byte uniteBytes(byte src0, byte src1) {
		byte _b0 = Byte.decode("0x" + new String(new byte[] { src0 }))
				.byteValue();
		_b0 = (byte) (_b0 << 4);
		byte _b1 = Byte.decode("0x" + new String(new byte[] { src1 }))
				.byteValue();
		byte ret = (byte) (_b0 ^ _b1);
		return ret;
	}

	/**
	 * 将指定字符串src，以每两个字符分割转换为16进制形式 如："2B44EFD9" --> byte[]{0x2B, 0x44, 0xEF,
	 * 0xD9}
	 * 
	 * @param src
	 *            String
	 * @return byte[]
	 */
	public static byte[] HexString2Bytes(String src) {
		byte[] ret = new byte[src.length() / 2];
		byte[] tmp = src.getBytes();
		for (int i = 0; i < ret.length; i++) {
			ret[i] = uniteBytes(tmp[i * 2], tmp[i * 2 + 1]);
		}
		return ret;
	}

	/**
	 * 
	 * 根据length长度来补齐字符串
	 * 
	 * @param oldStr
	 *            原来的字符串
	 * @param length
	 *            总长度
	 * @param fillStr
	 *            补齐的字符
	 * @param before
	 *            true在前补,false在后补
	 * @return
	 */
	public static String fillWithString(String oldStr, int length,
			String fillStr, boolean before) {
		String str = (null == oldStr ? "" : oldStr);
		while (str.getBytes().length < length) {
			if (before) {
				str = fillStr + str;
			} else {
				str = str + fillStr;
			}
		}
		return str;
	}
	
	/**
	 * 
	 * 根据length长度来补齐字符串
	 * 
	 * @param oldStr
	 *            原来的字符串
	 * @param length
	 *            总长度
	 * @param fillStr
	 *            补齐的字符
	 * @param before
	 *            true在前补,false在后补
	 *            
	 * @param charset
	 * 			  原来的字符串的字符集
	 * @return 
	 * @throws UnsupportedEncodingException 
	 */
	public static String fillWithString(String oldStr, int length,
			String fillStr, boolean before,String charset) throws UnsupportedEncodingException {
		
		String str = (null == oldStr ? "" : oldStr);
		
		charset = (null == charset ? "utf-8" : charset); 
		
		while (str.getBytes(charset).length < length) {
			if (before) {
				str = fillStr + str;
			} else {
				str = str + fillStr;
			}
		}
		return str;
	}

	public static String base64Decoder(String src) throws IOException {
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] bytesrc = decoder.decodeBuffer(src);
		return new String(bytesrc, "UTF-8");
	}
	
	public static byte[] base64DecoderToBytes(String src) throws IOException {
		BASE64Decoder decoder = new BASE64Decoder();
		return decoder.decodeBuffer(src);
	}

	public static String base64Encoder(String src) {
		return base64Encoder(src.getBytes());
	}

	public static String base64Encoder(byte[] src) {
		BASE64Encoder encoder = new BASE64Encoder();
		String bytesrc = encoder.encode(src).replace("\r\n", "");
		return bytesrc;
	}

	public static String strToHex(String str) {
		// 根据默认编码获取字节数组
		byte[] bytes = str.getBytes();
		StringBuilder sb = new StringBuilder(bytes.length * 2);
		// 将字节数组中每个字节拆解成2位16进制整数
		for (int i = 0; i < bytes.length; i++) {
			sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
			sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
		}
		return sb.toString();
	}

	public static String hexToStr(String bytes) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream(
				bytes.length() / 2);
		// 将每2位16进制整数组装成一个字节
		for (int i = 0; i < bytes.length(); i += 2)
			baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString
					.indexOf(bytes.charAt(i + 1))));
		return new String(baos.toByteArray());
	}

	public static String getStringLen(String source, int site) {
		String datalen = String.format("%0" + site + "x", source.length());
		return datalen;
	}

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		String a="PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz48cmVxdWVzdD48aGVhZD48cmVxdWVzdFRpbWU+MjAxNTAyMjcxNTQ4Mzk8L3JlcXVlc3RUaW1lPjwvaGVhZD48Ym9keT48b3JkZXJOb3M+MjAxNTAyMjcwMDAwMDAxNTAwMDE8L29yZGVyTm9zPjwvYm9keT48L3JlcXVlc3Q+";
		//System.out.println("SysUtil.base64Decoder(a)=============="+SysUtil.base64Decoder(a));
		String b="12345678901234500000";
		//System.out.println(String.format("%020s", b));
	
	}

	/**
	 * 将byte数组转成十六进制的字符串
	 * 
	 * @param b
	 *            byte[]
	 * @return String
	 */
	public static String Bytes2HexStringForCCB(byte[] b) {
		StringBuffer ret = new StringBuffer(b.length);
		String hex = "";
		for (int i = 0; i < b.length; i++) {
			hex = Integer.toHexString(b[i] & 0xFF);

			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			ret.append("%" + hex.toUpperCase());
		}
		return ret.toString();
	}

}
