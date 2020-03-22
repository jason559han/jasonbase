<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/common/taglib.jsp" %>
<script type="text/javascript" src="${ctx}/common/js/choose/roleChoose.js"></script>
<script type="text/javascript" src="${ctx}/common/js/system/manager.js"></script>
</head>
<body>
  <jq:gridTag url="managerIndexJson.do" clzzName="com.jason.base.entity.SysManager" 
  		editurl="managerEdit.do" sortname="managerAccount" toolbar="[true,'top']">
  	<jq:defBtnTag add="false"/>
  	<jq:newBtnTag onClickButton="showAddDialog" position="first" buttonicon="ui-icon-plus"/>
  	<jq:toolBtnTag onClickButton="showPasswdDialog" caption="设置密码"/>
  	<jq:toolBtnTag onClickButton="showRoleDialog" caption="配置角色"/>
  </jq:gridTag>

  <div id="addDialog" title="添加记录"><!-- 添加记录窗口 -->
    <form name="FormPost" id="FrmGrid_lister" class="FormGrid" onsubmit="return false;" style="width:auto;height:auto;">
      <div class="FormError ui-state-error" style="display:none;"></div>
      <input type="hidden" name="oper" id="oper" value="add"/>
      <table id="TblGrid_lister_1" class="EditTable ui-common-table">
        <tbody><tr class="FormData" id="tr_managerAccount">
          <td class="CaptionTD"><label for="managerAccount">账号</label></td>
          <td class="DataTD">
            <input type="text" id="managerAccount" name="managerAccount" maxlength="10" size="10"
                  class="FormElement ui-widget-content ui-corner-all">
          </td>
        </tr>
        <tr class="FormData" id="tr_realName">
          <td class="CaptionTD"><label for="realName">昵称</label></td>
          <td class="DataTD">
            <input type="text" size="10" maxlength="15" id="realName" name="realName"
                  class="FormElement ui-widget-content ui-corner-all">
          </td>
        </tr>
        <tr class="FormData" id="tr_state">
          <td class="CaptionTD"><label for="state">状态</label></td>
          <td class="DataTD">
            <select id="state" name="state" size="1" class="FormElement ui-widget-content ui-corner-all">
              <option value="0">停用</option>
              <option value="1">启用</option>
            </select>
          </td>
        </tr></tbody>
      </table>
    </form>
  </div>

  <div id="passwdDialog" title="设置密码"><!-- 设置密码窗口 -->
    <form name="FormPost" id="passwd_lister" class="FormGrid" onsubmit="return false;" style="width:auto;height:auto;">
      <div class="FormError ui-state-error" style="display:none;"></div>
      <table id="TblGrid_lister_2" class="EditTable ui-common-table">
        <tbody><tr class="FormData" id="tr_password">
          <td class="CaptionTD"><label for="password">新密码</label></td>
          <td class="DataTD">
            <input type="password" id="password" name="password" maxlength="10" size="15"
                  class="FormElement ui-widget-content ui-corner-all"/>
          </td>
        </tr>
        <tr class="FormData" id="tr_repasswd">
          <td class="CaptionTD"><label for="repasswd">重输密码</label></td>
          <td class="DataTD">
            <input type="password" id="repasswd" name="repasswd" maxlength="10" size="15"
                  class="FormElement ui-widget-content ui-corner-all"/>
          </td>
        </tr>
        <tr class="FormData" style="display:none">
          <td class="CaptionTD"><label for="passwdAccount"></label></td>
          <td colspan="1" class="DataTD">
            <input class="FormElement" id="passwdAccount" type="text" name="passwdAccount"/>
          </td>
        </tr></tbody>
      </table>
    </form>
  </div>
</body>
</html>