package com.itheima.service;

import com.itheima.domian.Permission;

import java.util.List;

public interface PermissionService {
    public List<Permission> findAll() throws  Exception;

    void save(Permission permission) throws  Exception;

    Permission findById(String id) throws Exception;

    void deletePermission(String id) throws  Exception;
}
