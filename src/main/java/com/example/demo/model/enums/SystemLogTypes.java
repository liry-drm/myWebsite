package com.example.demo.model.enums;

import lombok.Getter;

/**
 * 系统日志枚举类
 *
 * @author ASUS
 */
@Getter
public enum SystemLogTypes {
    /**
     * 退出
     */
    LOGOUT(0, "退出"),
    /**
     * 登录
     */
    LOGIN(1, "登录"),
    /**
     * 新增
     */
    ADD(2, "新增"),
    /**
     * 修改
     */
    UPDATE(3, "修改"),
    /**
     * 删除
     */
    DELETE(4, "删除"),
    /**
     * 开启服务
     */
    START_SERVICE(5, "开启服务"),
    /**
     * 关闭服务
     */
    STOP_SERVICE(6, "关闭服务"),
    /**
     * 操作失败
     */
    OPERATION_FAILURE(7, "操作失败"),
    /**
     * 操作异常
     */
    OPERATION_EXCEPTION(8, "操作异常");

    private int logTypeId;
    private String name;

    SystemLogTypes(int logTypeId, String logTypeName) {
        this.logTypeId = logTypeId;
        this.name = logTypeName;
    }

    /**
     * 通过typeId获取对应的枚举对象
     *
     * @param typeId typeId
     * @return 枚举对象
     * @author Lry
     **/
    public static SystemLogTypes getSystemLogTypesByTypeId(int typeId) {
        for (SystemLogTypes systemLogType : SystemLogTypes.values()) {
            if (typeId == systemLogType.logTypeId) {
                return systemLogType;
            }
        }
        return null;
    }
}
