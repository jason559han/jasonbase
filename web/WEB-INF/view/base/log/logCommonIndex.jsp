<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<title>常规日志</title>
<%@ include file="/common/taglib.jsp" %>
<script type="text/javascript">
function changeSection(section) {
	jQuery("#lister").jqGrid('setGridParam', {
	    url : "logCommonIndexJson.do?logLevel=${logLevel}&section=" + section
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
  <c:choose>
    <c:when test="${logLevel == 'info,warn'}">
      <jq:gridTag url="logCommonIndexJson.do?logLevel=info,warn&section=today" clzzName="com.jason.base.log.common.bean.LogInfoBean" 
  	      rowNum="100" rowList="[100,200,300]" sortname="logDate" sortorder="desc" height="330px">
        <jq:defBtnTag add="false" edit="false" del="false" view="false"/>
       </jq:gridTag>
    </c:when>
    <c:when test="${logLevel == 'error,fatal'}">
      <jq:gridTag url="logCommonIndexJson.do?logLevel=error,fatal&section=today" clzzName="com.jason.base.log.common.bean.LogErrorBean" 
  	      rowNum="100" rowList="[100,200,300]" sortname="logDate" sortorder="desc" height="330px">
        <jq:defBtnTag add="false" edit="false" del="false" view="false"/>
       </jq:gridTag>
    </c:when>
    <c:otherwise>
      <jq:gridTag url="logCommonIndexJson.do?section=today" clzzName="com.jason.base.entity.LogCommon" 
  	      rowNum="100" rowList="[100,200,300]" sortname="logDate" sortorder="desc" height="330px">
        <jq:defBtnTag add="false" edit="false" del="false" view="false"/>
       </jq:gridTag>
    </c:otherwise>
  </c:choose>
</body>
</html>