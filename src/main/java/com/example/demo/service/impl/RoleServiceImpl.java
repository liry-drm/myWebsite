package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.RoleMapper;
import com.example.demo.model.Role;
import com.example.demo.service.IRoleService;

/**
 *
 * service业务处理层
 * @author admin
 *@since 2019-12-18
 */
@Service
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
	
	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	public List<Role> getListByUserId(Long userId) {
		return roleMapper.getListByUserId(userId);
	}

}
