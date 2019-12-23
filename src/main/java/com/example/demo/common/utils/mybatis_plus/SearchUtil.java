package com.example.demo.common.utils.mybatis_plus;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.example.demo.common.utils.CommonUtil;
import com.example.demo.model.ConditionVo;

@SuppressWarnings("all")
public class SearchUtil {
	public static QueryWrapper parseWhereSql(String conditionJson) {
		QueryWrapper queryWrapper = new QueryWrapper();
		if (CommonUtil.isNotNull(conditionJson)) {
			List<ConditionVo> conditionList = JSON.parseArray(conditionJson, ConditionVo.class);
			if (CollectionUtils.isNotEmpty(conditionList)) {
				for (ConditionVo conditionVo : conditionList) {
					switch (conditionVo.getType()) {
					case "eq":
						queryWrapper.eq(conditionVo.getColumn(), conditionVo.getValue());
						break;
					case "ne":
						queryWrapper.ne(conditionVo.getColumn(), conditionVo.getValue());
						break;
					case "like":
						queryWrapper.like(conditionVo.getColumn(), conditionVo.getValue());
						break;
					case "leftlike":
						queryWrapper.likeLeft(conditionVo.getColumn(), conditionVo.getValue());
						break;
					case "rightlike":
						queryWrapper.likeRight(conditionVo.getColumn(), conditionVo.getValue());
						break;
					case "notlike":
						queryWrapper.notLike(conditionVo.getColumn(), conditionVo.getValue());
						break;
					case "gt":
						queryWrapper.gt(conditionVo.getColumn(), conditionVo.getValue());
						break;
					case "lt":
						queryWrapper.lt(conditionVo.getColumn(), conditionVo.getValue());
						break;
					case "ge":
						queryWrapper.ge(conditionVo.getColumn(), conditionVo.getValue());
						break;
					case "le":
						queryWrapper.le(conditionVo.getColumn(), conditionVo.getValue());
						break;
					}
				}
			}
		}
		return queryWrapper;
	}
}
