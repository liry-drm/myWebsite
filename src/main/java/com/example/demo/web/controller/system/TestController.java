package com.example.demo.web.controller.system;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.log.operLog;

@RestController
@RequestMapping("test")
public class TestController {
 
    //@RequiresRoles("") //表示只有admin这个角色才能访问这个方法
    @RequiresPermissions("user:view")
    @operLog(value="访问测试接口")
    @RequestMapping("test")
    public String test(){
        return "欢迎你，admin" ;
    }
 
}
