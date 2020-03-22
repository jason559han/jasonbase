package com.jason.base.choose.menuChoose.controller;

import com.jason.base.choose.menuChoose.bean.MenuChooseBean;
import com.jason.base.choose.menuChoose.service.MenuChooseService;
import com.jason.base.tags.grid.bean.GridPageBean;
import com.jason.base.tags.grid.utils.JqGridUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * 菜单选择
 * @author jason558han
 * @date 2020年02月27日
 */
@Controller
@RequestMapping("/menuChoose")
public class MenuChooseController {

	@Autowired
	private MenuChooseService menuChooseService;

	@RequestMapping("/menuChooseIndexJson")
	public void menuChooseIndexJson(HttpServletResponse response) throws IllegalAccessException, InvocationTargetException {
		GridPageBean gpb = JqGridUtil.getGridPageParams();
		List<MenuChooseBean> menuList = menuChooseService.getMenuChooseBeanListAll();
		gpb.setTotal(Long.valueOf(menuList.size()));
		JqGridUtil.toTreeGridJson(menuList, MenuChooseBean.class, gpb, response);
	}
}
