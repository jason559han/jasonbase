package com.jason.base.tags.grid.bean;

/**
 * 列表搜索规则
 * @author jason558han
 * @date 2020年02月19日
 */
public class GridSearchdRule {

	private String field;//搜索列名
	private String op;//option 比较预算符
	private String data;//搜索数据

	public GridSearchdRule() {
		super();
	}
	public GridSearchdRule(String field, String op, String data) {
		super();
		this.field = field;
		this.op = op;
		this.data = data;
	}

	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getOp() {
		return op;
	}
	public void setOp(String op) {
		this.op = op;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("GridSearchdRule{");
		if (field != null) builder.append("field:").append(field).append(",");
		if (op != null) builder.append("op:").append(op).append(",");
		if (data != null) builder.append("data:").append(data).append(",");
		builder.append("}");
		return builder.toString();
	}
}
