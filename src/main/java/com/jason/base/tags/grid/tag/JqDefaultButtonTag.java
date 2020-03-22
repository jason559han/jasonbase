package com.jason.base.tags.grid.tag;

import com.jason.base.common.utils.StringUtil;
import com.jason.base.tags.grid.utils.JqGridTagUtil;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * jqgrid标签合成表格默认按钮
 * jqgrid标签的子标签，但注意jqgrid标签内最多只有一个
 * 整体实例：
	//创建jqGrid的操作按钮容器
	//可以控制界面上增删改查的按钮是否显示
	jQuery("#lister").navGrid('#pager', 
			{edit : true,add : true,del : true,view : true}, //options
			{height : 400,reloadAfterSubmit : true,closeAfterEdit:true,afterSubmit:function(data,postdata, frmoper) {
				if (data.statusText == 'success' && data.responseText == 'okok') {
				}
				return 'res';
			}},//edit option
			{height : 400,reloadAfterSubmit : true,closeAfterAdd:true},//add option
			{reloadAfterSubmit : false},//dell option
			{multipleSearch: true}//search option
	);
 * @author jason558han
 * @date 2020年01月31日
 */
public class JqDefaultButtonTag extends SimpleTagSupport {

	private Boolean edit = true;//编辑按钮开关，默认为true
	private Boolean add = true;//新建按钮开关，默认为true
	private Boolean del = true;//删除按钮开关，默认为true
	private Boolean search = true;//搜索按钮开关，默认为true
	private Boolean refresh = true;//刷新按钮开关，默认为true
	private Boolean view = true;//视图按钮开关，默认为true

	private Boolean reloadAfterSubmit = true;//提交后刷新列表
	private Boolean closeOnEscape = true;//关闭关联esc

	private String addHeight;//新建窗口高度
	private Boolean closeAfterAdd = true;//新建窗口提交后关闭，默认为true
	private String addAfterSubmit;//新建提交后调用函数 参数data(responseText后台传递过来待msg，statusText:成功success),rowData 返回值[boolean,'msg','']

	private String editHeight;//编辑窗口高度
	private Boolean closeAfterEdit = true;//编辑后窗口关闭，默认为true
	private String editAfterSubmit;//编辑提交后调用函数  参数data(responseText后台传递过来待msg，statusText:成功success),rowData 返回值[boolean,'msg','']

	private String delBeforeShowForm;//关闭窗口出现前调用函数 参数delFormDiv
	private Boolean multipleSearch = true;//开启复合搜索
	private String viewHeight;//视图窗口高度

