package com.jason.base.interceptor;

import com.jason.base.common.Constants;
import com.jason.base.common.dao.BaseDao;
import com.jason.base.common.utils.ResponseUtil;
import com.jason.base.common.utils.StringUtil;
import com.jason.base.entity.SysManager;
import com.jason.base.entity.SysPermissionsUrl;
import com.jason.base.entity.SysRole;
import com.jason.base.entity.SysTreeMenu;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户权限验证拦截器
 * @author jason558han
 * @date 2020年02月29日
 */
public class PermissionsInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private BaseDao<SysRole> roleDao;
	@Autowired
	private BaseDao<SysTreeMenu> menuDao;
	@Autowired
	private BaseDao<SysPermissionsUrl> permissionDao;

	private static final Logger logger = LogManager.getLogger(PermissionsInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		SysManager manager = (SysManager) request.getSession().getAttribute(Constants.SESSION_MANAGER);
		//用户登录，并且不是超级管理员
		if (manager != null && !manager.getIsSuper()) {
			//是否通过拦截
			boolean isHandle = false;
			if (manager.getRoleId() != null) {
				String menuIds = roleDao.selectByPrimaryKey(manager.getRoleId()).getMenuIds();
				List<SysTreeMenu> menuList = menuDao.selectByIds(menuIds);
				List<SysPermissionsUrl> permissionsList = getPermissionsUrlListByMenuIds(menuIds);

				String uri = request.getRequestURI();
				if (StringUtil.isNotEmpty(Constants.applicationContextRoot)) {
					uri = uri.replaceAll(Constants.applicationContextRoot, "");
				}
				isHandle = checkUrlHandleByPermissions(uri, menuList, permissionsList);
			}

			//没有通过验证
			if (!isHandle) {
            	logger.warn("["+manager.getManagerAccount()+"]，没有通过链接权限验证，url:"+request.getServletPath());
            	ResponseUtil.toStringResponse("你没有权限访问该链接。", response);
                return false;
			}
		}
		return super.preHandle(request, response, handler);
	}

	//获得权限链接
	private List<SysPermissionsUrl> getPermissionsUrlListByMenuIds(String menuIds) {
		List<Integer> menuIdList = StringUtil.getIntegerListByStrings(menuIds);
		Example example = new Example(SysPermissionsUrl.class);
		Criteria cri = example.createCriteria();
		cri.andIn("menuId", menuIdList);
		return permissionDao.selectByExample(example);
	}

	//根据权限测试链接
	private boolean checkUrlHandleByPermissions(String url, List<SysTreeMenu> menuList, List<SysPermissionsUrl> permissionsList) {
		boolean isHandle = false;

		//检测菜单的链接
		if (menuList != null && !menuList.isEmpty()) {
			for (SysTreeMenu menu : menuList) {
				String menuUrl = menu.getLinkFile();
				if (StringUtil.isNotEmpty(menuUrl)) {
					if (menuUrl.contains("?")) {
						menuUrl = menuUrl.substring(0, menuUrl.indexOf("?"));
					}
					if (menuUrl.equals(url)) {
						isHandle = true;
						break;
					}
				}
			}
		}

		//检测权限配置链接
		if (!isHandle && permissionsList != null && !permissionsList.isEmpty()) {
			for (SysPermissionsUrl permissions : permissionsList) {
				if (permissions.getPermissionsUrl().equals(url)) {
					isHandle = true;
					break;
				}
			}
		}
		return isHandle;
	}
}
