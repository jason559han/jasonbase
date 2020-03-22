<%@ page contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/common/taglib.jsp"%>
<script type="text/javascript" src="${ctx}/js/test/testpeople.js"></script>
</head>
<body>
	<table id="lister"></table> 
	<div id="pager"></div>
	<input type="BUTTON" id="bedata" value="Edit Selected" />
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
    				colNames : [ '序号', '姓名', '性别', '年龄', '密码', '出生日期', '启用状态', '修改日期' ],//jqGrid的列显示名字
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
	   				            {name:'password',index:'password',hidden:true,editable:true,edittype:'password',editrules:{edithidden:true,required:true},
	 				            			editoptions:{size:10,maxLength:10},search:false},
    				            {name:'birthday',index:'birthday',aligh:'right',width:70,editable:true,editoptions:{size:12,dataInit:function(el){
    				                    	$(el).datepicker({dateFormat:'yy-mm-dd',changeMonth:true,changeYear:true});
    				                    }},search:true,stype:'text',searchoptions:{dataInit:function(el){
    				                    	$(el).datepicker({dateFormat:'yy-mm-dd',changeMonth:true,changeYear:true})},sopt:['eq','ne','lt','le','gt','ge']}
    				            },
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
    				treeGrid : true,
    				treeGridModel : 'adjacency',
    				toolbar : [ true, "top" ]
    			});
    	$("#t_lister").append(
            "<input type='button' class='rightBtn' value='Click Me' onclick=\"opendialog(jQuery('#lister').jqGrid('getGridParam','treeReader'))\" style='height:20px'/>");
    	/*创建jqGrid的操作按钮容器*/
    	/*可以控制界面上增删改查的按钮是否显示*/
    	/* jQuery("#lister").jqGrid('navGrid', '#pager', 
    			{edit : true,add : true,del : true,view : true,position : 'right'}, //options
    			{height : 400,reloadAfterSubmit : true,closeAfterEdit:true,afterSubmit:function(data,postdata, frmoper) {
    				return 'res';
    			}},//edit option
    			{height : 400,reloadAfterSubmit : true,closeAfterAdd:true},//add option
    			{reloadAfterSubmit : false},//dell option
    			{multipleSearch: true}//search option
    	); */
    	jQuery("#lister")  
    	.navGrid('#pager',{edit:false,add:false,del:false,search:false})  
    	.navButtonAdd('#pager',{  
    	   caption:"Add",   
    	   buttonicon:"ui-icon-add",   
    	   onClickButton: function(){   
    	     alert("Adding Row");  
    	   },   
    	   position:"last"  
    	})  
    	.navSeparatorAdd('#pager',{  
    	   position:"last"  
    	});
    	$("#bedata").click(function() {
    	    var gr = jQuery("#lister").jqGrid('getGridParam', 'selrow');
    	    if (gr != null)
    	      jQuery("#lister").jqGrid('editGridRow', gr, {
    	        height : 300,
    	        reloadAfterSubmit : false
    	      });
    	    else
    	      alert("Please Select Row");
    	  });
    	jQuery("#lister").jqGrid('gridResize',{minWidth:350,maxWidth:800,minHeight:80, maxHeight:350});
    	jQuery("#lister").jqGrid('bindKeys', {"onEnter":function( rowid ) { alert("你enter了一行， id为:"+rowid)}});
    }
    </script>
</body>
</html>