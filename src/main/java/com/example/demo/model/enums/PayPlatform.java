package com.example.demo.model.enums;

import lombok.Getter;

/**
 * 支付平台枚举类
 *
 * @author ASUS
 */
@Getter
public enum PayPlatform {
    /**
     *
     */
    WECHAT_PAY("weChatPay", "微信支付平台"),
    ALI_PAY("aliPay", "支付宝支付平台");

    private String code;
    private String name;

    PayPlatform(String code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * 根据code 获取枚举对象
     *
     * @param code code
     * @return com.example.demo.model.enums.PayPlatform
     * @author Lry
     **/
    public static PayPlatform getPayPlatformByCode(String code) {
        for (PayPlatform payPlat : PayPlatform.values()) {
            if (code.equals(payPlat.code)) {
                return payPlat;
            }
        }
        return null;
    }
}
