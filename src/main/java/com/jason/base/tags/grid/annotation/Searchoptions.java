package com.jason.base.tags.grid.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 配置搜索选项
 * (待添加：dataEvents,attr)
 * @author jason558han
 * @date 2020年1月29日
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Searchoptions {

	/**
	 * 依据stype，配置属性
	 * 例：性别男女，stype='select',searchoptions={value='0:待定;1:男;2:女'}
	 * @return 值
	 */
	String value() default "";

	/**
	 * 该属性只适用于 stype:’select’
	 * @return select链接
	 */
	String dataUrl() default "";

	/**
	 * 当控件被创建时仅触发1次 function null
	 * 等同于editoptions的dataInit
	 * @return 列表初始化
	 */
	String dataInit() default "";

	/**
	 * 该字段隐藏时,此属性可控制是否可搜索 
	 * @return 默认为false
	 */
	boolean searchhidden() default false;

	/**
	 * 默认值
	 * @return 默认值
	 */
	String defaultValue() default "";

	/**
	 * 比较运算符,可任意组合['eq','ne','lt','le','gt','ge','bw','bn','in','ni','ew','en','cn','nc'] <br/>
	 * 等于 eq =  &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
	 * 不等于 ne <>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 * 小于 lt <  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 * 小于等于 le <=  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 * 大于 gt >  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 * 大于等于 ge >=  <br/>
	 * 以*开头 bw like  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 * 不以*开头 bn not like  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 * 在 in in  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 * 不在 ni not in  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 * 以*结尾 ew like  <br/>
	 * 不以*结尾 en not like  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
	 * 包含 cn like  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 * 不包含 nc not like  <br/>
	 * 字符串常用合集： {"cn","nc","bw","bn","ew","en"}   <br/>
	 * 数字、日期等常用合集：  {"eq","ne","lt","le","gt","ge"}   <br/>
	 * select集合 {"eq"} <br/>
	 * @return 关系符数组
	 */
	String[] sopt() default {};
}
