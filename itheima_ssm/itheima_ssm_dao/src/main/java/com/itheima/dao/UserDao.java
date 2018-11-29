package com.itheima.dao;

import com.itheima.domian.Role;
import com.itheima.domian.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserDao {
    /**
     * 根据用户名查询所有用户（涉及多张表，首先根据用户名查询出userinfo信息，（一个用户名对应一个用户）
     * 还需要根据id查询出用户所有角色信息，一个用户对应多个角色。需要通过中间表去查询。）
     * @param username
     * @return
     * @throws Exception
     */
    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "username",property = "username"),
            @Result(column = "email",property = "email"),
            @Result(column = "password",property = "password"),
            @Result(column = "phoneNum",property = "phoneNum"),
            @Result(column = "status",property = "status"),
            @Result(column = "statusStr",property = "statusStr"),
            @Result(column = "id",property = "roles",javaType =java.util.List.class,many = @Many(select="com.itheima.dao.RoleDao.findRoleByUserId")),
    })
    public UserInfo findByUserName(String username) throws  Exception;

    /**
     * 查询所有用户信息；
     * @return
     */
    @Select("select * from users")
    List<UserInfo> findAllUser() throws Exception;

    /**
     * 添加用户的方法
     * @param userInfo
     */
    @Insert("insert into users (email,username,password,phoneNum,status)  values (#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo) throws  Exception;

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    @Select("select * from users where id =#{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "username",property = "username"),
            @Result(column = "email",property = "email"),
            @Result(column = "password",property = "password"),
            @Result(column = "phoneNum",property = "phoneNum"),
            @Result(column = "status",property = "status"),
            @Result(column = "id",property = "roles",javaType =java.util.List.class,many = @Many(select="com.itheima.dao.RoleDao.findRoleByUserId")),
    })
    UserInfo findById(String id) throws  Exception;

    /**
     * 根据userid查询角色
     * @param userId
     * @return
     */
    @Select("select * from role where id not in (select roleId from users_role where userId=#{userId}) ")
    List<Role> findRoleById(String userId);

    /**
     * 给用户添加角色
     * @param userId
     * @param roleId
     */
    @Insert("insert into users_role  (userId,roleId)  values (#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId);
}
