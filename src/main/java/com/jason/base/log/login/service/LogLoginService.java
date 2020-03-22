package com.jason.base.log.login.service;

import com.jason.base.entity.LogLogin;
import com.jason.base.tags.grid.bean.GridPageBean;

import java.util.List;

/**
 * 登录日志 管理 service
 * @author jason558han
 * @date 2020年02月19日
 */
public interface LogLoginService {

	/**
	 * 获得登录日志列表
	 * @param gpb 分页参数
	 * @param section 查询区间
	 * @return 登录日志列表
	 */
	List<LogLogin> getLogLoginListByGpb(GridPageBean gpb, String section);

	/**
	 * 保存登录日志
	 * @param logLogin 登录日志
	 */
	void saveLogLogin(LogLogin logLogin);
}
