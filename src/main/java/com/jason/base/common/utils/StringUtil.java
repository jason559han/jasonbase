package com.jason.base.common.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串处理工具类
 * @author jason558han
 * @date 2020年01月30日
 */
public class StringUtil {

	/**
	 * 字符串为空
	 * @param str 验证字符串
	 * @return true或false
	 */
	public static boolean isEmpty(String str) {
		return !isNotEmpty(str);
	}

	/**
	 * 字符串不为空
	 * @param str 验证字符串
	 * @return true或false
	 */
	public static boolean isNotEmpty(String str) {
		return str != null && !"".equals(str.trim());
	}

	/**
	 * 字符串添加json属性
	 * @param originBuffer 待处理的字符串
	 * @param key 键
	 * @param value 值
	 * @return  json处理后的字符串
	 */
	public static StringBuffer addJsonObjectStr(StringBuffer originBuffer, String key, String value) {
		if (originBuffer == null) {
			originBuffer = new StringBuffer();
		}
		//字符串不为空时，判断前方有没有已经加的属性
		if (originBuffer.length() > 0 && originBuffer.lastIndexOf("{")!=originBuffer.length()-1) {
			originBuffer.append(",");
		}
		if (value == null) {//如果值为空，则需要空字符串
			value = "";
		}
		//以下情况不用给value加引号
		if (value.trim().startsWith("function") || //value是一个function函数
				(value.startsWith("{") && value.endsWith("}")) ||// value以{}包围，即是一个完整对象
				(value.startsWith("[") && value.endsWith("]"))) {// value以[]包围，即是一个完整数组
			return originBuffer.append(key).append(":").append(value);
		}
		return originBuffer.append(key).append(":'").append(value).append("'");
	}

	/**
	 * 字符串添加json属性
	 * @param originBuffer 待处理的字符串
	 * @param key 键值对的键
	 * @param value 键值对的值
	 * @return 被处理后的字符串
	 */
	public static StringBuffer addJsonObjectStr(StringBuffer originBuffer, String key, boolean value) {
		return addJsonObjectStr(originBuffer,key,(Object) value);
	}

	/**
	 * 字符串添加json属性
	 * @param originBuffer 待处理的字符串
	 * @param key 键值对的键
	 * @param value 键值对的值
	 * @return 被处理后的字符串
	 */
	public static StringBuffer addJsonObjectStr(StringBuffer originBuffer, String key, int value) {
		return addJsonObjectStr(originBuffer,key,(Object) value);
	}

	/**
	 * 字符串添加json属性
	 * @param originBuffer 待处理的字符串
	 * @param key 键值对的键
	 * @param value 键值对的值
	 * @return 被处理的字符串
	 */
	public static StringBuffer addJsonObjectStr(StringBuffer originBuffer, String key, Object value) {
		if (originBuffer == null) {
			originBuffer = new StringBuffer();
		}
		//字符串不为空时，判断前方有没有已经加的属性
		if (originBuffer.length() > 0 && originBuffer.lastIndexOf("{")!=originBuffer.length()-1) {
			originBuffer.append(",");
		}
		return originBuffer.append(key).append(":").append(value);
	}

	/**
	 * 字符串添加json属性
	 * @param originBuffer 待处理的字符串
	 * @param key 键值对的键
	 * @param value 键值对的值
	 * @return 处理后的字符串
	 */
	public static StringBuffer addJsonObjectStr(StringBuffer originBuffer, String key, StringBuffer value) {
		if (originBuffer == null) {
			originBuffer = new StringBuffer();
		}
		//字符串不为空时，判断前方有没有已经加的属性
		if (originBuffer.length() > 0 && originBuffer.lastIndexOf("{")!=originBuffer.length()-1) {
			originBuffer.append(",");
		}
		if (value == null) {//如果值为空，则需要空字符串
			value = new StringBuffer();
		}
		//以下情况不用给value加引号
		if (value.indexOf("function")==0 || //value是一个function函数
				(value.indexOf("{")==0 && value.lastIndexOf("}")==value.length()-1) ||// value以{}包围，即是一个完整对象
				(value.indexOf("[")==0 && value.lastIndexOf("]")==value.length()-1)) {// value以[]包围，即是一个完整数组
			return originBuffer.append(key).append(":").append(value);
		}
		return originBuffer.append(key).append(":'").append(value).append("'");
	}

