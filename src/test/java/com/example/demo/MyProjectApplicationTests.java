package com.example.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.common.utils.CommonUtils;
import com.example.demo.mapper.PersonMapper;
import com.example.demo.model.Person;
import com.example.demo.model.enums.EducationEnum;
import com.example.demo.model.enums.SexEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyProjectApplicationTests {

	@Autowired
	private PersonMapper personMapper;

	@Test
	public void testSelect() {
		List<Person> personList = personMapper.selectList(null);
		personList.forEach(System.out::println);
	}
	@Test
	public void testSelectById() {
		Person person = personMapper.selectById(1);
		System.out.println(person);
	}

	@Test
	public void testInsert() {
		Person person = new Person();
		person.setName("刘强");
		person.setDescr("无趣钱财");
		person.setAddress("上海路成都胡同33号二单元4楼402");
		person.setEducation(EducationEnum.secondory);
		person.setSex(SexEnum.male);
		person.setDel_flg(0);
		person.setCreate_by("KIKI");
		person.setCreate_time(new Date());
		for (int i = 0; i < 100; i++) {
			person.setAge(CommonUtils.getRandom(23, 46));
			personMapper.insert(person);
		}
		//System.out.println("添加：" + personMapper.insert(person) + "条");// Mybatis-Plus
	}

	@Test
	public void testDeleteById() {
		System.out.println("删除：" + personMapper.deleteById(4) + "条");
	}

	@Test
	public void testDeleteBatchIds() {
		List<Integer> idList = new ArrayList<>();
		idList.add(6);
		idList.add(5);
		System.out.println("删除：" + personMapper.deleteBatchIds(idList) + "条");
	}

	@Test
	public void testDeleteByMap() {
		Map<String, Object> map = new HashMap<>(16);
		map.put("age", 99);
		System.out.println("删除：" + personMapper.deleteByMap(map) + "条");
	}

}
