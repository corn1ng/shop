package com.netease.shop.Service.Impl;

import com.netease.shop.Entity.User;
import com.netease.shop.Mapper.UserMapper;
import com.netease.shop.Service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;


    @Override
    public int selecttypeByNameAndPassword(User user) {
        return userMapper.selecttypeByNameAndPassword(user);
    }
}
