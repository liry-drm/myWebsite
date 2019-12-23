package com.example.demo.web.controller.system;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.config.shiro.ShiroUtils;
import com.example.demo.model.ReturnResult;
import com.example.demo.model.enums.ResultStatusCode;


@CrossOrigin
@RestController
@RequestMapping("/admin")
public class LoginController {

	@RequestMapping("/login")
	public ReturnResult login(String userName, String pwd) {
		try {
			UsernamePasswordToken token = new UsernamePasswordToken(userName, pwd);
			// 登录不在该处处理，交由shiro处理
			Subject subject = SecurityUtils.getSubject();
			subject.login(token);
			if (subject.isAuthenticated()) {
				JSON json = new JSONObject();
				((JSONObject) json).put("Token", subject.getSession().getId());
				return ReturnResult.ok("登录成功",json);
			} else {
				return new ReturnResult(ResultStatusCode.SHIRO_ERROR);
			}
		} catch (IncorrectCredentialsException | UnknownAccountException e) {
			return new ReturnResult(ResultStatusCode.NOT_EXIST_USER_OR_ERROR_PWD);
		} catch (LockedAccountException e) {
			return new ReturnResult(ResultStatusCode.USER_FROZEN);
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnResult(ResultStatusCode.SYSTEM_ERR);
		}
	}

	/**
	 * 退出登录
	 * 
	 * @return
	 */
	@RequestMapping("/logout")
	public ReturnResult logout() {
		SecurityUtils.getSubject().logout();
		return ReturnResult.ok("退出系统");
	}
}
