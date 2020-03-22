package com.jason.base.log.common.bean;

import com.jason.base.common.Constants;
import com.jason.base.tags.grid.JqGridConstants;
import com.jason.base.tags.grid.annotation.JqGrid;
import com.jason.base.tags.grid.annotation.Searchoptions;

import javax.persistence.Id;
import java.util.Date;

/**
 * 错误日志信息 列表结构
 * @author jason558han
 * @date 2020年02月22日
 */
@JqGrid(displayName = "错误日志信息")
public class LogErrorBean {

	@Id
	@JqGrid(displayName="序号",hidden=true,search=false)
	private Integer id;

	@JqGrid(displayName="所在类",serial=1)
	@Searchoptions(sopt={"cn","nc","bw","bn","ew","en"})
	private String className;

	@JqGrid(displayName="使用方法",serial=2,width=100)
	@Searchoptions(sopt={"cn","nc","bw","bn","ew","en"})
	private String methodName;

	@JqGrid(displayName="日志日期",serial=3,width=140,datefmt=Constants.DEFAULT_DATETIME_FORMAT)
	@Searchoptions(dataInit=JqGridConstants.TAG_DATE_INIT_FUNC,sopt={"eq","ne","lt","le","gt","ge"})
	private Date logDate;

	@JqGrid(displayName="日志等级",serial=4,width=60,stype="select")
	@Searchoptions(sopt={"eq"},value="error:error;fatal:fatal")
	private String logLevel;

	@JqGrid(displayName="详细信息",serial=5,width=1000)
	@Searchoptions(sopt={"cn","nc","bw","bn","ew","en"})
	private String logMessage;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public Date getLogDate() {
		return logDate;
	}
	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}
	public String getLogLevel() {
		return logLevel;
	}
	public void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
	}
	public String getLogMessage() {
		return logMessage;
	}
	public void setLogMessage(String logMessage) {
		this.logMessage = logMessage;
	}
}
