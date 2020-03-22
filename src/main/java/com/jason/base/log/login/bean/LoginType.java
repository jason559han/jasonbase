package com.jason.base.log.login.bean;

import com.jason.base.entity.SysManager;

/**
 * 登录类别
 * @author jason558han
 * @date 2020年02月19日
 */
public enum LoginType {

	MANAGER(SysManager.class.getName());

	private String clzzName;

	LoginType(String clzzName) {
		this.clzzName = clzzName;
	}

	public String getClzzName() {
		return clzzName;
	}
}
