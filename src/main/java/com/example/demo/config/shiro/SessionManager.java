package com.example.demo.config.shiro;

import java.io.Serializable;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.apache.shiro.web.util.WebUtils;

/**
 * shiro本身获取sessionid的方法是从cookie中获取的，如果cookie中没有就从url或参数中获取。
 * 在前后端分离中，我们推荐将sessionid放在请求头中，每次都从请求头中获取用户的sessionid。
 * 所以我们要重写shiro获取sessionid的放啊，建立SessionManager类，
 * 继承shiro的DefaultWebSessionManager，重写getSessionId方法，使我们从每次请求的请求头获取sessionId
 * 
 * @author admin
 *
 */
public class SessionManager extends DefaultWebSessionManager {
	private static final String AUTHORIZATION = "Token";
	private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";

	public SessionManager() {
	}

	@Override
	protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
		// 获取请求头，或者请求参数中的Token
		String id = StringUtils.isEmpty(WebUtils.toHttp(request).getHeader(AUTHORIZATION))
				? request.getParameter(AUTHORIZATION)
				: WebUtils.toHttp(request).getHeader(AUTHORIZATION);
		// 如果请求头中有 Token 则其值为sessionId
		if (StringUtils.isNotEmpty(id)) {
			request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
			request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, id);
			request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);

			return id;
		} else {
			// 否则按默认规则从cookie取sessionId
			return super.getSessionId(request, response);
		}
	}

	/**
	 * 获取session 优化单次请求需要多次访问redis的问题
	 *
	 * @param sessionKey
	 * @return
	 * @throws UnknownSessionException
	 */
	@Override
	protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {
		Serializable sessionId = getSessionId(sessionKey);
		ServletRequest request = null;
		if (sessionKey instanceof WebSessionKey) {
			request = ((WebSessionKey) sessionKey).getServletRequest();
		}
		if (request != null && null != sessionId) {
			Object sessionObj = request.getAttribute(sessionId.toString());
			if (sessionObj != null) {
				return (Session) sessionObj;
			}
		}
		Session session = super.retrieveSession(sessionKey);
		if (request != null && null != sessionId) {
			request.setAttribute(sessionId.toString(), session);
		}
		return session;
	}
}
