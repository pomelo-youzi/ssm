package com.itheima.service.impl;

import com.itheima.dao.SysLogDao;
import com.itheima.domian.SysLog;
import com.itheima.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysLogServiceImp implements SysLogService {
    @Autowired
    private SysLogDao sysLogDao;

    @Override
    public void save(SysLog sysLog) throws Exception {
              sysLogDao.save(sysLog);
    }

    @Override
    public List<SysLog> findAll() throws Exception {
        return sysLogDao.findAll();
    }
}
