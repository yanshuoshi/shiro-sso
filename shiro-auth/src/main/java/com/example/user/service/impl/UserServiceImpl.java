package com.example.user.service.impl;

import com.example.user.entity.User;
import com.example.user.mapper.UserMapper;
import com.example.user.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description 用户接口实现类
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * @description 根据用户名获取用户信息
     */
    @Override
    public User findByUserName(String userName) {
        return userMapper.findByUserName(userName);
    }

}
