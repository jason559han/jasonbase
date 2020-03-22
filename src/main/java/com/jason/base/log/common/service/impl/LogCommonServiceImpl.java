package com.jason.base.log.common.service.impl;

import com.jason.base.common.dao.BaseDao;
import com.jason.base.common.utils.StringUtil;
import com.jason.base.entity.LogCommon;
import com.jason.base.log.common.service.LogCommonService;
import com.jason.base.tags.grid.JqGridConstants;
import com.jason.base.tags.grid.bean.GridPageBean;
import com.jason.base.tags.grid.utils.JqCommonDaoUtil;
import com.jason.base.tags.grid.utils.JqGridUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 常规日志查看 
 * @author jason558han
 * @date 2020年02月20日
 */
@Transactional
@Service("logCommonService")
public class LogCommonServiceImpl implements LogCommonService {

	@Autowired
	private BaseDao<LogCommon> baseDao;

	@Override
	public List<LogCommon> getLogCommonListByGpb(GridPageBean gpb, String logLevel, String section) {
		this.addLogLevelToLogCommonGpb(gpb, logLevel);
		JqGridUtil.addDateSectionToGpb(gpb, "logDate", section);
		return JqCommonDaoUtil.getPageListByGpb(baseDao, gpb, LogCommon.class);
	}

	//添加日志等级搜索条件
	private void addLogLevelToLogCommonGpb(GridPageBean gpb, String logLevel) {
		if (StringUtil.isNotEmpty(logLevel)) {
			JqGridUtil.addAndSearchRuleToGpb(gpb, "logLevel", JqGridConstants.OP_IN, logLevel);
		}
	}
}
