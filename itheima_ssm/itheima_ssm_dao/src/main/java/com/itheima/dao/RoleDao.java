package com.itheima.dao;

import com.itheima.domian.Permission;
import com.itheima.domian.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface RoleDao {
    //    根据用户的id查询出所有的角色
    @Select("select * from role where id in(select roleId from users_role where userId=#{userId})")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "roleName", property = "roleName"),
            @Result(column = "roleDesc", property = "roleDesc"),
            @Result(column = "id", property = "permissions", javaType = java.util.List.class, many = @Many(select = "com.itheima.dao.PermissionDao.findByRoleId")),
    })
    public List<Role> findRoleByUserId(String userId) throws Exception;

    /**
     * 查询所有角色信息
     *
     * @return
     */
    @Select("select * from role")
    List<Role> findAll() throws Exception;

    /**
     * 添加角色
     *
     * @param role
     */
    @Insert("insert into role (roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role) throws Exception;

    /**
     * 根据id查询角色
     *
     * @param id
     * @return
     */
    @Select("select * from role where id=#{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id", javaType = java.util.List.class, many = @Many(select = "com.itheima.dao.PermissionDao.findByRoleId"))
    })
    Role findById(String id);

    /**
     * 根据roleid删除用户角色表的角色信息
     *
     * @param roleId
     */
    @Delete("delete from users_role where roleId=#{roleId}")
    void deleteUser_RoleById(@RequestParam(name = "id", required = true) String roleId);

    /**
     * 根据roleId删除角色权限表的角色信息
     *
     * @param roleId
     */
    @Delete("delete from role_permission where roleId=#{roleId}")
    void deleteRole_Permission(@RequestParam(name = "    id", required = true) String roleId);

    /**
     * 根据id删除角色表的角色信息
     *
     * @param id
     */
    @Delete("delete from role where id=#{id}")
    void deleteRoleById(String id);

    /**
     * 根据id查询出可添加的权限
     *
     * @param id
     * @return
     */
    @Select("select * from permission where id not in(select permissionId from role_permission where roleId=#{id})")
    List<Permission> findOtherPermission(String id);

    @Select("select * from role where id=#{id}")
    Role findByRid(String id);

    /**
     * 给角色添加权限
     *
     * @param roleId
     * @param permissionId
     */
    @Insert("insert into role_permission(roleId,permissionId) values (#{roleId},#{permissionId})")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId);
}