	@Override
	public void doTag() throws IOException {
		JqGridTag jqGridTag = (JqGridTag) getParent();
		String tableId = jqGridTag.getTableId();
		String pager = jqGridTag.getPager();

		StringBuffer tagBuffer = new StringBuffer();
		tagBuffer.append("jQuery(\"#").append(tableId).append("\").navGrid('#").append(pager).append("',");
		tagBuffer.append("{");//options
		//不为空且不是默认值时，添加
		if (!edit) {
			StringUtil.addJsonObjectStr(tagBuffer, "edit", edit);
		}
		if (!add) {
			StringUtil.addJsonObjectStr(tagBuffer, "add", add);
		}
		if (!del) {
			StringUtil.addJsonObjectStr(tagBuffer, "del", del);
		}
		if (!search) {
			StringUtil.addJsonObjectStr(tagBuffer, "search", search);
		}
		if (!refresh) {
			StringUtil.addJsonObjectStr(tagBuffer, "refresh", refresh);
		}
		if (view) {
			StringUtil.addJsonObjectStr(tagBuffer, "view", view);
		}

		tagBuffer.append("},{");//edit options
		if (reloadAfterSubmit) {
			StringUtil.addJsonObjectStr(tagBuffer, "reloadAfterSubmit", reloadAfterSubmit);
		}
		if (closeOnEscape) {
			StringUtil.addJsonObjectStr(tagBuffer, "closeOnEscape", closeOnEscape);
		}
		if (StringUtil.isNotEmpty(editHeight)) {
			StringUtil.addJsonObjectStr(tagBuffer, "height", editHeight);
		}
		if (closeAfterEdit) {
			StringUtil.addJsonObjectStr(tagBuffer, "closeAfterEdit", closeAfterEdit);
		}
		if (StringUtil.isNotEmpty(editAfterSubmit)) {
			if (editAfterSubmit.startsWith("function")) {
				StringUtil.addJsonObjectStr(tagBuffer, "afterSubmit", editAfterSubmit);
			} else {
				StringUtil.addJsonObjectStr(tagBuffer, "afterSubmit", JqGridTagUtil.getFunctionStr(editAfterSubmit, "data,postd", null, false));
			}
		}

		tagBuffer.append("},{");//add options
		if (reloadAfterSubmit) {
			StringUtil.addJsonObjectStr(tagBuffer, "reloadAfterSubmit", reloadAfterSubmit);
		}
		if (closeOnEscape) {
			StringUtil.addJsonObjectStr(tagBuffer, "closeOnEscape", closeOnEscape);
		}
		if (StringUtil.isNotEmpty(addHeight)) {
			StringUtil.addJsonObjectStr(tagBuffer, "height", addHeight);
		}
		if (closeAfterAdd) {
			StringUtil.addJsonObjectStr(tagBuffer, "closeAfterAdd", closeAfterAdd);
		}
		if (StringUtil.isNotEmpty(addAfterSubmit)) {
			if (addAfterSubmit.startsWith("function")) {
				StringUtil.addJsonObjectStr(tagBuffer, "afterSubmit", addAfterSubmit);
			} else {
				StringUtil.addJsonObjectStr(tagBuffer, "afterSubmit", JqGridTagUtil.getFunctionStr(addAfterSubmit, "data,postd", null, false));
			}
		}

		tagBuffer.append("},{");//del options
		if (reloadAfterSubmit) {
			StringUtil.addJsonObjectStr(tagBuffer, "reloadAfterSubmit", reloadAfterSubmit);
		}
		if (closeOnEscape) {
			StringUtil.addJsonObjectStr(tagBuffer, "closeOnEscape", closeOnEscape);
		}
		if (StringUtil.isNotEmpty(delBeforeShowForm)) {
			if (delBeforeShowForm.startsWith("function")) {
				StringUtil.addJsonObjectStr(tagBuffer, "beforeShowForm", delBeforeShowForm);
			} else {
				StringUtil.addJsonObjectStr(tagBuffer, "beforeShowForm", JqGridTagUtil.getFunctionStr(delBeforeShowForm, "rowData", null, false));
			}
		}

		tagBuffer.append("},{");//search options
		if (closeOnEscape) {
			StringUtil.addJsonObjectStr(tagBuffer, "closeOnEscape", closeOnEscape);
		}
		if (multipleSearch) {
			StringUtil.addJsonObjectStr(tagBuffer, "multipleSearch", multipleSearch);
		}

		tagBuffer.append("},{");//view options
		if (closeOnEscape) {
			StringUtil.addJsonObjectStr(tagBuffer, "closeOnEscape", closeOnEscape);
		}
		if (StringUtil.isNotEmpty(viewHeight)) {
			StringUtil.addJsonObjectStr(tagBuffer, "height", viewHeight);
		}

		tagBuffer.append("});");
		getJspContext().getOut().println(tagBuffer);
	}

	public void setEdit(Boolean edit) {
		this.edit = edit;
	}
	public void setAdd(Boolean add) {
		this.add = add;
	}
	public void setDel(Boolean del) {
		this.del = del;
	}
	public void setSearch(Boolean search) {
		this.search = search;
	}
	public void setRefresh(Boolean refresh) {
		this.refresh = refresh;
	}
	public void setView(Boolean view) {
		this.view = view;
	}
	public void setReloadAfterSubmit(Boolean reloadAfterSubmit) {
		this.reloadAfterSubmit = reloadAfterSubmit;
	}
	public void setCloseOnEscape(Boolean closeOnEscape) {
		this.closeOnEscape = closeOnEscape;
	}
	public void setAddHeight(String addHeight) {
		this.addHeight = addHeight;
	}
	public void setCloseAfterAdd(Boolean closeAfterAdd) {
		this.closeAfterAdd = closeAfterAdd;
	}
	public void setAddAfterSubmit(String addAfterSubmit) {
		this.addAfterSubmit = addAfterSubmit;
	}
	public void setEditHeight(String editHeight) {
		this.editHeight = editHeight;
	}
	public void setCloseAfterEdit(Boolean closeAfterEdit) {
		this.closeAfterEdit = closeAfterEdit;
	}
	public void setEditAfterSubmit(String editAfterSubmit) {
		this.editAfterSubmit = editAfterSubmit;
	}
	public void setDelBeforeShowForm(String delBeforeShowForm) {
		this.delBeforeShowForm = delBeforeShowForm;
	}
	public void setMultipleSearch(Boolean multipleSearch) {
		this.multipleSearch = multipleSearch;
	}
	public void setViewHeight(String viewHeight) {
		this.viewHeight = viewHeight;
	}
}
