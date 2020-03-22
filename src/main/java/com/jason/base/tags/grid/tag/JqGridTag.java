package com.jason.base.tags.grid.tag;

import com.jason.base.common.utils.StringUtil;
import com.jason.base.tags.grid.JqGridConstants;
import com.jason.base.tags.grid.annotation.*;
import com.jason.base.tags.grid.bean.JqColModel;
import com.jason.base.tags.grid.utils.JqGridTagUtil;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * jqgrid表格自定义标签
 * （未添加：[a1],[a2],autoencode,autowidth,cellLayout,cellsubmit,cellurl,datastr,deselectAfterSort,direction,
 * [a3],forceFit,gridstate,gridview一次性加载数据,hoverrows,loadonce,loadtext,loadui,
 * multikey,prmNames,postData,recordtext,resizeclass,subGrid,subGridModel,subGridType,subGridUrl,subGridWidth,
 * viewsortcols,cmTemplate）
 * （只读属性：lastpage,lastsort,reccount,records,savedRow,selarrrow,selrow,totaltime,sortable,userData,treeReader,
 * tree_root_level(root元素级别),jsonReader,xmlReader,treedatatype[默认与datatype相同]）
 * 整体实例：
 * 	<table id="lister"></table> 
	<div id="pager"></div>
    <script type="text/javascript">
    $(function(){
    	//页面加载完成之后执行
    	pageInit();
    });
    function pageInit(){
    	//创建jqGrid组件
    	jQuery("#lister").jqGrid(
    			{
    				url : 'testIndexJson.do',//组件创建完成之后请求数据的url
    				datatype : "json",//请求数据返回的类型。可选json,xml,txt
    				colNames : [ '序号', '姓名', '性别', '年龄', '出生日期', '密码', '启用状态', '修改日期'],//jqGrid的列显示名字
    				colModel : [ //jqGrid每一列的配置信息。包括名字，索引，宽度,对齐方式.....
    				             {name:'id', index:'id', align:'left', width:45, editable:false,search:false, 
    								editoptions:{readonly:true}}, 
    				             {name:'name', index:'name', align:'center', width:90, editable:true, editrules:{required:true}, 
    				            	 editoptions:{size:10,maxLength:7},search:true,stype:'text',searchoptions:{sopt:['cn','nc','bw','bn','ew','en']}},
    				             {name:'sex',index:'sex',align:'center',width:60,editable:true,edittype:'select',
    				            		 editoptions:{value:"0:待定;1:男;2:女"},formatter:'select',
    				            		 search:true,stype:'select',searchoptions:{sopt:['eq'],value:'0:待定;1:男;2:女'}},
    				             {name:'age',index:'age',align:'right',width:50,editable:true,editoptions:{size:10},
    				            			editrules:{required:true,number:true,minValue:10,maxValue:130},
    				            			search:true,stype:'text',searchoptions:{sopt:['eq','ne','lt','le','gt','ge']}},
    				            {name:'birthday',index:'birthday',aligh:'right',width:70,editable:true,editoptions:{size:12,dataInit:function(el){
    				                    	$(el).datepicker({dateFormat:'yy-mm-dd',changeMonth:true,changeYear:true});
    				                    }},search:true,stype:'text',searchoptions:{dataInit:function(el){
    				                    	$(el).datepicker({dateFormat:'yy-mm-dd',changeMonth:true,changeYear:true})},sopt:['eq','ne','lt','le','gt','ge']}
    				            },
    				            {name:'password',index:'password',hidden:true,editable:true,edittype:'password',editrules:{edithidden:true,required:true},
    				            			editoptions:{size:10,maxLength:10},search:false},
    				            {name:'status',index:'status',width:50,editable:false,editoptions:{value:"0:未启用;1:启用"},
    				            				formatter:'select',search:true,stype:'select',searchoptions:{sopt:['eq'],value:"0:未启用;1:启用"}},
    		    				{name:'editDate',index:'editDate',aligh:'right',width:70,search:true,stype:'text',searchoptions:{dataInit:function(el){
    	    				            	$(el).datepicker({dateFormat:'yy-mm-dd'})},sopt:['eq','ne','lt','le','gt','ge']}
    	    				      }
    				           ],
    				rowNum : 10,//一页显示多少条
    				height : '100%',
    				rowList : [ 10, 20, 30 ],//可供用户选择一页显示多少条
    				pager : '#pager',//表格页脚的占位符(一般是div)的id
    				sortname : 'id',//初始化的时候排序的字段
    				sortorder : "asc",//排序方式,可选desc,asc
    				mtype : "post",//向后台请求数据的ajax的类型。可选post,get
    				viewrecords : true,
    				multiselect : false,
    				editurl : 'testEdit.do',
    				caption : "人员列表",//表格的标题名字
    				toolbar : [ true, "top" ]
    			});
    }
    </script>
 * @author jason558han
 * @date 2020年01月29日
 */
