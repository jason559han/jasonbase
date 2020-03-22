package com.jason.base.common.bean;

import java.io.Serializable;

/**
 * 用于封装服务器到客户端的Json返回值
 * @author jason558han
 * @date 2020年02月16日
 */
public class JsonResult implements Serializable {

	private static final long serialVersionUID = -7057045283693449094L;

	private Boolean success = true;
	private String code;
	private String msg;

	public JsonResult() {
		super();
	}
	public JsonResult(Boolean success, String code, String msg) {
		super();
		this.success = success;
		this.code = code;
		this.msg = msg;
	}

	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
