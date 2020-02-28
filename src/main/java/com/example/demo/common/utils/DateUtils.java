package com.example.demo.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * fun:日期 时间 工具类
 * 
 * @author admin
 *
 */
public class DateUtils {

	/** 定义常量 **/
	public static final String DATE_GATHER = "MM-dd";
	public static final String DATE_JFP_STR = "yyyyMM";
	public static final String DATE_JF_STR = "yyyy-MM";
	public static final String DATE_FULL_STR = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_SMALL_STR = "yyyy-MM-dd";
	public static final String DATE_KEY_STR = "yyMMddHHmmss";
	public static final String DATE_DAY_STR = "yyMMddHHmm";
	public static final String DATE_KEY_FULL_STR = "yyyyMMddHHmmssSSS";
	public static final String DATE_KEY_ID = "yyMMddHHmmssSSS";

	public static final String DATE_DAY = "dd日 HH:mm:ss";
	public static final String DATE_DAY_H = "HH:mm:ss";
	public static final String DATE_DAY_HH = "HH";

	/**
	 * 获取时间戳+主键
	 * 
	 * @return
	 */
	public static String getID() {
		/** 当前日期 **/
		String key = DateUtils.getNowTime(DATE_KEY_STR);
		/** 添加3为随机码 **/
		String random = genRandomNum(3);
		return key + random;
	}

	/**
	 * 给定一个日期，返回加减n天后的日期
	 * 
	 * @param basicDate
	 * @param n
	 * @return
	 */
	public static String getLastTime(Date basicDate, int n) {
		SimpleDateFormat df = new SimpleDateFormat(DATE_FULL_STR);
		Calendar rightNow = Calendar.getInstance();
		rightNow.add(Calendar.DAY_OF_MONTH, n);
		return df.format(rightNow.getTime());
	}

	/**
	 * 使用预设格式提取字符串日期
	 * 
	 * @param strDate 日期字符串
	 * @return "yyyy-MM-dd HH:mm:ss"
	 */
	public static Date parse(String strDate) {
		return parse(strDate, DATE_FULL_STR);
	}

