<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/common/taglib.jsp" %>
<script type="text/javascript" src="${ctx}/common/js/choose/roleChoose.js"></script>
</head>
<body>
  <jq:gridTag tableId="chooseLister" url="roleChooseIndexJson.do" clzzName="com.jason.base.choose.roleChoose.bean.RoleChooseBean" 
  		sortname="orderNumber">
  </jq:gridTag>
</body>
</html>