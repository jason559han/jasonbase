package com.jason.base.entity;

import com.jason.base.tags.grid.annotation.Editoptions;
import com.jason.base.tags.grid.annotation.JqGrid;
import com.jason.base.tags.grid.annotation.Searchoptions;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 管理员
 * @author jason558han
 * @date 2020年02月13日
 */
@Table(name = "system_manager")
@JqGrid(displayName = "管理员账号管理")
public class SysManager implements Serializable {

	private static final long serialVersionUID = 4487276361950201114L;

	//管理员账号
	@Id
	@GeneratedValue(generator = "JDBC")
	@Column(name="manager_account",length=10)
	@JqGrid(displayName="账号",serial=1,width=80,editable=true)
	@Editoptions(readonly=true)
	@Searchoptions(sopt={"cn","nc","bw","bn","ew","en"})
	private String managerAccount;

	//密码
	@Column(name="manager_password",length=50)
	private String managerPassword;

	//真实姓名
	@Column(name="real_name",length=50)
	@JqGrid(displayName="昵称",serial=2,editable=true)
	@Editoptions(size=10,maxLength=15)
	@Searchoptions(sopt={"cn","nc","bw","bn","ew","en"})
	private String realName;

	//管理员状态 1启用 0停用 2删除
	@Column(length = 1)
	@JqGrid(displayName="状态",serial=3,width=80,editable=true,edittype="select",formatter="select",stype="select")
	@Editoptions(value="0:停用;1:启用")
	@Searchoptions(sopt={"eq"},value="0:停用;1:启用")
	private String state = "0";

	@Column(name="role_id")
	private Integer roleId;

	//角色名称
	@Transient
	@JqGrid(displayName="所选角色",serial=4,width=100)
	@Searchoptions(sopt={"cn","nc","bw","bn","ew","en"})
	private String roleName;

	// 是否超级管理员
	@Transient
	private Boolean isSuper = false;

	public SysManager() {
		super();
	}
	public SysManager(String managerAccount, String managerPassword) {
		super();
		this.managerAccount = managerAccount;
		this.managerPassword = managerPassword;
	}

	public String getManagerAccount() {
		return managerAccount;
	}
	public void setManagerAccount(String managerAccount) {
		this.managerAccount = managerAccount;
	}
	public String getManagerPassword() {
		return managerPassword;
	}
	public void setManagerPassword(String managerPassword) {
		this.managerPassword = managerPassword;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Boolean getIsSuper() {
		return isSuper;
	}
	public void setIsSuper(Boolean isSuper) {
		this.isSuper = isSuper;
	}
}
