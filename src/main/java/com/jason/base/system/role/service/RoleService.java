package com.jason.base.system.role.service;

import com.jason.base.entity.SysRole;
import com.jason.base.tags.grid.bean.GridPageBean;

import java.util.List;
import java.util.Map;

/**
 * 角色信息维护 service
 * @author jason558han
 * @date 2020年02月26日
 */
public interface RoleService {

	/**
	 * 获取角色信息列表
	 * @param gpb 分页参数
	 * @return 角色列表
	 */
	List<SysRole> getSysRoleListByGpb(GridPageBean gpb);

	/**
	 * 编辑角色信息
	 * @param map 编辑参数
	 * @return 编辑情况
	 */
	String editSysRoleByMap(Map<String, String[]> map);

	/**
	 * 给角色配置菜单
	 * @param roleId 角色id
	 * @param menuIds 菜单id数组
	 * @return 操作后的情况
	 */
	String updateRoleMenuIds(String roleId, String menuIds);
}
