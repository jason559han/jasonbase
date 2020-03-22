package com.jason.base.security;

import com.jason.base.common.Constants;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 服务器初始化执行数据
 * @author jason558han
 * @date 2020年02月06日
 */
public class BusinessInitListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		//服务器路径
		Constants.applicationContextRoot = arg0.getServletContext().getContextPath();
		Constants.applicationRealPath = arg0.getServletContext().getRealPath("/");

		//BeanUtils日期转化格式化
		ConvertUtils.register(new Converter() {
			public Object convert(Class type, Object value) {
            	if (value instanceof Date) {
            		return value;
            	}
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DEFAULT_DATE_FORMAT);
                    try {
						return simpleDateFormat.parse(value.toString());
					} catch (ParseException e) {
						e.printStackTrace();
					}
                return null;
            }
        }, Date.class);
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {}
}
