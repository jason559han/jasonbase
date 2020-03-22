package com.jason.base.choose.roleChoose.service;

import com.jason.base.entity.SysRole;

import java.util.List;

/**
 * 角色选择 服务
 * @author jason558han
 * @date 2020年02月29日
 */
public interface RoleChooseService {

	/**
	 * 获取角色列表
	 * @return 返回角色列表
	 */
	List<SysRole> getSysRoleListAll();
}
