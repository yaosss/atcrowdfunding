package com.luyao.crowd.service.impl;

import com.luyao.crowd.mapper.AdminMapper;
import com.luyao.crowd.pojo.Admin;
import com.luyao.crowd.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author yao
 * @create 2022-03-20
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)//事务注解增删改
    @Override
    public void saveAdmin(Admin admin) {
        adminMapper.insert(admin);
    }
    @Transactional(readOnly = true)//事务注解查询
    @Override
    public Admin queryById(Integer id) {
        return adminMapper.selectByPrimaryKey(id);
    }

}
