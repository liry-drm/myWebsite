package com.example.demo.model.enums;

import lombok.Getter;

/**
 * 支付类型枚举类
 *
 * @author Lry
 **/
@Getter
public enum PayTypes {
    /**
     * 微信公众号支付
     */
    WECHAT_MP_PAY("weChatMp", "微信公众号支付"),
    /**
     * 微信H5支付
     */
    WECHAT_H5("weChatH5", "微信H5支付"),
    /**
     * 微信小程序支付
     */
    WECHAT_APPLETS("weChatMini", "微信小程序支付"),
    /**
     * 微信PC支付
     */
    WECHAT_PC("weChatPC", "微信PC支付"),
    /**
     * 支付宝H5支付
     */
    ALI_H5("aliH5", "支付宝H5支付"),
    /**
     * 支付宝PC支付
     */
    ALI_PC("aliPC", "支付宝PC支付");

    private String code;
    private String name;

    PayTypes(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static PayTypes getPayTypeNameByCode(String code) {
        for (PayTypes payType : PayTypes.values()) {
            if (code.equals(payType.code)) {
                return payType;
            }
        }
        return null;
    }
}
