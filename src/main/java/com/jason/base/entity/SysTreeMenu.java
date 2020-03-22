package com.jason.base.entity;

import com.jason.base.tags.grid.annotation.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 系统菜单
 * @author jason558han
 * @date 2020年02月02日
 */
@Table(name = "system_tree_menu")
@JqGrid(displayName = "系统菜单")
public class SysTreeMenu implements Serializable {

	@Id
	@GeneratedValue(generator = "JDBC")
	@JqGrid(displayName="序号",hidden=true,key=true)
	private Integer id;

	@Column(name="menu_name")
	@JqGrid(displayName="菜单名称",serial=1,width=120,editable=true)
	@Editoptions(size=15,maxLength=15)
	@Editrules(required=true)
	@Searchoptions(sopt={"cn","nc","bw","bn","ew","en"})
	@JqTreeGrid(expandColumn=true)
	private String menuName;//菜单名称

	@JqGrid(displayName="菜单编码",serial=2,width=60,editable=true)
	@Editoptions(size=15,maxLength=10)
	@Editrules(required=true)
	private String code;//菜单编码

	@JqTreeGrid(renderName="level")
	private Integer level;//层级

	@Column(name="order_number")
	@JqGrid(displayName="顺序",serial=3,width=40,editable=true)
	private Integer orderNumber;//顺序级

	@Column(name="menu_type")
	@JqGrid(displayName="菜单类型",serial=4,width=50,editable=true,formatter="select",edittype="select")
	@Editoptions(value="0:菜单大类;1:菜单链接")
	@Searchoptions(sopt={"eq"},value="0:菜单大类;1:菜单链接")
	private String menuType;//菜单类型 0菜单大类 1菜单链接

	@Column(name="link_file")
	@JqGrid(displayName="菜单链接",serial=5,width=350,editable=true)
	@Editoptions(size=40,maxLength=300)
	@Searchoptions(sopt={"cn","nc","bw","bn","ew","en"})
	private String linkFile;//菜单链接

	@JqGrid(displayName="菜单状态",serial=6,width=50,editable=true,formatter="select",edittype="select")
	@Editoptions(value="0:显示;1:隐藏")
	@Searchoptions(sopt={"eq"},value="0:显示;1:隐藏")
	private String state;//状态 1显示2隐藏

	@JqTreeGrid(renderName="isLeaf")
	private Boolean isLeaf = true;//是否是叶(没有父级)，默认为true

	@JqTreeGrid(renderName="parent")
	private Integer upId;//上级id

	public SysTreeMenu() {
		super();
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getMenuType() {
		return menuType;
	}
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	public String getLinkFile() {
		return linkFile;
	}
	public void setLinkFile(String linkFile) {
		this.linkFile = linkFile;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Boolean getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(Boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
	public Integer getUpId() {
		return upId;
	}
	public void setUpId(Integer upId) {
		this.upId = upId;
	}
}
