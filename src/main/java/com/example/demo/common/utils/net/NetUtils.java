package com.example.demo.common.utils.net;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.common.utils.CommonUtils;
import com.example.demo.web.controller.BaseController;

/**
 * @author ASUS
 */
public class NetUtils {
	
	private static final String UNKNOWN = "unknown";
	
	/**
	 * 验证是否是ajax请求
	 * @param req
	 * @return
	 */
    public static boolean jsAjax(HttpServletRequest req){
        //判断是否为ajax请求，默认不是
        boolean isAjaxRequest = false;
        if(!CommonUtils.isBlank(req.getHeader("x-requested-with")) && "XMLHttpRequest".equals(req.getHeader("x-requested-with"))){
            isAjaxRequest = true;
        }
        return isAjaxRequest;
    }
    

	/**
	 * 获取 IP地址
	 * 使用 Nginx等反向代理软件， 则不能通过 request.getRemoteAddr()获取 IP地址
	 * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，
	 * X-Forwarded-For中第一个非 unknown的有效IP字符串，则为真实IP地址
	 */
	public static String getIpAddr(HttpServletRequest request) {
        String ip = BaseController.getClientIp(request, UNKNOWN);
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
	}
}
