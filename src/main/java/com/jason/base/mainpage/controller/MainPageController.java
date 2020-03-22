package com.jason.base.mainpage.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jason.base.common.Constants;
import com.jason.base.entity.SysManager;
import com.jason.base.entity.SysTreeMenu;
import com.jason.base.mainpage.service.MainPageService;

/**
 * 登录后主页面管理controller
 * @author jason558han
 * @date 2020年02月09日
 */
@Controller
@RequestMapping("/main")
public class MainPageController {

	@Autowired
	private MainPageService mainPageService;

	@RequestMapping("/mainPage")
	public ModelAndView mainPage(HttpSession session) {
		ModelAndView mav = new ModelAndView("base/main/mainPage");
		SysManager manager = (SysManager) session.getAttribute(Constants.SESSION_MANAGER);
		mav.addObject("realName", manager.getRealName());
		List<SysTreeMenu> menuList = mainPageService.getSysTreeMenuListByManager(manager);
		mav.addObject("menuList", menuList);
		return mav;
	}

	@RequestMapping("/defaultPage")
	public ModelAndView defaultPage(HttpSession session) {
		ModelAndView mav = new ModelAndView("base/main/defaultPage");
		SysManager manager = (SysManager) session.getAttribute(Constants.SESSION_MANAGER);
		mav.addObject("realName", manager.getRealName());
		return mav;
	}
}
