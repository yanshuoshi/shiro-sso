package com.example.project_a.core.mapper;


import com.example.project_a.core.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    /**
     * @description 根据用户名获取用户信息
     */
    User findByUserName(String userName);

}
