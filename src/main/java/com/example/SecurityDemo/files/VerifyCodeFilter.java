package com.example.SecurityDemo.files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ：zfx
 * @date ：Created in 2020/7/10 17:12
 * @description：
 * @modified By：
 * @version: $
 */
@Component
public class VerifyCodeFilter extends GenericFilterBean {
    //如果请求为Post,并且请求地址为login则会被拦截器拦截并比对验证码是否正确，正确继续检验，否则提示错误给前台
    private String defaultFilterProcessUrl = "/dologin";
    @Autowired
    private UserDetailsService userDetailsService;
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        if ("POST".equalsIgnoreCase(request.getMethod()) && defaultFilterProcessUrl.equals(request.getServletPath()))
        {
            System.err.println("验证码验证**************************************************************");
            System.out.println(request.getParameter("username"));

//            if (request.getParameter("username") != null && SecurityContextHolder.getContext().getAuthentication() == null) {
////                UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getParameter("username"));
////                if (jwtTokenUtil.validateToken(authHeader)) {
////                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
////                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
////                    SecurityContextHolder.getContext().setAuthentication(authentication);
////                }
////            }
// 验证码验证
//            String requestCaptcha = request.getParameter("code");
//            String genCaptcha = (String) request.getSession().getAttribute("index_code");
//            if (StringUtils.isEmpty(requestCaptcha)) throw new AuthenticationServiceException("验证码不能为空!");
//            if (!genCaptcha.toLowerCase().equals(requestCaptcha.toLowerCase()))
//            { throw new AuthenticationServiceException("验证码错误!");
//
//            }
        }
        chain.doFilter(request, response);
    }
}


