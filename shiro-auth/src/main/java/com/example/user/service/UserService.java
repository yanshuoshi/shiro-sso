package com.example.user.service;

import com.example.user.entity.User;

/**
 * @description 用户接口服务层
 */
public interface UserService {

    /**
     * @description 根据用户名获取用户信息
     */
    User findByUserName(String userName);

}
