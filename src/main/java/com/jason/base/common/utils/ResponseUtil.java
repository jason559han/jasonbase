package com.jason.base.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * response字符串传递
 * @author jason558han
 * @date 2020年02月24日
 */
public class ResponseUtil {

	private static final Logger logger = LogManager.getLogger(ResponseUtil.class);

	/**
	 * object转换json字符串传递
	 * @param obj 待处理的obj
	 * @param response 服务参数
	 */
	public static void toObjectJson(Object obj, HttpServletResponse response) {
		response.setContentType("application/json;charset=utf-8");
		String objStr = "";
		if (obj != null) {
			objStr = JSONObject.toJSONString(obj);
		}
		try {
			response.getWriter().write(objStr);
		} catch (IOException e) {
			String message = "object传递json字符串加载方法报错：objStr=["+objStr+"]，errorMsg=["+e.getMessage()+"]";
			logger.error(message, e);
			e.printStackTrace();
		}
	}

	/**
	 * list转换json字符串传递
	 * @param list 列表数据
	 * @param response 服务数据
	 */
	public static void toListJson(List<?> list, HttpServletResponse response) {
		response.setContentType("application/json;charset=utf-8");
		String listStr = "";
		if (list != null) {
			listStr = JSONObject.toJSONString(list);
		}
		try {
			response.getWriter().write(listStr);
		} catch (IOException e) {
			String message = "list传递json字符串加载方法报错["+listStr+"]。";
			logger.error(message, e);
			e.printStackTrace();
		}
	}

	/**
	 * response传递字符串方法
	 * @param str 待处理字符串
	 * @param response 服务参数
	 */
	public static void toStringResponse(String str, HttpServletResponse response) {
		response.setContentType("application/json;charset=utf-8");
		try {
			response.getWriter().write(str);
		} catch (IOException e) {
			String message = "response传递字符串方法报错["+str+"]。";
			logger.error(message, e);
			e.printStackTrace();
		}
	}
}
