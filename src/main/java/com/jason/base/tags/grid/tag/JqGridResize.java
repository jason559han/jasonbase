package com.jason.base.tags.grid.tag;

import com.jason.base.common.utils.StringUtil;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * jqgrid标签合成表格功能标签，可让表格自由改变长宽
 * jqgrid标签的子标签，但注意jqgrid标签内最多只能有一个
 * 例：
 * jQuery("#lister").jqGrid('gridResize',{minWidth:350,maxWidth:800,minHeight:80, maxHeight:350});
 * @author jason558han
 * @date 2020年02月01日
 */
public class JqGridResize extends SimpleTagSupport {

	private Integer minWidth;
	private Integer maxWidth;
	private Integer minHeight;
	private Integer maxHeight;

	@Override
	public void doTag() throws IOException {
		JqGridTag jqGridTag = (JqGridTag) getParent();
		String tableId = jqGridTag.getTableId();

		StringBuffer tagBuffer = new StringBuffer();
		tagBuffer.append("jQuery(\"#").append(tableId).append("\").jqGrid('gridResize',{");
		if (minWidth != null && minWidth >= 0)  {
			StringUtil.addJsonObjectStr(tagBuffer, "minWidth", minWidth);
		}
		if (maxWidth != null && maxWidth >= 0)  {
			StringUtil.addJsonObjectStr(tagBuffer, "maxWidth", maxWidth);
		}
		if (minHeight != null && minHeight >= 0)  {
			StringUtil.addJsonObjectStr(tagBuffer, "minHeight", minHeight);
		}
		if (maxHeight != null && maxHeight >= 0)  {
			StringUtil.addJsonObjectStr(tagBuffer, "maxHeight", maxHeight);
		}
		tagBuffer.append("});");
		getJspContext().getOut().println(tagBuffer);
	}

	public void setMinWidth(Integer minWidth) {
		this.minWidth = minWidth;
	}
	public void setMaxWidth(Integer maxWidth) {
		this.maxWidth = maxWidth;
	}
	public void setMinHeight(Integer minHeight) {
		this.minHeight = minHeight;
	}
	public void setMaxHeight(Integer maxHeight) {
		this.maxHeight = maxHeight;
	}
}
