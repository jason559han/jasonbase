package com.jason.base.login.controller;

import com.jason.base.common.Constants;
import com.jason.base.common.utils.VerifyCodeUtil;
import com.jason.base.entity.LogLogin;
import com.jason.base.entity.SysManager;
import com.jason.base.log.login.service.LogLoginService;
import com.jason.base.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 登录 管理controller
 * @author jason558han
 * @date 2020年02月09日
 */
@Controller
@RequestMapping("")
public class LoginController {

	@Autowired
	private LoginService loginService;
	@Autowired
	private LogLoginService logLoginService;

	private SysManager manager;

	/**
	 * 默认主页面
	 * @param session session对象
	 * @return 页面路径
	 */
	@RequestMapping("/main")
	public String main(HttpSession session) {
		manager = (SysManager) session.getAttribute(Constants.SESSION_MANAGER);
		if (manager != null) {
			return "forward:main/mainPage.do";
		} else {
			return "base/main/loginPage";
		}
	}

	/**
	 * 登录验证后转向页面
	 * @return 页面路径
	 */
	@RequestMapping("/notLoginPage")
	public ModelAndView notLoginPage() {
		ModelAndView mav = new ModelAndView("base/main/loginPage");
		mav.addObject("msg", "未登录，请先登录");
		return mav;
	}

	/**
	 * 登录
	 * @return 页面路径
	 */
	@RequestMapping("/login")
	public @ResponseBody String login(HttpServletRequest request, HttpServletResponse response) {
		String msg = "okok";
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String verifyCode = request.getParameter("verifyCode");
		HttpSession session = request.getSession();
		String rightCode = (String) session.getAttribute(Constants.VERIFY_CODE);

		//判断验证码是否正确
		if (verifyCode != null && rightCode.toLowerCase().equals(verifyCode.toLowerCase())) {
			//登录日志
			LogLogin log = new LogLogin(account);
			log.setIpAddress(request.getRemoteAddr());
			manager = loginService.getManagerByAccountPassword(account, password);

			if (manager != null) {//登录成功
				session.setAttribute(Constants.SESSION_MANAGER, manager);
				Cookie aCookie = new Cookie("account", account);
				Cookie pCookie = new Cookie("password", password);
				response.addCookie(aCookie);
				response.addCookie(pCookie);

				log.setRealName(manager.getRealName());
			} else {//登录失败
				msg = "errorAccountPassword";

				log.setLoginState(LogLogin.login_state_error);
			}
			logLoginService.saveLogLogin(log);
		} else {
			msg = "errorVerifyCode";
		}
		return msg;
	}

	/**
	 * 获取验证码图片
	 * @param request 请求对象
	 * @param response 响应对象
	 */
    @RequestMapping("/getVerifyCode")
    public void getVerificationCode(HttpServletRequest request,HttpServletResponse response) {
    	try {
    		int width=190;
    		int height=40;
    		BufferedImage verifyImg=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

    		//生成对应宽高的初始图片
    		String randomText = VerifyCodeUtil.drawRandomText(width,height,verifyImg);

    		//单独的一个类方法，出于代码复用考虑，进行了封装。
    		//功能是生成验证码字符并加上噪点，干扰线，返回值为验证码字符
    		request.getSession().setAttribute(Constants.VERIFY_CODE, randomText);
    		response.setContentType("image/png");//必须设置响应内容类型为图片，否则前台不识别
    		OutputStream os = response.getOutputStream(); //获取文件输出流
            ImageIO.write(verifyImg,"png",os);//输出图片流

            os.flush();
            os.close();//关闭流
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }

	/**
	 * 登出
	 * @param session session对象
	 * @return 页面路径
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request,HttpSession session) {
		//登出日志
		LogLogin log = new LogLogin();
		log.setIpAddress(request.getRemoteAddr());
		log.setLoginOper(LogLogin.login_oper_out);
		SysManager manager = (SysManager) session.getAttribute(Constants.SESSION_MANAGER);
		if (manager != null) {
			log.setAccount(manager.getManagerAccount());
			log.setRealName(manager.getRealName());
		}
		logLoginService.saveLogLogin(log);

		session.removeAttribute(Constants.SESSION_MANAGER);
		return "redirect:main.do";
	}

	public SysManager getManager() {
		return manager;
	}
	public void setManager(SysManager manager) {
		this.manager = manager;
	}
}
