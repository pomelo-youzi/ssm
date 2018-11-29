package com.itheima.service;

import com.itheima.domian.Role;
import com.itheima.domian.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<UserInfo> findAll() throws  Exception;

    void save(UserInfo userInfo) throws Exception;

    UserInfo findById(String id) throws  Exception;

    List<Role> findRoleById(String userId) throws  Exception;

    void addRoleToUser(String userId, String[] roleIds) throws  Exception;
}
