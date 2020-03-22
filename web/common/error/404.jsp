<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/common/taglib.jsp"%>
<style type="text/css">
.sls-body {
  background: #2c4359;
  color: #e1e463;
}
.img-logo {
  margin-top: 25px;
  margin-left: 30px;
  float: left;
}
.img-logo img {
  height: 180px;
}
.sls-text {
  float: left;
  margin-left: 30px;
}
.sls-text h1 {
  font-size: 4em;
}
</style>
</head>
<body class="sls-body">
  <div class="img-logo">
    <a href="${ctx}/main.do"><img src="${ctx}/images/kingdom-hui.png" alt="404，url无法访问！"></a>
  </div>
  <div class="sls-text">
    <h1>404</h1>
    <h2>无法找到该网页</h2>
  </div>
</body>
</html>