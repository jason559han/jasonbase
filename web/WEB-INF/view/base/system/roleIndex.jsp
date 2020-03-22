<%@ page contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<title>角色列表</title>
<%@ include file="/common/taglib.jsp" %>
<script type="text/javascript" src="${ctx}/common/js/system/role.js"></script>
<script type="text/javascript" src="${ctx}/common/js/choose/menuChoose.js"></script>
</head>
<body>
  <jq:gridTag url="roleIndexJson.do" clzzName="com.jason.base.entity.SysRole" editurl="roleEdit.do" 
  		sortname="orderNumber" toolbar="[true,'top']">
  	<jq:defBtnTag/>
  	<jq:toolBtnTag caption="配置菜单" onClickButton="configMenus"/>
  </jq:gridTag>
</body>
</html>