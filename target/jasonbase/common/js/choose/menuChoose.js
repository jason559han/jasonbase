
//菜单列表添加
var nameFormat = '<input type="checkbox" id="checkChxAll" onclick="checkboxChxAll()"/>';
var nameFormatChecked = '<input type="checkbox" id="checkChxAll" checked="checked" onclick="checkboxChxAll()"/>';

(function($){
	/* 菜单选择框 options={selIds:"1,2",success:function(ids){} */
	$.menuChooseDialog = function(options) {
		var menuTitle = "菜单选择框";
		var chooseHtml = "<div title='"+menuTitle+"'><table id='chooseLister'/>" +
				"<div id='choosePager'/></div>";
		var chooseDialogDiv = $(chooseHtml).appendTo("body").dialog({
			dialogClass : "suspend-dialog",
			width : 380,
			position: ['top','left'],
			buttons : {
				'确定':function() {
					var ids = getSelectIds();
					options.success&&options.success(ids);
					closeChooseDialog();
				},
				'取消':function() {
					closeChooseDialog();
				}
			}
		});

		//获取选取
		function getSelectIds() {
			var ids = "";
			$("input[id^='chx']").each(function() {
				if ($(this).prop("checked")) {
					if (ids !== "") ids += ",";
					ids += $(this).val();
				}
			});
			return ids;
		}

		//关闭选择悬浮框
		function closeChooseDialog() {
			chooseDialogDiv.dialog("close").remove();
		}

		jQuery("#chooseLister").jqGrid({
			url:ctx+'/menuChoose/menuChooseIndexJson.do',
			datatype:'json',
			pager:'#choosePager',
			colNames:['序号','<label id="chxLabel">'+nameFormat+'</label><span>名称</span>','菜单类型'],
			colModel:[
				{name:'id',index:'id',hidden:true,key:true},
				{name:'menuName',index:'menuName',width:250,resizable:true,sortable:false,formatter:showName},
				{name:'menuType',index:'menuType',width:80,formatter:'select',editoptions:{value:'0:菜单大类;1:菜单链接'}}
			],
			treeGrid:true,
			treeGridModel:'adjacency',
			treedatatype:'json',
			ExpandColumn:'menuName',
			height:'100%',
			loadonce:true,
			sortable:false,
			gridComplete: function() {
				//默认选择菜单
				if (options.selIds) {
					$("input[id^='chx']").each(function() {
						var idStr = ","+options.selIds+",";
						if (idStr.search(","+$(this).val()+",") !== -1) {
							$(this).prop("checked", true);
						}
					});
				}
			}
		});

		//添加选择框
		function showName(cellvalue,options,cell) {
			const rowId = cell[0];
			return '<label><input type="checkbox" id="chx' + rowId + '" value="' + rowId + '" onchange="clickCheckbox(' + rowId
				+ ', this)"/>' + cellvalue + '</label>';
		}
	}
})(jQuery);

//全选中//全不选中
function checkboxChxAll() {
	var allChecked = $("#checkChxAll").is(':checked');
	$("input[id^='chx']").each(function() {
		$(this).prop("checked", allChecked);
	});
	$("#chxLabel").html(allChecked?nameFormatChecked:nameFormat);
}

//checkbox事件
function clickCheckbox(rowid, $this) {
	checkChildren(rowid, $this);
	if ($($this).is(':checked')) {
		checkParent(rowid, $this);
	} else {
		$("#checkChxAll").prop("checked",false);
	}
}

//递归选中子节点
function checkChildren(rowid,$this){
	const records = $("#chooseLister").jqGrid('getNodeChildren', $("#chooseLister").jqGrid("getLocalRow", rowid));
	if (records.length>0) {
		for (let i=0; i<records.length; i++) {
			const cb = $("#chx" + records[i].id);
			cb.prop("checked", $($this).is(':checked'));
			checkChildren(records[i].id, cb);
		}
	}
}

//递归选中父节点
function checkParent(rowid, $this){
	const parent = $("#chooseLister").jqGrid('getNodeParent', $("#chooseLister").jqGrid("getLocalRow", rowid));
	if (parent) {
		const cb = $("#chx" + parent.id);
		cb.prop("checked", $($this).is(':checked'));
		checkParent(parent.id,cb);
	}
}