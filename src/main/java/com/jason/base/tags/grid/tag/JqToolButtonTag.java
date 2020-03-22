package com.jason.base.tags.grid.tag;

import com.jason.base.common.utils.StringUtil;
import com.jason.base.tags.grid.utils.JqGridTagUtil;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * jqgrid标签合成表格新建工具栏按钮标签
 * jqgrid标签的子标签，但注意jqgrid标签内可以多个
 * 例：
 * $("#t_lister").append(
            "<input type='button' class='toolBtn' value='Click Me' onclick=\"function(){alert('click me!');}\"/>");
 * @author jason558han
 * @date 2020年02月01日
 */
public class JqToolButtonTag extends SimpleTagSupport {

	private String caption = "";//按钮名称
	private String title;//按钮title
	private String cssClass;//按钮样式
	private String buttonId;//按钮id
	private String onClickButton;//点击按钮执行函数
	private Boolean isBottom = false;//按钮是否在下方部位

	@Override
	public void doTag() throws IOException {
		JqGridTag jqGridTag = (JqGridTag) getParent();
		String tableId = jqGridTag.getTableId();

		StringBuffer tagBuffer = new StringBuffer();
		tagBuffer.append("$(\"#t");
		if (isBottom) {
			tagBuffer.append("b");
		}
		tagBuffer.append("_").append(tableId).append("\").append(\"");
		tagBuffer.append("<input type='button' class='toolBtn");
		if (StringUtil.isNotEmpty(cssClass)) {
			tagBuffer.append(" ").append(cssClass);
		}
		tagBuffer.append("'");
		if (StringUtil.isNotEmpty(buttonId)) {
			tagBuffer.append(" id='").append(buttonId).append("'");
		}
		if (StringUtil.isNotEmpty(title)) {
			tagBuffer.append(" title='").append(title).append("'");
		}
		tagBuffer.append(" value='").append(caption).append("'");
		if (StringUtil.isNotEmpty(onClickButton)) {
			Boolean multiselect = jqGridTag.getMultiselect();
			tagBuffer.append(" onclick=\\\"var onclickEle={clickFunc:");
			if (!onClickButton.trim().startsWith("function")) {
				tagBuffer.append(JqGridTagUtil.getFunctionStr(onClickButton, null, tableId, multiselect));
			} else {
				tagBuffer.append(onClickButton);
			}
			tagBuffer.append("};onclickEle.clickFunc.call(this);\\\"");
		}
		tagBuffer.append("/>\");");
		getJspContext().getOut().println(tagBuffer);
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}
	public void setButtonId(String buttonId) {
		this.buttonId = buttonId;
	}
	public void setOnClickButton(String onClickButton) {
		this.onClickButton = onClickButton;
	}
	public void setIsBottom(Boolean isBottom) {
		this.isBottom = isBottom;
	}
}
