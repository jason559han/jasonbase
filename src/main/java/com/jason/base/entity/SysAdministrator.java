package com.jason.base.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 超级管理员
 * @author jason558han
 * @date 2020年02月26日
 */
@Table(name = "system_administrator")
public class SysAdministrator implements Serializable {

	private static final long serialVersionUID = -3755505022508152626L;

	//超级管理员账号
	@Id
	@GeneratedValue(generator = "JDBC")
	@Column(name="admin_account")
	private String adminAccount;

	//密码
	@Column(name="admin_password",length=50)
	private String adminPassword;

	//真实姓名
	@Column(name="real_name",length=50)
	private String realName;

	public SysAdministrator() {
		super();
	}
	public SysAdministrator(String adminAccount, String adminPassword) {
		super();
		this.adminAccount = adminAccount;
		this.adminPassword = adminPassword;
	}

	public String getAdminAccount() {
		return adminAccount;
	}
	public void setAdminAccount(String adminAccount) {
		this.adminAccount = adminAccount;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
}
