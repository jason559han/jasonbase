$(function() {
  //添加管理员窗口
  $("#addDialog").dialog({
    autoOpen:false,
    buttons:{
      '提交':function() {
        const managerAccount = $("#managerAccount").val();
        const realName = $("#realName").val();
        if (checkRequired(managerAccount,"账号") && checkRequired(realName,"昵称")) {
          $.ajax({
            url:'managerEdit.do',
            data:$("#FrmGrid_lister").serialize(),
            type:"post",
            dataType:'text',
            success:function(data) {
              if (data==='okok') {
                closeEditDialog('addDialog','FrmGrid_lister');
                $("#lister").trigger("reloadGrid");
              }
            },
            error:function(request) {
              $("#FrmGrid_lister .FormError").html(request.responseText).show();
            }
          });
        }
      },
      '取消':function() {
        closeEditDialog('addDialog','FrmGrid_lister');
      }
    }
  });

  //修改管理员密码窗口
  $("#passwdDialog").dialog({
    autoOpen:false,
    buttons:{
      '提交':function() {
        const password = $("#password").val();
        const repasswd = $("#repasswd").val();
        if (!password) {
          $("#passwd_lister .FormError").html("密码不能为空！").show();
        } else if (password.length < 4) {
          $("#passwd_lister .FormError").html("密码长度不得小于4！").show();
        } else if (password !== repasswd) {
          $("#passwd_lister .FormError").html("输入的密码不一致！").show();
        } else {
          $.ajax({
            url:"managerSetPasswd.do",
            data:$("#passwd_lister").serialize(),
            type:"post",
            dataType:'text',
            success:function(data) {
              if (data==='okok') {
                closeEditDialog('passwdDialog','passwd_lister');
              }
            },
            error:function(request) {
              $("#passwd_lister .FormError").html(request.responseText).show();
            }
          });
        }
      },
      '取消':function() {
        closeEditDialog('passwdDialog','passwd_lister');
      }
    }
  });
});

//显示添加窗口
function showAddDialog() {
  $('#oper').val('add');
  $("#addDialog").dialog("open");
}

//设置密码窗口
function showPasswdDialog(rowData) {
  if (rowData) {
    $("#passwdAccount").val(rowData.managerAccount);
    $("#passwdDialog").dialog("open");
  } else {
    $.alertDialog({content:"请选择一条记录！"});
  }
}

//显示选择角色窗口
function showRoleDialog(managerData) {
  if (managerData) {
    showRoleChooseDialog({
      success:function(roleData){
		$.ajax({
		  url: "managerConfigRole.do",
		  type: "post",
		  data: {managerAccount:managerData.managerAccount,roleId:roleData.id},
		  dataType: "text",
		  success: function (data) {
		    if (data === "okok") {
		      $("#lister").trigger("reloadGrid");//刷新表格
		    } else {
			  $.alertDialog({content:"配置菜单失败！"});
			}
		  }
		});
      }
	});
  } else {
    $.alertDialog({content:"请选择一条记录！"});
  }
}