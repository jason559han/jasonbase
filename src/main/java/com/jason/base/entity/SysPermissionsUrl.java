package com.jason.base.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 菜单权限链接 url
 * @author jason558han
 * @date 2020年02月22日
 */
@Table(name = "system_permissions_url")
public class SysPermissionsUrl implements Serializable {

	private static final long serialVersionUID = 4927743330535624996L;

	@Id
	@GeneratedValue(generator = "JDBC")
	private Integer id;

	@Column(name = "menu_id")
	private Integer menuId;

	@Column(name="permissions_url", length=400)
	private String permissionsUrl;

	@Column(length=200)
	private String remark;

	public SysPermissionsUrl() {
		super();
	}
	public SysPermissionsUrl(Integer menuId, String permissionsUrl) {
		super();
		this.menuId = menuId;
		this.permissionsUrl = permissionsUrl;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	public String getPermissionsUrl() {
		return permissionsUrl;
	}
	public void setPermissionsUrl(String permissionsUrl) {
		this.permissionsUrl = permissionsUrl;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
