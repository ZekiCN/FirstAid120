package com.hc.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 字符串工具类
 */

public class StringUtils {

	protected static final Logger log = LoggerFactory.getLogger(StringUtils.class);
	private final static String DES = "DES";

	public static boolean stringIsNull(String s) {
		return (null == s) || ("").equals(s);
	}

	public static boolean isBlank(String s) {
		return org.apache.commons.lang3.StringUtils.isBlank(s);
	}

	public static boolean isNotBlank(String s) {
		return org.apache.commons.lang3.StringUtils.isNotBlank(s);
	}

	/**
	 * 二行制转字符串
	 * 
	 * @param b
	 * @return
	 */
	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; b != null && n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs.toUpperCase();
	}

	public static byte[] hex2byte(byte[] b) {
		if ((b.length % 2) != 0)
			throw new IllegalArgumentException("长度不是偶数");
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	}

	/**
	 * 将字符串用空格分割并放入队列
	 * 
	 * @param tags
	 * @return
	 */
	public static List<?> stringToList(String tags) {
		if (tags == null)
			return null;
		ArrayList<String> tagList = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(tags);
		while (st.hasMoreElements()) {
			tagList.add(st.nextToken());
		}
		return tagList;
	}

	/**
	 * 大小写无关的字符串替换策略
	 * 
	 * @param str
	 * @param src
	 * @param obj
	 * @return
	 */
	public static String replaceIgnoreCase(String str, String src, String obj) {
		String l_str = str.toLowerCase();
		String l_src = src.toLowerCase();
		int fromIdx = 0;
		StringBuffer result = new StringBuffer();
		do {
			int idx = l_str.indexOf(l_src, fromIdx);
			if (idx == -1)
				break;
			result.append(str.substring(fromIdx, idx));
			result.append(obj);
			fromIdx = idx + src.length();
		} while (true);
		result.append(str.substring(fromIdx));
		return result.toString();
	}

	/**
	 * 判断字符串是否是一个IP地址
	 * 
	 * @param addr
	 * @return
	 */
	public static boolean isIPAddr(String addr) {
		if (SysUtil.stringIsNull(addr))
			return false;
		String[] ips = addr.split("\\.");
		if (ips.length != 4)
			return false;
		try {
			int ipa = Integer.parseInt(ips[0]);
			int ipb = Integer.parseInt(ips[1]);
			int ipc = Integer.parseInt(ips[2]);
			int ipd = Integer.parseInt(ips[3]);
			return ipa >= 0 && ipa <= 255 && ipb >= 0 && ipb <= 255 && ipc >= 0 && ipc <= 255 && ipd >= 0 && ipd <= 255;
		} catch (Exception e) {
		}
		return false;
	}

	public static double getDouble(final String str, final double defaultValue) {
		try {
			return Double.parseDouble(str);
		} catch (final Exception e) {
			return defaultValue;
		}
	}

	public static long getLong(final String str, final long defaultValue) {
		try {
			return Long.parseLong(str);
		} catch (final Exception e) {
			return defaultValue;
		}
	}

	public static int getInt(final String str, final int defaultValue) {
		try {
			return Integer.parseInt(str);
		} catch (final Exception e) {
			return defaultValue;
		}
	}

	public static String getString(final String str, final String defaultValue) {
		return str == null ? defaultValue : str;
	}

	public static boolean getBoolean(final String str, final boolean defaultValue) {
		if (str == null) {
			return defaultValue;
		} else if (str.equalsIgnoreCase("true")) {
			return true;
		} else if (str.equalsIgnoreCase("false")) {
			return false;
		} else {
			return defaultValue;
		}
	}

	public static String convertStringToHtml(final String str) {

		if ((str == null) || "".equals(str)) {
			return "";
		}

		final StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < str.length(); i++) {
			final char currentChar = str.charAt(i);
			if (currentChar == '\n') {
				sb.append("<br>");
			} else if (currentChar == ' ') {
				sb.append("&nbsp;");
			} else if (currentChar == '\t') {
				sb.append("&nbsp;&nbsp;&nbsp;&nbsp;");
			} else if (currentChar != '\r') {
				sb.append(currentChar);
			}
		}
		return sb.toString();
	}

	public static String convertHtmlToString(String str) {
		if (str == null) {
			return "";
		}
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("\"", "&quot;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		return str;
	}

	public static String getShortString(final String str, final int len) {

		if ((str == null) || (str.length() == 0)) {
			return "";
		}
		if (str.length() > len) {
			final String s = StringUtils.convertStringToDoubleBytes(str);
			return s.substring(0, len) + StringUtils.convertEncoding("…", "gb2312", "iso-8859-1");
		} else {
			return str;
		}
	}

	public static String convertEncoding(final String str, final String source, final String dest) {

		if ((str == null) || str.trim().equals("")) {
			return "";
		}

		try {
			return new String(str.getBytes(source), dest);
		} catch (final java.io.UnsupportedEncodingException ue) {
			log.error(ue.getMessage());
		}
		return str;
	}

	public static String getShortClassName(final Class<?> clazz) {
		return (clazz.getName().lastIndexOf(".") == -1) ? clazz.getName()
				: clazz.getName().substring(clazz.getName().lastIndexOf(".") + 1);
	}

	public static String formatNumber(final double value, final int fractionDigits) {
		final NumberFormat nf = NumberFormat.getInstance();
		nf.setGroupingUsed(false);
		nf.setMaximumFractionDigits(fractionDigits);
		nf.setMinimumFractionDigits(0);
		return nf.format(value);
	}

	public static String DoubleToString(Double doub) throws Exception {
		DecimalFormat f = new DecimalFormat("##############0.00");
		String str = f.format(doub);
		return str;
	}

	/***************************************************************************
	 * 
	 * 格式化显示手机号和邮箱；主要是为了不显示完整的信息
	 * 
	 * @param content
	 * @param isPhone
	 * @return
	 */
	public static String formatMobile(String content, boolean isPhone) {
		String fmt = content;
		if (fmt != null && fmt.trim().length() > 0) {
			if (isPhone) {
				Pattern p = Pattern.compile("1[0-9]{1}[0-9]{1}([0-9]{4})[0-9]{4}");
				Matcher m = p.matcher(fmt);
				String group = "";
				while (m.find()) {
					StringBuffer sb = new StringBuffer();
					group = m.group(1);
					sb.append(content.substring(0, content.indexOf(group)));
					sb.append("****");
					sb.append(content.substring(content.indexOf(group) + group.length(), content.length()));
					fmt = sb.toString();
				}
			} else {
				MessageFormat mf = new MessageFormat("{0}@{1}");

				String temp = "";
				StringBuffer sb = new StringBuffer();
				try {
					Object[] vs = mf.parse(fmt);
					temp = (String) vs[0];
					if (temp.length() <= 3 && temp.length() > 0) {
						sb.append(temp);
					} else if (temp.length() >= 4) {
						sb.append(temp.substring(0, 3));
					} else {
						throw new RuntimeException();
					}
					sb.append("****");
					sb.append("@").append((String) vs[1]);
					fmt = sb.toString();
				} catch (Exception e) {
					return fmt;
				}
			}

		}

		return fmt;
	}

	/**
	 * "a;b;;c"=>[a, b, c]
	 * 
	 * @param str
	 * @param delim
	 * @return
	 */
	public static String[] convert2StringArray(final String str, final String delim) {

		final StringTokenizer token = new StringTokenizer(str, delim);
		final String[] ret = new String[token.countTokens()];

		int i = 0;
		while (token.hasMoreElements()) {

			ret[i++] = token.nextToken();
		}

		return ret;
	}

	/**
	 * 过滤字符串
	 * 
	 * @param str
	 * @param filterStr
	 * @return
	 */
	public static String filterString(final String str, final String filterStr) {

		final int index = str.indexOf(filterStr);
		final int len = filterStr.length() + 1;

		if (filterStr.equals("") || (index == -1)) {
			return str;
		} else if (index == 0) {
			return str.substring(len);
		} else {
			if (index + len > str.length()) {
				return str.substring(0, index);
			} else {
				return str.substring(0, index) + str.substring(index + len);
			}
		}
	}

	public static String convertStringToDoubleBytes(final String s) {

		final StringBuffer buffer = new StringBuffer("");

		int delimiters = 0;
		for (int i = 0; i < s.length(); i++) {
			final char c = s.charAt(i);
			if (isDelimiter(c)) {
				delimiters++;
			} else if (delimiters != 0) {
				if (delimiters % 2 != 0) {
					buffer.append(" ");
				}
				delimiters = 0;
			}
			buffer.append(c);
		}

		return buffer.toString();
	}

	public static String formatYuanToFen(String input) {
		String out = "";
		NumberFormat ft = NumberFormat.getInstance();
		Number nbInput;
		try {
			nbInput = ft.parse(input);
			double fInput = nbInput.doubleValue() * 100.0;
			ft.setGroupingUsed(false);
			ft.setMaximumFractionDigits(0);
			out = ft.format(fInput);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return out;
	}

	public static String formatFenToYuan(String input) {

		BigDecimal orderAmount = new BigDecimal(input);
		BigDecimal decimal = new BigDecimal(100);

		input = String.valueOf(orderAmount.divide(decimal));

		return new DecimalFormat("###0.00").format(Double.parseDouble(input));
	}

	public static boolean isDelimiter(final char c) {
		return (c > 0) && (c < 0xA0);
	}

	/**
	 * 选中的值是否在系统中存在
	 * 
	 * @param chooseValue
	 *            选中的值
	 * @param values
	 *            系统集合
	 * @return
	 */
	public static boolean isValueExistence(String chooseValue, String values) {
		boolean isTrue = false;
		String[] arrayValues = values.split(",");
		for (int i = 0; i < arrayValues.length; i++) {
			if (chooseValue.equals(arrayValues[i])) {
				isTrue = true;
				break;
			}
		}
		return isTrue;
	}

	/**
	 * 截取时间字符串
	 * 
	 * @param src
	 *            需要截取的字符串
	 * @return 返回最终字符串
	 */
	public static String subStringDataTime(String src) {
		if (src == null) {
			return "";
		}
		if (!src.equals("") && src.length() > 8) {
			src = src.substring(0, 8);
			return src;
		} else {
			return src;
		}
	}

	/**
	 * 判断某个字符串是否存在于数组中
	 * 
	 * @param stringArray
	 *            原数组
	 * @param source
	 *            查找的字符串
	 * @return 是否找到
	 */
	public static boolean contains(String[] stringArray, String source) {
		// 转换为list
		List<String> tempList = Arrays.asList(stringArray);

		// 利用list的包含方法,进行判断
		if (tempList.contains(source)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 生成指定长度随机数
	 * 
	 * @param numLeng
	 * @return
	 */
	public static String getRandomNum(int numLeng) {

		// 35是因为数组是从0开始的，26个字母+10个数字
		final int maxNum = 36;

		int i; // 生成的随机数

		int count = 0;

		char[] str = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

		StringBuffer StrSB = new StringBuffer();

		Random r = new Random();

		while (count < numLeng) {
			// 生成随机数，取绝对值，防止生成负数，

			i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1

			if (i >= 0 && i < str.length) {
				StrSB.append(str[i]);
				count++;
			}
		}
		return StrSB.toString();
	}

	/**
	 * 字符串风格由驼峰形式转换成下划线形式
	 * @param str
	 * @return
	 */
	public static String trans(String str) {

		List record = new ArrayList();
		for (int i = 0; i < str.length(); i++) {
			char tmp = str.charAt(i);

			if ((tmp <= 'Z') && (tmp >= 'A')) {
				record.add(i);// 记录每个大写字母的位置
			}

		}
		//record.remove(0);// 第一个不需加下划线

		str = str.toLowerCase();
		char[] charofstr = str.toCharArray();
		String[] t = new String[record.size()];
		for (int i = 0; i < record.size(); i++) {
			t[i] = "_" + charofstr[(int) record.get(i)];// 加“_”
		}
		String result = "";
		int flag = 0;
		for (int i = 0; i < str.length(); i++) {
			if ((flag < record.size()) && (i == (int) record.get(flag))) {
				result += t[flag];
				flag++;
			} else
				result += charofstr[i];
		}

		return result;
	}

	public static void main(String[] args) {
		//System.out.println(formatMobile("18628022917", false));
		String hh = "startTime";
		//System.out.println(trans(hh));
	}

	/**
	 * 将整形转换为固定长度字符串，自动补零
	 * @param number
	 * @param length
	 * @return
	 */
	public static String getFixedLengthNumber(Integer number, Integer length){
		//得到一个NumberFormat的实例
		NumberFormat nf = NumberFormat.getInstance();
		//设置是否使用分组
		nf.setGroupingUsed(false);
		//设置最大整数位数
		nf.setMaximumIntegerDigits(length);
		//设置最小整数位数
		nf.setMinimumIntegerDigits(length);
		return nf.format(number);
	}
	
	//效验
    public static boolean sqlValidate(String str) {
        str = str.toLowerCase();//统一转为小写
        String badStr = "'|and|exec|execute|insert|select|delete|update|count|drop|*|%|chr|mid|master|truncate|" +
                "char|declare|sitename|net user|xp_cmdshell|;|or|-|+|,|like'|and|exec|execute|insert|create|drop|" +
                "table|from|grant|use|group_concat|column_name|" +
                "information_schema.columns|table_schema|union|where|select|delete|update|order|by|count|*|" +
                "chr|mid|master|truncate|char|declare|or|;|-|--|+|,|like|//|/|%|#";//过滤掉的sql关键字，可以手动添加
        String[] badStrs = badStr.split("\\|");
        for (int i = 0; i < badStrs.length; i++) {
            if (str.indexOf(badStrs[i]) >= 0) {
                return true;
            }
        }
        return false;
    }
}
