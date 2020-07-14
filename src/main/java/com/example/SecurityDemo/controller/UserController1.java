//package com.example.SecurityDemo.controller;
//
//import com.example.SecurityDemo.domain.User;
////import com.example.SecurityDemo.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.util.List;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/hello")
//public class UserController1 {
//
////    @Autowired
////    private UserService userService;
//@Value("${auth.wechat.appId}")
//private  String  aa;
//    //权限测试
//    @GetMapping
//    public String hello() {
//        System.err.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+aa);
//        return "hello SpringSecurity";
//    }
//
////权限测试
//    @GetMapping("/helloAdmin")
//    @PreAuthorize("hasAnyRole('admin')")
//    public String helloAdmin() {
//        return "hello,Admin ";
//    }
//
//    //权限测试（普通用户）
//    @GetMapping("/helloUser")
//    @PreAuthorize("hasAnyRole('normal','admin')")
//    public String helloUser() {
//        return "hello,User";
//    }
//
//
//    @GetMapping("/login")
//    public String login() {
//        return "login";
//    }
//
//    @GetMapping("/users")
//    public ModelAndView listUsers(ModelAndView modelAndView) {
//
////        List<User> users = userService.listUsers();
////        modelAndView.addObject("users",users);
//        modelAndView.setViewName("index");
//        return modelAndView;
//
//    }
//
//    @GetMapping("/register")
//    public String register() {
//
//        return "register";
//
//    }
//    @PostMapping("register")
//    @ResponseBody
//    public String register(User user) {
//
//        //设置随机的id
//     //   user.setId(UUID.randomUUID().toString().replaceAll("-", ""));
//
//     //   int result = userService.register(user);
////        if (result == 1) {
////            return "注册成功！";
////        } else {
////            return "注册失败！";
////        }
//        return "注册成功！";
//    }
//
//}
