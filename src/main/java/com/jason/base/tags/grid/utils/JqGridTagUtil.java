package com.jason.base.tags.grid.utils;

import com.jason.base.common.utils.StringUtil;

/**
 * jqgrid自定义标签工具类
 * @author jason558han
 * @date 2020年02月01日
 */
public class JqGridTagUtil {

	/**
	 * 生成函数
	 * @param funcName 函数名
	 * @param params 函数参数
	 * @param tableId 如果为空，则不自动获取id
	 * @param multiselect 是否多选
	 * @return 多选参数ids,rowDatas，单选参数rowData
	 */
	public static StringBuffer getFunctionStr(String funcName, String params, String tableId, boolean multiselect) {
		StringBuffer funcBuffer = new StringBuffer();
		funcBuffer.append("function(");
		if (StringUtil.isNotEmpty(params)) {
			funcBuffer.append(params);
		}
		funcBuffer.append("){");

		StringBuilder selectParam = new StringBuilder();
		if (StringUtil.isNotEmpty(tableId)) {//如果为空，则没有选择行参数
			if (multiselect) {//多选
				funcBuffer.append("var ids = jQuery('#").append(tableId).append("').jqGrid('getGridParam', 'selarrrow');");
				funcBuffer.append("var rowDatas = ids?new Array():null;");
				funcBuffer.append("if (ids) {");
				funcBuffer.append("for (var i=0; i<ids.length; i++) {");
				funcBuffer.append("rowDatas.push(jQuery('#").append(tableId).append("').jqGrid('getRowData',ids[i]));");
				funcBuffer.append("}}");
				selectParam.append("ids,rowDatas");
			} else {//单选
				funcBuffer.append("var id = jQuery('#").append(tableId).append("').jqGrid('getGridParam', 'selrow');");
				funcBuffer.append("var rowData = id?jQuery('#").append(tableId).append("').jqGrid('getRowData',id):null;");
				selectParam.append("rowData");
			}
		}
		funcBuffer.append("return ").append(funcName).append("(");
		if (selectParam.length() > 0) {
			funcBuffer.append(selectParam);
			if (StringUtil.isNotEmpty(params)) {
				funcBuffer.append(",");
			}
		}
		if (StringUtil.isNotEmpty(params)) {
			funcBuffer.append(params);
		}
		funcBuffer.append(");");
		funcBuffer.append("}");
		return funcBuffer;
	}

	/**
	 * 生成函数
	 * @param funcName 函数名称
	 * @param params 函数参数
	 * @return function
	 */
	public static StringBuffer getFunctionStr(String funcName, String params) {
		return getFunctionStr(funcName, params, null, false);
	}
}
