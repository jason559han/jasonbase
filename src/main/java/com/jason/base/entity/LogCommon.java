package com.jason.base.entity;

import com.jason.base.common.Constants;
import com.jason.base.tags.grid.JqGridConstants;
import com.jason.base.tags.grid.annotation.JqGrid;
import com.jason.base.tags.grid.annotation.Searchoptions;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 常规日志 实体类
 * @author jason558han
 * @date 2020年02月20日
 */
@Table(name = "log_common")
@JqGrid(displayName = "常规日志查看")
public class LogCommon implements Serializable {

	private static final long serialVersionUID = 5783984733889142598L;

	@Id
	@GeneratedValue(generator = "JDBC")
	@JqGrid(displayName="序号",hidden=true,search=false)
	private Integer id;

	@Column(name="class_name",length=250)
	@JqGrid(displayName="所在类",serial=1,width=150)
	@Searchoptions(sopt={"cn","nc","bw","bn","ew","en"})
	private String className;

	@Column(name="method_name",length=150)
	@JqGrid(displayName="使用方法",serial=2,width=100)
	@Searchoptions(sopt={"cn","nc","bw","bn","ew","en"})
	private String methodName;

	@Column(name="log_date")
	@JqGrid(displayName="日志日期",serial=3,width=140,datefmt=Constants.DEFAULT_DATETIME_FORMAT)
	@Searchoptions(dataInit=JqGridConstants.TAG_DATE_INIT_FUNC,sopt={"eq","ne","lt","le","gt","ge"})
	private Date logDate;

	@Column(name="log_level",length=50)
	@JqGrid(displayName="日志等级",serial=4,width=60,stype="select")
	@Searchoptions(sopt={"eq"},value="info:info;warn:warn;error:error;fatal:fatal")
	private String logLevel;

	@Column(name="log_message",length=4000)
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
