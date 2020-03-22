(function($){

	/* 提示框添加 */
	$.alertDialog = function(options) {
		var alertDialogDiv;
		var title = options&&options.title ? options.title : $.jason.alertDialog.title;
		var content = options&&options.content ? options.content : $.jason.alertDialog.content;
		//关闭并移除提示框
		var closeAlertDialog = function() {
			alertDialogDiv.dialog("close").remove();
		};
		//按钮
		options.buttons = {
				'确定':function() {
					closeAlertDialog();
				}
		}
		options.dialogClass = "suspend-dialog";
		delete options.title;
		delete options.content;

		alertDialogDiv = $("<div title='"+title+"'><p>"+content+"</p></div>").appendTo("body").dialog(options);
	};

	/* 确认框添加 */
	$.confirmDialog = function(options) {
		var confirmDialogDiv;
		var title = options&&options.title ? options.title : $.jason.confirmDialog.title;
		var content = options&&options.content ? options.content : $.jason.confirmDialog.content;
		var confirmFunc = options&&options.confirm;
		//关闭并移除确认框
		var closeConfirmDialog = function() {
			confirmDialogDiv.dialog("close").remove();
		};
		//按钮
		options.buttons = {
				'确定':function() {
					confirmFunc&&confirmFunc();
					closeConfirmDialog();
				},
				'取消':function() {
					closeConfirmDialog();
				}
		}
		options.dialogClass = "suspend-dialog";
		delete options.title;
		delete options.content;
		delete options.confirm;

		confirmDialogDiv = $("<div title='"+title+"'><p>"+content+"</p></div>").appendTo("body").dialog(options);
	};

	/* 悬浮窗口 */
	$.iframeDialog = function(options) {
		//悬浮窗口参数设置
		var dialogId = options&&options.dialogId ? options.dialogId : $.jason.iframeDialog.dialogId;
		var title = options&&options.title ? options.title : $.jason.iframeDialog.title;
		var frameId = options&&options.frameId ? options.frameId : $.jason.iframeDialog.frameId;
		var url = options&&options.url ? options.url : $.jason.iframeDialog.url;
		options.dialogClass = "suspend-dialog";
		delete options.title;
		delete options.frameId;
		delete options.url;

		$("<div id='"+dialogId+"' title='"+title+"'><iframe id='"+frameId+"' frameborder='0' height='100%' width='100%' " 
				+ "src='"+url+"'/></div>").appendTo("body").dialog(options);
	};

	/* 悬浮窗口关闭 */
	$.closeIframeDialog = function(dialogId) {
		var id = dialogId ? dialogId : $.jason.iframeDialog.dialogId;
		$("#"+id).dialog("close").remove();
	}

	$.jason = $.jason || {};
	$.extend($.jason,{
		alertDialog : {
			title : '提示框',
			content : '发生错误，无法执行此操作。'
		},
		confirmDialog : {
			title : '确认框',
			content : '你确定要执行此操作吗？'
		},
		iframeDialog : {
			dialogId : 'myDialog',
			frameId : 'myFrame',
			title : '悬浮窗口',
			url : ctx+'/main/defaultPage.do'
		}
	});
})(jQuery);