package com.example.demo.web.controller.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.utils.RedisUtil;
import com.example.demo.web.controller.BaseController;

@RestController
@RequestMapping("/redis")
public class RedisController extends BaseController {

	@Autowired
	private RedisUtil redisTemplate;

	@GetMapping("/add")
	public String add(String key) {
		if(StringUtils.isEmpty(key)) {
			return "添加失败！key不能为空";
		}
		return redisTemplate.set(key, "是的驕傲和房價肯定是")==true?"成功":"失败";
	}
//	@GetMapping("/addUser")
//	public String addUser() {
//		User user = new User();
//		user.setId(1);
//		user.setName("张三");
//		user.setPassword("1111");
//		return redisTemplate.set("user", user)==true?"成功":"失败";
//	}
//
//	@GetMapping("/getUser")
//	public User findUserByKey() {
//		User user=(User) redisTemplate.get("user");
//		
//		return ObjectUtils.isEmpty(user)?null:user;
//	}
	@GetMapping("/get")
	public String get(String key) {
		return (String)redisTemplate.get(key);
	}
	@GetMapping("/del")
	public String del(String key) {
		try {
			redisTemplate.del(key);
		}catch (Exception e) {
			return "删除失败"+e.getLocalizedMessage();
		}
		return "成功";
	}
}
