package com.example.demo.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

import lombok.Getter;

/**
 * 枚举学历类
 * @author admin
 *
 */
@Getter
public enum SexEnum {
	male(0, "male"), 
	female(1, "female");

	SexEnum(int code, String descp) {
		this.code = code;
		this.descp = descp;
	}

	@EnumValue
	private final int code;
	
	private final String descp;
}