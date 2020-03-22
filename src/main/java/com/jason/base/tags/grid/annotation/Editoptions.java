package com.jason.base.tags.grid.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 编辑的一系列选项
 * （待添加：dataEvents）
 * @author jason558han
 * @date 2020年01月28日
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Editoptions {

	/**
	 * 依据edittype，配置属性
	 * 例：性别男女，edittype='select',editoptions={value='0:待定;1:男;2:女'}
	 * 注：列表上，即formatter:'select'时，用的也是这个值
	 * @return 属性值
	 */
	String value() default "";

	/**
	 * 这个属性只适用与edittype属性为select 
	 * (指定select的数据源edittype:"select",editoptions:{dataUrl:'test.txt', defaultValue:'Intime'}) 
	 * @return select链接
	 */
	String dataUrl() default "";

	/**
	 * 当控件被创建时仅触发1次 function null
	 * 例1：选择日期控件
	 * function(el){$(el).datepicker({dateFormat:'yy-mm-dd'})}
	 * @return 数据初始化
	 */
	String dataInit() default "";

	/**
	 * 默认值(可以是函数返回值) 
	 * @return 默认值
	 */
	String defaultValue() default "";

	/**
	 * 表单输入框显示字符大小
	 * @return 字符大小
	 */
	int size() default 0;

	/**
	 * 表单输入框只读属性
	 * @return 默认为false
	 */
	boolean readonly() default false;

	/**
	 * 表单输入框限制最长输入字数
	 * @return 最长长度
	 */
	int maxLength() default 0;
}
