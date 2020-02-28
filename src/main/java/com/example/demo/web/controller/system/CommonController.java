package com.example.demo.web.controller.system;

import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ReturnResult;
import com.example.demo.model.enums.ResultStatusCode;

@RequestMapping("/common")
@RestController
public class CommonController {
 
    /**
     * 未授权跳转方法
     * @return
     */
    @RequestMapping("/unauth")
    public ReturnResult unauth(){
        //SysUser principal = (SysUser) SecurityUtils.getSubject().getPrincipal();
        SecurityUtils.getSubject().logout();
        return new ReturnResult(ResultStatusCode.UNAUTHO_ERROR);
    }
    /**
     * 被踢出后跳转方法
     * @return
     */
    @RequestMapping("/kickout")
    public ReturnResult kickout(){
        return new ReturnResult(ResultStatusCode.INVALID_TOKEN);
    }
}
