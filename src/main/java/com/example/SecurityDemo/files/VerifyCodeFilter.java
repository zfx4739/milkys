package com.example.SecurityDemo.files;

import com.alibaba.fastjson.JSON;
import com.example.SecurityDemo.diary.factroy.AsyncFactory;
import com.example.SecurityDemo.redis.RedisCache;
import com.example.SecurityDemo.util.Constants;
import com.example.SecurityDemo.util.Result;
import com.example.SecurityDemo.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import sun.misc.MessageUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    private String defaultFilterProcessUrl = "/login";
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private RedisCache redisCache;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException
    {
        Result result=new Result();
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String username=request.getParameter("username");
        System.err.println("验证码验证**************************************************************");
           System.out.println(request.getParameter("username"));
        System.out.println("uuid"+request.getParameter("uuid"));
        System.out.println("code"+request.getParameter("code"));
//        HttpSession session =new HttpSession();
         request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        System.out.println("SPRING_SECURITY_CONTEXT"+request.getSession().getAttribute("SPRING_SECURITY_CONTEXT"));
//        if(request.getSession().getAttribute("SPRING_SECURITY_CONTEXT")==null){
//            throw new AuthenticationServiceException("请重新登录!");
//        }

//        if ("POST".equalsIgnoreCase(request.getMethod()) && defaultFilterProcessUrl.equals(request.getServletPath()))
//        {
//            System.err.println("验证码验证**************************************************************");
//            System.out.println(request.getParameter("username"));
////            if (request.getParameter("username") != null && SecurityContextHolder.getContext().getAuthentication() == null) {
////                System.err.println("SecurityContextHolder.getContext().getAuthentication() == null**************************************************************");
////            UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getParameter("username"));
////                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
////                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
////                    SecurityContextHolder.getContext().setAuthentication(authentication);
////
////            }

//        SecurityContext securityContext=SecurityContextHolder.getContext();
//            if (SecurityContextHolder.getContext()!= null ) {
//                System.err.println(" null????????????????????????");
//            }
//            String requestCaptcha = request.getParameter("code");
//            String uuid = request.getParameter("uuid");
//            //拼接唯一标识，取出缓存中验证码
//            String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
//            String captcha = redisCache.getCacheObject(verifyKey);
//             redisCache.deleteObject(verifyKey);
//            //String genCaptcha = (String) request.getSession().getAttribute("index_code");
//            //判读用户请求是否填写验证码
//            if (StringUtils.isEmpty(requestCaptcha)) {
//                throw new AuthenticationServiceException("验证码不能为空!");
//            }
//            //判断缓存中验证码是否失效
//            if (captcha == null)
//            {
//               new AsyncFactory().recordLogininfor(username, Constants.LOGIN_FAIL, "验证码已失效");
//                throw new AuthenticationServiceException("验证码已失效");
//            }
//            // 方法用于将字符串与指定的对象比较，不考虑大小写
//                if (!requestCaptcha.equalsIgnoreCase(captcha))
//            {
//                //加入到登录日志
//                new AsyncFactory().recordLogininfor(username, Constants.LOGIN_FAIL, "验证码错误");
//                throw new AuthenticationServiceException("验证码错误!");
//
//            }
//        }
        chain.doFilter(request, response);
}
}


