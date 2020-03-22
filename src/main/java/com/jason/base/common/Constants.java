package com.jason.base.common;

/**
 * 常量
 * @author jason558han
 * 2020年02月06日
 */
public class Constants {

	//服务器路径
	public static String applicationContextRoot;
	public static String applicationRealPath;

	/**
	 * 登录session
	 */
	public final static String SESSION_MANAGER = "sessionManager";

	//md5加密码
	public static final String SLAT = "#*kingdom@jason20200104*#";

	/**
	 * 没有登陆时转向url
	 */
	public static final String NOTLOGIN_URL = "/notLoginPage.do";

	/**
	 * 默认日期字符串转化格式
	 */
	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

	/**
	 * 默认日期时间字符串转化格式
	 */
	public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 验证码session
	 */
	public static final String VERIFY_CODE = "verifyCode";
}
