package com.example.SecurityDemo.mapper;

import com.example.SecurityDemo.domain.RoleUser;
import com.example.SecurityDemo.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    User findByUserName(String username);

    Integer saveUser(User user);

    List<User> findAll();

    Integer addRoleInfo(RoleUser roleUser);


}
