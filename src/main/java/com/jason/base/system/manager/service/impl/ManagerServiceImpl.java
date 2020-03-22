package com.jason.base.system.manager.service.impl;

import com.jason.base.common.Constants;
import com.jason.base.common.dao.BaseDao;
import com.jason.base.common.utils.MD5Util;
import com.jason.base.common.utils.StringUtil;
import com.jason.base.entity.SysManager;
import com.jason.base.entity.SysRole;
import com.jason.base.system.manager.service.ManagerService;
import com.jason.base.tags.grid.bean.GridPageBean;
import com.jason.base.tags.grid.utils.JqCommonDaoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.List;
import java.util.Map;

/**
 * 管理员账号管理service 
 * @author jason558han
 * @date 2020年02月13日
 */
@Transactional
@Service("managerService")
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	private BaseDao<SysManager> managerDao;
	@Autowired
	private BaseDao<SysRole> roleDao;

	@Override
	public List<SysManager> getSysManagerListByGpb(GridPageBean gpb) {
		List<SysManager> managerLisrt = JqCommonDaoUtil.getPageListByGpb(managerDao, gpb, SysManager.class);
		if (managerLisrt != null && !managerLisrt.isEmpty()) {
			List<SysRole> roleList = this.getSysRoleListAll();
			for (SysManager manager : managerLisrt) {
				if (manager.getRoleId() != null) {
					roleRound : for (SysRole role : roleList) {
						if (manager.getRoleId().equals(role.getId())) {
							manager.setRoleName(role.getRoleName());
							break roleRound;
						}
					}
				}
			}
		}
		return managerLisrt;
	}

	//获得角色数据
	private List<SysRole> getSysRoleListAll() {
		Example example = new Example(SysRole.class);
		Criteria cri = example.createCriteria();
		cri.andEqualTo("state", "1");//启用
		return roleDao.selectByExample(example);
	}

	@Override
	public String editSysManagerByMap(Map<String, String[]> map) {
		return JqCommonDaoUtil.defaultEditEntityByMap(map, managerDao, SysManager.class);
	}

	@Override
	public String updateManagerPasswordByAccount(String account, String password) {
		String msg = "okok";
		String p1 = password.substring(0, 1);
		String p2 = password.substring(1);
		String password2 = MD5Util.getMD5Str(p2+p1, Constants.SLAT);
		SysManager manager = managerDao.selectByPrimaryKey(account);

		manager.setManagerPassword(password2);
		managerDao.updateByPrimaryKey(manager);
		return msg;
	}

	@Override
	public String updateManagerRoleByRoleId(String managerAccount, String roleId) {
		String msg = "okok";
		SysManager manager = managerDao.selectByPrimaryKey(managerAccount);
		if (StringUtil.isNotEmpty(roleId)) {
			SysRole role = roleDao.selectByPrimaryKey(roleId);
			if (role != null) {
				manager.setRoleId(role.getId());
				managerDao.updateByPrimaryKey(manager);
			}
		}
		return msg;
	}
}
