package com.example.demo.service.impl;

import com.example.demo.model.Log;
import com.example.demo.mapper.LogMapper;
import com.example.demo.service.ILogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * service业务处理层
 * @author admin
 *@since 2019-12-19
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {

}
