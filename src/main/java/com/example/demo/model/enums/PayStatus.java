package com.example.demo.model.enums;

/**
 * 支付状态枚举类
 *
 * @author ASUS
 */

public enum PayStatus {
    /**
     * 待支付
     */
    WAIT(1, "待支付"),
    /**
     * 已支付
     */
    PAID(2, "已支付"),
    /**
     * 支付成功
     */
    SUCCESS(3, "支付成功"),
    /**
     * 取消支付
     */
    CANCEL(4, "取消支付"),
    /**
     * 支付失败
     */
    FAIL(5, "支付失败"),
    /**
     * 关闭支付
     */
    CLOSE(6, "关闭支付"),
    /**
     * 已退款
     */
    REFUND(7, "已退款");

    private int code;
    private String description;

    PayStatus(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

}
