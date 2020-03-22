package com.jason.base.choose.menuChoose.bean;

import com.jason.base.tags.grid.annotation.Editoptions;
import com.jason.base.tags.grid.annotation.JqGrid;
import com.jason.base.tags.grid.annotation.JqTreeGrid;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * 选择系统菜单 bean
 * @author jason558han
 * @date 2020年02月27日
 */
@JqGrid(displayName = "选择系统菜单")
public class MenuChooseBean implements Serializable {

	private static final long serialVersionUID = 3038364661326324014L;

	@Id
	@JqGrid(displayName="序号",hidden=true,key=true)
	private Integer id;

	@JqGrid(displayName="菜单名称",serial=1,width=120)
	@JqTreeGrid(expandColumn=true)
	private String menuName;//菜单名称

	@JqGrid(displayName="菜单类型",serial=4,width=50,formatter="select")
	@Editoptions(value="0:菜单大类;1:菜单链接")
	private String menuType;//菜单类型 0菜单大类 1菜单链接

	@JqTreeGrid(renderName="level")
	private Integer level;//层级

	@JqTreeGrid(renderName="isLeaf")
	private Boolean isLeaf = true;//是否是叶(没有父级)，默认为true

	@JqTreeGrid(renderName="parent")
	private Integer upId;//上级id

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
	public String getMenuType() {
		return menuType;
	}
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
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
