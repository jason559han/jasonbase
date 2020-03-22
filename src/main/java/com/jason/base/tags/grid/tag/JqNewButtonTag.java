package com.jason.base.tags.grid.tag;

import com.jason.base.common.utils.StringUtil;
import com.jason.base.tags.grid.utils.JqGridTagUtil;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * jqgrid标签合成表格新建按钮标签
 * jqgrid标签的子标签，但注意jqgrid标签内可以多个
 * 例1:
 jQuery("#lister").navButtonAdd('#pager',{  
	   caption:"Add",   
	   buttonicon:"ui-icon-add",   
	   onClickButton: function(){   
	     alert("Adding Row");  
	   },   
	   position:"last"
	}); 
 * @author jason558han
 * @date 2020年01月31日
 */
public class JqNewButtonTag extends SimpleTagSupport {

	private String caption;//按钮名称
	private String title;//按钮title
	private String buttonicon = "ui-icon-newwin";//按钮图标
	private String onClickButton;//点击按钮执行函数
	private String position = "last";//按钮位置，取值first,last(默认)
	private String cursor = "pointer";//鼠标指向

	@Override
	public void doTag() throws IOException {
		JqGridTag jqGridTag = (JqGridTag) getParent();
		String tableId = jqGridTag.getTableId();
		String pager = jqGridTag.getPager();
		Boolean multiselect = jqGridTag.getMultiselect();

		StringBuffer tagBuffer = new StringBuffer();
		tagBuffer.append("jQuery(\"#").append(tableId).append("\").navButtonAdd('#").append(pager).append("',{");

		//如果不为空，且不是默认值，则添加
		StringUtil.addJsonObjectStr(tagBuffer, "caption", StringUtil.isNotEmpty(caption)?caption:"");
		if (StringUtil.isNotEmpty(title)) {
			StringUtil.addJsonObjectStr(tagBuffer, "title", title);
		}
		if (!"ui-icon-newwin".equals(buttonicon)) {
			StringUtil.addJsonObjectStr(tagBuffer, "buttonicon", StringUtil.isNotEmpty(buttonicon)?buttonicon:"");
		}
		if (StringUtil.isNotEmpty(onClickButton)) {
			if (!onClickButton.trim().startsWith("function")) {
				StringBuffer funcStrBuffer = JqGridTagUtil.getFunctionStr(onClickButton, "e", tableId, multiselect);
				StringUtil.addJsonObjectStr(tagBuffer, "onClickButton", funcStrBuffer);
			} else {
				StringUtil.addJsonObjectStr(tagBuffer, "onClickButton", onClickButton);
			}
		}
		if (StringUtil.isNotEmpty(position) && !"last".equals(position)) {
			StringUtil.addJsonObjectStr(tagBuffer, "position", position);
		}
		if (StringUtil.isNotEmpty(cursor) && !"pointer".equals(cursor)) {
			StringUtil.addJsonObjectStr(tagBuffer, "cursor", cursor);
		}
		tagBuffer.append("});");
		getJspContext().getOut().println(tagBuffer);
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setButtonicon(String buttonicon) {
		this.buttonicon = buttonicon;
	}
	public void setOnClickButton(String onClickButton) {
		this.onClickButton = onClickButton;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public void setCursor(String cursor) {
		this.cursor = cursor;
	}
}
