package com.example.project_b.core.serviceimpl;

import com.example.project_b.core.entity.User;
import com.example.project_b.core.mapper.UserMapper;
import com.example.project_b.core.service.UserService;
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
