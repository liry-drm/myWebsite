package com.example.demo.config.log;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.common.utils.DateUtils;
import com.example.demo.common.utils.net.HttpContextUtil;
import com.example.demo.common.utils.net.NetUtils;
import com.example.demo.config.shiro.ShiroUtils;
import com.example.demo.model.Log;
import com.example.demo.model.User;
import com.example.demo.service.ILogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * AOP 记录用户操作日志
 *
 * @author liruyi
 */
@Aspect
@Component
public class LogAspect {

	@Autowired
	private ILogService logService;
	@Autowired
	private ObjectMapper objectMapper;

	private Log operaLog = new Log();

	@Pointcut("@annotation(com.example.demo.config.log.operLog)")
	public void pointcut() {
		// do nothing
	}

	@Around("pointcut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		Object result = null;
		long beginTime = System.currentTimeMillis();
		// 执行方法
		result = point.proceed();
		// 获取 request
		HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
		// 设置 IP 地址
		String ip = NetUtils.getIpAddr(request);
		// 执行时长(毫秒)
		long time = System.currentTimeMillis() - beginTime;
		// 保存日志
		User user = ShiroUtils.getUserEntity();
		String username = user.getUsername();
		operaLog.setUsername(username);
		operaLog.setIp(ip);
		operaLog.setTime(BigDecimal.valueOf(time));
		packageLog(point);
		logService.save(operaLog);
		return result;
	}

	public void packageLog(ProceedingJoinPoint joinPoint) throws JsonProcessingException {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		operLog logAnnotation = method.getAnnotation(operLog.class);
		if (logAnnotation != null) {
			// 注解上的描述
			operaLog.setOperation(logAnnotation.value());
		}
		// 请求的类名
		String className = joinPoint.getTarget().getClass().getName();
		// 请求的方法名
		String methodName = signature.getName();
		operaLog.setMethod(className + "." + methodName + "()");
		// 请求的方法参数值
		Object[] args = joinPoint.getArgs();
		// 请求的方法参数名称
		LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
		String[] paramNames = u.getParameterNames(method);
		if (args != null && paramNames != null) {
			StringBuilder params = new StringBuilder();
			params = handleParams(params, args, Arrays.asList(paramNames));
			operaLog.setParams(params.toString());
		}
		operaLog.setCreateTime(DateUtils.getNowDate());
		//operaLog.setLocation(AddressUtil.getCityInfo(operaLog.getIp()));
	}

	@SuppressWarnings("rawtypes")
	private StringBuilder handleParams(StringBuilder params, Object[] args, List paramNames)
			throws JsonProcessingException {
		for (int i = 0; i < args.length; i++) {
			if (args[i] instanceof Map) {
				Set set = ((Map) args[i]).keySet();
				List<Object> list = new ArrayList<>();
				List<Object> paramList = new ArrayList<>();
				for (Object key : set) {
					list.add(((Map) args[i]).get(key));
					paramList.add(key);
				}
				return handleParams(params, list.toArray(), paramList);
			} else {
				if (args[i] instanceof Serializable) {
					Class<?> aClass = args[i].getClass();
					try {
						aClass.getDeclaredMethod("toString", new Class[] { null });
						// 如果不抛出 NoSuchMethodException 异常则存在 toString 方法 ，安全的 writeValueAsString ，否则 走
						// Object的 toString方法
						params.append(" ").append(paramNames.get(i)).append(": ")
								.append(objectMapper.writeValueAsString(args[i]));
					} catch (NoSuchMethodException e) {
						params.append(" ").append(paramNames.get(i)).append(": ")
								.append(objectMapper.writeValueAsString(args[i].toString()));
					}
				} else if (args[i] instanceof MultipartFile) {
					MultipartFile file = (MultipartFile) args[i];
					params.append(" ").append(paramNames.get(i)).append(": ").append(file.getName());
				} else {
					params.append(" ").append(paramNames.get(i)).append(": ").append(args[i]);
				}
			}
		}
		return params;
	}
}
