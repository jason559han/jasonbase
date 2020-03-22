package com.jason.base.resolver;

import com.alibaba.fastjson.JSONObject;
import com.jason.base.common.bean.JsonResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 异常处理
 * @author jason558han
 * @date 2020年02月16日
 */
public class BusinessExceptionResolver extends SimpleMappingExceptionResolver {

	private static final Logger logger = LogManager.getLogger(BusinessExceptionResolver.class);

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		String message = "["+request.getRemoteAddr()+"]访问uri["+request.getRequestURI()+"]报错，errorMsg=["+ex.getMessage()+"]";
		//日志处理
		logger.error(message,ex);

		//判断异常处理时 ex instanceof ExceptionClass
		String viewName = determineViewName(ex, request);
		if (viewName != null && !(request.getHeader("accept").contains("application/json")
				|| (request.getHeader("X-Requested-With")!=null 
				&& request.getHeader("X-Requested-With").contains("XMLHttpRequest")))) {
			//如果不是异步请求
			Integer statusCode = determineStatusCode(request, viewName);
			if (statusCode != null) {
				applyStatusCodeIfPossible(request, response, statusCode);
			}
			return getModelAndView(viewName, ex, request);
		} else {//JSON格式返回
			JsonResult result = new JsonResult();
			result.setSuccess(false);
			try {
				result.setCode("500");
				result.setMsg(ex.getMessage());
				PrintWriter writer = response.getWriter();
				writer.write(JSONObject.toJSONString(result));
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
