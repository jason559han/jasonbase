
/**
 * 选择角色悬浮框
 * @param options
 * @returns
 */
function showRoleChooseDialog(options) {
  $.iframeDialog({
	title : "选择角色窗口",
	url : ctx+"/roleChoose/roleChooseIndex.do",
	buttons : {
	  '确认':function() {
	    const rowData = document.getElementById("myFrame").contentWindow.getRoleRowData();
	    if (rowData) {
	      options&&options.success&&options.success(rowData);
		  $.closeIframeDialog();
	    } else {
	      $.alertDialog({content:"请选择一条记录！"});
	    }
	  },
	  '取消':function() {
	    $.closeIframeDialog();
	  }
	},
	width : 490,
	height : 355
  });
}

/**
 * 获得选择行的数据
 * @returns
 */
function getRoleRowData() {
	let rowData = null;
	const id = $("#chooseLister").jqGrid('getGridParam', 'selrow');
	if (id) {
	  rowData = $("#chooseLister").jqGrid('getRowData', id);
  }
  return rowData;
}