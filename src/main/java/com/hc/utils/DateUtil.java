package com.hc.utils;

import org.apache.commons.lang3.time.DurationFormatUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/** 
 * 说明：日期处理
 * 创建人：
 * 修改时间：2015年11月24日
 * @version
 */
public class DateUtil {
	
	private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
	private final static SimpleDateFormat sdfMonth = new SimpleDateFormat("MM");
	private final static SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
	private final static SimpleDateFormat sdfDays = new SimpleDateFormat("yyyyMMdd");
	private final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private final static SimpleDateFormat sdfTimeNotModifier = new SimpleDateFormat("yyyyMMddHHmmss");
	private final static SimpleDateFormat sdfYearMonth = new SimpleDateFormat("yyyy-MM");

	/**
	 * 获取YYYY格式
	 * @return
	 */
	public static String getYear() {
		return sdfYear.format(new Date());
	}
	
	public static String getMonth() {
		return sdfMonth.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD格式
	 * @return
	 */
	public static String getDay() {
		return sdfDay.format(new Date());
	}

	public static String getYearMonth(){
		return sdfYearMonth.format(new Date());
	}

	public static String getYearMonth(Date date){
		return sdfYearMonth.format(date);
	}

	public static Date getYearMonthDate(String date){
		try {
			return sdfYearMonth.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 获取YYYYMMDD格式
	 * @return
	 */
	public static String getDays(){
		return sdfDays.format(new Date());
	}

	/**
	 * 获取yyyyMMddHHmmss格式
	 * @return
	 */
	public static String getTimeNotModifier() {
		return sdfTimeNotModifier.format(new Date());
	}

	/**
	 * 获取yyyyMMddHHmmss格式
	 * @return
	 */
	public static String getTimeNotModifier(Date date) {
		return sdfTimeNotModifier.format(date);
	}


	/**
	 * 将yyyyMMddHHmmss转换为yyyy-MM-dd HH:mm:ss
	 * @param dateStr
	 * @return
	 */
	public static String convertTimeNotModifier(String dateStr){
		try {
			Date date =  sdfTimeNotModifier.parse(dateStr);
			return sdfTime.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 将yyyy-MM-dd HH:mm:ss转换为yyyyMMddHHmmss
	 * @param dateStr
	 * @return
	 */
	public static String convertTime(String dateStr){
		try {
			Date date =  sdfTime.parse(dateStr);
			return sdfTimeNotModifier.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取YYYY-MM-DD HH:mm:ss格式
	 * @return
	 */
	public static String getTime() {
		return sdfTime.format(new Date());
	}
	/**
	* @Title: compareDate
	* @Description: TODO(日期比较，如果s>=e 返回true 否则返回false)
	* @param s
	* @param e
	* @return boolean  
	* @throws
	* 
	 */
	public static boolean compareDate(String s, String e) {
		if(fomatDate(s)==null||fomatDate(e)==null){
			return false;
		}
		return fomatDate(s).getTime() >=fomatDate(e).getTime();
	}

	/**
	 * 格式化日期
	 * @return
	 */
	public static Date fomatDate(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 校验日期是否合法
	 * @return
	 */
	public static boolean isValidDate(String s) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fmt.parse(s);
			return true;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return false;
		}
	}
	
	/**
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static int getDiffYear(String startTime,String endTime) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			//long aa=0;
			int years=(int) (((fmt.parse(endTime).getTime()-fmt.parse(startTime).getTime())/ (1000 * 60 * 60 * 24))/365);
			return years;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return -1;
		}
	}
	
	/**
	 * 计算时间差（按分钟）
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static int getDiffMinute(String startTime,String endTime) {
		DateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");
		try {
			//long aa=0;
			//System.out.println(fmt.parse(endTime).getTime()-fmt.parse(startTime).getTime());
			int minute=(int) ((fmt.parse(endTime).getTime()-fmt.parse(startTime).getTime())/ (1000 * 60));
			return minute;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return -1;
		}
	}
	 
	/**
     * <li>功能描述：时间相减得到天数
     * @param beginDateStr
     * @param endDateStr
     * @return
     * long 
     * @author Administrator
     */
    public static long getDaySub(String beginDateStr,String endDateStr){
        long day=0;
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        java.util.Date beginDate = null;
        java.util.Date endDate = null;

            try {
				beginDate = format.parse(beginDateStr);
				endDate= format.parse(endDateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
            day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
            ////System.out.println("相隔的天数="+day);
      
        return day;
    }
    
    /**
     * 得到n天之后的日期
     * @param days
     * @return
     */
    public static String getAfterDayDate(String days) {
    	int daysInt = Integer.parseInt(days);
    	
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        
        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdfd.format(date);
        
        return dateStr;
    }

	/**
	 * 得到n天之后的日期，格式yyyy-MM-dd HH:mm:ss
	 * @param days
	 * @return
	 */
	public static Date getAfterDayDate(Date date, Integer days) {

		Calendar calendar = Calendar.getInstance(); // java.util包
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days); // 日期减 如果不够减会将月变动

		return calendar.getTime();
	}

	/**
	 * 得到n月之后的日期
	 * @param months
	 * @return
	 */
	public static Date getAfterMonthDate(Date date, Integer months) {

		Calendar calendar = Calendar.getInstance(); // java.util包
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, months); // 日期减 如果不够减会将月变动

		return calendar.getTime();
	}

    
    /**
     * 得到n天之后的日期
     * @param days
     * @return
     */
    public static String getAfterDaysDate(String days, String formatDate) {
    	int daysInt = Integer.parseInt(days);
    	
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        
        SimpleDateFormat sdfd = new SimpleDateFormat(formatDate);
        String dateStr = sdfd.format(date);
        
        return dateStr;
    }
    
    
    /**
     * 得到n天之后是周几
     * @param days
     * @return
     */
    public static String getAfterDayWeek(String days) {
    	int daysInt = Integer.parseInt(days);
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("E");
        String dateStr = sdf.format(date);
        return dateStr;
    }
    
    public static void main(String[] args) {
    	//System.out.println(getDiffMinute("20170519175009","20170519175309"));
    }

	public static String getMonthSub(Date startDate, Date endDate){
		String duration = DurationFormatUtils.formatPeriod(startDate.getTime(), endDate.getTime(), "M");
		Integer i = Integer.parseInt(duration);
		i++;
		return  i+"";
	}
}
