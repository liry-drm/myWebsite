package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Menu;
import com.example.demo.service.IBaseService;

/**
 * @author admin
 */
public interface IMenuService extends IBaseService<Menu> {

	List<Menu> getListByRoleId(Long roleId);

	List<String> getPermsListByUserId(Long userId);

}
