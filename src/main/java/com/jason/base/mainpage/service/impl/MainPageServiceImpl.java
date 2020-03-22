package com.jason.base.mainpage.service.impl;

import com.jason.base.common.dao.BaseDao;
import com.jason.base.common.utils.StringUtil;
import com.jason.base.entity.SysManager;
import com.jason.base.entity.SysRole;
import com.jason.base.entity.SysTreeMenu;
import com.jason.base.mainpage.service.MainPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.ArrayList;
import java.util.List;

/**
 * 登录主页面管理service
 * @author jason558han
 * @date 2020年02月09日
 */
@Transactional
@Service("mainPageService")
public class MainPageServiceImpl implements MainPageService {

	@Autowired
	private BaseDao<SysTreeMenu> menuDao;
	@Autowired
	private BaseDao<SysRole> roleDao;

	@Override
	public List<SysTreeMenu> getSysTreeMenuListByManager(SysManager manager) {
		Example example = new Example(SysTreeMenu.class);
		Criteria cri = example.createCriteria();
		cri.andEqualTo("state", 0);

		if (!manager.getIsSuper()) {//不是超级管理员
			List<Integer> idList = new ArrayList<>();
			if (manager.getRoleId() != null) {
				//通过角色获得菜单id数组
				String menuIds = roleDao.selectByPrimaryKey(manager.getRoleId()).getMenuIds();
				idList = StringUtil.getIntegerListByStrings(menuIds);
			}
			cri.andIn("id", idList);
		}

		example.setOrderByClause("code asc");
		return menuDao.selectByExample(example);
	}
}
