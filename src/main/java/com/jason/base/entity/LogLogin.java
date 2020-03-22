package com.jason.base.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jason.base.log.login.bean.LoginType;
import com.jason.base.tags.grid.JqGridConstants;
import com.jason.base.tags.grid.annotation.Editoptions;
import com.jason.base.tags.grid.annotation.JqGrid;
import com.jason.base.tags.grid.annotation.Searchoptions;

/**
 * 登录日志 实体类
 * @author jason558han
 * @date 2020年02月19日
 */
@Table(name = "log_login")
@JqGrid(displayName = "登录日志查看")
public class LogLogin implements Serializable {

	private static final long serialVersionUID = 6794334220097848971L;

	public final static String login_oper_in = "1";//登录
	public final static String login_oper_out = "0";//登出
	public final static String login_state_success = "1";//成功
	public final static String login_state_error = "0";//失败

	@Id
	@GeneratedValue(generator = "JDBC")
	@JqGrid(displayName="序号",hidden=true,search=false)
	private Integer id;

	@JqGrid(displayName="账号",serial=1,width=80)
	@Searchoptions(sopt={"cn","nc","bw","bn","ew","en"})
	private String account;

	@Column(name="real_name",length=50)
	@JqGrid(displayName="昵称",serial=2,width=100)
	@Searchoptions(sopt={"cn","nc","bw","bn","ew","en"})
	private String realName;

	@Column(name="login_date")
	@JqGrid(displayName="登录日期",serial=3,width=140,datefmt="yyyy-MM-dd HH:mm:ss")
	@Searchoptions(dataInit=JqGridConstants.TAG_DATE_INIT_FUNC,sopt={"eq","ne","lt","le","gt","ge"})
	private Date loginDate;

	//登录类别 现有管理员登录
	@Column(name="login_type",length=40)
	private String loginType = LoginType.MANAGER.getClzzName();

	//登录操作 登录和登出
	@Column(name="login_oper",length=1)
	@JqGrid(displayName="登录操作",serial=4,width=60,formatter="select",stype="select")
	@Editoptions(value="1:登录;0:登出")
	@Searchoptions(sopt={"eq"},value="1:登录;0:登出")
	private String loginOper = login_oper_in;

	//登录状态 登录成功或者失败
	@Column(name="login_state",length=1)
	@JqGrid(displayName="登录状态",serial=5,width=60,formatter="select",stype="select")
	@Editoptions(value="1:成功;0:失败")
	@Searchoptions(sopt={"eq"},value="1:成功;0:失败")
	private String loginState = login_state_success;

	@Column(name="ip_address",length=50)
	@JqGrid(displayName="ip地址",serial=6,width=100)
	private String ipAddress;

	public LogLogin() {
		super();
	}
	public LogLogin(String account) {
		super();
		this.account = account;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	public String getLoginType() {
		return loginType;
	}
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	public String getLoginOper() {
		return loginOper;
	}
	public void setLoginOper(String loginOper) {
		this.loginOper = loginOper;
	}
	public String getLoginState() {
		return loginState;
	}
	public void setLoginState(String loginState) {
		this.loginState = loginState;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
}
