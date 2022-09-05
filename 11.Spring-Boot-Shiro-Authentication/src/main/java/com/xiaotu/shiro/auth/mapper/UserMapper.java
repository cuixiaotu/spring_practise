package com.xiaotu.shiro.auth.mapper;


import com.xiaotu.shiro.auth.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User findByUserName(String userName);
}