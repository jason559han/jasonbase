package com.jason.base.choose.roleChoose.controller;

import com.jason.base.choose.roleChoose.service.RoleChooseService;
import com.jason.base.entity.SysRole;
import com.jason.base.tags.grid.bean.GridPageBean;
import com.jason.base.tags.grid.utils.JqGridUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 角色选择 类框
 * @author jason558han
 * @date 2020年02月29日
 */
@Controller
@RequestMapping("/roleChoose")
public class RoleChooseController {

	@Autowired
	private RoleChooseService roleChooseService;

	@RequestMapping("/roleChooseIndex")
	public ModelAndView roleChooseIndex() {
		return new ModelAndView("base/choose/roleChooseIndex");
	}

	@RequestMapping("/roleChooseIndexJson")
	public void roleChooseIndexJson(HttpServletResponse response) {
		GridPageBean gpb = JqGridUtil.getGridPageParams();
		List<SysRole> roleList = roleChooseService.getSysRoleListAll();
		gpb.setTotal(Long.valueOf(roleList.size()));
		JqGridUtil.toGridJson(roleList, SysRole.class, gpb, response);
	}
}
