package com.jason.base.system.role.controller;

import com.jason.base.common.Constants;
import com.jason.base.entity.SysManager;
import com.jason.base.entity.SysRole;
import com.jason.base.system.role.service.RoleService;
import com.jason.base.tags.grid.JqGridConstants;
import com.jason.base.tags.grid.bean.GridPageBean;
import com.jason.base.tags.grid.utils.JqGridUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 角色信息维护 controller
 * @author jason558han
 * @date 2020年02月26日
 */
@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	//日志处理
	private static final Logger logger = LogManager.getLogger(RoleController.class);

	@RequestMapping("/roleIndex")
	public ModelAndView roleIndex() {
		return new ModelAndView("/base/system/roleIndex");
	}

	@RequestMapping("/roleIndexJson")
	public void roleIndexJson(HttpServletResponse response) {
		GridPageBean gpb = JqGridUtil.getGridPageParams();
		List<SysRole> roleList = roleService.getSysRoleListByGpb(gpb);
		JqGridUtil.toGridJson(roleList, SysRole.class, gpb, response);
	}

	@RequestMapping("/roleEdit")
	public @ResponseBody String roleEdit(HttpServletRequest request, HttpSession session) {
		Map<String, String[]> map = request.getParameterMap();
		String msg = roleService.editSysRoleByMap(map);

		//日志处理
		SysManager manager = (SysManager) session.getAttribute(Constants.SESSION_MANAGER);
		logger.info((manager!=null?"["+manager.getManagerAccount()+"]":"")+"管理员账号["+JqGridConstants.EDIT_OPER_MAP.get(map.get("oper")[0])
			+ "]方法操作报错。");
		return msg;
	}

	/**
	 * 设置权限菜单
	 * @param roleId 角色id
	 * @param menuIds 菜单ids
	 * @param session 时间区间
	 * @return 返回执行情况
	 */
	@RequestMapping("/roleSetMenuIds")
	public @ResponseBody String roleSetMenuIds(String roleId, String menuIds, HttpSession session) {
		String msg = roleService.updateRoleMenuIds(roleId, menuIds);

		//日志处理
		SysManager manager = (SysManager) session.getAttribute(Constants.SESSION_MANAGER);
		logger.info((manager!=null?"["+manager.getManagerAccount()+"]":"")+"设置权限菜单"
				+ "{roleId:"+roleId+",menuIds:"+menuIds+"}。");
		return msg;
	}
}
