package com.example.demo.config.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

import com.example.demo.common.utils.endecode.Md5Util;
 
public class CredentialsMatcher extends SimpleCredentialsMatcher {
 
	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		UsernamePasswordToken utoken = (UsernamePasswordToken) token;
		// 获得用户输入的密码:(可以采用加盐(salt)的方式去检验)
		String inPassword = new String(utoken.getPassword());
		String inUsername = new String (utoken.getUsername());
		String password = Md5Util.md5_salt(inPassword, inUsername);
		// 进行密码的比对
		return password.equals(info.getCredentials().toString());
	}
}
