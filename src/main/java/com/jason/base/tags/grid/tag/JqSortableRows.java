package com.jason.base.tags.grid.tag;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * jqgrid标签合成表格功能标签，可拖动排序
 * jqgrid标签的子标签，但注意jqgrid标签内最多只能有一个
 * 例：
 * jQuery("#lister").jqGrid('sortableRows');
 * @author jason558han
 * @date 2020年01月01日
 */
public class JqSortableRows extends SimpleTagSupport {

	@Override
	public void doTag() throws IOException {
		JqGridTag jqGridTag = (JqGridTag) getParent();
		String tableId = jqGridTag.getTableId();

		StringBuffer tagBuffer = new StringBuffer();
		tagBuffer.append("jQuery(\"#").append(tableId).append("\").jqGrid('sortableRows');");
		getJspContext().getOut().println(tagBuffer);
	}
}
