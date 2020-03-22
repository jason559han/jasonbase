package com.jason.base.common.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * properties文件工具类
 * @author jason558han
 * @date 2020年02月21日
 */
public class PropertiesUtil {

	private static final Logger logger = LogManager.getLogger(PropertiesUtil.class);
	private Properties props = new Properties();//properties文件

	public PropertiesUtil() {
		super();
	}

	public PropertiesUtil(String fileName) {
		try {
			InputStream in = this.getClass().getResourceAsStream(fileName);
			props.load(in);
			in.close();
		} catch (IOException e) {
			logger.error("根据fileName获取properties配置文件，fileName=["+fileName+"]，errorMsg=["+e.getMessage()+"]", e);
			e.printStackTrace();
		}
	}

	/**
	 * 将properties文件转化为map
	 * @return map对象
	 */
	public Map<String, String> readAllProperties() {
		Map<String, String> map = new HashMap<String, String>();
		Enumeration<?> en = props.propertyNames();
		while (en.hasMoreElements()) {
			String key = (String) en.nextElement();
			String Property = props.getProperty(key);
			map.put(key, Property);
		}
		return map;
	}

	public Properties getProps() {
		return props;
	}
}
