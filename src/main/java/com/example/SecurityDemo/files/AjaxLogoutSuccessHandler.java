package com.example.SecurityDemo.files;

import com.alibaba.fastjson.JSON;
import com.example.SecurityDemo.util.Result;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AjaxLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        Result responseBody=new Result();
        responseBody.setCode(100);
        responseBody.setMessage("Logout Success!");
        httpServletResponse.getWriter().write(JSON.toJSONString(responseBody));
    }
}
