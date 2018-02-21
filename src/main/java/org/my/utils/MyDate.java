package org.my.utils;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DurationFormatUtils;
import org.apache.commons.lang.math.NumberUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期常用工具类
 * @author 欧阳洁
 */
public class MyDate {

	private static String[] m_nMonth = { "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC" };

	public static void main(String[] args) {
		System.out.println(MyDate.formatToStr(new Date(), "yyyy-M-d"));
	}

	/**
	 * 验证日期格式是否合法
	 * 
	 * @param strDate
	 *            需要验证的日期字符串
	 * @param format
	 *            指定日期字符串的格式，如果不输入默认yyyy-MM-dd
	 * @return
	 */
	public static boolean isDate(String strDate, String... format) {
		String fstr = "yyyy-MM-dd";
		if (format.length == 1) {
			fstr = format[0];
		}
		// 指定日期格式为四位年/两位月份/两位日期，注意yyyy-MM-dd区分大小写；
		SimpleDateFormat formatdate = new SimpleDateFormat(fstr);
		// 设置lenient为false.
		// 否则SimpleDateFormat会比较宽松地验证日期，比如2007-02-29会被接受，并转换成2007-03-01
		formatdate.setLenient(false);
		try {
			formatdate.parse(strDate);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	/**
	 * 验证日期时间格式
	 * 
	 * @param datetime
	 *            需要验证的日期时间字符串
	 * 
	 * @param format
	 *            指定日期字符串的格式，如果不输入默认yyyy-MM-dd HH:mm:ss
	 * 
	 * @return boolean 如果格式合法返回true
	 */
	public static boolean isDatetime(String datetime, String... format) {
		String fstr = "yyyy-MM-dd HH:mm:ss";
		if (format.length == 1) {
			fstr = format[0];
		}
		return isDate(datetime, fstr);
	}

	/**
	 * 得到现在日期时间
	 * 
	 * @return new Date()
	 */
	public static Date getNow() {
		return new Date();
	}

	/**
	 * 
	 * @return 获取当前日期 返回短时间格式 yyyy-MM-dd
	 */
	public static Date getNowDateShort() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		ParsePosition pos = new ParsePosition(0);
		Date currentTime_2 = formatter.parse(dateString, pos);
		return currentTime_2;
	}

	/**
	 * 根据用户传入的时间表示格式，返回当前时间的格式 如果是yyyyMMdd，注意字母y不能大写。
	 * 
	 * @param sformat
	 *            yyyyMMddHHmmss
	 * @return
	 */
	public static String getUserDate(String sformat) {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(sformat);
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 获取现在日期时间
	 * 
	 * @return 返回字符串格式 yyyy-MM-dd HH:mm:ss
	 */
	public static String getStringDate() {
		return getUserDate("yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 获取现在时间
	 * 
	 * @return 返回短时间字符串格式yyyy-MM-dd HHmm
	 */
	public static String getStringDateShortmm() {
		return getUserDate("yyyy-MM-dd HHmm");
	}

	/**
	 * 获取现在日期
	 * 
	 * @return 返回短时间字符串格式yyyy-MM-dd
	 */
	public static String getStringDateShort() {
		return getUserDate("yyyy-MM-dd");
	}

	/**
	 * 获取现在时间
	 * 
	 * @return 返回时间字符串格式HH:mm:ss
	 */
	public static String getStringTime() {
		return getUserDate("HH:mm:ss");
	}

	/**
	 * 获取现在时间
	 * 
	 * @return 返回短时间字符串格式HH:mm
	 */
	public static String getTimeShort() {
		return getUserDate("HH:mm");
	}

	/**
	 * 得到现在小时
	 * 
	 * @return HH
	 */
	public static String getHour() {
		String dateString = getStringDate();
		return dateString.substring(11, 13);
	}

	/**
	 * 得到现在分钟
	 * 
	 * @return mm
	 */
	public static String getTime() {
		String dateString = getStringDate();
		return dateString.substring(14, 16);
	}

	/**
	 * 得到现在秒钟
	 * 
	 * @return ss
	 */
	public static String getSecond() {
		String dateString = getStringDate();
		return dateString.substring(17, 19);
	}

	/**
	 * 将指定格式的字符串转为日期时间对象
	 * 
	 * @param strDate
	 *            时间
	 * @param format
	 *            格式
	 * @return 时间 date
	 */
	public static Date formatToDate(String strDate, String format) {
		if (strDate == null) {
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	/**
	 * 将日期时间对象转为 指定格式的字符串
	 * 
	 * @param strDate
	 * @param format
	 * @return string
	 */
	public static String formatToStr(Date dateDate, String format) {
		if (dateDate == null) {
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String dateString = formatter.format(dateDate);
		return dateString;
	}

	/**
	 * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date strToDateLong(String strDate) {
		return formatToDate(strDate, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateDate
	 * @return
	 */
	public static String dateToStrLong(Date dateDate) {
		return formatToStr(dateDate, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 将短时间格式字符串转换为时间 yyyy-MM-dd
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date strToDate(String strDate) {
		return formatToDate(strDate, "yyyy-MM-dd");
	}

	/**
	 * 将短时间格式时间转换为字符串 yyyy-MM-dd
	 * 
	 * @param dateDate
	 * @param k
	 * @return
	 */
	public static String dateToStr(Date dateDate) {
		return formatToStr(dateDate, "yyyy-MM-dd");
	}

	/**
	 * 二个时间之间相差的小时，返回可以为小数 st1 - st2
	 * 
	 * @param st1
	 *            HH:mm格式的时间
	 * @param st2
	 *            HH:mm格式的时间
	 * @return 返回 st1 - st2 之间间隔的小时
	 */
	public static double getTwoHourShort(String st1, String st2) {
		Date d1 = formatToDate(st1, "HH:mm");
		Date d2 = formatToDate(st2, "HH:mm");
		double l = d1.getTime() - d2.getTime();
		return (l / 1000) / 60 / 60;
	}

	/**
	 * 二个时间之间相差的分钟，返回可以为小数 st1 - st2
	 * 
	 * @param st1
	 *            HH:mm格式的时间
	 * @param st2
	 *            HH:mm格式的时间
	 * @return 返回 st1 - st2 之间间隔的分钟
	 */
	public static double getTwoMinShort(String st1, String st2) {
		Date d1 = formatToDate(st1, "HH:mm");
		Date d2 = formatToDate(st2, "HH:mm");
		double l = d1.getTime() - d2.getTime();
		return (l / 1000) / 60;
	}

	/**
	 * 判断现在时间是否在2个时间之间 处理下面几种情况 正常的 00:00-08:00 开始时间比结束时间大的 20:00-01:00
	 * 岔开的05:00-02:00
	 * 
	 * 
	 * @param sxsj
	 *            00:00-08:00,10:00-17:00
	 * 
	 */
	public static boolean getSxsjIsOk(String sxsj) {
		if (StringUtils.isBlank(sxsj)) {
			return true;
		}
		String[] s = sxsj.split(",");
		String nt = MyDate.getUserDate("HH:mm");
		for (String o : s) {
			String[] o1 = o.split("-");
			if (o1.length == 2) {
				if (nt.compareTo(o1[0]) >= 0 && nt.compareTo(o1[1]) <= 0) {
					return true;
				} else {
					// 如果是反过来设置的 23:00-06:00 08:00-01:00
					if (o1[0].compareTo(o1[1]) > 0) {
						if (nt.compareTo(o1[1]) >= 0
								&& nt.compareTo(o1[0]) <= 0) {
							return false;
						} else {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	/**
	 * 得到二个日期间的间隔月数 同一个月返回0
	 * 
	 * @param date1
	 *            较大的日期 格式是 yyyy-MM-dd
	 * @param date2
	 *            较小的日期 格式是 yyyy-MM-dd
	 * @return 间隔的月数 date1 - date2
	 */
	public static int getTwoMonth(String date1, String date2) {
		try {
			long d1 = formatToDate(date1, "yyyy-MM-dd").getTime();
			long d2 = formatToDate(date2, "yyyy-MM-dd").getTime();

			if (d1 < d2) {
				return -Integer.valueOf(DurationFormatUtils.formatPeriod(d1, d2, "M"));
			} else {
				return Integer.valueOf(DurationFormatUtils.formatPeriod(d2, d1, "M"));
			}
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 得到二个日期间的间隔天数 同一个日期返回0
	 * 
	 * @param date1
	 *            较大的日期 格式是 yyyy-MM-dd
	 * @param date2
	 *            较小的日期 格式是 yyyy-MM-dd
	 * @return 间隔的天数 date1 - date2
	 */
	public static int getTwoDay(String date1, String date2) {
		try {
			Date d1 = formatToDate(date1, "yyyy-MM-dd");
			Date d2 = formatToDate(date2, "yyyy-MM-dd");
			Long day = (d1.getTime() - d2.getTime()) / (24 * 60 * 60 * 1000);
			return day.intValue();
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 得到二个日期间的间隔天数 同一个日期返回0
	 * 
	 * @param date1
	 *            较大的日期 格式是 yyyy-MM-dd
	 * @param date2
	 *            较小的日期 格式是 yyyy-MM-dd
	 * @return 间隔的天数 date1 - date2
	 */
	public static int getTwoDay(Date date1, Date date2) {
		try {
			Long day = (date1.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000);
			return day.intValue();
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 得到二个日期间的间隔分钟 同一个日期时间返回0
	 * 
	 * @param datettime1
	 *            较大的日期 格式是 yyyy-MM-dd HH:mm:ss
	 * @param datettime2
	 *            较小的日期 格式是 yyyy-MM-dd HH:mm:ss
	 * @return 间隔的分钟 datettime1 - datettime2
	 */
	public static int getTwoMin(String datettime1, String datettime2) {
		try {
			Date d1 = formatToDate(datettime1, "yyyy-MM-dd HH:mm:ss");
			Date d2 = formatToDate(datettime2, "yyyy-MM-dd HH:mm:ss");
			Long day = (d1.getTime() - d2.getTime()) / (60 * 1000);
			return day.intValue();
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 得到二个日期间的间隔分钟 同一个日期时间返回0
	 * 
	 * @param datettime1
	 *            较大的日期 格式是 yyyy-MM-dd HH:mm:ss
	 * @param datettime2
	 *            较小的日期 格式是 yyyy-MM-dd HH:mm:ss
	 * @return 间隔的分钟 datettime1 - datettime2
	 */
	public static int getTwoMin(Date datettime1, Date datettime2) {
		try {
			Long day = (datettime1.getTime() - datettime2.getTime()) / (60 * 1000);
			return day.intValue();
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 得到二个日期间的间隔秒 同一个日期时间返回0
	 * 
	 * @param datettime1
	 *            较大的日期 格式是 yyyy-MM-dd HH:mm:ss
	 * @param datettime2
	 *            较小的日期 格式是 yyyy-MM-dd HH:mm:ss
	 * @return 间隔的秒数 datettime1 - datettime2
	 */
	public static int getTwoSec(String datettime1, String datettime2) {
		try {
			Date d1 = formatToDate(datettime1, "yyyy-MM-dd HH:mm:ss");
			Date d2 = formatToDate(datettime2, "yyyy-MM-dd HH:mm:ss");
			Long day = (d1.getTime() - d2.getTime()) / 1000;
			return day.intValue();
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 得到二个日期间的间隔秒 同一个日期时间返回0
	 * 
	 * @param datettime1
	 *            较大的日期 格式是 yyyy-MM-dd HH:mm:ss
	 * @param datettime2
	 *            较小的日期 格式是 yyyy-MM-dd HH:mm:ss
	 * @return 间隔的秒数 datettime1 - datettime2
	 */
	public static int getTwoSec(Date datettime1, Date datettime2) {
		try {
			Long day = (datettime1.getTime() - datettime2.getTime()) / 1000;
			return day.intValue();
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 时间前推或后推秒钟,其中second表示秒钟数
	 * 
	 * @param date
	 *            yyyy-MM-dd HH:mm:ss
	 * @param second
	 *            向前推为负数，向后推为正数
	 * @return
	 */
	public static Date getPreSec(Date date, int second) {
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.add(Calendar.SECOND, second);
		return gc.getTime();
	}

	/**
	 * 时间前推或后推秒钟,其中second表示秒钟数
	 * 
	 * @param datetime
	 *            yyyy-MM-dd HH:mm:ss
	 * @param second
	 *            向前推为负数，向后推为正数
	 * @return
	 */
	public static String getPreSec(String datetime, int second) {
		Date date = strToDateLong(datetime);
		return dateToStrLong(getPreSec(date, second));
	}

	/**
	 * 时间前推或后推分钟,其中min表示分钟数
	 * 
	 * @param datetime
	 *            yyyy-MM-dd HH:mm:ss
	 * @param min
	 *            向前推为负数，向后推为正数
	 * @return
	 */
	public static Date getPreMin(Date datetime, int min) {
		Calendar gc = Calendar.getInstance();
		gc.setTime(datetime);
		gc.add(Calendar.MINUTE, min);
		return gc.getTime();
	}

	/**
	 * 时间前推或后推分钟,其中min表示分钟数
	 * 
	 * @param datetime
	 *            yyyy-MM-dd HH:mm:ss
	 * @param min
	 *            向前推为负数，向后推为正数
	 * @return
	 */
	public static String getPreMin(String datetime, int min) {
		Date date = strToDateLong(datetime);
		return dateToStrLong(getPreMin(date, min));
	}

	/**
	 * 日期前推或后推天数,其中day表示天数
	 * 
	 * @param dateDate
	 *            yyyy-MM-dd
	 * @param day
	 *            向前推为负数，向后推为正数
	 * @return
	 */
	public static Date getPreDay(Date dateDate, int day) {
		Calendar gc = Calendar.getInstance();
		gc.setTime(dateDate);
		gc.add(Calendar.DATE, day);
		return gc.getTime();
	}

	/**
	 * 日期前推或后推天数,其中day表示天数
	 * 
	 * @param date
	 *            yyyy-MM-dd
	 * @param day
	 *            向前推为负数，向后推为正数
	 * @return
	 */
	public static String getPreDay(String date, int day) {
		Date dateDate = strToDate(date);
		return dateToStr(getPreDay(dateDate, day));
	}

	/**
	 * 日期前推或后推月数,其中month表示月数
	 * 
	 * @param dateDate
	 *            yyyy-MM-dd
	 * @param month
	 *            向前推为负数，向后推为正数
	 * @return
	 */
	public static Date getPreMonth(Date dateDate, int month) {
		Calendar gc = Calendar.getInstance();
		gc.setTime(dateDate);
		gc.add(Calendar.MONTH, month);
		return gc.getTime();
	}

	/**
	 * 日期前推或后推月数,其中month表示月数
	 * 
	 * @param date
	 *            yyyy-MM-dd
	 * @param month
	 *            向前推为负数，向后推为正数
	 * @return
	 */
	public static Date getPreMonth(String date, int month) {
		Date dateDate = strToDate(date);
		return getPreMonth(dateDate, month);
	}

	/**
	 * 返回英文简写星期 如星期五 返回 Fri
	 * 
	 * @param sdate
	 * @return
	 */
	public static String getWeekEn(String sdate) {
		Date date = MyDate.strToDate(sdate);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return new SimpleDateFormat("EEE", Locale.ENGLISH).format(c.getTime());
	}

	/**
	 * 返回数字星期 星期天是0 星期一是1
	 * 
	 * @param sdate
	 * @return
	 */
	public static int getWeekNum(String sdate) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(strToDate(sdate));
		return cal.get(Calendar.DAY_OF_WEEK) - 1;
	}

	/**
	 * 返回数字星期 星期天是0 星期一是1
	 * 
	 * @param sdate
	 * @return
	 */
	public static int getWeekNum(Date sdate) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(sdate);
		return cal.get(Calendar.DAY_OF_WEEK) - 1;
	}

	/**
	 * 返回日期的星期
	 * 
	 * @param sdate
	 * @return
	 */
	public static String getWeekXq(String sdate) {
		int w = getWeekNum(sdate);
		String s = "";
		switch (w) {
		case 0:
			s = "星期天";
			break;
		case 1:
			s = "星期一";
			break;
		case 2:
			s = "星期二";
			break;
		case 3:
			s = "星期三";
			break;
		case 4:
			s = "星期四";
			break;
		case 5:
			s = "星期五";
			break;
		case 6:
			s = "星期六";
			break;
		default:
			s = "";
			break;
		}
		return s;
	}

	/**
	 * 返回日期的周几
	 * 
	 * @param sdate
	 * @return
	 */
	public static String getWeekz(String sdate) {
		int w = getWeekNum(sdate);
		String s = "";
		switch (w) {
		case 0:
			s = "周日";
			break;
		case 1:
			s = "周一";
			break;
		case 2:
			s = "周二";
			break;
		case 3:
			s = "周三";
			break;
		case 4:
			s = "周四";
			break;
		case 5:
			s = "周五";
			break;
		case 6:
			s = "周六";
			break;
		default:
			s = "";
			break;
		}
		return s;
	}

	/**
	 * 返回日期的周几
	 * 
	 * @param sdate
	 * @return
	 */
	public static String getWeekz1(String sdate) {
		int w = getWeekNum(sdate);
		String s = "";
		switch (w) {
		case 0:
			s = "日";
			break;
		case 1:
			s = "一";
			break;
		case 2:
			s = "二";
			break;
		case 3:
			s = "三";
			break;
		case 4:
			s = "四";
			break;
		case 5:
			s = "五";
			break;
		case 6:
			s = "六";
			break;
		default:
			s = "";
			break;
		}
		
		return s;
	}

	/**
	 * 判断二个时间是否在同一个周
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameWeekDates(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(date1);
		cal2.setTime(date2);
		int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
		if (0 == subYear) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR)) {
				return true;
			}
		} else if (1 == subYear && 11 == cal2.get(Calendar.MONTH)) {
			// 如果12月的最后一周横跨来年第一周的话则最后一周即算做来年的第一周
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR)) {
				return true;
			}
		} else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH)) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获得一个日期所在月的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getMonthFirst(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		return c.getTime();
	}

	/**
	 * 获得一个日期所在月的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getMonthEnd(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));// 设置为最后一天,当前日期既为本月第一天
		return c.getTime();
	}

	/**
	 * 补齐开始日期到秒.使其格式为yyyy-MM-dd 00:00:00
	 * 
	 * @param date
	 *            补齐开始日期
	 * @return Date
	 */
	public static Date startDateTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 补齐结束日期到秒.使其格式为yyyy-MM-dd 23:59:59
	 * 
	 * @param date
	 *            补齐结束日期
	 * @return Date
	 */
	public static Date endDateTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

	/**
	 * 产生周序列,即得到当前时间所在的年度是第几周
	 * 
	 * @return 2015-03-16返回 201512
	 */
	public static String getSeqWeek() {
		Calendar c = Calendar.getInstance(Locale.CHINA);
		String week = Integer.toString(c.get(Calendar.WEEK_OF_YEAR));
		if (week.length() == 1) {
			week = "0" + week;
		}
		String year = Integer.toString(c.get(Calendar.YEAR));
		return year + week;
	}

	/**
	 * 获得一个日期所在的周的星期几的日期，如要找出2002年2月3日所在周的星期一是几号
	 * 
	 * @param sdate
	 * @param week
	 * @return 返回日期
	 */
	public static String getWeek(String sdate, int week) {
		// 再转换为时间
		Date dd = MyDate.strToDate(sdate);
		Calendar c = Calendar.getInstance();
		c.setTime(dd);
		if (1 == week) { // 返回星期一所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		} else if (2 == week) { // 返回星期二所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
		} else if (3 == week) { // 返回星期三所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
		} else if (4 == week) { // 返回星期四所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
		} else if (5 == week) { // 返回星期五所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
		} else if (6 == week) { // 返回星期六所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		} else if (0 == week) { // 返回星期日所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		}
		return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
	}

	// =========================

	/**
	 * 取得数据库主键 生成格式为yyyymmddhhmmss+k位随机数 表示是取几位随机数，可以自己定
	 */

	private static String randString = "";

	/**
	 * 保证一次产生10000个随机数内无重复 平均一秒钟能产生250个随机数
	 */
	public static synchronized String getNo(int k) {
		if (randString.length() > 20000) {
			randString = "";
		}
		String rno = getNoNo(k);
		while (randString.indexOf(rno + ",") >= 0) {
			rno = getNoNo(k);
		}
		randString += rno + ",";
		return rno;
	}

	private static String getNoNo(int k) {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return getUserDate("yyMMddHHmmss") + RandomStringUtils.randomNumeric(k);
	}

	/**
	 * 返回一个随机数
	 * 
	 * @param i
	 * @return
	 */
	public static String getRandom(int i) {
		Random jjj = new Random();

		if (i == 0) {
			return "";
		}
		String jj = "";
		for (int k = 0; k < i; k++) {
			jj = jj + jjj.nextInt(9);
		}
		return jj;
	}

	/**
	 * 根据出生日期获取年龄(实岁)
	 * 
	 * @param birthday
	 */
	public static int getAge(String birthday) {
		Date now = MyDate.getNow();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);

		Date csrq = MyDate.strToDate(birthday);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(csrq);

		return calendar.get(Calendar.YEAR) - calendar2.get(Calendar.YEAR);
	}

	/**
	 * 将英文月份转换成数字月份
	 */
	public static String getNumMonthByEn(String monthEn) {
		if ("JAN".equals(monthEn)) {
			return "01";
		} else if ("FEB".equals(monthEn)) {
			return "02";
		} else if ("MAR".equals(monthEn)) {
			return "03";
		} else if ("APR".equals(monthEn)) {
			return "04";
		} else if ("MAY".equals(monthEn)) {
			return "05";
		} else if ("JUN".equals(monthEn)) {
			return "06";
		} else if ("JUL".equals(monthEn)) {
			return "07";
		} else if ("AUG".equals(monthEn)) {
			return "08";
		} else if ("SEP".equals(monthEn)) {
			return "09";
		} else if ("OCT".equals(monthEn)) {
			return "10";
		} else if ("NOV".equals(monthEn)) {
			return "11";
		} else if ("DEC".equals(monthEn)) {
			return "12";
		}
		return monthEn;
	}

	/**
	 * 把2个日期，按月拆分成数组
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static List<Date[]> date2SplitMonth(Date startDate, Date endDate) {
		Date fn = startDate;
		Date tn = endDate;
		List<Date[]> l = new ArrayList<Date[]>();
		// 在一个月内的
		if (endDate.compareTo(getMonthEnd(startDate)) <= 0) {
			l.add(new Date[] { startDate, endDate });
		} else {
			fn = startDate;
			tn = getMonthEnd(startDate);
			l.add(new Date[] { fn, tn });
			// 下一个月第一天
			fn = getPreMonth(getMonthFirst(startDate), 1);
			while (endDate.compareTo(getMonthEnd(fn)) > 0) {
				l.add(new Date[] { fn, getMonthEnd(fn) });
				fn = getPreMonth(fn, 1);
			}
			l.add(new Date[] { getMonthFirst(endDate), endDate });
		}
		return l;
	}

	// 不适用日期 格式2015-03-02|2015-03-04,2015-03-07|2015-03-08
	/**
	 * 把 fromdate 到 todate 这个日期段，排除掉str日期后，组成的日期段
	 * 
	 * @param fromDate
	 * @param toDate
	 * @param str
	 * @return
	 */

	public static List<Date[]> dateExclude(Date fromDate, Date toDate, String str) {

		Set<Date> dset = new TreeSet<Date>();
		int pre = MyDate.getTwoDay(toDate, fromDate);
		for (int i = 0; i <= pre; i++) {
			dset.add(MyDate.getPreDay(fromDate, i));
		}
		String[] strsz = StringUtils.trimToEmpty(str).replaceAll("\\s", "")
				.split(",");
		for (String s : strsz) {

			String[] ssz = StringUtils.trimToEmpty(s).split("\\|");
			Date d1 = null;
			Date d2 = null;

			if (ssz.length > 0) {
				d1 = MyDate.strToDate(ssz[0]);
			}
			if (ssz.length > 1) {
				d2 = MyDate.strToDate(ssz[1]);
			}
			if (d1 == null && d2 == null) {
				continue;
			}
			if (d1 == null || d2 == null || d1.compareTo(d2) == 0) {
				d1 = (d1 == null ? d2 : d1);
				dset.remove(d1);
				continue;
			}
			if (d1.compareTo(d2) > 0) {
				Date dt = d1;
				d1 = d2;
				d2 = dt;
			}
			int p = MyDate.getTwoDay(d2, d1);

			for (int i = 0; i <= p; i++) {
				dset.remove(MyDate.getPreDay(d1, i));
			}
		}

		List<Date[]> list = new ArrayList<Date[]>();
		Iterator<Date> iter = dset.iterator();
		Date[] da = new Date[2];
		while (iter.hasNext()) {
			Date dd = iter.next();
			if (da[0] == null) {
				da[0] = dd;
				da[1] = dd;
			} else {
				if (dd.equals(MyDate.getPreDay(da[1], 1))) {
					da[1] = dd;
				} else {
					list.add(da);
					da = new Date[2];
					da[0] = dd;
					da[1] = dd;
				}
			}
		}
		if (da[0] != null && da[1] != null) {
			list.add(da);
		}

		return list;
	}

	/**
	 * 把一位月日时分秒格式化为2位
	 * 
	 * @param date
	 * @return
	 */
	public static String fmtDate(String date) {
		if (StringUtils.isBlank(date)) {
			return "";
		}
		String str = "";
		String[] yyhh = date.split(" ");
		if (yyhh.length > 0) { // yyyy-mm-dd
			String[] yymmddsz = yyhh[0].split("-");
			for (String s : yymmddsz) {
				if (s.length() == 1) {
					s = "0" + s;
				}
				if ("".equals(str)) {
					str = s;
				} else {
					str += "-" + s;
				}
			}
		}

		if (yyhh.length > 1) { // hh:mm:ss
			str += " ";
			String[] hhmmsssz = yyhh[1].split(":");
			for (int i = 0; i < hhmmsssz.length; i++) {
				String s = hhmmsssz[i];
				if (s.length() == 1) {
					s = "0" + s;
				}
				if (i == 0) {
					str += s;
				} else {
					str += ":" + s;
				}
			}
		}
		return str;
	}

	/**
	 * 把字符传格式为HH:mm 不是很精确的 12:00 12 1200
	 * 
	 */
	public static String Ftmwk(String tm) {
		tm = StringUtils.trimToEmpty(tm).replaceAll(":+ ", ":");
		if (tm.length() > 5) {
			tm = StringUtils.substring(tm, 0, 5);
		}
		int c = tm.indexOf(":");
		if (c == 2 && tm.length() == 5) {
			return tm;
		}
		if (tm.endsWith(":")) {
			tm += "0";
		}

		if (c > 0) {
			Date d = MyDate.formatToDate(tm, "HH:mm");
			tm = MyDate.formatToStr(d, "HH:mm");
		}
		if (tm == null) {
			return "00:00";
		}
		if (tm.length() == 1) {
			tm = "0" + tm + ":00";
		} else if (tm.length() == 2) {
			tm = tm + ":00";
		} else if (tm.length() == 3) {
			tm = tm + "00";
		} else if (tm.length() == 4) {
			tm = StringUtils.substring(tm, 0, 2) + ":" + StringUtils.substring(tm, 2);
		}
		if (tm.indexOf(":") < 0) {
			tm = StringUtils.substring(tm, 0, 2) + ":" + StringUtils.substring(tm, 2, 4);
		}
		return tm;
	}

	/**
	 * 用于返回指定日期格式的日期增加指定天数的日期
	 * 
	 * @param appDate
	 *            指定日期
	 * @param format
	 *            指定日期格式yyyy-MM-dd
	 * @param days
	 *            指定天数
	 * @return 指定日期格式的日期增加指定天数的日期
	 */
	public static String getFutureDay(String appDate, String format, int days) {
		String future = "";
		try {
			Calendar calendar = GregorianCalendar.getInstance();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
			Date date = simpleDateFormat.parse(appDate);
			calendar.setTime(date);
			calendar.add(Calendar.DATE, days);
			date = calendar.getTime();
			future = simpleDateFormat.format(date);
		} catch (Exception e) {
		}
		return future;
	}

	/**
	 * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数
	 * 
	 * @param nowdate
	 *            现在日期
	 * @param delay
	 *            delay为前移或后延的天数
	 * @return 日期
	 */
	public static String getNextDay(String nowdate, String delay) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String mdate = "";
		Date d = null;
		if (nowdate == null || "".equals(nowdate)) {
			d = new Date();
		} else {
			d = strToDate(nowdate);
		}

		long myTime = (d.getTime() / 1000) + Integer.parseInt(delay) * 24 * 60 * 60;
		d.setTime(myTime * 1000);
		mdate = format.format(d);
		return mdate;
	}

	/**
	 * 判断一个给定的日期字符串是否符合给定的格式，并且是有效的日期
	 * 
	 * @param date
	 *            给定的日期字符串
	 * @param format
	 *            要求的日期格式
	 * @return boolean 如果符合格式并且是有效的日期返回true 否则返回false
	 * @see [类、类#方法、类#成员]
	 */
	public static boolean isRightDate(String date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.format(sdf.parse(date)).equalsIgnoreCase(date);
		} catch (Exception ex) {
			return false;
		}
	}

	/**
	 * 返回美国时间格式 26 Apr 2006
	 * 
	 * @param str
	 * @return
	 */
	public static String getEDate(String str) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(str, pos);
		String j = strtodate.toString();
		String[] k = j.split(" ");
		return k[2] + k[1].toUpperCase() + k[5].substring(2, 4);
	}

	public static int getYear() {
		Calendar a = Calendar.getInstance();
		return a.get(Calendar.YEAR);
	}

	/**
	 * xiongzhao 2012-09-19 将yyyy-MM-dd日期转换成
	 * 
	 * @param date
	 *            yyyy-MM-dd
	 * @return 19SEP12
	 */
	public static String dateCommandTime(String date) {

		if (date == null || date.length() != 10
				|| !MyDate.isRightDate(date, "yyyy-MM-dd")) {
			return null;
		}
		String nmonth = date.substring(5, 7); // 获取月份：09
		String nDate = date.substring(8, 10); // 获取日期：13
		String nyear = date.substring(0, 4); // 获取年份
		if (StringUtils.isNotBlank(nyear)) {
			if (nyear.length() == 4) {
				nyear = nyear.substring(2, 4); // 获取年份的后两位:07
			}
			// 将月和日期转换成13SEP:13是日期,SEP是月份,m_nMonth是个的数组包含了日期字符串，
			// 该类的最上面
			String EnMonth = m_nMonth[Integer.parseInt(nmonth) - 1];
			return nDate + EnMonth + nyear;
		}

		return null;
	}

	/**
	 * 两个时间之间的天数
	 * 
	 * @param date1
	 *            格式：yyyy-MM-dd
	 * @param date2
	 *            格式：yyyy-MM-dd
	 * @return 天数
	 */
	public static long getDays(String date1, String date2) {
		if (StringUtils.isBlank(date1) || StringUtils.isBlank(date2)) {
			return 0;
		}
		// 转换为标准时间
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		Date mydate = null;
		try {
			date = myFormatter.parse(date1);
			mydate = myFormatter.parse(date2);
		} catch (Exception e) {
		}
		if (date == null || mydate == null) {
			return 0;
		}
		long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		return day;
	}

	/**
	 * 字符型日期转Calendar，格式不对返回null
	 * 
	 * @param strDate
	 *            格式：yyyy-MM-dd or yyyy-MM-dd hh:mm:ss
	 * @return 日历对象
	 */
	public static Calendar strToCalendar(String strDate) {
		if (StringUtils.isBlank(strDate)) {
			return null;
		}
		int len = strDate.length();
		if (len == 16) {
			strDate += ":00";
		} else if (len == 10) {
			strDate += " 00:00:00";
		}
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		calendar.setTime(date);

		return calendar;
	}

	/**
	 * 如果origDate 大于 destDate，返回1；小于destDate，返回-1 等于destDate，返回0，出现异常，返回-2
	 * 格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @param origDate
	 *            比较方
	 * @param destDate
	 *            被比较方
	 * @return
	 */
	public static int compareDate(String origDate, String destDate) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int i = 0;
		try {
			Date dt1 = df.parse(origDate);
			Date dt2 = df.parse(destDate);
			if (dt1.getTime() > dt2.getTime()) {
				i = 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				i = -1;
			} else {
				i = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			i = -2;
		}
		return i;
	}

	/**
	 * 取得指定日期的下一个月
	 * 
	 * @param dates
	 *            指定日期。
	 * @param count
	 * @return
	 */
	public static String getNextMonth(String dates, int count) {
		Date date = strToDate(dates);
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.add(Calendar.MONTH, count);

		return dateToStr(gc.getTime());
	}

	/**
	 * 取得指定月份的第一天
	 * 
	 * @param dat
	 * @return
	 */
	public static String getMonthBegin(String dat) {
		String str = dat.substring(0, 8);
		return str + "01";
	}

	/**
	 * 获取一个月的最后一天
	 * 
	 * @param dat
	 * @return
	 */
	public static String getEndDateOfMonth(String dat) { // yyyy-MM-dd
		String str = dat.substring(0, 7);
		String month = dat.substring(5, 7);
		int mon = Integer.parseInt(month);
		if (mon == 1 || mon == 3 || mon == 5 || mon == 7 || mon == 8
				|| mon == 10 || mon == 12) {
			str += "-31";
		} else if (mon == 4 || mon == 6 || mon == 9 || mon == 11) {
			str += "-30";
		} else {
			if (isLeapYear(dat)) {
				str += "-29";
			} else {
				str += "-28";
			}
		}
		return str;
	}

	/**
	 * 判断是否润年
	 * 
	 * @param ddate
	 * @return
	 */
	public static boolean isLeapYear(String ddate) {

		/**
		 * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年
		 * 3.能被4整除同时能被100整除则不是闰年
		 */
		Date d = strToDate(ddate);
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(d);
		int year = gc.get(Calendar.YEAR);
		if ((year % 400) == 0) {
			return true;
		} else if ((year % 4) == 0) {
			return (year % 100) != 0;
		} else {
			return false;
		}
	}

	/**
	 * 获取两个日期间的间隔分钟
	 * 
	 * @param sj1
	 * @param sj2
	 * @return
	 */
	public static String getTwoMil(String sj1, String sj2) {
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long day = 0;
		try {
			Date date = myFormatter.parse(sj1);
			Date mydate = myFormatter.parse(sj2);
			day = (date.getTime() - mydate.getTime()) / (60 * 1000);
		} catch (Exception e) {
			return "";
		}
		return day + "";
	}

	/**
	 * 获取两个日期间的间隔秒数
	 * 
	 * @param sj1
	 * @param sj2
	 * @return
	 */
	public static String getTwoMiaos(String sj1, String sj2) {
		SimpleDateFormat myFormatter = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		long day = 0;
		try {
			Date date = myFormatter.parse(sj1);
			Date mydate = myFormatter.parse(sj2);
			day = (date.getTime() - mydate.getTime()) / 1000;
		} catch (Exception e) {
			return "";
		}
		return day + "";
	}

	/**
	 * 二个小时时间间的差值,必须保证二个时间都是"HH:MM"的格式，返回字符型的分钟
	 * 
	 * @param st1
	 * @param st2
	 * @return
	 */
	public static String getTwoHour(String st1, String st2) {
		String[] kk = null;
		String[] jj = null;
		kk = st1.split(":");
		jj = st2.split(":");
		if (Integer.parseInt(kk[0]) < Integer.parseInt(jj[0])) {
			return "0";
		} else {
			double y = Double.parseDouble(kk[0]) + Double.parseDouble(kk[1]) / 60;
			double u = Double.parseDouble(jj[0]) + Double.parseDouble(jj[1]) / 60;
			if ((y - u) > 0) {
				return y - u + "";
			} else {
				return "0";
			}
		}
	}

	/**
	 * 二个小时时间间的差值,必须保证二个时间都是"HH:MM"的格式，返回分钟 VIP
	 * 
	 * @param st1
	 * @param st2
	 * @return
	 */
	public static int getMinutesFromTwoHour(String st1, String st2) {
		String[] kk = st1.split(":");
		String[] jj = st2.split(":");
		if (kk.length != 2 || jj.length != 2) {
			return 0;
		}
		int y = NumberUtils.toInt(kk[0], 0) * 60 + NumberUtils.toInt(kk[1], 0);
		int u = NumberUtils.toInt(jj[0], 0) * 60 + NumberUtils.toInt(jj[1], 0);
		return u - y;
	}

	/**
	 * 判断是否是周末
	 * 
	 * @param dateStr
	 *            将长时间格式时间 yyyy-MM-dd HH:mm:ss
	 * @return 周六、周日 true
	 */
	public static Boolean isWeekend(String dateStr) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(strToDateLong(dateStr));
		int weekNum = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (weekNum == 6 || weekNum == 0) {
			return true;
		}
		return false;
	}

	/**
	 * @category 判断第一个日期是否在第二个日期之后
	 * 
	 */
	public static boolean after(String srcDate, String desDate) {
		Date date1 = strToDate(srcDate);
		Date date2 = strToDate(desDate);
		return date1.after(date2);
	}
}