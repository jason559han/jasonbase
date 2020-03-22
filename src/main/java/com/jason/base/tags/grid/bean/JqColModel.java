package com.jason.base.tags.grid.bean;

import com.jason.base.common.utils.StringUtil;
import com.jason.base.tags.grid.annotation.*;
import com.jason.base.tags.grid.utils.JqGridTagUtil;

import java.lang.reflect.Field;

/**
 * 列model数据整合
 * @author jason558han
 * @date 2020年01月30日
 */
public class JqColModel implements Comparable<JqColModel> {

	private String name;//属性名
	private int serial;//顺序
	private Field field;//属性反射函数
	private JqGrid jqGrid;//列model参数选项
	private Formatoptions formatoptions;//列model列表参数
	private Editoptions editoptions;//列model修改选项
	private Editrules editrules;//列model修改列表验证规则
	private Searchoptions searchoptions;//列model列表搜索选项

	public JqColModel() {}
	public JqColModel(String name, int serial, JqGrid jqGrid) {
		super();
		this.name = name;
		this.serial = serial;
		this.jqGrid = jqGrid;
	}
	public JqColModel(String name, int serial, Field field, JqGrid jqGrid) {
		super();
		this.name = name;
		this.serial = serial;
		this.field = field;
		this.jqGrid = jqGrid;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSerial() {
		return serial;
	}
	public void setSerial(int serial) {
		this.serial = serial;
	}
	public Field getField() {
		return field;
	}
	public void setField(Field field) {
		this.field = field;
	}
	public JqGrid getJqGrid() {
		return jqGrid;
	}
	public void setJqGrid(JqGrid jqGrid) {
		this.jqGrid = jqGrid;
	}
	public Formatoptions getFormatoptions() {
		return formatoptions;
	}
	public void setFormatoptions(Formatoptions formatoptions) {
		this.formatoptions = formatoptions;
	}
	public Editoptions getEditoptions() {
		return editoptions;
	}
	public void setEditoptions(Editoptions editoptions) {
		this.editoptions = editoptions;
	}
	public Editrules getEditrules() {
		return editrules;
	}
	public void setEditrules(Editrules editrules) {
		this.editrules = editrules;
	}
	public Searchoptions getSearchoptions() {
		return searchoptions;
	}
	public void setSearchoptions(Searchoptions searchoptions) {
		this.searchoptions = searchoptions;
	}

	/**
	 * 按照serial比较大小来排序
	 */
	@Override
	public int compareTo(JqColModel o) {
		if (this == o) {
			return 0;
		}
		if (o == null) {
			return 1;
		}
		return serial - o.getSerial();
	}

	/**
	 * tag字符串
	 */
	@Override
	public String toString() {
		StringBuffer strBuffer = new StringBuffer("{");
		StringUtil.addJsonObjectStr(strBuffer, "name", name);
		StringUtil.addJsonObjectStr(strBuffer, "index", StringUtil.isNotEmpty(jqGrid.index())?jqGrid.index():name);
		//不为空，并且不是默认值的，要重新设定，即要添加显示，以下属性皆是这种情况
		if (jqGrid.width()>0 && jqGrid.width() != 150) {
			StringUtil.addJsonObjectStr(strBuffer, "width", jqGrid.width());
		}
		if (StringUtil.isNotEmpty(jqGrid.align()) && !"left".equalsIgnoreCase(jqGrid.align())) {
			StringUtil.addJsonObjectStr(strBuffer, "align", jqGrid.align());
		}
		if (jqGrid.hidden()) {
			StringUtil.addJsonObjectStr(strBuffer, "hidden", jqGrid.hidden());
		}
		if (StringUtil.isNotEmpty(jqGrid.classes())) {
			StringUtil.addJsonObjectStr(strBuffer, "classes", jqGrid.classes());
		}
		if (!jqGrid.sortable()) {
			StringUtil.addJsonObjectStr(strBuffer, "sortable", jqGrid.sortable());
		}
		if (jqGrid.editable()) {
			StringUtil.addJsonObjectStr(strBuffer, "editable", jqGrid.editable());
		}
		if (StringUtil.isNotEmpty(jqGrid.edittype()) && !"text".equalsIgnoreCase(jqGrid.edittype())) {
			StringUtil.addJsonObjectStr(strBuffer, "edittype", jqGrid.edittype());
		}
		if (jqGrid.fixed()) {
			StringUtil.addJsonObjectStr(strBuffer, "fixed", jqGrid.fixed());
		}
		if (StringUtil.isNotEmpty(jqGrid.formatter())) {
			StringUtil.addJsonObjectStr(strBuffer, "formatter", jqGrid.formatter());
		}
		if (jqGrid.key()) {
			StringUtil.addJsonObjectStr(strBuffer, "key", jqGrid.key());
		}
		if (!jqGrid.resizable()) {
			StringUtil.addJsonObjectStr(strBuffer, "resizable", jqGrid.resizable());
		}
		if (!jqGrid.search()) {
			StringUtil.addJsonObjectStr(strBuffer, "search", jqGrid.search());
		}
		if (StringUtil.isNotEmpty(jqGrid.stype()) && !"text".equalsIgnoreCase(jqGrid.stype())) {
			StringUtil.addJsonObjectStr(strBuffer, "stype", jqGrid.stype());
		}
		if (StringUtil.isNotEmpty(jqGrid.cellattr())) {
			StringUtil.addJsonObjectStr(strBuffer, "cellattr", jqGrid.cellattr());
		}
		if (jqGrid.frozen()) {
			StringUtil.addJsonObjectStr(strBuffer, "frozen", jqGrid.frozen());
		}

		if (formatoptions != null) {//列model列表参数
			StringBuffer formatBuffer = new StringBuffer("{");
			//列参数选项中，如果不是默认值，则添加
			if (StringUtil.isNotEmpty(formatoptions.thousandsSeparator())) {
				StringUtil.addJsonObjectStr(formatBuffer, "thousandsSeparator", formatoptions.thousandsSeparator());
			}
			if (StringUtil.isNotEmpty(formatoptions.defaulValue())) {
				StringUtil.addJsonObjectStr(formatBuffer, "defaulValue", formatoptions.defaulValue());
			}
			if (StringUtil.isNotEmpty(formatoptions.decimalSeparator())) {
				StringUtil.addJsonObjectStr(formatBuffer, "decimalSeparator", formatoptions.decimalSeparator());
			}
			if (StringUtil.isNotEmpty(formatoptions.decimalPlaces())) {
				StringUtil.addJsonObjectStr(formatBuffer, "decimalPlaces", formatoptions.decimalPlaces());
			}
			if (StringUtil.isNotEmpty(formatoptions.prefix())) {
				StringUtil.addJsonObjectStr(formatBuffer, "prefix", formatoptions.prefix());
			}
			if (StringUtil.isNotEmpty(formatoptions.suffix())) {
				StringUtil.addJsonObjectStr(formatBuffer, "suffix", formatoptions.suffix());
			}
			if (StringUtil.isNotEmpty(formatoptions.srcformat())) {
				StringUtil.addJsonObjectStr(formatBuffer, "srcformat", formatoptions.srcformat());
			}
			if (StringUtil.isNotEmpty(formatoptions.newformat())) {
				StringUtil.addJsonObjectStr(formatBuffer, "newformat", formatoptions.newformat());
			}
			if (StringUtil.isNotEmpty(formatoptions.target())) {
				StringUtil.addJsonObjectStr(formatBuffer, "target", formatoptions.target());
			}
			if (StringUtil.isNotEmpty(formatoptions.baseLinkUrl())) {
				StringUtil.addJsonObjectStr(formatBuffer, "baseLinkUrl", formatoptions.baseLinkUrl());
			}
			if (StringUtil.isNotEmpty(formatoptions.showAction())) {
				StringUtil.addJsonObjectStr(formatBuffer, "showAction", formatoptions.showAction());
			}
			if (StringUtil.isNotEmpty(formatoptions.addParam())) {
				StringUtil.addJsonObjectStr(formatBuffer, "addParam", formatoptions.addParam());
			}
			if (StringUtil.isNotEmpty(formatoptions.idName())) {
				StringUtil.addJsonObjectStr(formatBuffer, "idName", formatoptions.idName());
			}
			if (!formatoptions.disabled()) {
				StringUtil.addJsonObjectStr(formatBuffer, "disabled", formatoptions.disabled());
			}
			if (formatoptions.keys()) {
				StringUtil.addJsonObjectStr(formatBuffer, "keys", formatoptions.keys());
			}

			formatBuffer.append("}");
			if (formatBuffer.length()>2) {
				StringUtil.addJsonObjectStr(strBuffer, "formatoptions", formatBuffer);
			}
		}

		if (editoptions != null) {//列model修改选项
			StringBuffer editoptBuffer = new StringBuffer("{");
			//修改选项中，如果不是默认值，则添加
			if (StringUtil.isNotEmpty(editoptions.value())) {
				StringUtil.addJsonObjectStr(editoptBuffer, "value", editoptions.value());
			}
			if (StringUtil.isNotEmpty(editoptions.dataUrl())) {
				StringUtil.addJsonObjectStr(editoptBuffer, "dataUrl", editoptions.dataUrl());
			}
			if (StringUtil.isNotEmpty(editoptions.dataInit())) {
				StringUtil.addJsonObjectStr(editoptBuffer, "dataInit", editoptions.dataInit());
			}
			if (StringUtil.isNotEmpty(editoptions.defaultValue())) {
				StringUtil.addJsonObjectStr(editoptBuffer, "defaultValue", editoptions.defaultValue());
			}
			if (editoptions.size()>0) {
				StringUtil.addJsonObjectStr(editoptBuffer, "size", editoptions.size());
			}
			if (editoptions.readonly()) {
				StringUtil.addJsonObjectStr(editoptBuffer, "readonly", editoptions.readonly());
			}
			if (editoptions.maxLength()>0) {
				StringUtil.addJsonObjectStr(editoptBuffer, "maxLength", editoptions.maxLength());
			}
			editoptBuffer.append("}");
			if (editoptBuffer.length()>2) {
				StringUtil.addJsonObjectStr(strBuffer, "editoptions", editoptBuffer);
			}
		}

		if (editrules != null) {//设置编辑字段的控件的验证规则
			StringBuffer editruleBuffer = new StringBuffer("{");
			//验证规则选项中，如果不是默认值，则添加
			if (editrules.edithidden()) {
				StringUtil.addJsonObjectStr(editruleBuffer, "edithidden", editrules.edithidden());
			}
			if (editrules.required()) {
				StringUtil.addJsonObjectStr(editruleBuffer, "required", editrules.required());
			}
			if (editrules.number()) {
				StringUtil.addJsonObjectStr(editruleBuffer, "number", editrules.number());
			}
			if (editrules.integer()) {
				StringUtil.addJsonObjectStr(editruleBuffer, "integer", editrules.integer());
			}
			if (editrules.minValue() > Integer.MIN_VALUE) {
				StringUtil.addJsonObjectStr(editruleBuffer, "minValue", editrules.minValue());
			}
			if (editrules.maxValue() < Integer.MAX_VALUE) {
				StringUtil.addJsonObjectStr(editruleBuffer, "maxValue", editrules.maxValue());
			}
			if (editrules.email()) {
				StringUtil.addJsonObjectStr(editruleBuffer, "email", editrules.email());
			}
			if (editrules.url()) {
				StringUtil.addJsonObjectStr(editruleBuffer, "url", editrules.url());
			}
			if (editrules.date()) {
				StringUtil.addJsonObjectStr(editruleBuffer, "date", editrules.date());
			}
			if (editrules.time()) {
				StringUtil.addJsonObjectStr(editruleBuffer, "time", editrules.time());
			}
			if (editrules.custom()) {
				StringUtil.addJsonObjectStr(editruleBuffer, "custom", editrules.custom());
			}
			if (StringUtil.isNotEmpty(editrules.custom_func())) {
				if (editrules.custom_func().startsWith("function")) {
					StringUtil.addJsonObjectStr(editruleBuffer, "custom_func", editrules.custom_func());
				} else {
					StringUtil.addJsonObjectStr(editruleBuffer, "custom_func", JqGridTagUtil.getFunctionStr(editrules.custom_func(), "val,nm,valref", null, false));
				}
			}
			editruleBuffer.append("}");
			if (editruleBuffer.length()>2) {
				StringUtil.addJsonObjectStr(strBuffer, "editrules", editruleBuffer);
			}
		}

		if (searchoptions != null) {//配置搜索选项 
			StringBuffer searchoptBuffer = new StringBuffer("{");
			//验证规则选项中，如果不是默认值，则添加
			if (StringUtil.isNotEmpty(searchoptions.value())) {
				StringUtil.addJsonObjectStr(searchoptBuffer, "value", searchoptions.value());
			}
			if (StringUtil.isNotEmpty(searchoptions.dataUrl())) {
				StringUtil.addJsonObjectStr(searchoptBuffer, "dataUrl", searchoptions.dataUrl());
			}
			if (StringUtil.isNotEmpty(searchoptions.dataInit())) {
				StringUtil.addJsonObjectStr(searchoptBuffer, "dataInit", searchoptions.dataInit());
			}
			if (searchoptions.searchhidden()) {
				StringUtil.addJsonObjectStr(searchoptBuffer, "searchhidden", searchoptions.searchhidden());
			}
			if (StringUtil.isNotEmpty(searchoptions.defaultValue())) {
				StringUtil.addJsonObjectStr(searchoptBuffer, "defaultValue", searchoptions.defaultValue());
			}
			if (searchoptions.sopt().length > 0) {
				StringUtil.addJsonObjectStr(searchoptBuffer, "sopt", searchoptions.sopt());
			}
			searchoptBuffer.append("}");
			if (searchoptBuffer.length() > 2) {
				StringUtil.addJsonObjectStr(strBuffer, "searchoptions", searchoptBuffer);
			}
		}
		strBuffer.append("}");
		return strBuffer.toString();
	}
}
