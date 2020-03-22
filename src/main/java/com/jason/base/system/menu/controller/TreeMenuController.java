package com.jason.base.system.menu.controller;

import com.jason.base.common.Constants;
import com.jason.base.common.utils.ResponseUtil;
import com.jason.base.entity.SysManager;
import com.jason.base.entity.SysPermissionsUrl;
import com.jason.base.entity.SysTreeMenu;
import com.jason.base.system.menu.service.TreeMenuService;
import com.jason.base.tags.grid.JqGridConstants;
import com.jason.base.tags.grid.bean.GridPageBean;
import com.jason.base.tags.grid.utils.JqGridUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 系统菜单管理controller
 * @author jason558han
 * @date 2020年02月04日
 */
@Controller
@RequestMapping("/menu")
public class TreeMenuController {

	@Autowired
	private TreeMenuService treeMenuService;
	//日志处理
	private static final Logger logger = LogManager.getLogger(TreeMenuController.class);

	/**
	 * 菜单管理主页面
	 * @return 请求路径
	 */
	@RequestMapping("/menuIndex")
	public String menuIndex() {
		return "base/system/menuIndex";
	}

	/**
	 * 菜单列表加载方法
	 * @param response 响应对象
	 */
	@RequestMapping("/menuIndexJson")
	public void menuIndexJson(HttpServletResponse response) {
		GridPageBean gpb = JqGridUtil.getGridPageParams();
		List<SysTreeMenu> list = treeMenuService.getSysTreeMenuListByGpb(gpb);
		JqGridUtil.toTreeGridJson(list, SysTreeMenu.class, gpb, response);
	}

	/**
	 * 菜单编辑
	 * @param request 请求对象
	 * @return 执行情况
	 */
	@RequestMapping("/menuEdit")
	public @ResponseBody String menuEdit(HttpServletRequest request, HttpSession session) {
		Map<String, String[]> map = request.getParameterMap();
		String msg = treeMenuService.editSysTreeMenuListByMap(map);

		//日志处理
		SysManager manager = (SysManager) session.getAttribute(Constants.SESSION_MANAGER);
		logger.info((manager!=null?"["+manager.getManagerAccount()+"]":"")+"菜单编辑["+JqGridConstants.EDIT_OPER_MAP.get(map.get("oper")[0])
			+ "]方法操作。");
		return msg;
	}

	/**
	 * 权限链接通过json传说到前台
	 * @param menuId 菜单id
	 * @param response 响应对象
	 */
	@RequestMapping("/toPermissionsUrlListJson")
	public void toPermissionsUrlListJson(String menuId, HttpServletResponse response) {
		List<SysPermissionsUrl> urlList = treeMenuService.getPermissionsUrlListByMenuId(menuId);
		ResponseUtil.toListJson(urlList, response);
	}

	/**
	 * 添加权限链接保存
	 * @param menuId 菜单id
	 * @param permissionsUrl 权限链接
	 * @param remark 备注说明
	 * @param session session对象
	 * @return 执行情况
	 */
	@RequestMapping("/insertPermissionsUrlList")
	public @ResponseBody String insertPermissionsUrlList(String menuId, String[] permissionsUrl, String[] remark, HttpSession session) {
		String msg = treeMenuService.insertPermissionsUrlList(menuId, permissionsUrl, remark);

		//日志处理
		SysManager manager = (SysManager) session.getAttribute(Constants.SESSION_MANAGER);
		logger.info((manager!=null?"["+manager.getManagerAccount()+"]":"")+"菜单添加权限链接方法操作。menuId=["+menuId+"]");
		return msg;
	}
}
