<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/common/taglib.jsp"%>
<style type="text/css">
.content-div {
	display: block;
	margin-left:2em;
	margin-top:1em;
	float: left;
	width: 60%
}
.date-div {
	display: block;
	float: right;
	margin-right:3em;
	margin-top: 3em;
	width: 30%;
}
</style>
<script type="text/javascript">
$(function(){
	$("#todayDatepicker").datepicker();
	$("#todayLabel").html(getDateStrByFormat(new Date(),"yy年mm月dd日"));
});
</script>
</head>
<body>
<div class="content-div">
  <h2>你好，${realName}，欢迎您！</h2>
  <ul>
    <li>这里是系统引导</li>
    <li>略</li>
  </ul>
</div>
<div class="date-div">
今天是<label id="todayLabel"></label><div id="todayDatepicker"></div>
</div>
</body>
</html>