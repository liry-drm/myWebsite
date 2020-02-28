package com.example.demo.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.model.Role;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2019-12-18
 */
public interface RoleMapper extends BaseMapper<Role> {

	List<Role> getListByUserId(Long userId);

}
