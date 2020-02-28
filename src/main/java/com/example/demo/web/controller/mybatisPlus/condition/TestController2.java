package com.example.demo.web.controller.mybatisPlus.condition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.utils.mybatis_plus.SearchUtil;
import com.example.demo.model.Person;
import com.example.demo.service.IMybatisPlusTestService;
import com.example.demo.web.controller.BaseController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/mybatisPlusTest")
@SuppressWarnings("all")
public class TestController2 extends BaseController {
	
	@Autowired
	private IMybatisPlusTestService mybatisPlusTestService;
	
	@RequestMapping(value = "/list")
	public Object getTestList2(@RequestBody Map<String,Object> param) {
		int pageNum = (int) param.get("pageNum");
		int pageSize = (int) param.get("pageSize");
		Object condition =param.get("condition");
		String conditionJson = JSON.toJSONString(condition);
		QueryWrapper queryWrapper = SearchUtil.parseWhereSql(conditionJson);
		queryWrapper.orderByDesc("age");
		Page<Person> page = new Page<Person>(pageNum,pageSize);
		IPage iPage = mybatisPlusTestService.page(page, queryWrapper);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("pageNum",pageNum);
		map.put("pageSize",pageSize);
		map.put("totalpages",iPage.getPages());
		map.put("totalSize",iPage.getTotal());
		map.put("data",iPage.getRecords());
		return map;
	}
}
