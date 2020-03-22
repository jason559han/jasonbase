<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<title>登录页面</title>
<%@ include file="/common/taglib.jsp"%>
<link rel="stylesheet" href="${ctx}/common/css/base/login.core.css"/>
<link rel="stylesheet" href="${ctx}/common/css/base/login.theme.css"/>
<script type="text/javascript" src="${ctx}/common/js/base/login.js"></script>
</head>
<body class="login-body">
  <div class="ui-login-div ui-corner-all ui-widget ui-widget-content">
    <div class="ui-login-titlebar ui-corner-all ui-widget-header">
      <span class="ui-dialog-title">登录窗口</span>
    </div>
    <div class="input-login-div">
      <form action="login.do" id="loginForm" method="post">
        <label class="red" id="redTd">${msg}</label><br/>
        <label>账号：</label><br/>
        <input type="text" name="account" id="account" maxlength="10" title="请输入账号"/><br/>
        <label>密码：</label><br/>
        <input type="password" name="password" id="password" maxlength="12" title="请输入密码"/><br/>
        <label>验证码：</label><br/>
        <input type="text" name="verifyCode" id="verifyCode" maxlength="4" title="请输入验证码"/>
        <img id="verifyCodeImage" onclick="changeCode()" src="" alt="验证图像"/><br/><br/>
      </form>
    </div>
    <div class="ui-login-button">
      <input type="button" name="loginButton" id="loginButton" value="登 录"/>
    </div>
  </div>
</body>
</html>