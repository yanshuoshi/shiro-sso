package com.example.project_b.core.service;


import com.example.project_b.core.entity.User;

/**
 * @description 用户接口服务层
 */
public interface UserService {

    /**
     * @description 根据用户名获取用户信息
     */
    User findByUserName(String userName);

}
