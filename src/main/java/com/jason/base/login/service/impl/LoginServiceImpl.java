package com.jason.base.login.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jason.base.common.Constants;
import com.jason.base.common.dao.BaseDao;
import com.jason.base.common.utils.MD5Util;
import com.jason.base.entity.SysAdministrator;
import com.jason.base.entity.SysManager;
import com.jason.base.login.service.LoginService;

/**
 * 登录 service
 * @author jason558han
 * @date 2020年02月13日
 */
@Transactional
@Service("loginService")
public class LoginServiceImpl implements LoginService {

	@Autowired
	private BaseDao<SysManager> managerDao;
	@Autowired
	private BaseDao<SysAdministrator> adminDao;

	@Override
	public SysManager getManagerByAccountPassword(String account, String password) {
		String p1 = password.substring(0, 1);
		String p2 = password.substring(1);
		String password2 = MD5Util.getMD5Str(p2+p1, Constants.SLAT);

		//超级管理员账号
		SysAdministrator admin = adminDao.selectOne(new SysAdministrator(account, password2));
		if (admin != null) {
			SysManager manager = new SysManager(account, password2);
			manager.setRealName("超级管理员");
			manager.setIsSuper(true);
			return manager;
		} else {
			//管理员账号
			SysManager record = new SysManager(account, password2);
			record.setState("1");

			return managerDao.selectOne(record);
		}
	}
}
