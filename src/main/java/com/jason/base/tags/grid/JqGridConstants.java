package com.jason.base.tags.grid;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * jqgrid标签 常量
 * @author jason558han
 * date 2020年02月22日
 */
public class JqGridConstants {

	//dao层 sql语句 链接符
	public static final String GROUPOP_AND = "AND";
	public static final String GROUPOP_OR = "OR";
	public static final String OP_EQUAL = "eq";//比较运算符 等于 = 
	public static final String OP_NOT_EQUAL = "ne";//比较运算符 不等于 <>
	public static final String OP_LESS_THAN = "lt";//比较运算符 小于 <
	public static final String OP_LESS_EQUAL = "le";//比较运算符 小于等于 <=
	public static final String OP_GREATER_THAN = "gt";//比较运算符 大于 > 
	public static final String OP_GREATER_EQUAL = "ge";//比较运算符 大于等于 >=
	public static final String OP_BLOSSOM_HEAD_CONTAIN = "bw";//比较运算符 以*开头 like 
	public static final String OP_NOT_BLOSSOM_HEAD_CONTAIN = "bn";//比较运算符 不以*开头 not like
	public static final String OP_IN = "in";//比较运算符 在 in
	public static final String OP_NOT_IN = "ni";//比较运算符 不在 not in
	public static final String OP_BLOSSOM_END_CONTAIN = "ew";//比较运算符 以*结尾 like
	public static final String OP_NOT_BLOSSOM_END_CONTAIN = "en";//比较运算符 不以*结尾 not like
	public static final String OP_CONTAIN = "cn";//比较运算符 包含 like
	public static final String OP_NOT_CONTAIN = "nc";//比较运算符 不包含 not like

	/**
	 * 选择日期控件初始化 dataInit 设置
	 */
	public static final String TAG_DATE_INIT_FUNC = "function(el){$(el).datepicker({dateFormat:'yy-mm-dd',changeMonth:true,changeYear:true})}";

	/**
	 * 指定父节点id字段名称
	 */
	public static final String PARENT_FIELD_NAME = "upId";

	/**
	 * 指定level字段名称
	 */
	public static final String LEVEL_FIELD_NAME = "level";

	/**
	 * 指定level字段名称
	 */
	public static final String ISLEAF_FIELD_NAME = "isLeaf";

	/**
	 * 指定code字段名称
	 */
	public static final String CODE_FIELD_NAME = "code";

	/**
	 * 操作对应字母
	 */
	public static final Map<String, String> EDIT_OPER_MAP = new HashMap<>();
	static {
		EDIT_OPER_MAP.put("del", "删除");
		EDIT_OPER_MAP.put("add", "添加");
		EDIT_OPER_MAP.put("edit", "修改");
	}

	/**
	 * 日期区间map
	 */
	public static final Map<String, String> DATE_SECTION_MAP = new LinkedHashMap<>();
	static {
		DATE_SECTION_MAP.put("today", "当天");
		DATE_SECTION_MAP.put("week", "一星期内");
		DATE_SECTION_MAP.put("month", "一个月内");
		DATE_SECTION_MAP.put("season", "三个月内");
		DATE_SECTION_MAP.put("year", "一年内");
		DATE_SECTION_MAP.put("all", "全部");
	}
}
