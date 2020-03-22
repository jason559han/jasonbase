package com.jason.base.entity;

import com.jason.base.tags.grid.annotation.Editoptions;
import com.jason.base.tags.grid.annotation.Editrules;
import com.jason.base.tags.grid.annotation.JqGrid;
import com.jason.base.tags.grid.annotation.Searchoptions;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 角色信息
 * @author jason558han
 * @date 2020年02月26日
 */
@Table(name = "system_role")
@JqGrid(displayName = "角色信息")
public class SysRole implements Serializable {

	@Id
	@GeneratedValue(generator = "JDBC")
	@JqGrid(displayName="序号",hidden=true,search=false)
	private Integer id;

	@Column(name="role_name",length=30)
	@JqGrid(displayName="角色名",serial=1,width=100,editable=true)
	@Editoptions(maxLength=15,size=18)
	@Editrules(required=true)
	@Searchoptions(sopt={"cn","nc","bw","bn","ew","en"})
	private String roleName;

	@Column(name="role_note",length=100)
	@JqGrid(displayName="角色说明",serial=2,width=200,editable=true)
	@Editoptions(maxLength=50,size=45)
	@Searchoptions(sopt={"cn","nc","bw","bn","ew","en"})
	private String roleNote;

	@Column(name="order_number")
	@JqGrid(displayName="顺序号",serial=3,width=60,editable=true)
	@Editoptions(maxLength=10,size=8)
	@Editrules(integer=true,minValue=0,maxValue=1000,required=true)
	@Searchoptions(sopt={"eq","ne","lt","le","gt","ge"})
	private Integer orderNumber = 0;

	//角色状态 1启用 0停用 2删除
	@Column(length=1)
	@JqGrid(displayName="状态",serial=4,width=80,editable=true,edittype="select",formatter="select",stype="select")
	@Editoptions(value="0:停用;1:启用")
	@Searchoptions(sopt={"eq"},value="0:停用;1:启用")
	private String state = "0";

	//菜单ids，以,号隔开
	@Column(name="menu_ids",length=800)
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
