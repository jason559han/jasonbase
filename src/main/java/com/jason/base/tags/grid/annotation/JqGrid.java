package com.jason.base.tags.grid.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据表格标签注解
 * （待添加：defval,sorttype,hidedlg,jsonmap,label,surl,xmlmap,unformat）
 * (formoptions,searchrules)
 * @author jason558han
 * @date 2020年01月28日
 */
@Target({ElementType.TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JqGrid {

	/**
	 * 表列显示名称
	 * @return 列名
	 */
	String displayName();

	/**
	 * 列顺序
	 * @return 顺序
	 */
	int serial() default 0;

	/**
	 * 列宽度 
	 * @return 默认为150
	 */
	int width() default 150;

	/**
	 * 索引，默认为属性名称
	 * @return 索引
	 */
	String index() default "";

	/**
	 * left, center, right.
	 * @return 默认为 left
	 */
	String align() default "left";

	/**
	 * 是否列隐藏
	 * @return 默认false
	 */
	boolean hidden() default false;

	/**
	 * 设置列的css。多个class之间用空格分隔，如：'class1 class2' 。表格默认的css属性是ui-ellipsis
	 * @return class属性
	 */
	String classes() default "";

	/**
	 * 是否可排序，
	 * @return 默认true
	 */
	boolean sortable() default true;

	/**
	 * 日期格式，
	 * @return 默认为"yyyy-MM-dd"
	 */
	String datefmt() default "yyyy-MM-dd";

	/**
	 * 单元格是否可编辑
	 * @return 默认false
	 */
	boolean editable() default false;

	/**
	 * 可以编辑的类型。可选值：text, textarea, select, checkbox, password, button, image and file.
	 * @return 默认text
	 */
	String edittype() default "text";

	/**
	 * 列宽度是否要固定不可变
	 * @return 默认false
	 */
	boolean fixed() default false;

	/**
	 * 对列进行格式化时设置的函数名或者类型 
	 * 可以设置为：integer、number、currency、select、email、function、date、link、showlink、checkbox、actions
	 * 注：formatter常与formatoptions{}搭配
	 * 例function:{name:’sex’,index:’sex’, align:’center’,width:60,editable:true,edittype:’select’,editoptions: {value:’0:待定;1:男;2:女’},formatter:function(cellvalue, options, rowObject){
	 * var temp = “<img src=’/jqGrid/jquery-ui-1.7.2.custom/css/img/”
	 * if(cellvalue==1){
	 * temp = temp +”user-white.png”;
	 * } else if(cellvalue==2){
	 * temp = temp +”user-white-female.png”;
	 * } else {
	 * temp = temp + “user-silhouette.png”;
	 * }
	 * temp = temp + “‘ border=’0 ′ />”
	 * return temp;
	 * }},// 返回性别的图标。
	 * @return 格式
	 */
	String formatter() default "";

	/**
	 * 当从服务器端返回的数据中没有id时，将此作为唯一rowid使用只有一个列可以做这项设置。如果设置多于一个，那么只选取第一个，其他被忽略
	 * @return 默认false
	 */
	boolean key() default false;

	/**
	 * 是否可以被resizable
	 * @return 默认true
	 */
	boolean resizable() default true;

	/**
	 * 在搜索模式下，定义此列是否可以作为搜索列
	 * @return 默认为true
	 */
	boolean search() default true;

	/**
	 * 定义搜索元素的类型 值text,select
	 * @return 默认为text
	 */
	String stype() default "text";

	/**
	 * 添加表格属性
	 * 例1:
	 * function(rowId, value, rowObject, colModel, arrData) {
	 *     return ' colspan=2';
	 * }
	 * 例2:
	 * function(rowId, value, rowObject, colModel, arrData) {
	 *    return " style=display:none; ";
	 * }
	 * @return 表格属性
	 */
	String cellattr() default "";

	/**
	 * 冻结列，shrinkToFit设置为true
	 * @return 是否冻结列
	 */
	boolean frozen() default false;
}
