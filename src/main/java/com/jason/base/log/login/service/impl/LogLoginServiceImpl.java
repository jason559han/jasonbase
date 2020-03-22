package com.jason.base.log.login.service.impl;

import com.jason.base.common.dao.BaseDao;
import com.jason.base.entity.LogLogin;
import com.jason.base.log.login.service.LogLoginService;
import com.jason.base.tags.grid.bean.GridPageBean;
import com.jason.base.tags.grid.utils.JqCommonDaoUtil;
import com.jason.base.tags.grid.utils.JqGridUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 登录日志操作处理 service 实例
 * @author jason558han
 * @date 2020年02月19日
 */
@Transactional
@Service("logLoginService")
public class LogLoginServiceImpl implements LogLoginService {

	@Autowired
	private BaseDao<LogLogin> logLoginDao;

	@Override
	public List<LogLogin> getLogLoginListByGpb(GridPageBean gpb, String section) {
		JqGridUtil.addDateSectionToGpb(gpb, "loginDate", section);
		return JqCommonDaoUtil.getPageListByGpb(logLoginDao, gpb, LogLogin.class);
	}

	@Override
	public void saveLogLogin(LogLogin logLogin) {
		logLogin.setLoginDate(new Date());
		logLoginDao.insert(logLogin);
	}
}
