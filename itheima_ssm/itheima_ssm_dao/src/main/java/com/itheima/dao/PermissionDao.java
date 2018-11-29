package com.itheima.dao;

import com.itheima.domian.Permission;
import com.itheima.domian.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface PermissionDao {
    /**
     * 根据roleid查询所有权限
     * @param RoleId
     * @return
     * @throws Exception
     */
    @Select("select * from permission where id in(select permissionId  from role_permission WHERE  roleId =#{RoleId})")
    public List<Permission> findByRoleId(String RoleId) throws Exception;

    /**
     * 查询所有权限
     * @return
     * @throws Exception
     */
    @Select("select * from permission")
    List<Permission> findAll() throws Exception;

    /**
     * 添加权限
     * @param permission
     */
    @Insert("insert into permission (permissionName,url) values(#{permissionName},#{url})")
    void save(Permission permission);
@Select("select * from permission where id =#{id}")
    Permission findById(String id);

    /**
     *  删除角色权限表权限信息
     * @param permissionId
     * @throws Exception
     */
    @Delete("delete from role_permission where permissionid=#{permissionId}")
    void deleteRole_Permission(@RequestParam(name = "id",required = true) String permissionId) throws  Exception;

    /**
     * 删除权限表权限信息
     * @param id
     */
    @Delete("delete from permission where id=#{id}")
    void deletePermission(String id);
}