	/**
	 * 使用用户格式提取字符串日期
	 * 
	 * @param strDate 日期字符串
	 * @param pattern 日期格式
	 * @return
	 */
	public static Date parse(String strDate, String pattern) {
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		try {
			return df.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 与当前时间比较大小
	 * 
	 * @param date
	 * @return
	 */
	public static int compareDateWithNow(Date date1) {
		return compareDate(new Date(),date1);
	}

	/**
	 * 与当前时间比较指定差值大小(时间戳比较)。
	 * 	当timestamp与当前时间戳差值>diff时,return 1;当timestamp与当前时间戳差值<diff时,return -1;当timestamp与当前时间戳差值=diff时,return 0;
	 * @param timestamp 时间戳
	 * @param diff	差值
	 * @return 
	 */
	public static int compareDateWithNow(long timestamp,long diff) {
		long date2 = dateToUnixTimestamp();
		if (timestamp > date2-diff) {
			return 1;
		}else if (timestamp < date2-diff) {
			return -1;
		} else {
			return 0;
		}
	}

	/**
	 *返回 两个时间分钟差值
	 * @return
	 */
	public static long getDistanceTime(Date one, Date two) {
		long diff = 0;
		long time1 = one.getTime();
		long time2 = two.getTime();
		if (time2 > time1) {
			diff = (time2-time1) / (60 * 1000); // 分钟
		}else if(time2 < time1) {
			diff = (time1-time2) / (60 * 1000); // 分钟
		}
		return diff;
	}

	/**
	 * 获取系统当前时间
	 * @return "yyyy-MM-dd HH:mm:ss"
	 */
	public static String getNowTime() {
		return getNowTime(DATE_FULL_STR);
	}

	/**
	 * 获取系统当前时间date
	 * 
	 * @return
	 */
	public static Date getNowDate() {
		SimpleDateFormat df = new SimpleDateFormat(DATE_FULL_STR);
		try {
			return df.parse(df.format(new Date()));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取系统当前时间(年)
	 * 
	 * @return
	 */
	public static int getNowYear() {
		return Integer.parseInt(getNowTime("yyyy"));
	}

	/**
	 * 根据时间格式获取系统当前时间
	 * 
	 * @param dateFormat 格式
	 * @return
	 */
	public static String getNowTime(String dateFormat) {
		return getTime(new Date(), dateFormat);
	}

	/**
	 * 获取指定时间字符串
	 * 
	 * @param time       时间 （Date型）
	 * @param dateFormat 格式
	 * @return
	 */
	public static String getTime(Date time, String dateFormat) {
		return new SimpleDateFormat(dateFormat).format(time);
	}

	/**
	 * 将指定的日期转换成Unix时间戳
	 * 
	 * @param String date 需要转换的日期 yyyy-MM-dd HH:mm:ss
	 * @return long 时间戳
	 */
	public static long dateToUnixTimestamp(String date) {
		return dateToUnixTimestamp(date, DATE_FULL_STR);
	}

	/**
	 * 将指定的日期转换成Unix时间戳
	 * 
	 * @param date
	 * @param dateFormat 需要转换的日期
	 * @return long 13位时间戳
	 */
	public static long dateToUnixTimestamp(String date, String dateFormat) {
		long timestamp = 0;
		try {
			timestamp = new SimpleDateFormat(dateFormat).parse(date).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return timestamp;
	}

	/**
	 * 将当前日期转换成Unix时间戳
	 * 
	 * @return long 13位时间戳
	 */
	public static long dateToUnixTimestamp() {
		return dateToUnixTimestamp(false);
	}

	/**
	 * 将当前日期转换成Unix时间戳
	 * 
	 * @param isSecend 是否取秒的时间戳 ,true取秒；false取毫秒
	 * @return
	 */
	public static long dateToUnixTimestamp(boolean isSecend) {
		long timestamp = new Date().getTime();
		if (isSecend) {
			timestamp = timestamp / 1000;
		}
		return timestamp;
	}

	/**
	 * 将Unix时间戳转换成日期（使用东8区即北京时间时区）
	 * 
	 * @param timestamp 时间戳 当位数小于13时，自动补0；大于13时，截掉13位以后的数字
	 * @return String 日期字符串 (yyyy-MM-dd HH:mm:ss)
	 */
	public static String unixTimestampToDate(long timestamp) {
		if (String.valueOf(timestamp).length() < 13) {
			StringBuilder sb = new StringBuilder(String.valueOf(timestamp));
			for (int i = 1; i <= 13 - String.valueOf(timestamp).length(); i++) {
				sb.append(0);
			}
			timestamp = Long.parseLong(sb.toString());
		} else if (String.valueOf(timestamp).length() > 13) {
			long n = (long) Math.pow(10, String.valueOf(timestamp).length() - 13);
			timestamp = timestamp / n;
		}
		SimpleDateFormat sd = new SimpleDateFormat(DATE_FULL_STR);
		sd.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		return sd.format(new Date(timestamp));
	}

	/**
	 * 获取指定长度随机数
	 * 
	 * @param len 长度
	 * @return
	 */
	public static String genRandomNum(int len) {
		String[] beforeShuffle = new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		List<String> list = Arrays.asList(beforeShuffle);
		Collections.shuffle(list);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i));
		}
		return sb.toString().substring(0, len);
	}

	/**
	 * 
	 * 获取两个日期相差的天数
	 * 
	 * @param beginDateStr 日期1 （yyyy-MM-dd）
	 * @param endDateStr   日期2（yyyy-MM-dd）
	 * @return 天数long型
	 */
	public static long getDaySub(String beginDateStr, String endDateStr) {
		long day = 0;
		SimpleDateFormat format = new SimpleDateFormat(DATE_SMALL_STR);
		Date beginDate;
		Date endDate;
		try {
			beginDate = format.parse(beginDateStr);
			endDate = format.parse(endDateStr);
			day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return day;
	}

	/**
	 * 将时间点字符串格式化
	 * 
	 * @param date 格式：yyyyMMddhhmmss
	 * @return yyyy-MM-dd hh:mm:ss
	 */
	public static String parseDate(String date) {
		String reg = "(\\d{4})(\\d{2})(\\d{2})(\\d{2})(\\d{2})(\\d{2})";
		date = date.replaceAll(reg, "$1-$2-$3 $4:$5:$6");
		return date;

	}

	/**
	 * 给日期加指定月数
	 * 
	 * @param date 日期字符串 (yyyy-mm)
	 * @param num  月数
	 * @return
	 */
	public static String dateMonAdd(String date, int num) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(parse(date, DATE_JF_STR));

		calendar.add(Calendar.MONTH, num);

		return format.format(calendar.getTime());
	}

	/**
	 * 返回给定年的天数
	 * 
	 * @param year
	 * @return
	 */
	public static int getdays(int year) {
		int days = 0;
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {// 闰年的判断规则
			days = 366;
		} else {
			days = 365;
		}
		return days;
	}

	/**
	 * 比较两个时间大小
	 * 
	 * @param old
	 * @param newdate
	 * @return old<newdate返回1;old>newdate返回-1;old=newdate返回0;
	 */
	public static int compareDate(Date old, Date newdate) {
		int i = newdate.compareTo(old);
		return i;
	}

	/**
	 * 判断当前日期是星期几
	 * 
	 * @return
	 **/
	public static String dayForWeek() {
		Calendar cal = Calendar.getInstance();
//      String[] weekDays = { "7", "1", "2", "3", "4", "5", "6" };
		String[] weekDaysEng = { "SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT" };
		try {
			cal.setTime(new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 指示一个星期中的某天。
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0) {
			w = 0;
		}
		return weekDaysEng[w];
	}

	/**
	 * 将日期转换为字符串
	 * 
	 * @param date 待转换的日期
	 * @return 日期字符串 (yyyy-mm-dd)
	 */
	public static String dateToString(Date date) {
		if (null == date) {
			return "1900-01-01";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
}
