package com.jason.base.tags.grid.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 设置编辑字段的控件的规则
 * @author jason558han
 * @date 2020年01月29日
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Editrules {
	/**
	 * 该字段隐藏时, 此属性可以控制是否可编辑
	 * @return 默认为false
	 */
	boolean edithidden() default false;
	/**
	 * 是否验证空数据
	 * @return 默认为false
	 */
	boolean required() default false;
	/**
	 * 是否验证数字
	 * @return 默认为false
	 */
	boolean number() default false;
	/**
	 * 是否验证整数
	 * @return 默认为false
	 */
	boolean integer() default false;
	/**
	 * 验证最小值
	 * @return 最小值
	 */
	int minValue() default Integer.MIN_VALUE;
	/**
	 * 验证最大值
	 * @return 最大值
	 */
	int maxValue() default Integer.MAX_VALUE;
	/**
	 * 是否验证email
	 * 注：如果此字段不是必填,如果填了才验证是否是email 那么应该配置 email:true, required:false。以下字段相同
	 * @return 默认为false
	 */
	boolean email() default false;
	/**
	 * 是否验证是有效的网址
	 * @return 默认为false
	 */
	boolean url() default false;
	/**
	 * 是否验证日期
	 * @return 默认为false
	 */
	boolean date() default false;
	/**
	 * 是否验证时间
	 * @return 默认为false
	 */
	boolean time() default false;
	/**
	 * 是否有自定义验证
	 * @return 默认为false
	 */
	boolean custom() default false;
	/**
	 * 自定义验证函数
	 * 例:
	 * function(val,nm,valref) {
	 *     if(parseFloat(val) >= 200 && parseFloat(val)<=300) {
	 *         return [true,"",""];
	 *     } else {
	 *         return [false,"The value should be between 200 and 300!",""];
	 *     }
	 * }
	 * @return 自定义验证函数
	 */
	String custom_func() default "";
}
