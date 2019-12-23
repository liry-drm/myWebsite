package com.example.demo.config.shiro;

import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import com.example.demo.model.ReturnResult;
import com.example.demo.model.enums.ResultStatusCode;

public class ShiroFormAuthenticationFilter extends FormAuthenticationFilter {
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        boolean authenticated = subject.isAuthenticated();
        if(subject.isAuthenticated() || subject.isRemembered()) {
            //如果登录了，直接进行之后的流程
            return true;
        }
        subject.logout();
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println(new ReturnResult(ResultStatusCode.UNAUTHO_ERROR).toJson());
        out.flush();
        out.close();
        return false;
    }
}
