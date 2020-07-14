package com.example.SecurityDemo.files;

import com.alibaba.fastjson.JSON;
import com.example.SecurityDemo.util.Result;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        Result responseBody = new Result();
        Object principal = authentication.getPrincipal();
        httpServletResponse.setContentType("application/json;charset=utf-8");
     //   System.err.println(authentication.getPrincipal());
        System.err.println(authentication.getDetails());
        responseBody.setCode(200);
        responseBody.setMessage("Login Success!");
        responseBody.setPrincipal(principal);

        httpServletResponse.getWriter().write(JSON.toJSONString(responseBody));
    }
}

