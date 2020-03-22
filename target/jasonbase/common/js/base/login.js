//登录页面登录
$(document).ready(function(){
	changeCode();
	initLoginPage();
	$(document).tooltip();
	$("#loginButton")
			.button()
			.click(function(){
		var account = removeBlank($("#account").val());
		var password = removeBlank($("#password").val());
		var verifyCode = removeBlank($('#verifyCode').val());
		var errorStr = "";

		if (isEmpty(account)) {
			errorStr += " 账号";
		}
		if (isEmpty(password)) {
			errorStr += " 密码";
		}
		if (isEmpty(verifyCode)) {
			errorStr += " 验证码";
		}
		if (!isEmpty(errorStr)) {
			$("#redTd").html(errorStr+"不能为空！");
			changeCode();
			return;
		}

		$.ajax({
			url: "login.do",
			type: "post",
			data: {'account':account,'password':password,'verifyCode':verifyCode},
			dataType: "text",
			success: function (data) {
				if (data === "okok") {
		        	$(location).prop('href', 'main.do');
				} else if (data === "errorVerifyCode") {
					$("#redTd").html("验证码不正确！");
					changeCode();
				} else if (data === "errorAccountPassword") {
					$("#redTd").html("账号或密码不正确！");
					changeCode();
				}
			}
		});
	});
});

//改变验证码
function changeCode() {
	var src = "getVerifyCode.do?"+new Date().getTime(); //加时间戳，防止浏览器利用缓存
    $('#verifyCodeImage').attr("src",src);
}

//初始化登录页面
function initLoginPage() {
	var account = $.cookie("account");
	var password = $.cookie("password");
	if (!isEmpty(account)) {
		$("#account").val(account);
	}
	if (!isEmpty(password)) {
		$("#password").val(password);
	}
}