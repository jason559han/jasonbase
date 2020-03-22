package com.jason.base.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 获取application.xml内配置实例工具类
 * @author jason558han
 * @date 2020年2月21日
 */
public class SpringContextUtil implements ApplicationContextAware {

	// Spring应用上下文环境  
    private static ApplicationContext applicationContext;  

    /** 
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境 
     * @param applicationContext 上下文属性
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {  
        SpringContextUtil.applicationContext = applicationContext;  
    }  

    /** 
     * @return ApplicationContext 
     */  
    public static ApplicationContext getApplicationContext() {  
        return applicationContext;
    }  

    /** 
     * 获取对象 
     * @param name bean name
     * @return Object
     * @throws BeansException 获得bean报错
     */  
	public static Object getBean(String name) throws BeansException { 
        return applicationContext.getBean(name);
    }
}
