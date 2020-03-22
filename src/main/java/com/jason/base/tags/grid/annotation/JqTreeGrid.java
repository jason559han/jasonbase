package com.jason.base.tags.grid.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据表格树状标签treegrid注解
 * 注：
 * 树状表格必须要标注readerName属性
 * 必须标注等都属性值有level、parent、isLeaf
 * 想要使用系统默认等编辑保存等实体类必须含有字段有
 * Integer level
 * Boolean isLeaf
 * Integer upId
 * String code
 * @author jason558han
 * @date 2020年02月04日
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JqTreeGrid {

	/**
	 * 指定那列来展开tree grid，默认为第一列，只有在treeGrid为true时起作用
	 * @return 点击列展开
	 */
	boolean expandColumn() default false;

	/**
	 * tree grid 树状列表专用
	 * 所用值<br/>
	 * level 层级，从0开始，int必填<br/>
	 * parent 父元素id，类型与实体类id相同，子元素必填<br/>
	 * isLeaf 是否是叶元素(没有子元素)，boolean必填<br/>
	 * expanded 是否展开，boolean，默认为false<br/>
	 * loaded 是否已经加载，boolean，默认为false<br/>
	 * icon 图标，String，默认以标签上定义等为准
	 * @return 以上值
	 */
	String renderName() default "";
}
