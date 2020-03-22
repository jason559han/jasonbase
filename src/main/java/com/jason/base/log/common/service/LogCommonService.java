package com.jason.base.log.common.service;

import com.jason.base.entity.LogCommon;
import com.jason.base.tags.grid.bean.GridPageBean;

import java.util.List;

/**
 * 常规日志服务端
 * @author jason558han
 * @date 2020年02月20日
 */
public interface LogCommonService {

	/**
	 * 加载常规日志列表
	 * @param gpb 分页参数
	 * @param logLevel 日志等级
	 * @param section 日志时间范围
	 * @return 日志列表
	 */
	List<LogCommon> getLogCommonListByGpb(GridPageBean gpb, String logLevel, String section);
}
