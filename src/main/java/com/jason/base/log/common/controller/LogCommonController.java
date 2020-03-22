package com.jason.base.log.common.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jason.base.entity.LogCommon;
import com.jason.base.log.common.service.LogCommonService;
import com.jason.base.tags.grid.JqGridConstants;
import com.jason.base.tags.grid.bean.GridPageBean;
import com.jason.base.tags.grid.utils.JqGridUtil;

/**
 * 常规日志查看
 * @author jason558han
 * @date 2020年02月20日
 */
@Controller
@RequestMapping("/logCommon")
public class LogCommonController {

	@Autowired
	private LogCommonService logCommonService;

	@RequestMapping("/logCommonIndex")
	public ModelAndView logCommonIndex(String logLevel) {
		ModelAndView mav = new ModelAndView("base/log/logCommonIndex");
		mav.addObject("sectionMap", JqGridConstants.DATE_SECTION_MAP);
		mav.addObject("logLevel", logLevel);
		return mav;
	}

	@RequestMapping("/logCommonIndexJson")
	public void logCommonIndexJson(HttpServletResponse response, String logLevel, String section) {
		GridPageBean gpb = JqGridUtil.getGridPageParams();
		List<LogCommon> list = logCommonService.getLogCommonListByGpb(gpb, logLevel, section);
		JqGridUtil.toGridJson(list, LogCommon.class, gpb, response);
	}
}
