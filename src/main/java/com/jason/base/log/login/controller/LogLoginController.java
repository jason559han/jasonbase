package com.jason.base.log.login.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jason.base.entity.LogLogin;
import com.jason.base.log.login.service.LogLoginService;
import com.jason.base.tags.grid.JqGridConstants;
import com.jason.base.tags.grid.bean.GridPageBean;
import com.jason.base.tags.grid.utils.JqGridUtil;

/**
 * 登录页面查看管理
 * @author jason558han
 * @date 2020年02月19日
 */
@Controller
@RequestMapping("/loglogin")
public class LogLoginController {

	@Autowired
	private LogLoginService logLoginService;

	@RequestMapping("/index")
	public ModelAndView logLoginIndex() {
		ModelAndView mav = new ModelAndView("base/log/logLoginIndex");
		mav.addObject("sectionMap", JqGridConstants.DATE_SECTION_MAP);
		return mav;
	}

	@RequestMapping("/logLoginIndexJson")
	public void logLoginIndexJson(HttpServletResponse response, String section) {
		GridPageBean gpb = JqGridUtil.getGridPageParams();
		List<LogLogin> list = logLoginService.getLogLoginListByGpb(gpb, section);
		JqGridUtil.toGridJson(list, LogLogin.class, gpb, response);
	}
}
