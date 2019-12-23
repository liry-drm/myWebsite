package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Role;
import com.example.demo.service.IBaseService;

/**
 * @author admin
 */
public interface IRoleService extends IBaseService<Role> {

	List<Role> getListByUserId(Long userId);

}