public class JqGridTag extends SimpleTagSupport {

	private String tableId = "lister";//table主题id，默认为lister
	private String pager = "pager";//表格页脚的占位符(一般是div)的id，默认为pager
	private String url;//组件创建完成之后请求数据的url
	private String datatype = "json";//请求数据返回的类型。可选json,xml,txt，默认json
	private String mtype = "GET";//ajax提交方式。POST或者GET，默认GET
	private String clzzName;//colModel对应的实体类
	private String excludes;//屏蔽字段 字段a,字段b
	private Integer rowNum = 10;//在grid上显示记录条数，这个参数是要被传递到后台,默认为10
	private String rowList = "[10, 20, 30]";//可供用户选择一页显示多少条，默认为[10, 20, 30]
	private String sortname;//初始化的时候排序的字段
	private String sortorder = "asc";//排序方式,可选desc,asc，默认asc
	private Integer width = 0;//如果设置则按此设置为主，如果没有设置则按colModel中定义的宽度计算
	private String height;//表格高度，可以是数字，像素值或者百分比
	private Boolean viewrecords = true;//是否要显示总记录数，默认为true
	private String emptyrecords;//当返回的数据行数为0时显示的信息。只有当属性 viewrecords 设置为ture时起作用
	private String editurl;//定义对form编辑时的url
	private Boolean altRows = false;//设置表格 zebra-striped 值，默认为false
	private String altclass;//用来指定行显示的css，可以编辑自己的css文件，只有当altRows设为 ture时起作用
	private Boolean cellEdit = false;//启用或者禁用单元格编辑功能，默认为false
	private Boolean hiddengrid = false;//当为ture时，表格不会被显示，只显示表格的标题。只有当点击显示表格的那个按钮时才会去初始化表格数据。默认为false
	private Boolean hidegrid = true;//启用或者禁用控制表格显示、隐藏的按钮，只有当caption 属性不为空时起效
	private Boolean multiselect = false;//定义是否可以多选，默认为false
	private Boolean multiboxonly = false;//只有当multiselect = true.起作用，当multiboxonly 为ture时只有选择checkbox才会起作用，默认为false
	private Integer multiselectWidth = 20;//当multiselect为true时设置multiselect列宽度，默认为20
	private Boolean scroll = false;//创建一个动态滚动的表格，当为true时，翻页栏被禁用，使用垂直滚动条加载数据，且在首次访问服务器端时将加载所有数据到客户端。当此参数为数字时，表格只控制可见的几行，所有数据都在这几行中加载
	private Integer scrollOffset = 18;//设置垂直滚动条宽度
	private Boolean scrollrows = false;//当为true时让所选择的行可见
	private Integer page = 1;//设置初始的页码
	private String pagerpos = "center";//指定分页栏的位置，默认为center
	private Boolean pgbuttons = true;//是否显示翻页按钮，默认为true
	private Boolean pginput = true;//是否显示跳转页面的输入框，默认为true
	private String pgtext;//当前页信息
	private String recordpos = "right";//定义了记录信息的位置： left, center, right(默认)
	private Boolean rownumbers = false;//如果为ture则会在表格左边新增一列，显示行顺序号，从1开始递增。此列名为'rn'.
	private Integer rownumWidth = 25;//如果rownumbers为true，则可以设置column的宽度
	private String toolbar = "[false,'']";//表格的工具栏。数组中有两个值，第一个为是否启用，第二个指定工具栏位置（相对于body layer），如：[true,”both”] 。工具栏位置可选值：“top”,”bottom”, “both”. 如果工具栏在上面，则工具栏id为“t_”+表格id；如果在下面则为 “tb_”+表格id；如果只有一个工具栏则为 “t_”+表格id
	private Boolean userDataOnFooter = false;//页面汇总行。当为true时把userData放到底部，用法：如果userData的值与colModel的值相同，那么此列就显示正确的值，如果不等那么此列就为空
	private Boolean shrinkToFit = false;//此属性用来说明当初始化列宽度时候的计算类型，如果为ture，则按比例初始化列宽度。如果为false，则列宽度使用colModel指定的宽度
	private String onSelectRow;//参数rowid,status，当选择行时触发此事件。rowid：当前行id；status：选择状态，当multiselect 为true时此参数才可用
	private String ondblClickRow;//参数rowid,iRow,iCol,e，双击行时触发。rowid：当前行id；iRow：当前行索引位置；iCol：当前单元格位置索引；e:event对象
	private Boolean treeGrid = false;//启用或者禁用treegrid模式，默认false
	private final String treeGridModel = "adjacency";//treeGrid所使用的方法，默认adjacency，无法修改
	private String expandColumn;//指定那列来展开tree grid，该值在jqgrid注解中设置，默认为第一列，只有在treeGrid为true时起作用
	private Boolean expandColClick = true;//当为true时，点击展开行的文本时，treeGrid就能展开或者收缩，不仅仅是点击图片
	private String treeIcons = "{plus:'ui-icon-triangle-1-e',minus:'ui-icon-triangle-1-s',leaf:'ui-icon-radio-off'}";//树的图标，默认值：{plus:'ui-icon-triangle-1-e',minus:'ui-icon-triangle-1-s',leaf:'ui-icon-radio-off'}

