package com.example.demo.web.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.utils.endecode.Md5Util;
import com.example.demo.model.ReturnResult;
import com.example.demo.model.User;
import com.example.demo.service.impl.UserServiceImpl;

@RequestMapping("/register")
@RestController
public class RegisterController {
 
	@Autowired
    private UserServiceImpl userService;
	
    /**
     * 注册
     * @return
     */
    @RequestMapping("/")
    public ReturnResult register(User user) {
    	user.setPassword(Md5Util.md5_salt(user.getPassword(), user.getUsername()));
    	if(userService.save(user)) {
    		return ReturnResult.ok("注册成功!");
    	}else {
            return ReturnResult.err("注册失败!");
        }
    }
}
