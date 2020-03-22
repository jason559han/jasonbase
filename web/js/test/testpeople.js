
function opendialog(data) {
	$.confirmDialog({
		title:'测试窗口',
		content:'测试，你好吗？',
		confirm:function() {
		}
	});
}

function checkAge(val,nm,valref) {
	if (val>90 || val<15) {
		return [false,'老年人和幼龄儿童除外！',''];
	} else {
		return [true,'',''];
	}
}