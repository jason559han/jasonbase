package com.jason.base.tags.grid.bean;

import java.lang.reflect.Field;

/**
 * 树状列model数据整合
 * 注：该类是对JqColModel补充
 * @author jason558han
 * @date 2020年02月04日
 */
public class JqTreeColModel {

	private Field levelField;//层级属性
	private Field parentField;//父元素id属性
	private Field isLeafField;//是否是叶元素属性
	private Field expandedField;//是否展开属性
	private Field loadedField;//是否已经加载属性
	private Field iconField;//图标属性

	public Field getLevelField() {
		return levelField;
	}
	public void setLevelField(Field levelField) {
		this.levelField = levelField;
	}
	public Field getParentField() {
		return parentField;
	}
	public void setParentField(Field parentField) {
		this.parentField = parentField;
	}
	public Field getIsLeafField() {
		return isLeafField;
	}
	public void setIsLeafField(Field isLeafField) {
		this.isLeafField = isLeafField;
	}
	public Field getExpandedField() {
		return expandedField;
	}
	public void setExpandedField(Field expandedField) {
		this.expandedField = expandedField;
	}
	public Field getLoadedField() {
		return loadedField;
	}
	public void setLoadedField(Field loadedField) {
		this.loadedField = loadedField;
	}
	public Field getIconField() {
		return iconField;
	}
	public void setIconField(Field iconField) {
		this.iconField = iconField;
	}
}
