package com.example.SecurityDemo.controller;
import com.example.SecurityDemo.config.Service;
import com.example.SecurityDemo.domain.Server;
import com.example.SecurityDemo.domain.SysMenu;
import com.example.SecurityDemo.domain.SysRole;
import com.example.SecurityDemo.domain.SysUser;
import com.example.SecurityDemo.dto.LoginBody;
import com.example.SecurityDemo.service.ISysMenuService;
import com.example.SecurityDemo.service.UserService;
import com.example.SecurityDemo.service.sysroleService;
import com.example.SecurityDemo.util.*;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController
public class StartController {

    @Autowired
    private UserService userService;
 ;
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private sysroleService sysroleService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ISysMenuService menuService;
    @GetMapping ("/login")
    public Result login(HttpServletRequest httpServletRequest,LoginBody loginBody) {
        Result ajax = new Result();
        // 用户验证
        System.err.println("用户验证***************************************************************"+loginBody.getUsername());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(loginBody.getUsername(),loginBody.getPassword());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginBody.getUsername());
        SecurityContext securityContext = SecurityContextHolder.getContext();
        HttpSession session = httpServletRequest.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ajax.setData(userDetails);
        ajax.setCode(200);
        return ajax;
        }
    @GetMapping("getInfo")
    public AjaxResult getInfo()
    {
        SysUser user = userService.detali(2);
               // tokenService.getLoginUser(ServletUtils.getRequest());
//        SysUser  = loginUser.getUser();
        // 角色集合
        Set<SysRole> roles = sysroleService.selectRolePermissionByUserId(user.getId());
        // 权限集合
      //  Set<String> permissions = permissionService.getMenuPermission(user);
        Set<String> permissions =menuService.selectMenuPermsByUserId(user.getId().longValue());
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public AjaxResult getRouters()
    {
        SysUser user = userService.detali(2);
        // 用户信息

        List<SysMenu> menus = menuService.selectMenuTreeByUserId(user.getId().longValue());
        return AjaxResult.success(menuService.buildMenus(menus));
    }
//    @GetMapping("/logout")
//    public String logout() {
//        return "logout";
//    }

/**
* @description 获取验证码
*@params
* @return
* @author  zfx
* @date  2020/7/13 9:30
*
*/
    @GetMapping("/vercode")
    public void code(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        VerifyCodeUtils vc = new VerifyCodeUtils();
        BufferedImage image = vc.getImage();
        String text = vc.getText();
        HttpSession session = req.getSession();
        session.setAttribute("index_code", text);
        VerifyCodeUtils.output(image, resp.getOutputStream());
    }

    @GetMapping("/monitor/server")
    public AjaxResult server() throws Exception {
        System.err.println("********************服务器监控**********************");
        Server server = new Server();
        server.copyTo();
        return AjaxResult.success(server);
    }

    @GetMapping("/system/user/profile")
    public AjaxResult profile()
    {

        SysUser user = userService.detali(2);;
        AjaxResult ajax = AjaxResult.success(user);
//        ajax.put("roleGroup", userService.selectUserRoleGroup(loginUser.getUsername()));
//        ajax.put("postGroup", userService.selectUserPostGroup(loginUser.getUsername()));
        return ajax;
    }
//    /**
//     * 个人信息
//     */
//    @GetMapping("/system/user/profile")
//    public AjaxResult profile()
//    {
//        Object principal = authentication.getPrincipal();
//        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
//        SysUser user = loginUser.getUser();
//        AjaxResult ajax = AjaxResult.success(user);
//        ajax.put("roleGroup", userService.selectUserRoleGroup(loginUser.getUsername()));
//        ajax.put("postGroup", userService.selectUserPostGroup(loginUser.getUsername()));
//        return ajax;
//    }
    /**
     * 生成验证码
     */
    @GetMapping("/captchaImage")
    public AjaxResult getCode(HttpServletResponse response) throws IOException
    {
        // 生成随机字串
        String verifyCode = VerifyCodeUtils2.generateVerifyCode(4);
        // 唯一标识
        String uuid = IdUtils.simpleUUID();
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;

        //redisCache.setCacheObject(verifyKey, verifyCode, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        // 生成图片
        int w = 111, h = 36;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        VerifyCodeUtils2.outputImage(w, h, stream, verifyCode);
        try
        {
            AjaxResult ajax = AjaxResult.success();
            ajax.put("uuid", uuid);
            ajax.put("img", Base64.encode(stream.toByteArray()));
            return ajax;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return AjaxResult.error(e.getMessage());
        }
        finally
        {
            stream.close();
        }
    }
    @GetMapping("/users")
    public ModelAndView listUsers(ModelAndView modelAndView) {

        List<SysUser> users = userService.findAll();
        modelAndView.addObject("users",users);
        modelAndView.setViewName("index");
        return modelAndView;

    }

    @GetMapping("/register")
    public String register() {

        return "register";

    }

    @PostMapping("register")
    @ResponseBody
    public String register(SysUser user) {

        //设置随机的id
//        user.setId(UUID.randomUUID().toString().replaceAll("-", ""));

        int result = userService.addUser(user);
            if (result == 1) {
            return "注册成功！";
        } else {
            return "注册失败！";
        }
    }

}
