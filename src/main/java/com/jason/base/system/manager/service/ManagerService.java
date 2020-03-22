package com.jason.base.system.manager.service;

import com.jason.base.entity.SysManager;
import com.jason.base.tags.grid.bean.GridPageBean;

import java.util.List;
import java.util.Map;

/**
 * 管理员账号管理 service
 * @author jason558han
 * @date 2020年2月13日
 */
public interface ManagerService {

	/**
	 * 管理员账号列表
	 * @param gpb 分页参数
	 * @return 管理原账号列表
	 */
	List<SysManager> getSysManagerListByGpb(GridPageBean gpb);

	/**
	 * 增删改管理员账号
	 * @param map 编辑参数
	 * @return 编辑情况
	 */
	String editSysManagerByMap(Map<String, String[]> map);

	/**
	 * 根据id修改管理员密码
	 * @param account 账号
	 * @param password 密码
	 * @return 编辑情况
	 */
	String updateManagerPasswordByAccount(String account, String password);

	/**
	 * 根据角色id修改配置角色
	 * @param managerAccount 账号
	 * @param roleId 角色id
	 * @return 编辑情况
	 */
	String updateManagerRoleByRoleId(String managerAccount, String roleId);
}
