package com.jason.base.system.manager.controller;

import com.jason.base.common.Constants;
import com.jason.base.entity.SysManager;
import com.jason.base.system.manager.service.ManagerService;
import com.jason.base.tags.grid.JqGridConstants;
import com.jason.base.tags.grid.bean.GridPageBean;
import com.jason.base.tags.grid.bean.GridSearchFilters;
import com.jason.base.tags.grid.bean.GridSearchdRule;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 管理员账号管理 controller
 * @author jason558han
 * @date 2020年02月13日
 */
@Controller
@RequestMapping("/managers")
public class ManagerController {

	@Autowired
	private ManagerService managerService;
	//日志处理
	private static final Logger logger = LogManager.getLogger(ManagerController.class);

	@RequestMapping("/managerIndex")
	public String managerIndex() {
		return "base/system/managerIndex";
	}

	@RequestMapping("/managerIndexJson")
	public void managerIndexJson(HttpServletResponse response) {
		GridPageBean gpb = JqGridUtil.getGridPageParams();
		GridSearchFilters filters = gpb.getFilters() != null ? gpb.getFilters() : new GridSearchFilters();
		filters.setGroupOp(JqGridConstants.GROUPOP_AND);
		List<GridSearchdRule> rules = filters.getRules() != null ? filters.getRules() : new ArrayList<>();
		rules.add(new GridSearchdRule("state", JqGridConstants.OP_NOT_EQUAL, "2"));//删除的不显示
		filters.setRules(rules);
		gpb.setFilters(filters);
		List<SysManager> list = managerService.getSysManagerListByGpb(gpb);
		JqGridUtil.toGridJson(list, SysManager.class, gpb, response);
	}

	@RequestMapping("/managerEdit")
	public @ResponseBody String managerEdit(HttpServletRequest request, HttpSession session) {
		Map<String, String[]> map = request.getParameterMap();
		String msg = managerService.editSysManagerByMap(map);

		//日志处理
		SysManager manager = (SysManager) session.getAttribute(Constants.SESSION_MANAGER);
		logger.info((manager!=null?"["+manager.getManagerAccount()+"]":"")+"管理员账号["+JqGridConstants.EDIT_OPER_MAP.get(map.get("oper")[0])
			+ "]方法操作。");
		return msg;
	}

	@RequestMapping("/managerSetPasswd")
	public @ResponseBody String managerSetPasswd(String passwdAccount, String password, HttpSession session) {
		String msg = managerService.updateManagerPasswordByAccount(passwdAccount,password);

		//日志处理
		SysManager manager = (SysManager) session.getAttribute(Constants.SESSION_MANAGER);
		logger.info((manager!=null?"["+manager.getManagerAccount()+"]":"")+"管理员账号["+passwdAccount+"]修改密码操作");
		return msg;
	}

	@RequestMapping("/managerConfigRole")
	public @ResponseBody String managerConfigRole(String managerAccount, String roleId, HttpSession session) {
		String msg = managerService.updateManagerRoleByRoleId(managerAccount, roleId);

		//日志处理
		SysManager manager = (SysManager) session.getAttribute(Constants.SESSION_MANAGER);
		logger.info((manager!=null?"["+manager.getManagerAccount()+"]":"")+"管理员账号["+managerAccount+"]配置角色操作");
		return msg;
	}
}
