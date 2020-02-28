package com.example.demo.common.exception;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.ReturnResult;
import com.example.demo.model.enums.ResultStatusCode;

/**
 * 描述：
 *
 * @author caojing
 * @create 2019-01-27-17:12
 */
@ControllerAdvice
public class NoPermissionException {
	
    @ResponseBody
    @ExceptionHandler(UnauthorizedException.class)
    public ReturnResult handleShiroException(Exception ex) {
    	 return new ReturnResult(ResultStatusCode.UNAUTHO_ERROR);
    }
    @ResponseBody
    @ExceptionHandler(AuthorizationException.class)
    public ReturnResult AuthorizationException(Exception ex) {
    	return new ReturnResult(ResultStatusCode.UNAUTHO_ERROR);
    }
}
