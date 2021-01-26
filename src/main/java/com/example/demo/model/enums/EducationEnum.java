package com.example.demo.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

import lombok.Getter;
import lombok.Setter;

/**
 * 枚举学历类
 * @author admin
 *
 */
@Getter
public enum EducationEnum {
    /**
     * 小学学历
     */
	PRIMARY(1, "小学"),
    /**
     * 中学学历
     */
    SECONDARY(2, "中学"),
    /**
     * 高中学历
     */
	HIGH(3, "高中"),
    /**
     * 大学学历
     */
	UNIVERSITY(4, "大学");

	EducationEnum(int code, String description) {
		this.code = code;
		this.description = description;
	}

	@EnumValue
	private final int code;
	
	private final String description;
}
