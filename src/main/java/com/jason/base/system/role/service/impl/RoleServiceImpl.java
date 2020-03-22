package com.jason.base.system.role.service.impl;

import com.jason.base.common.dao.BaseDao;
import com.jason.base.common.utils.StringUtil;
import com.jason.base.entity.SysRole;
import com.jason.base.system.role.service.RoleService;
import com.jason.base.tags.grid.bean.GridPageBean;
import com.jason.base.tags.grid.utils.JqCommonDaoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 角色信息维护 
 * @author jason558han
 * @date 2020年02月26日
 */
@Transactional
@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	private BaseDao<SysRole> roleDao;

	@Override
	public List<SysRole> getSysRoleListByGpb(GridPageBean gpb) {
		return JqCommonDaoUtil.getPageListByGpb(roleDao, gpb, SysRole.class);
	}

	@Override
	public String editSysRoleByMap(Map<String, String[]> map) {
		String msg;
		if ("del".equals(map.get("oper")[0])) {
			String id = map.get("id")[0];
			SysRole role = roleDao.selectByPrimaryKey(id);
			role.setState("2");//删除
			roleDao.updateByPrimaryKey(role);
			msg = "okok";
		} else {
			msg = JqCommonDaoUtil.defaultEditEntityByMap(map, roleDao, SysRole.class);
		}
		return msg;
	}

	@Override
	public String updateRoleMenuIds(String roleId, String menuIds) {
		String msg = "okok";
		if (StringUtil.isNotEmpty(roleId) && StringUtil.isNotEmpty(menuIds)) {
			SysRole role = roleDao.selectByPrimaryKey(roleId);
			role.setMenuIds(menuIds);
			roleDao.updateByPrimaryKey(role);
		}
		return msg;
	}
}
