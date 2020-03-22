package com.jason.base.tags.grid.tag;

import com.jason.base.common.utils.StringUtil;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * jqgrid标签合成表格功能标签，监听事件
 * jqgrid标签的子标签，但注意jqgrid标签内最多只能有一个
 * 注：和<jq:sortableRows>标签冲突
 * 例：
 * jQuery("#lister").jqGrid('bindKeys', {"onEnter":function( rowid ) { alert("你enter了一行， id为:"+rowid)}});
 * @author jason558han
 * @date 2020年02月01日
 */
public class JqBindKeys extends SimpleTagSupport {

	private String onEnter;//回车键事件函数 有参数rowid
	private String onSpace;//空格键事件函数 有参数rowid
	private String onLeftKey;//鼠标左键事件函数 有参数rowid
	private String onRightKey;//鼠标右键事件函数 有参数rowid

	@Override
	public void doTag() throws IOException {
		JqGridTag jqGridTag = (JqGridTag) getParent();
		String tableId = jqGridTag.getTableId();

		StringBuffer tagBuffer = new StringBuffer();
		tagBuffer.append("jQuery(\"#").append(tableId).append("\").jqGrid('bindKeys',{");
		if (StringUtil.isNotEmpty(onEnter)) {
			if (onEnter.trim().startsWith("function")) {
				StringUtil.addJsonObjectStr(tagBuffer, "onEnter", onEnter);
			} else {
				StringUtil.addJsonObjectStr(tagBuffer, "onEnter", "function(rowid){"+onEnter+"(rowid);}");
			}
		}
		if (StringUtil.isNotEmpty(onSpace)) {
			if (onSpace.trim().startsWith("function")) {
				StringUtil.addJsonObjectStr(tagBuffer, "onSpace", onSpace);
			} else {
				StringUtil.addJsonObjectStr(tagBuffer, "onSpace", "function(rowid){"+onSpace+"(rowid);}");
			}
		}
		if (StringUtil.isNotEmpty(onLeftKey)) {
			if (onLeftKey.trim().startsWith("function")) {
				StringUtil.addJsonObjectStr(tagBuffer, "onLeftKey", onLeftKey);
			} else {
				StringUtil.addJsonObjectStr(tagBuffer, "onLeftKey", "function(rowid){"+onLeftKey+"(rowid);}");
			}
		}
		if (StringUtil.isNotEmpty(onRightKey)) {
			if (onRightKey.trim().startsWith("function")) {
				StringUtil.addJsonObjectStr(tagBuffer, "onRightKey", onRightKey);
			} else {
				StringUtil.addJsonObjectStr(tagBuffer, "onRightKey", "function(rowid){"+onRightKey+"(rowid);}");
			}
		}
		tagBuffer.append("});");
		getJspContext().getOut().println(tagBuffer);
	}

	public void setOnEnter(String onEnter) {
		this.onEnter = onEnter;
	}
	public void setOnSpace(String onSpace) {
		this.onSpace = onSpace;
	}
	public void setOnLeftKey(String onLeftKey) {
		this.onLeftKey = onLeftKey;
	}
	public void setOnRightKey(String onRightKey) {
		this.onRightKey = onRightKey;
	}
}
