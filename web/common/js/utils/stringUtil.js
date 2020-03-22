/**
 * js字符串处理工具
 */

/**
 * 去掉前后空格
 * @param str
 * @returns
 */
function removeBlank(str) {
	if (str) {
		return str.trim();
	} else {
		return str;
	}
}

/**
 * 判断是否为空
 * 0、-0、null、""、false、undefined 或者 NaN，if判断 false
 * @param str
 * @returns
 */
function isEmpty(str) {
	return !str;
}

/**
 * 获得日期字符串
 * @param date 默认new Date()
 * @param format 默认 yy-mm-dd
 * @returns
 */
function getDateStrByFormat(date, format) {
	date = date || new Date();
	format = format || "yy-mm-dd";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
       month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
       strDate = "0" + strDate;
    }
    return format.replace(/yy/g, year).replace(/mm/g, month).replace(/dd/g, strDate);
}