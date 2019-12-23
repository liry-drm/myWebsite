package com.example.demo.config.shiro;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.model.enums.ResultStatusCode;
import com.example.demo.service.IMenuService;
import com.example.demo.service.IRoleService;
import com.example.demo.service.IUserService;

public class ShiroRealm extends AuthorizingRealm {
	private static final Logger LOGGER = LoggerFactory.getLogger(ShiroRealm.class);

	@Autowired 
	private IUserService userService;
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IMenuService menuService;

	/**
	 * @return : org.apache.shiro.authz.AuthorizationInfo
	 * @Author : liruyi
	 * @Description : //TODO 授权
	 * @Param : [principals]
	 **/
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("---------------------开始授权----------------------");
		Object principal = principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		if (principal == null) {
			return null;
		}
		if (principal instanceof User) {
			User User = (User) principal;
			List<Role> roles = roleService.getListByUserId(User.getUserId());
			if (!CollectionUtils.isEmpty(roles)) {
				//绑定角色集
				Set<String> collect = roles.stream().map(Role::getRoleName).collect(Collectors.toSet());
				info.addRoles(collect);
				//绑定权限集
				List<String> permslist = menuService.getPermsListByUserId(User.getUserId());
				info.addStringPermissions(permslist);
			}
			System.out.println("---------------------授权完成----------------------");
			return info;
		}
		return null;
	}

	/**
	 * @return : org.apache.shiro.authc.AuthenticationInfo
	 * @Author : liruyi
	 * @Description : //TODO 登录认证
	 * @Param : [authcToken]
	 **/
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		String name = token.getUsername();
		User user = (User) userService.getOne(new QueryWrapper<User>().eq("username", name));
		if (user == null) {
			throw new DisabledAccountException(ResultStatusCode.NOT_EXIST_USER_OR_ERROR_PWD.getMsg());
		}
		if (!(user.getStatus() == 1)) {
			throw new DisabledAccountException(ResultStatusCode.USER_FROZEN.getMsg());
		}
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(),getName());
		return info;
	}
}
