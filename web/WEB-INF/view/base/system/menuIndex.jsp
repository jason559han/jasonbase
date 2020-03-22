<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<title>菜单管理</title>
<%@ include file="/common/taglib.jsp"%>
<style type="text/css">
.ui-area table * {
	font-size: 0.9em;
}
.ui-area a {
	text-decoration: none;
}
</style>
<script type="text/javascript" src="${ctx}/common/js/system/menu.js"></script>
</head>
<body>
	<jq:gridTag url="menuIndexJson.do" clzzName="com.jason.base.entity.SysTreeMenu" editurl="menuEdit.do" 
			treeGrid="true" toolbar="[true,'top']" height="100%">
		<jq:defBtnTag add="false" edit="false" search="false"/>
        <jq:newBtnTag title="修改记录" buttonicon="ui-icon-pencil" position="first" onClickButton="showEditDialog"/>
        <jq:newBtnTag title="添加记录" buttonicon="ui-icon-plus" position="first" onClickButton="showAddDialog"/>
		<jq:toolBtnTag caption="权限链接配置" onClickButton="showPermissionsDialog"/>
	</jq:gridTag>
    <div id="editMenuDialog" title="添加记录"><!-- 添加记录窗口 -->
      <form name="FormPost" id="FrmGrid_lister" class="FormGrid" onsubmit="return false;" style="width:auto;height:auto;">
        <div class="FormError ui-state-error" style="display:none;"></div>
        <input type="hidden" name="oper" id="oper" value="add"/>
        <input type="hidden" name="id" id="id"/>
        <input type="hidden" name="level" id="level"/>
        <input type="hidden" name="parent" id="parent"/>
        <input type="hidden" name="codeHeader" id="codeHeader"/>
        <table id="TblGrid_lister" class="EditTable ui-common-table">
          <tbody><tr class="FormData" id="tr_menuName">
            <td class="CaptionTD"><label for="menuName">菜单名称</label></td>
            <td class="DataTD">
              <input type="text" id="menuName" name="menuName" maxlength="15" size="10"
                     class="FormElement ui-widget-content ui-corner-all">
            </td>
          </tr>
          <tr class="FormData" id="tr_code">
            <td class="CaptionTD"><label for="code">菜单编码</label></td>
            <td class="DataTD">
              <input type="text" id="code" name="code" maxlength="10" size="10"
                     class="FormElement ui-widget-content ui-corner-all">
            </td>
          </tr>
          <tr class="FormData" id="tr_orderNumber">
            <td class="CaptionTD"><label for="orderNumber">顺序</label></td>
            <td class="DataTD">
              <input type="text" id="orderNumber" name="orderNumber" maxlength="6" size="10"
                     class="FormElement ui-widget-content ui-corner-all">
            </td>
          </tr>
          <tr class="FormData" id="tr_menuType">
            <td class="CaptionTD"><label for="menuType">菜单类型</label></td>
            <td class="DataTD">
              <select id="menuType" name="menuType" class="FormElement ui-widget-content ui-corner-all">
                <option value="0">菜单大类</option>
                <option value="1">菜单链接</option>
              </select>
            </td>
          </tr>
          <tr class="FormData" id="tr_linkFile">
            <td class="CaptionTD"><label for="linkFile">菜单链接</label></td>
            <td class="DataTD">
              <input type="text" id="linkFile" name="linkFile" maxlength="200" size="25"
                     class="FormElement ui-widget-content ui-corner-all"/>
            </td>
          </tr>
          <tr class="FormData" id="tr_state">
            <td class="CaptionTD"><label for="state">菜单状态</label></td>
            <td class="DataTD">
              <select id="state" name="state" class="FormElement ui-widget-content ui-corner-all">
                <option value="0">显示</option>
                <option value="1">隐藏</option>
              </select>
            </td>
          </tr></tbody>
        </table>
      </form>
    </div>
    <div id="permissionsDialog" title="权限链接配置窗口">
      <form id="permissionsForm">
        <div class="ui-widget ui-widget-content ui-corner-all ui-area">
          <input type="hidden" name="menuId" id="menuId"/>
          <table class="ui-small-table" cellspacing="0">
            <caption>菜单信息</caption>
            <tr class="ui-tr">
              <th class="ui-th" width="30%">菜单名称</th>
              <td class="ui-td" width="70%" id="menuNameTd"></td>
            </tr>
            <tr class="ui-tr">
              <th class="ui-th">菜单链接</th>
              <td class="ui-td" id="menuUrl"></td>
            </tr>
          </table>
        </div>
        <div class="ui-widget ui-widget-content ui-corner-all ui-area">
          <table class="ui-small-table" cellspacing="0">
            <caption>权限链接信息</caption>
            <thead>
              <tr class="ui-tr">
                <th class="ui-th" width="70%">权限链接(必填)</th>
                <th class="ui-th" width="22%">链接说明</th>
                <th class="ui-th" width="8%">操作<a onclick="addPermissionsUrl()" href="#"> + </a></th>
              </tr>
            </thead>
            <tbody id="permissionsUrlTbody">
            </tbody>
          </table>
        </div>
      </form>
    </div>
</body>
</html>