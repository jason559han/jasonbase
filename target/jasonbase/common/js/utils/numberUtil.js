/**
 * 验证obj是否整数
 * @param obj
 * @returns {boolean|boolean}
 */
function isInteger(obj) {
    if (typeof obj === 'string' && !isNaN(obj)) {
        obj = Number(obj);
    }
    return typeof obj === 'number' && obj%1 === 0
}