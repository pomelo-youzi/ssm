package com.itheima.service;

import com.itheima.domian.Permission;
import com.itheima.domian.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll() throws Exception;

    void save(Role role) throws  Exception;

    Role findById(String id) throws Exception;

    void deleteRoleById(String id) throws Exception;

    List<Permission> findOtherPermission(String id) throws  Exception;

    Role findByRid(String id) throws  Exception;

    void addPermissionToRole(String roleId, String[] permissionIds) throws  Exception;
}
