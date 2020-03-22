
/**
 * 配置菜单
 * @param rowData
 * @returns
 */
function configMenus(rowData) {
	if (rowData) {
		$.menuChooseDialog({
			selIds:rowData.menuIds,
			success:function(ids) {
				if (ids) {
					$.ajax({
						url: "roleSetMenuIds.do",
						type: "post",
						data: {roleId:rowData.id,menuIds:ids},
						dataType: "text",
						success: function (data) {
							if (data === "okok") {
								$("#lister").trigger("reloadGrid");//刷新表格
							} else {
								$.alertDialog({content:"配置菜单失败！"});
							}
						}
					});
				} else {
					$.alertDialog({content:"请至少选择一条菜单。"});
				}
			}
		});
	} else {
		$.alertDialog({content:"请选择一个角色进行配置。"});
	}
}