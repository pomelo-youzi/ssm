package com.itheima.dao;

import com.itheima.domian.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysLogDao {
    /**
     *将日志文件保存到数据库
     * @param sysLog
     * @throws Exception
     */
    @Insert("insert into syslog(visitTime,username,ip,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void save(SysLog sysLog) throws Exception;

    /**
     * 查询出所有日志信息；
     * @return
     */
    @Select("select * from sysLog")
    List<SysLog> findAll();
}
