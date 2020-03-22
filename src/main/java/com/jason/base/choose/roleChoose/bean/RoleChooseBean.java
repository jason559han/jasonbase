package com.jason.base.choose.roleChoose.bean;

import java.io.Serializable;

import javax.persistence.Id;

import com.jason.base.tags.grid.annotation.Editoptions;
import com.jason.base.tags.grid.annotation.JqGrid;

/**
 * 角色选择 bean
 * @author jason558han
 * @date 2020年02月29日
 */
public class RoleChooseBean implements Serializable {

	private static final long serialVersionUID = 459479205302849247L;

	@Id
	@JqGrid(displayName="序号",hidden=true,search=false)
	private Integer id;

	@JqGrid(displayName="角色名",serial=1,width=100,search=false)
	private String roleName;

	@JqGrid(displayName="角色说明",serial=2,width=200,search=false)
	private String roleNote;

	@JqGrid(displayName="顺序号",serial=3,width=60,search=false)
	private Integer orderNumber = 0;

	//角色状态 1启用 0停用 2删除
	@JqGrid(displayName="状态",serial=4,width=80,search=false,formatter="select")
	@Editoptions(value="0:停用;1:启用")
	private String state = "0";

	//菜单ids，以,号隔开
	@JqGrid(displayName="菜单组",hidden=true,search=false)
	private String menuIds;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleNote() {
		return roleNote;
	}
	public void setRoleNote(String roleNote) {
		this.roleNote = roleNote;
	}
	public Integer getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getMenuIds() {
		return menuIds;
	}
	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}
}
