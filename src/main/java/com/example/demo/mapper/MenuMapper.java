package com.example.demo.mapper;

import com.example.demo.model.Menu;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2019-12-18
 */
public interface MenuMapper extends BaseMapper<Menu> {

	List<Menu> getListByRoleId(Long roleId);

	List<String> getPermsListByUserId(Long userId);

}
