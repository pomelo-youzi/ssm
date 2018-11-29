package com.itheima.service;

import com.itheima.domian.SysLog;

import java.util.List;

public interface SysLogService {
    void save(SysLog sysLog) throws  Exception;

    List<SysLog> findAll() throws Exception;
}
