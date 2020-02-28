package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.MenuMapper;
import com.example.demo.model.Menu;
import com.example.demo.service.IMenuService;

/**
 *
 * service业务处理层
 * @author admin
 *@since 2019-12-18
 */
@Service
@Transactional
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

	@Autowired
	private MenuMapper menuMapper;
	
	@Override
	public List<Menu> getListByRoleId(Long roleId) {
		return menuMapper.getListByRoleId(roleId);
	}

	@Override
	public List<String> getPermsListByUserId(Long userId) {
		return menuMapper.getPermsListByUserId(userId);
	}

}
