package com.example.SecurityDemo.config;
import com.example.SecurityDemo.files.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//开启表示喂配置类
@Configuration
//启用springSecrity
@EnableWebSecurity
//开启安全控制(true  会拦截@PreAuthrize)
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)//开启角
//默认WebSecurityConfigurerAdapter 是已经实现了configure方法
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AjaxAuthenticationEntryPoint authenticationEntryPoint;           //  未登陆时返回 JSON 格式的数据给前端（否则为 html）

    @Autowired
    AjaxAccessDeniedHandler accessDeniedHandler;                    // 无权访问返回的 JSON 格式数据给前端（否则为 403 html 页面）

    @Autowired
    AjaxAuthenticationSuccessHandler authenticationSuccessHandler;  // 登录成功返回的 JSON 格式数据给前端（否则为 html）

    @Autowired
    AjaxAuthenticationFailureHandler authenticationFailureHandler;  //  登录失败返回的 JSON 格式数据给前端（否则为 html）

    @Autowired
    AjaxLogoutSuccessHandler logoutSuccessHandler;                 // 注销成功返回的 JSON 格式数据给前端（否则为 登录时的 html）

    @Autowired
    VerifyCodeFilter verifyCodeFilter;                             //验证图片过滤器

//    @Autowired
//    private UserDetailsService userDetailsService;

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        //获取用户账号密码及权限信息
        return new Service.UserDetailsServiceImpl();
    }

    /**
     * 重写configure方法完成用户认证
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }
    //注入PasswordEncoder，加密方式
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * anyRequest          |   匹配所有请求路径
     * access              |   SpringEl表达式结果为true时可以访问
     * anonymous           |   匿名可以访问
     * denyAll             |   用户不能访问
     * fullyAuthenticated  |   用户完全认证可以访问（非remember-me下自动登录）
     * hasAnyAuthority     |   如果有参数，参数表示权限，则其中任何一个权限可以访问
     * hasAnyRole          |   如果有参数，参数表示角色，则其中任何一个角色可以访问
     * hasAuthority        |   如果有参数，参数表示权限，则其权限可以访问
     * hasIpAddress        |   如果有参数，参数表示IP地址，如果用户IP和参数匹配，则可以访问
     * hasRole             |   如果有参数，参数表示角色，则其角色可以访问
     * permitAll           |   用户可以任意访问
     * rememberMe          |   允许通过remember-me登录的用户访问
     * authenticated       |   用户登录后可访问
     *
     * http.csrf().disable()
     * 在Security的默认拦截器里，默认会开启CSRF处理，判断请求是否携带了token，
     * 如果没有就拒绝访问。 并且，在请求为(GET|HEAD|TRACE|OPTIONS)时，
     * 则不会开启既然是因为默认开启了CSRF，那关掉便是，我本地使用springboot，
     * 只需在WebSecurityConfigurerAdapter的配置类的http中关闭
     */
    //匿名允许经过接口
    @Override
    public void configure(WebSecurity web) throws Exception {

        web.ignoring().antMatchers(
                "/v2/api-docs",//放行swagger
                "/swagger-resources/**",
                "/configuration/ui",
                "/configuration/security",
                "/swagger-ui.html/**",
                "/webjars/**",
                "/js/**",                   //放行静态文件
                "/css/**",
                "/images/**",
               "/captchaImage",
                "/swagger-ui.html/**",
                "/webjars/**",
                "/member/getList",         //放行登录验证码等
                "/product/wx/**",
                "/start/wx/login",
                "/orderm/**",
                "/login",
                "/getInfo",
                "/getRouters",
                "/monitor/server",
                "/system/user/profile");
    }

    /**
     * 重写configure 完成权限拦截
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //添加前置通知过滤器
       http.addFilterBefore(verifyCodeFilter, UsernamePasswordAuthenticationFilter.class);
        http
              //  .httpBasic().authenticationEntryPoint(authenticationEntryPoint)
               // .and()
                .authorizeRequests()
                .antMatchers("/store/getList").hasRole("ADMIN")//访问该地址需要拥有admin权限
                .antMatchers("/price/getList").hasRole("MEMBER") // 角色为member
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .successHandler(authenticationSuccessHandler) // 登录成功
                .failureHandler(authenticationFailureHandler) // 登录失败
                .permitAll()// 登录页面用户任意访问
                .and().headers().frameOptions().disable()
                .and().logout()
                .logoutUrl("/logout").
                logoutSuccessHandler(logoutSuccessHandler)
                .permitAll();
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler); // 无权访问 JSON 格式的数据

    }
}
