package com.itheima.service.impl;

import com.itheima.dao.RoleDao;
import com.itheima.domian.Permission;
import com.itheima.domian.Role;
import com.itheima.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImp implements RoleService {
    @Autowired
    private RoleDao roleDao;
    @Override
    public List<Role> findAll() throws Exception {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) throws Exception {
        roleDao.save(role);
    }

    @Override
    public Role findById(String id) throws Exception {
        return roleDao.findById(id);
    }

    /**
     * 根据id删除角色；（需删除三个表的数据，users_role,role,role_permisssion）
     * @param id
     * @throws Exception
     */
    @Override
    public void deleteRoleById(String id) throws Exception {
//        删除用户角色表；
        roleDao.deleteUser_RoleById(id);
//        删除角色权限表
        roleDao.deleteRole_Permission(id);
//        删除角色表；
        roleDao.deleteRoleById(id);
    }

    /**
     * 根据id查询出可添加的权限
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public List<Permission> findOtherPermission(String id) throws Exception {
        return roleDao.findOtherPermission(id);
    }

    @Override
    public Role findByRid(String id) throws Exception {
        return roleDao.findByRid(id);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) throws Exception {
        for (String permissionId : permissionIds) {
            roleDao.addPermissionToRole(roleId,permissionId);
        }
    }
}
