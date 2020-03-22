package com.jason.base.choose.roleChoose.service.impl;

import com.jason.base.choose.roleChoose.service.RoleChooseService;
import com.jason.base.common.dao.BaseDao;
import com.jason.base.entity.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.List;

/**
 * 角色选择服务 impl
 * @author jason558han
 * @date 2020年02月29日
 */
@Transactional
@Service("roleChooseService")
public class RoleChooseServiceImpl implements RoleChooseService {

	@Autowired
	private BaseDao<SysRole> roleDao;

	@Override
	public List<SysRole> getSysRoleListAll() {
		Example example = new Example(SysRole.class);
		Criteria cri = example.createCriteria();
		cri.andEqualTo("state", '1');//启用
		return roleDao.selectByExample(example);
	}
}
