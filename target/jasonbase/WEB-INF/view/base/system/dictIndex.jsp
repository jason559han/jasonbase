<%--
  Created by IntelliJ IDEA.
  User: jason558han
  Date: 2020/3/16
  Time: 7:47 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8"/>
    <title>字典信息维护</title>
    <%@ include file="/common/taglib.jsp"%>
    <script type="text/javascript" src="${ctx}/common/js/system/dictionary.js"></script>
</head>
<body>
    <table id="lister"></table>
    <div id="pager"></div>
    <div id="editDataDialog" title="编辑记录"><!-- 添加记录窗口 -->
        <form name="FormPost" id="FrmGrid_lister" class="FormGrid" onsubmit="return false;" style="width:auto;height:auto;">
            <div class="FormError ui-state-error" style="display:none;"></div>
            <div class="tinfo topinfo"></div>
            <input type="hidden" name="subGridTableId" id="subGridTableId"/>
            <input type="hidden" name="oper" id="oper" value="add"/>
            <input type="hidden" name="dictTypeId" id="dictTypeId"/>
            <input type="hidden" name="id" id="id"/>
            <input type="hidden" name="codeHeader" id="codeHeader"/>
            <table id="TblGrid_lister" class="EditTable ui-common-table">
                <tbody><tr class="FormData" id="tr_dictName">
                    <td class="CaptionTD"><label for="dictName">名称</label></td>
                    <td class="DataTD">
                        <input type="text" id="dictName" name="dictName" maxlength="20" size="10"
                               class="FormElement ui-widget-content ui-corner-all">
                    </td>
                </tr>
                <tr class="FormData" id="tr_dictCode">
                    <td class="CaptionTD"><label for="dictCode">编码</label></td>
                    <td class="DataTD">
                        <input type="text" id="dictCode" name="dictCode" maxlength="15" size="10"
                               class="FormElement ui-widget-content ui-corner-all">
                    </td>
                </tr>
                <tr class="FormData" id="tr_dictOrder">
                    <td class="CaptionTD"><label for="dictOrder">顺序</label></td>
                    <td class="DataTD">
                        <input type="text" id="dictOrder" name="dictOrder" maxlength="6" size="10"
                               class="FormElement ui-widget-content ui-corner-all">
                    </td>
                </tr>
                <tr class="FormData" id="tr_dictRemark">
                    <td class="CaptionTD"><label for="dictRemark">备注</label></td>
                    <td class="DataTD">
                        <textarea id="dictRemark" name="dictRemark" maxlength="50" rows="2" cols="13"
                                class="FormElement ui-widget-content ui-corner-all"></textarea>
                    </td>
                </tr></tbody>
            </table>
        </form>
    </div>
</body>
</html>
