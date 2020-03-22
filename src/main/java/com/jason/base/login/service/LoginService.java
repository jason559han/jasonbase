package com.jason.base.login.service;

import com.jason.base.entity.SysManager;

/**
 * 登录服务器
 * @author jason558han
 *
 */
public interface LoginService {

	/**
	 * 根据账号密码获取管理员账户
	 * @param account 账号
	 * @param password 密码
	 * @return 登录的账户
	 */
	SysManager getManagerByAccountPassword(String account, String password);
}
