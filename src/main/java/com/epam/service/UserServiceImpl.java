package com.epam.service;

import com.epam.entity.User;
import com.epam.entity.enums.UserRoleEnum;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    ///Create user using information from database for our application
    @Override
    public User getUser(String username){
        User user = new User();
        user.setUsername(username);
        user.setPassword("1234"); //DATABASE
        user.setRole(UserRoleEnum.USER); //DATABASE
        return user;
    }
}
