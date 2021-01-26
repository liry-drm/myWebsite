package com.example.demo.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

import lombok.Getter;

/**
 * 枚举学历类
 *
 * @author admin
 */
@Getter
public enum Sex {
    /**
     * 女性
     */
    MALE(0, "male"),
    /**
     * 男性
     */
    FEMALE(1, "female");

    @EnumValue
    private final int code;
    private final String description;

    Sex(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
