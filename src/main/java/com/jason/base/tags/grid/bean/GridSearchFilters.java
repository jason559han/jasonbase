package com.jason.base.tags.grid.bean;

import java.util.List;

/**
 * 搜索过滤层
 * @author jason558han
 * @date 2020年02月19日
 */
public class GridSearchFilters {

	private String groupOp;//AND还是OR
	private List<GridSearchdRule> rules;//列表搜索规则

	public GridSearchFilters() {
		super();
	}
	public GridSearchFilters(String groupOp) {
		super();
		this.groupOp = groupOp;
	}

	public String getGroupOp() {
		return groupOp;
	}
	public void setGroupOp(String groupOp) {
		this.groupOp = groupOp;
	}
	public List<GridSearchdRule> getRules() {
		return rules;
	}
	public void setRules(List<GridSearchdRule> rules) {
		this.rules = rules;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("GridSearchFilters{");
		if (groupOp != null) builder.append("groupOp:").append(groupOp).append(",");
		if (rules != null && !rules.isEmpty()) {
			builder.append("rows:[");
			for (GridSearchdRule rule : rules) {
				builder.append(rule).append(",");
			}
			builder.append("]");
		}
		builder.append("}");
		return builder.toString();
	}
}
