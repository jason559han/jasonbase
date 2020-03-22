/**
 * 判断必须字段
 */
function checkRequired(value,field,listerId) {
  if (isEmpty(value)) {
    const lister_id = listerId||'FrmGrid_lister';
    $('#'+lister_id+' .FormError').html(field+':该字段不能为空！').show();
    return false;
  }
  return true;
}

/**
 * 清理数据表格
 */
function clearEditTable(listerId) {
  const lister_id = listerId||'FrmGrid_lister';
  $('#'+lister_id+' .FormError').html('').hide();
  $('#'+lister_id+' input').val('');
  $('#'+lister_id+' textarea')&&$('#'+lister_id+' textarea').val('');
}

/**
 * 关闭编辑窗口
 * @param editDialogId
 * @param listerId
 */
function closeEditDialog(editDialogId, listerId) {
  clearEditTable(listerId);
  const edit_dialog_id = editDialogId||'editDialog';
  $('#'+edit_dialog_id).dialog("close");
}

/**
 * 验证数值
 * @param value
 * @param field
 * @param minValue
 * @param maxValue
 * @param listerId
 */
function checkInteger(value, field, minValue, maxValue, listerId) {
  const lister_id = listerId||'FrmGrid_lister';
  if (!isInteger(value)) {
    $('#'+lister_id+' .FormError').html(field+':该字段必须是整数！').show();
    return false;
  }
  if (minValue && value<minValue) {
    $('#'+lister_id+' .FormError').html(field+':该字段必须大于等于'+minValue+'！').show();
    return false;
  }
  if (maxValue && value>=maxValue) {
    $('#'+lister_id+' .FormError').html(field+':该字段必须大于等于'+minValue+'！').show();
    return false;
  }
  return true;
}