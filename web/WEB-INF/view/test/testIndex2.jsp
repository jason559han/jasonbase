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
	<jq:gridTag url="testIndexJson2.do" clzzName="com.jason.test.entity.TestPeople" editurl="testEdit.do" 
			height="210px">
		<jq:defBtnTag edit="true" add="true" del="true" view="false"/>
	</jq:gridTag>
</body>
</html>