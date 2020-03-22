package com.jason.base.common.utils;

import org.springframework.util.DigestUtils;

/**
 * MD5加密
 * @author jason558han
 *
 */
public class MD5Util {

	/**
	 * 获取md5加密字符串
	 * @param str 需要加密的字符串
	 * @param slat 字符串标识
	 * @return 返回加密的字符串
	 */
	public static String getMD5Str(String str, String slat) {
		String base = str + "/" + slat;
		return DigestUtils.md5DigestAsHex(base.getBytes());
	}
}