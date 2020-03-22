package com.jason.base.interceptor;

import com.jason.base.common.Constants;
import com.jason.base.common.utils.StringUtil;
import com.jason.base.entity.SysManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户登录状态验证拦截器
 * @author jason558han
 * @date 2020年02月11日
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LogManager.getLogger(LoginInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
        String url = request.getServletPath();
        if(StringUtil.isNotEmpty(url)){
            //判斷是否已登录
            SysManager manager = (SysManager) request.getSession().getAttribute(Constants.SESSION_MANAGER);
            if(manager == null){
                //session中无則是未登录状态
            	logger.warn("["+request.getRemoteAddr()+"]，未登录，请重新登录，url:"+url);
                response.sendRedirect(request.getContextPath()+Constants.NOTLOGIN_URL);
                return false;
            }
        }
        return super.preHandle(request, response, handler);
	}
}
