package com.example.SecurityDemo.service;

import com.example.SecurityDemo.domain.RoleUser;
import com.example.SecurityDemo.domain.User;
import com.example.SecurityDemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userMapper.findByUserName(username);
        Optional<User> op = Optional.ofNullable(user);
        op.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return op.get();

    }


    public List<User> listUsers() {

        List<User> users = userMapper.findAll();
        return users;

    }

    public int register(User user) {

        //使用BCrypt方式加密
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));

       int result = userMapper.saveUser(user);

        RoleUser roleuser = new RoleUser();
        roleuser.setUserId(user.getId());
        roleuser.setRoleId("2");//默认为普通用户
        userMapper.addRoleInfo(roleuser);

        return result;
    }

}
