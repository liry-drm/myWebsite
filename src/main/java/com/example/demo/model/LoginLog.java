package com.example.demo.model;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 
 *
 * @author admin
 *@since 2019-12-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_login_log")
public class LoginLog extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    @TableField("USERNAME")
    private String username;

    /**
     * 登录时间
     */
    @TableField("LOGIN_TIME")
    private Date loginTime;

    /**
     * 登录地点
     */
    @TableField("LOCATION")
    private String location;

    /**
     * IP地址
     */
    @TableField("IP")
    private String ip;


}