	/**
	 * 字符串添加json属性
	 * @param originBuffer 待处理的字符串
	 * @param key 键值对的键
	 * @param value 键值对的值
	 * @return 处理后的字符串
	 */
	public static StringBuffer addJsonObjectStr(StringBuffer originBuffer, String key, String[] value) {
		if (value == null || value.length == 0) {
			return addJsonObjectStr(originBuffer, key, "[]");
		}
		StringBuffer valueBuffer = new StringBuffer("[");
		for (String val : value) {
			if (valueBuffer.length() > 1) {
				valueBuffer.append(",");
			}
			valueBuffer.append("'").append(val).append("'");
		}
		valueBuffer.append("]");
		return addJsonObjectStr(originBuffer, key, valueBuffer);
	}

	/**
	 * 字符串添加json属性
	 * @param originBuffer 待处理的字符串
	 * @param key 键值对的键
	 * @param value 键值对的值
	 * @return 处理后的字符串
	 */
	public static StringBuffer addJsonObjectStr(StringBuffer originBuffer, String key, Integer[] value) {
		if (value == null || value.length == 0) {
			return addJsonObjectStr(originBuffer, key, "[]");
		}
		StringBuffer valueBuffer = new StringBuffer("[");
		for (Integer val : value) {
			if (valueBuffer.length() > 1) {
				valueBuffer.append(",");
			}
			valueBuffer.append(val);
		}
		valueBuffer.append("]");
		return addJsonObjectStr(originBuffer, key, valueBuffer);
	}

	/**
	 * 字符首字母大写
	 * @param str 待处理的字符串
	 * @return 处理后的字符串
	 */
	public static String getInitialsUpperCase(String str) {
		if (isEmpty(str)) return str;
		if (str.length() == 1) return str.toUpperCase();
		return str.substring(0, 1).toUpperCase()+str.substring(1);
	}

	/**
	 * 字符数组中是否含有该字符串
	 * @param array 待处理字符串数组
	 * @param str 检测的字符串
	 * @return boolean
	 */
	public static boolean isContainInArray(String[] array, String str) {
		if (isEmpty(str)) return false;
		if (array != null && array.length > 0) {
			for (String key : array) {
				if (key.equals(str)) return true;
			}
		}
		return false;
	}

	/**
     * 大写字母转小写并在该字母前面加_
     * @param name 待处理的字符串
     * @return 处理后的字符串
     */
    public static String bigLetterToSmallAndBeforeAdd_(String name) {
        StringBuilder stringBuffer = new StringBuilder();
        char[] chars = name.toCharArray();
        for (char ch : chars) {
            boolean digit = Character.isUpperCase(ch);
            if (digit) {
                String str = String.valueOf(ch);
                String lstr = str.toLowerCase();
                stringBuffer.append("_");
                stringBuffer.append(lstr);
            } else {
                stringBuffer.append(ch);
            }
        }
        return stringBuffer.toString();
    }

    /**
     * 根据字符串数组获得数值数组
     * @param strs 按,号隔开
     * @return 值数组
     */
    public static List<Integer> getIntegerListByStrings(String strs) {
    	List<Integer> list = new ArrayList<>();
    	if (StringUtil.isNotEmpty(strs)) {
    		String[] strsList = strs.split(",");
        	for (String str : strsList) {
        		Integer value = Integer.valueOf(str);
        		list.add(value);
        	}
    	}
    	return list;
    }
}