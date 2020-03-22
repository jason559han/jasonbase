$(function() {
	$('#editMenuDialog').dialog({
		autoOpen: false,
		width: '450px',
		buttons: {
			'提交':function () {
				if (validMenuDialog()) {
					$.ajax({
						url:'menuEdit.do',
						data:$('#FrmGrid_lister').serialize(),
						type:'post',
						dataType:'text',
						success:function (data) {
							if (data==='okok') {
								$('#lister').trigger('reloadGrid');
								closeEditDialog('editMenuDialog','FrmGrid_lister');
							}
						},
						error:function (request) {
							$('#FrmGrid_lister .FormError').html(request.responseText).show();
						}
					});
				}
			},
			'取消':function () {
				closeEditDialog('editMenuDialog','FrmGrid_lister');
			}
		}
	});
	$("#permissionsDialog").dialog({
		autoOpen:false,
		width:'75%',
		buttons:{
			'确定':function() {
				if (checkPermissionUrls()) {
					$.ajax({
						url: "insertPermissionsUrlList.do",
						type: "post",
						data: $("#permissionsForm").serialize(),
						dataType: "text",
						success: function (data) {
							if (data === "okok") {
								clearPermissionsParams();
								$("#permissionsDialog").dialog("close");
							} else {
								$.alertDialog({content:"权限链接配置失败！"});
							}
						}
					});
				}
			},
			'取消':function() {
				clearPermissionsParams();
				$("#permissionsDialog").dialog("close");
			}
		}
	});
});

/**
 * 显示添加菜单窗口
 * @param rowData 选择行数据
 */
function showAddDialog(rowData) {
	initEditDialog('add',rowData);
}

/**
 * 显示修改菜单窗口
 * @param rowData
 */
function showEditDialog(rowData) {
	if (rowData) {
		initEditDialog('edit',rowData);
	} else {
		$.alertDialog({content:'请选择一条记录！'});
	}
}

/**
 * 验证菜单表单
 */
function validMenuDialog() {
	const menuName = $('#menuName').val();
	const code = $('#code').val();
	const orderNumber = $('#orderNumber').val();
	const menuType = $('#menuType').val();
	const linkFile = $('#linkFile').val();
	const level = $('#level').val();
	const codeHeader = $('#codeHeader').val();

	if (!(checkRequired(menuName,'菜单名称') && checkRequired(code,'菜单编码')
		&& checkRequired(orderNumber, '顺序'))) {
		return false;
	}
	if (level == 1 && code.indexOf(codeHeader) !== 0) {
		$('#FrmGrid_lister .FormError').html('编码:该字段必须以父编码开头！').show();
		$('#code').val(codeHeader);
		return false;
	}
	if (menuType == 1 && !checkRequired(linkFile, '菜单链接')) {
		return false;
	}
	return true;
}

/**
 * 初始化编辑窗口
 * @param oper
 */
function initEditDialog(oper,menuData) {
	const upMenuData = menuData&&menuData.parent ? $('#lister').jqGrid('getRowData', menuData.parent) : null;
	$('#oper').val(oper);
	switch (oper) {
		case 'add':
			$('#level').val(menuData ? 1 : 0);
			if (menuData) {
				if (upMenuData) {
					$('#parent').val(upMenuData.id);
					$('#codeHeader').val(upMenuData.code+'\.');
					$('#code').val(upMenuData.code+'\.');
				} else {
					$('#parent').val(menuData.id);
					$('#codeHeader').val(menuData.code+'\.');
					$('#code').val(menuData.code+'\.');
				}
			}
			break;
		case 'edit':
			$('#id').val(menuData.id);
			$('#level').val(menuData.level);
			if (menuData.level == 1) {
				$('#parent').val(menuData.parent);
				$('#codeHeader').val(upMenuData.code+'\.');
			}
			$('#menuName').val(menuData.menuName);
			$('#code').val(menuData.code);
			$('#orderNumber').val(menuData.orderNumber);
			$('#menuType').val(menuData.menuType);
			$('#linkFile').val(menuData.linkFile);
			$('#state').val(menuData.state);
			break;
	}
	$('#editMenuDialog').dialog('open');
}

/**
 * 显示权限链接配置窗口
 * @param rowData
 * @returns
 */
function showPermissionsDialog(rowData) {
	if (rowData) {
		setPermissionsToParams(rowData);
		$("#permissionsDialog").dialog("open");
	} else {
		$.alertDialog({content:'请选择一条菜单进行权限链接配置'});
	}
}

/**
 * 配置权限链接信息
 * @param rowData
 * @returns
 */
function setPermissionsToParams(rowData) {
	$("#menuId").val(rowData.id);
	$("#menuNameTd").text(rowData.menuName);
	$("#menuUrl").text(rowData.linkFile);
	$.ajax({
		url: "toPermissionsUrlListJson.do",
		type: "post",
		data: {'menuId':rowData.id},
		dataType: "json",
		success: function (urlList) {
			if (urlList&&urlList.length) {
				for (let i=0; i<urlList.length; i++) {
					$("#permissionsUrlTbody").append("<tr class='ui-tr'><td class='ui-td'>"
							+"<input type='text' name='permissionsUrl' maxLength='200' size='50' value='"+urlList[i].permissionsUrl+"'/>"
							+"<span class='red'/>"
							+"</td><td class='ui-td'>"
							+"<input type='text' name='remark' maxLength='100' size='20' value='"+urlList[i].remark+"'/>"
							+"</td><td class='ui-td' align='center'>"
							+"<a href='#' onclick='deletePermissionsUrlTr(this)'>删除</a>"
							+"</td></tr>");
				}
			}
		}
	});
}

/**
 * 添加权限链接行
 * @returns
 */
function addPermissionsUrl() {
	$("#permissionsUrlTbody").append("<tr class='ui-tr'><td class='ui-td'>"
			+"<input type='text' name='permissionsUrl' maxLength='200' size='50' value=''/>"
			+"<span class='red'/>"
			+"</td><td class='ui-td'>"
			+"<input type='text' name='remark' maxLength='100' size='20' value=''/>"
			+"</td><td class='ui-td' align='center'>"
			+"<a href='#' onclick='deletePermissionsUrlTr(this)'>删除</a>"
			+"</td></tr>");
}

/**
 * 删除权限链接行
 * @param elem
 * @returns
 */
function deletePermissionsUrlTr(elem) {
	$(elem).parent().parent().remove();
}

/**
 * 清空权限链接信息
 * @returns
 */
function clearPermissionsParams() {
	$("#menuId").val("");
	$("#menuNameTd").text("");
	$("#menuUrl").text("");
	$("#permissionsUrlTbody").empty();
}

/**
 * 验证权限链接是否为空
 * @returns
 */
function checkPermissionUrls() {
	$("span.red").text("");
	var b = true;
	var permissionsUrlElems = $("input[name='permissionsUrl']");
	for (var i=0; i<permissionsUrlElems.length; i++) {
		if (!permissionsUrlElems[i].value) {
			$(permissionsUrlElems[i]).parent().find("span.red").text("权限链接必填");
			b = false;
		}
	}
	return b;
}