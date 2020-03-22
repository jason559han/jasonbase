<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<title>登录日志</title>
<%@ include file="/common/taglib.jsp" %>
<script type="text/javascript">
function changeSection(section) {
	jQuery("#lister").jqGrid('setGridParam', {
	    url : "logLoginIndexJson.do?section=" + section
	  }).trigger("reloadGrid");
}
</script>
</head>
<body>
  <div class="ui-widget ui-widget-content ui-corner-all ui-area">
    <div class="ui-corner-all ui-widget-header">
      <span class="ui-dialog-title">搜索窗口</span>
    </div>
    <div class="ui-area-content">
      <label for="section">查看区间：</label>
      <select id="section" name="section" class="ui-widget-content ui-corner-all" onchange="changeSection(this.value)">
        <c:forEach items="${sectionMap}" var="item">
          <option value="${item.key}">${item.value}</option>
        </c:forEach>
      </select>
    </div>
  </div>
  <jq:gridTag url="logLoginIndexJson.do?section=today" clzzName="com.jason.base.entity.LogLogin" rowNum="100" rowList="[100,200,300]" 
  		sortname="loginDate" sortorder="desc" height="300px">
  	<jq:defBtnTag add="false" edit="false" del="false" view="false"/>
  </jq:gridTag>
</body>
</html>