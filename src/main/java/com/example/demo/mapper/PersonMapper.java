package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.demo.model.Person;

public interface PersonMapper extends BaseMapper<Person> {
	@Select("select * from t_person ${ew.customSqlSegment}")
	List<Person> getPageTestList(@Param(Constants.WRAPPER) Wrapper<Person> query);
}