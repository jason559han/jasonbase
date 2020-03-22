package com.jason.base.choose.menuChoose.service;

import com.jason.base.choose.menuChoose.bean.MenuChooseBean;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * 按钮选择
 * @author jason558han
 * @date 2020年02月27日
 */
public interface MenuChooseService {

	/**
	 * 获得加载列表
	 * @return 菜单选择数据列表
	 */
	List<MenuChooseBean> getMenuChooseBeanListAll() throws InvocationTargetException, IllegalAccessException;
}
