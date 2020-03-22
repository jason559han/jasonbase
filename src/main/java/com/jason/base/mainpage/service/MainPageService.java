package com.jason.base.mainpage.service;

import com.jason.base.entity.SysManager;
import com.jason.base.entity.SysTreeMenu;

import java.util.List;

/**
 * 登录主页面管理service
 * @author jason558han
 * @date 2020年02月09日
 */
public interface MainPageService {

	/**
	 * 根据显示状态显示菜单
	 * @param manager 登录的管理员账户
	 * @return 菜单列表
	 */
	List<SysTreeMenu> getSysTreeMenuListByManager(SysManager manager);
}
