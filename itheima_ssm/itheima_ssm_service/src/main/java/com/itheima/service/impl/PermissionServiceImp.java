package com.itheima.service.impl;

import com.itheima.dao.PermissionDao;
import com.itheima.domian.Permission;
import com.itheima.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionServiceImp implements PermissionService {
    @Autowired
    private PermissionDao permissionDao;

    @Override
    public List<Permission> findAll() throws Exception {
        return permissionDao.findAll();
    }

    @Override
    public void save(Permission permission) throws  Exception {
        permissionDao.save(permission);
    }

    @Override
    public Permission findById(String id) throws Exception {
        return permissionDao.findById(id);
    }

    @Override
    public void deletePermission(String id) throws Exception {
//        删除角色权限表权限信息
        permissionDao.deleteRole_Permission(id);
//        删除权限表信息
        permissionDao.deletePermission(id);
    }
}
