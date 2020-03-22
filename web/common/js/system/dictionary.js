
$(function(){
    pageInit();
});

function pageInit(){
    jQuery('#lister').jqGrid({
        url:'dictTypeIndexJson.do',
        datatype:'json',
        pager:'#pager',
        caption:'字典信息维护',
        colNames:['账号','字典类型名称','字典类型编码','字典类型说明'],
        colModel:[
            {name:'id',index:'id',hidden:true,search:false},
            {name:'typeName',index:'typeName',width:130,editable:true,
                editoptions:{size:18,maxLength:20},editrules:{required:true},
                searchoptions:{sopt:['cn','nc','bw','bn','ew','en']}},
            {name:'typeCode',index:'typeCode',width:100,editable:true,
                editoptions:{size:16,maxLength:15},editrules:{required:true},
                searchoptions:{sopt:['cn','nc','bw','bn','ew','en']}},
            {name:'typeRemark',index:'typeRemark',width:300,editable:true,
                editoptions:{size:20,maxLength:50},
                searchoptions:{sopt:['cn','nc','bw','bn','ew','en']}}
        ],
        rowNum:10,
        rowList:[10, 20, 30],
        viewrecords:true,
        editurl:'dictTypeEdit.do',
        height: '100%',
        subGrid:true,
        subGridRowExpanded : function(subgrid_id, row_id) {
            /* we pass two parameters
             subgrid_id is a id of the div tag created whitin a table data
             the id of this elemenet is a combination of the 'sg_' + id of the row
             the row_id is the id of the row
             If we wan to pass additinal parameters to the url we can use
             a method getRowData(row_id) - which returns associative array in type name-value
             here we can easy construct the flowing */
            let subgrid_table_id, pager_id;
            subgrid_table_id = subgrid_id + '_t';
            pager_id = 'p_' + subgrid_table_id;
            $('#' + subgrid_id).html(
                "<table id='" + subgrid_table_id
                + "' class='scroll'/><div id='"
                + pager_id + "' class='scroll'/>");
            jQuery('#' + subgrid_table_id).jqGrid(
                {
                    url : 'dictDataIndexJson.do?q=2&id=' + row_id,
                    datatype : 'json',
                    colNames : [ '账号','名称','编码','顺序','备注','类型id' ],
                    colModel : [
                        {name:'id',index:'id',hidden:true,search:false},
                        {name : 'dictName',index : 'dictName',width : 80},
                        {name : 'dictCode',index : 'dictCode',width : 80},
                        {name : 'dictOrder',index : 'dictOrder',width : 40,align:'right',search:false},
                        {name : 'dictRemark',index : 'dictRemark',width : 130},
                        {name : 'dictTypeId',index : 'dictTypeId',hidden:true}
                    ],
                    rowNum : 20,
                    pager : pager_id,
                    editurl: 'dictDataEdit.do',
                    sortname : 'dictOrder',
                    sortorder : 'asc',
                    height : '100%'
                });
            jQuery('#'+subgrid_table_id).navGrid('#'+pager_id, {edit:false,add:false},
                {},{},{reloadAfterSubmit:true,closeOnEscape:true},
                {closeOnEscape:true,multipleSearch:true}
            );
            jQuery('#'+subgrid_table_id).navButtonAdd('#'+pager_id,
                {caption:'',title:'修改记录',buttonicon:'ui-icon-pencil',position:'first',
                    onClickButton:function () {
                        const sel = $('#' + subgrid_table_id).jqGrid('getGridParam', 'selrow');
                        if (sel) {
                            initEditDictDataTable('edit',row_id,subgrid_table_id, sel);
                        } else {
                            $.alertDialog({content:'请选择一条记录！'});
                        }
                    }});
            jQuery('#'+subgrid_table_id).navButtonAdd('#'+pager_id,
                {caption:'',title:'添加记录',buttonicon:'ui-icon-plus',position:'first',
                    onClickButton:function () {
                        initEditDictDataTable('add',row_id,subgrid_table_id);
                    }});
        }
    });
    jQuery('#lister').navGrid('#pager', {},
        {reloadAfterSubmit:true,closeOnEscape:true,closeAfterEdit:true},
        {reloadAfterSubmit:true,closeOnEscape:true,closeAfterAdd:true},
        {reloadAfterSubmit:true,closeOnEscape:true},
        {closeOnEscape:true,multipleSearch:true}
    );

    $('#editDataDialog').dialog({
        autoOpen: false,
        buttons: {
            '提交' : function() {
                if (checkEditDictDataTable()) {
                    $.ajax({
                        url:'dictDataEdit.do',
                        data:$('#FrmGrid_lister').serialize(),
                        type:'post',
                        datatype:'text',
                        success:function (data) {
                            if (data==='okok') {
                                const tableId = $('#subGridTableId').val();
                                $('#'+tableId).trigger('reloadGrid');
                                closeEditDialog('editDataDialog');
                            }
                        },
                        error:function (request) {
                            $('#FrmGrid_lister .FormError').html(request.responseText).show();
                        }
                    });
                }
            },
            '取消' : function() {
                closeEditDialog('editDataDialog');
            }
        }
    });
}

/**
 * 初始化编辑字典数据表格
 * @param oper
 * @param rowId
 * @param subGridTableId
 * @param dictDataId
 */
function initEditDictDataTable(oper, rowId, subGridTableId, dictDataId) {
    clearEditTable();
    $('#oper').val(oper);
    $('#dictTypeId').val(rowId);
    $('#subGridTableId').val(subGridTableId);
    $('#editDataDialog').dialog('open');
    const dictType = $('#lister').jqGrid('getRowData', rowId);
    $('#codeHeader').val(dictType.typeCode)
    if (dictDataId) {
        const dictData = $('#' + subGridTableId).jqGrid('getRowData', dictDataId);
        $('#id').val(dictData.id);
        $('#dictName').val(dictData.dictName);
        $('#dictCode').val(dictData.dictCode);
        $('#dictOrder').val(dictData.dictOrder);
        $('#dictRemark').val(dictData.dictRemark);
    } else {
        $('#dictCode').val(dictType.typeCode);
    }
    $('#editDataDialog').dialog("open");
}

/**
 * 验证编辑字典数据表格
 */
function checkEditDictDataTable() {
    const dictName = $('#dictName').val();
    const dictCode = $('#dictCode').val();
    const dictOrder = $('#dictOrder').val();
    const codeHeader = $('#codeHeader').val();

    if (!(checkRequired(dictName,'名称') && checkRequired(dictCode,'编码')
        && checkRequired(dictOrder,'顺序'))) {
        return false;
    }
    if (dictCode.indexOf(codeHeader) != 0) {
        $('#FrmGrid_lister .FormError').html('编码必须以所属类型编码开头！').show();
        $('#dictCode').val(codeHeader);
        return false;
    }
    return checkInteger(dictOrder, '顺序', 0, 1000);

}