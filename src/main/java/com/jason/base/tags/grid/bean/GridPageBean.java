package com.jason.base.tags.grid.bean;

/**
 * 搜索、分页等参数
 * @author jason558han
 * @date 2020年01月15日
 */
public class GridPageBean {

	/**
	 * 页数
	 */
	private Integer page = 1;
	/**
	 * 每页行数
	 */
	private Integer rows;
	/**
	 * 总记录数
	 */
	private Long total;
	/**
	 * 是排序的字段(属性)
	 */
	private String sort;
	/**
	 * 升序还是降序
	 */
	private String dir;
	
	/**
	 * 开始的记录数
	 */
	private Integer start;
	/**
	 * 每次查询多少条记录
	 */
	private Integer limit;

	/**
	 * 节点id
	 */
	private String noteid;

	/**
	 * 搜索列列名
	 */
	private String searchField;

	/**
	 * 搜索内容
	 */
	private String searchString;

	/**
	 * 搜索比较运算符
	 */
	private String searchOper;

	/**
	 * 搜索过滤器
	 */
	private GridSearchFilters filters;

	public GridPageBean(){}

	public GridPageBean(Integer start, Integer limit) {
		this.start = start;
		this.limit = limit;
		this.rows = limit;
	}

	public GridPageBean(Integer start, Integer limit, String sort, String dir) {
		this.start = start;
		this.limit = limit;
		this.sort = sort;
		this.dir = dir;
	}

	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public String getNoteid() {
		return noteid;
	}
	public void setNoteid(String noteid) {
		this.noteid = noteid;
	}
	public String getSearchField() {
		return searchField;
	}
	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}
	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	public String getSearchOper() {
		return searchOper;
	}
	public void setSearchOper(String searchOper) {
		this.searchOper = searchOper;
	}
	public GridSearchFilters getFilters() {
		return filters;
	}
	public void setFilters(GridSearchFilters filters) {
		this.filters = filters;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("GridPageBean{");
		if (page != null) builder.append("page:").append(page).append(",");
		if (rows != null) builder.append("rows:").append(rows).append(",");
		if (total != null) builder.append("total:").append(total).append(",");
		if (sort != null) builder.append("sort:").append(sort).append(",");
		if (dir != null) builder.append("dir:").append(dir).append(",");
		if (start != null) builder.append("start:").append(start).append(",");
		if (limit != null) builder.append("limit:").append(limit).append(",");
		if (noteid != null) builder.append("noteid:").append(noteid).append(",");
		if (searchField != null) builder.append("searchField:").append(searchField).append(",");
		if (searchString != null) builder.append("searchString:").append(searchString).append(",");
		if (searchOper != null) builder.append("searchOper:").append(searchOper).append(",");
		if (filters != null) builder.append("filters:").append(filters).append(",");
		builder.append("}");
		return builder.toString();
	}
}
