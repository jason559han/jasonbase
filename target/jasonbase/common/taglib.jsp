<%@ page pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript" src="${ctx}/plugIn/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${ctx}/plugIn/js/jquery.cookie.js" defer="defer"></script>

<!-- jquery-ui插件 -->
<link rel="stylesheet" href="${ctx}/plugIn/css/jquery-ui.css"/>
<link rel="stylesheet" href="${ctx}/plugIn/css/jquery-ui.structure.css"/>
<link rel="stylesheet" href="${ctx}/plugIn/css/jquery-ui.theme.css"/>
<script type="text/javascript" src="${ctx}/plugIn/js/jquery-ui.js" defer="defer"></script>

<!-- jqgrid插件 -->
<link rel="stylesheet" href="${ctx}/plugIn/css/ui.jqgrid.css"/>
<script type="text/javascript" src="${ctx}/plugIn/js/jquery.jqGrid.min.js" defer="defer"></script>
<script type="text/javascript" src="${ctx}/plugIn/jqgrid/i18n/grid.locale-cn.js" defer="defer"></script>
<%@taglib uri="/taglibs/tld/jqGrid.tld" prefix="jq"%>

<!-- 工具类 -->
<script type="text/javascript" src="${ctx}/common/js/utils/stringUtil.js" defer="defer"></script>
<script type="text/javascript" src="${ctx}/common/js/utils/numberUtil.js" defer="defer"></script>

<!-- 全局变量与函数 -->
<script type="text/javascript">
ctx = '${ctx}';
</script>

<!-- 通用 -->
<link rel="stylesheet" href="${ctx}/common/css/base/common.core.css"/>
<link rel="stylesheet" href="${ctx}/common/css/base/common.theme.css"/>
<script type="text/javascript" src="${ctx}/common/js/base/common.core.js" defer="defer"></script>
<script type="text/javascript" src="${ctx}/common/js/base/jqgrid.core.js" defer="defer"></script>

<!-- 确认框插件 -->
<!-- <script type="text/javascript" src="http://malsup.github.io/jquery.form.js" defer="defer"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css"/>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js" defer="defer"></script> -->
