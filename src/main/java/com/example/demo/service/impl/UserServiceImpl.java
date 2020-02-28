package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.common.utils.DateUtils;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.service.IUserService;

/**
 *
 * service业务处理层
 * @author admin
 *@since 2019-12-18
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

	@Override
	public boolean save(User user) {
		user.setCreateTime(DateUtils.getNowDate());
		return super.save(user);
	}
	public boolean updateLoginDate(String username) {
		return super.update(new User().setLastLoginTime(DateUtils.getNowDate()),new UpdateWrapper<User>().eq("username",username));
	}
}
