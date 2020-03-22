package com.jason.base.system.menu.service;

import com.jason.base.entity.SysPermissionsUrl;
import com.jason.base.entity.SysTreeMenu;
import com.jason.base.tags.grid.bean.GridPageBean;

import java.util.List;
import java.util.Map;

/**
 * 菜单管理服务接口
 * @author jason558han
 * @date 2020年02月04日
 */
public interface TreeMenuService {

	/**
	 * 获取分页数据
	 * @param gpb 分页参数
	 * @return 菜单列表
	 */
	List<SysTreeMenu> getSysTreeMenuListByGpb(GridPageBean gpb);

	/**
	 * 编辑系统菜单管理
	 * @param map 编辑参数
	 * @return 编辑情况
	 */
	String editSysTreeMenuListByMap(Map<String, String[]> map);

	/**
	 * 根据菜单id获取菜单权限链接
	 * @param menuId 菜单id
	 * @return 权限链接列表
	 */
	List<SysPermissionsUrl> getPermissionsUrlListByMenuId(String menuId);

	/**
	 * 保存菜单权限链接数组
	 * @param menuId 菜单id
	 * @param permissionsUrl 权限链接数组
	 * @param remark 备注数组
	 * @return 编辑情况
	 */
	String insertPermissionsUrlList(String menuId, String[] permissionsUrl, String[] remark);
}
