package com.jason.base.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期工具类
 * @author jason558han
 * @date 2020年01月23日
 */
public class DateUtil {

	/**
	 * date转string
	 * @param date 日期
	 * @param format 日期格式
	 * @return 转换后的字符串
	 */
	public static String getDateStr(Date date, String format) {
		return date != null ? new SimpleDateFormat(format).format(date) : "";
	}

	/**
	 * string转date
	 * @param dateStr 日期字符串
	 * @param format 日期格式
	 * @return 日期
	 * @throws ParseException 转换日期报错
	 */
	public static Date getDateByDateStr(String dateStr, String format) throws ParseException {
		if (format == null) {
			return getDateByDateStr(dateStr);
		}
		return dateStr != null && !"".equals(dateStr) ? new SimpleDateFormat(dateStr).parse(dateStr) : null;
	}

	/**
	 * 默认格式 string转date
	 * @param dateStr 日期字符串
	 * @return 日期
	 * @throws ParseException 转换日期报错
	 */
	public static Date getDateByDateStr(String dateStr) throws ParseException {
		return dateStr != null && !"".equals(dateStr) ? 
				new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US).parse(dateStr) : null;
	}


	/**
	 * 获取日期区间前日期
	 * @param section 日期区间
	 * @return 日期
	 */
	public static Date getDateBySection(String section) {
		Date date = null;
		Calendar cal = Calendar.getInstance();
		switch (section) {
		case "today":
			date = cal.getTime();
			break;
		case "week":
			cal.add(Calendar.DATE, -7);
			date = cal.getTime();
			break;
		case "month":
			cal.add(Calendar.MONTH, -1);
			date = cal.getTime();
			break;
		case "season":
			cal.add(Calendar.MONTH, -3);
			date = cal.getTime();
			break;
		case "year":
			cal.add(Calendar.YEAR, -1);
			date = cal.getTime();
			break;
		}
		return date;
	}

}
