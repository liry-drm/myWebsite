package com.example.demo.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

import lombok.Getter;

/**
 * 枚举学历类
 * @author admin
 *
 */
@Getter
public enum EducationEnum {
	primary(1, "小学"), 
	secondory(2, "中学"), 
	high(3, "高中"),
	university(4, "大学");

	EducationEnum(int code, String descp) {
		this.code = code;
		this.descp = descp;
	}

	@EnumValue
	private final int code;
	
	private final String descp;
}