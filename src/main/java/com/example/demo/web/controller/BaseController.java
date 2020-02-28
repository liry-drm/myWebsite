package com.example.demo.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.example.demo.common.utils.en_decode.Md5Util;

public abstract class BaseController {
	protected Log log = LogFactory.getLog(getClass());
	protected String resultCode;
	protected String resultMessage;

	protected byte[] getBytesFromFile(File file) throws IOException {
		byte[] bytes = null;
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			long length = file.length();
			if (length > Integer.MAX_VALUE) {
				// File is too large
			}
			bytes = new byte[(int) length];
			int offset = 0;
			int numRead = 0;
			while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
				offset += numRead;
			}
			if (offset < bytes.length) {
				throw new IOException("Could not completely read file " + file.getName());
			}
		} finally {
			if (null != is) {
				is.close();
			}
		}
		return bytes;
	}

	protected String getClientIp(HttpServletRequest request) {
		String ipAddress = null;
		// ipAddress = this.getRequest().getRemoteAddr();
		ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1") || "0:0:0:0:0:0:0:1".equals(ipAddress)) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}

		}

		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length() = 15
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		// System.out.println("ipAddress:" + ipAddress);
		return ipAddress;
	}

	/**
	 * 检测requestToken是否合法，合法返回true,否则返回false
	 * 
	 * @param tokenStr
	 * @param requestToken
	 * @return
	 */
	protected boolean checkRequestToken(String tokenStr, String requestToken) {
		boolean result = false;
		String realRequestToken = Md5Util.md5(tokenStr);
		if (realRequestToken.equalsIgnoreCase(requestToken))
			result = true;

		return result;
	}

	protected String urldecode(String str) {
		try {
			str = URLDecoder.decode(str, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}

	public String getIPAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip)))
			ip = request.getHeader("Proxy-Client-IP");

		if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip)))
			ip = request.getHeader("WL-Proxy-Client-IP");

		if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip)))
			ip = request.getRemoteAddr();

		return ip;
	}
}