	@Override
	public void doTag() throws JspException, IOException {
		StringBuffer tagBuffer = new StringBuffer();
		JspWriter out = getJspContext().getOut();

		//表单
		tagBuffer.append("<table id=\"").append(tableId).append("\"></table>");
		tagBuffer.append("<div id=\"").append(pager).append("\"></div>");
		//js表达式
		tagBuffer.append("<script type=\"text/javascript\">");
		tagBuffer.append("$(function(){");
		tagBuffer.append("pageInit();").append("});");
		tagBuffer.append("function pageInit(){");
		tagBuffer.append("jQuery(\"#").append(tableId).append("\").jqGrid({");
		StringUtil.addJsonObjectStr(tagBuffer, "url", url);
		StringUtil.addJsonObjectStr(tagBuffer, "datatype", datatype);
		StringUtil.addJsonObjectStr(tagBuffer, "pager", "#"+pager);
		if (StringUtil.isNotEmpty(mtype) && !"GET".equalsIgnoreCase(mtype)) {
			StringUtil.addJsonObjectStr(tagBuffer, "mtype", mtype);
		}

		try {
			Class<?> clzz = Class.forName(clzzName);
			if (clzz.isAnnotationPresent(JqGrid.class)) {//表格标题
				String caption = clzz.getAnnotation(JqGrid.class).displayName();
				StringUtil.addJsonObjectStr(tagBuffer, "caption", caption);
			}

			//获取colModels数据
			Field[] fields = clzz.getDeclaredFields();
			if (fields.length > 0) {
				List<JqColModel> colModels = new ArrayList<>();

				String[] excludeStrs = null;
				if (StringUtil.isNotEmpty(excludes)) {
					excludeStrs = excludes.split(",");
				} else {
					excludeStrs = new String[]{};
				}

				roundField : for (Field field : fields) {
					if (field.isAnnotationPresent(JqGrid.class)) {
						//指定tree展开列
						if (field.isAnnotationPresent(JqTreeGrid.class) && field.getAnnotation(JqTreeGrid.class).expandColumn()) {
							expandColumn = field.getName();
						}
						//排除屏蔽字段
						if (StringUtil.isContainInArray(excludeStrs, field.getName())) {
							continue roundField;
						}

						JqGrid jqGrid = field.getAnnotation(JqGrid.class);
						JqColModel jqColModel = new JqColModel(field.getName(), jqGrid.serial(), jqGrid);

						if (field.isAnnotationPresent(Formatoptions.class)) {
							jqColModel.setFormatoptions(field.getAnnotation(Formatoptions.class));
						}
						if (field.isAnnotationPresent(Editoptions.class)) {
							jqColModel.setEditoptions(field.getAnnotation(Editoptions.class));
						}
						if (field.isAnnotationPresent(Editrules.class)) {
							jqColModel.setEditrules(field.getAnnotation(Editrules.class));
						}
						if (field.isAnnotationPresent(Searchoptions.class)) {
							jqColModel.setSearchoptions(field.getAnnotation(Searchoptions.class));
						}
						colModels.add(jqColModel);
					}
				}
				Collections.sort(colModels);

				//colNames列名称
				StringBuffer colNamesBuffer = new StringBuffer("[");
				for (JqColModel colModel : colModels) {
					if (colNamesBuffer.length()>1) {
						colNamesBuffer.append(",");
					}
					colNamesBuffer.append("'").append(colModel.getJqGrid().displayName()).append("'");
				}
				colNamesBuffer.append("]");
				StringUtil.addJsonObjectStr(tagBuffer, "colNames", colNamesBuffer);

				//colModel列模版
				StringBuffer colModelBuffer = new StringBuffer("[");
				for (JqColModel colModel : colModels) {
					if (colModelBuffer.length()>1) {
						colModelBuffer.append(",");
					}
					colModelBuffer.append(colModel.toString());
				}
				colModelBuffer.append("]");
				StringUtil.addJsonObjectStr(tagBuffer, "colModel", colModelBuffer);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//treegrid，树状表结构设置
		if (treeGrid != null && treeGrid) {
			StringUtil.addJsonObjectStr(tagBuffer, "treeGrid", treeGrid);
			StringUtil.addJsonObjectStr(tagBuffer, "treeGridModel", treeGridModel);
			StringUtil.addJsonObjectStr(tagBuffer, "treedatatype", datatype);
			if (StringUtil.isNotEmpty(expandColumn)) {
				StringUtil.addJsonObjectStr(tagBuffer, "ExpandColumn", expandColumn);
			}
			if (expandColClick) {
				StringUtil.addJsonObjectStr(tagBuffer, "ExpandColClick", expandColClick);
			}
			if (StringUtil.isNotEmpty(treeIcons)) {
				StringUtil.addJsonObjectStr(tagBuffer, "treeIcons", treeIcons);
			}

			//默认排序code
			if (StringUtil.isEmpty(sortname)) {
				sortname = JqGridConstants.CODE_FIELD_NAME;
			}
		} else {
			StringUtil.addJsonObjectStr(tagBuffer, "rowNum", rowNum);
			StringUtil.addJsonObjectStr(tagBuffer, "rowList", rowList);
		}

		//如果不为空，并且不是默认值，则添加
		if (StringUtil.isNotEmpty(sortname)) {
			StringUtil.addJsonObjectStr(tagBuffer, "sortname", sortname);
		}
		if (StringUtil.isNotEmpty(sortorder) && !"asc".equalsIgnoreCase(sortorder)) {
			StringUtil.addJsonObjectStr(tagBuffer, "sortorder", sortorder);
		}

		if (width != null && width > 0) {
			StringUtil.addJsonObjectStr(tagBuffer, "width", width);
		}
		if (StringUtil.isNotEmpty(height) && !"150".equals(height)) {
			StringUtil.addJsonObjectStr(tagBuffer, "height", height);
		}
		if (viewrecords) {
			StringUtil.addJsonObjectStr(tagBuffer, "viewrecords", viewrecords);
		}
		if (StringUtil.isNotEmpty(emptyrecords)) {
			StringUtil.addJsonObjectStr(tagBuffer, "emptyrecords", emptyrecords);
		}
		if (StringUtil.isNotEmpty(editurl)) {
			StringUtil.addJsonObjectStr(tagBuffer, "editurl", editurl);
		}
		if (altRows) {
			StringUtil.addJsonObjectStr(tagBuffer, "altRows", altRows);
		}
		if (StringUtil.isNotEmpty(altclass)) {
			StringUtil.addJsonObjectStr(tagBuffer, "altclass", altclass);
		}
		if (cellEdit) {
			StringUtil.addJsonObjectStr(tagBuffer, "cellEdit", cellEdit);
		}
		if (hiddengrid) {
			StringUtil.addJsonObjectStr(tagBuffer, "hiddengrid", hiddengrid);
		}
		if (!hidegrid) {
			StringUtil.addJsonObjectStr(tagBuffer, "hidegrid", hidegrid);
		}
		if (multiselect) {
			StringUtil.addJsonObjectStr(tagBuffer, "multiselect", multiselect);
		}
		if (multiboxonly) {
			StringUtil.addJsonObjectStr(tagBuffer, "multiboxonly", multiboxonly);
		}
		if (multiselectWidth != null && multiselectWidth > 0 && multiselectWidth != 20) {
			StringUtil.addJsonObjectStr(tagBuffer, "multiselectWidth", multiselectWidth);
		}
		if (scroll) {
			StringUtil.addJsonObjectStr(tagBuffer, "scroll", scroll);
		}
		if (scrollOffset != null && scrollOffset >0 && scrollOffset != 18) {
			StringUtil.addJsonObjectStr(tagBuffer, "scrollOffset", scrollOffset);
		}
		if (scrollrows) {
			StringUtil.addJsonObjectStr(tagBuffer, "scrollrows", scrollrows);
		}
		if (page != null && page > 1) {
			StringUtil.addJsonObjectStr(tagBuffer, "page", page);
		}
		if (StringUtil.isNotEmpty(pagerpos) && !"center".equals(pagerpos)) {
			StringUtil.addJsonObjectStr(tagBuffer, "pagerpos", pagerpos);
		}
		if (!pgbuttons) {
			StringUtil.addJsonObjectStr(tagBuffer, "pgbuttons", pgbuttons);
		}
		if (!pginput) {
			StringUtil.addJsonObjectStr(tagBuffer, "pginput", pginput);
		}
		if (StringUtil.isNotEmpty(pgtext)) {
			StringUtil.addJsonObjectStr(tagBuffer, "pgtext", pgtext);
		}
		if (StringUtil.isNotEmpty(recordpos) && !"right".equals(recordpos)) {
			StringUtil.addJsonObjectStr(tagBuffer, "recordpos", recordpos);
		}
		if (rownumbers) {
			StringUtil.addJsonObjectStr(tagBuffer, "rownumbers", rownumbers);
		}
		if (rownumWidth != null && rownumWidth > 0 && rownumWidth != 25) {
			StringUtil.addJsonObjectStr(tagBuffer, "rownumWidth", rownumWidth);
		}
		if (StringUtil.isNotEmpty(toolbar) && !"[false,'']".equals(toolbar)) {
			StringUtil.addJsonObjectStr(tagBuffer, "toolbar", toolbar);
		}
		if (userDataOnFooter) {
			StringUtil.addJsonObjectStr(tagBuffer, "userDataOnFooter", userDataOnFooter);
		}
		if (shrinkToFit) {
			StringUtil.addJsonObjectStr(tagBuffer, "shrinkToFit", shrinkToFit);
		}
		if (StringUtil.isNotEmpty(onSelectRow)) {
			if (onSelectRow.startsWith("function")) {
				StringUtil.addJsonObjectStr(tagBuffer, "onSelectRow", onSelectRow);
			} else {
				StringUtil.addJsonObjectStr(tagBuffer, "onSelectRow", JqGridTagUtil.getFunctionStr(onSelectRow, "rowid,status", null, false));
			}
		}
		if (StringUtil.isNotEmpty(ondblClickRow)) {
			if (ondblClickRow.startsWith("function")) {
				StringUtil.addJsonObjectStr(tagBuffer, "ondblClickRow", ondblClickRow);
			} else {
				StringUtil.addJsonObjectStr(tagBuffer, "ondblClickRow", JqGridTagUtil.getFunctionStr(ondblClickRow, "rowid,iRow,iCol,e", null, false));
			}
		}

		tagBuffer.append("});");
		out.println(tagBuffer);

		if (getJspBody() != null) {
			getJspBody().invoke(null);
		}
		out.println("}</script>");
	}

	public String getTableId() {
		return tableId;
	}
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}
	public String getPager() {
		return pager;
	}
	public void setPager(String pager) {
		this.pager = pager;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}
	public void setMtype(String mtype) {
		this.mtype = mtype;
	}
	public void setClzzName(String clzzName) {
		this.clzzName = clzzName;
	}
	public void setExcludes(String excludes) {
		this.excludes = excludes;
	}
	public void setRowNum(Integer rowNum) {
		this.rowNum = rowNum;
	}
	public void setRowList(String rowList) {
		this.rowList = rowList;
	}
	public void setSortname(String sortname) {
		this.sortname = sortname;
	}
	public void setSortorder(String sortorder) {
		this.sortorder = sortorder;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public void setViewrecords(Boolean viewrecords) {
		this.viewrecords = viewrecords;
	}
	public void setEmptyrecords(String emptyrecords) {
		this.emptyrecords = emptyrecords;
	}
	public void setEditurl(String editurl) {
		this.editurl = editurl;
	}
	public void setAltRows(Boolean altRows) {
		this.altRows = altRows;
	}
	public void setAltclass(String altclass) {
		this.altclass = altclass;
	}
	public void setCellEdit(Boolean cellEdit) {
		this.cellEdit = cellEdit;
	}
	public void setHiddengrid(Boolean hiddengrid) {
		this.hiddengrid = hiddengrid;
	}
	public void setHidegrid(Boolean hidegrid) {
		this.hidegrid = hidegrid;
	}
	public Boolean getMultiselect() {
		return multiselect;
	}
	public void setMultiselect(Boolean multiselect) {
		this.multiselect = multiselect;
	}
	public void setMultiboxonly(Boolean multiboxonly) {
		this.multiboxonly = multiboxonly;
	}
	public void setMultiselectWidth(Integer multiselectWidth) {
		this.multiselectWidth = multiselectWidth;
	}
	public void setScroll(Boolean scroll) {
		this.scroll = scroll;
	}
	public void setScrollOffset(Integer scrollOffset) {
		this.scrollOffset = scrollOffset;
	}
	public void setScrollrows(Boolean scrollrows) {
		this.scrollrows = scrollrows;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public void setPagerpos(String pagerpos) {
		this.pagerpos = pagerpos;
	}
	public void setPgbuttons(Boolean pgbuttons) {
		this.pgbuttons = pgbuttons;
	}
	public void setPginput(Boolean pginput) {
		this.pginput = pginput;
	}
	public void setPgtext(String pgtext) {
		this.pgtext = pgtext;
	}
	public void setRecordpos(String recordpos) {
		this.recordpos = recordpos;
	}
	public void setRownumbers(Boolean rownumbers) {
		this.rownumbers = rownumbers;
	}
	public void setRownumWidth(Integer rownumWidth) {
		this.rownumWidth = rownumWidth;
	}
	public void setToolbar(String toolbar) {
		this.toolbar = toolbar;
	}
	public void setUserDataOnFooter(Boolean userDataOnFooter) {
		this.userDataOnFooter = userDataOnFooter;
	}
	public void setShrinkToFit(Boolean shrinkToFit) {
		this.shrinkToFit = shrinkToFit;
	}
	public void setOnSelectRow(String onSelectRow) {
		this.onSelectRow = onSelectRow;
	}
	public void setOndblClickRow(String ondblClickRow) {
		this.ondblClickRow = ondblClickRow;
	}
	public void setTreeGrid(Boolean treeGrid) {
		this.treeGrid = treeGrid;
	}
	public void setExpandColClick(Boolean expandColClick) {
		this.expandColClick = expandColClick;
	}
	public void setTreeIcons(String treeIcons) {
		this.treeIcons = treeIcons;
	}
}
