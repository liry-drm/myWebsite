package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.PersonMapper;
import com.example.demo.model.Person;
import com.example.demo.service.IMybatisPlusTestService;

@Service
@Transactional
public class MybatisPlusTestServiceImpl extends ServiceImpl<PersonMapper, Person> implements IMybatisPlusTestService{
}