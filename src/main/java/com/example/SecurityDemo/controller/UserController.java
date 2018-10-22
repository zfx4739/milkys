package com.example.SecurityDemo.controller;

import com.example.SecurityDemo.domain.User;
import com.example.SecurityDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/users")
    public ModelAndView listUsers(ModelAndView modelAndView) {

        List<User> users = userService.listUsers();
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
    public String register(User user) {

        //设置随机的id
        user.setId(UUID.randomUUID().toString().replaceAll("-", ""));

        int result = userService.register(user);
        if (result == 1) {
            return "注册成功！";
        } else {
            return "注册失败！";
        }
    }

}
