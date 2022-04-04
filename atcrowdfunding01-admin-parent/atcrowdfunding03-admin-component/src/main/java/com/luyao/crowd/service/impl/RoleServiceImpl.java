package com.luyao.crowd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.luyao.crowd.mapper.RoleMapper;
import com.luyao.crowd.pojo.Role;
import com.luyao.crowd.pojo.RoleExample;
import com.luyao.crowd.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author yao
 * @create 2022-03-31
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Transactional(readOnly = true)
    @Override
    public PageInfo<Role> getPageInfo(String keyword, Integer pageNum, Integer pageSize) {
        //开启分页并设置参数
        PageHelper.startPage(pageNum, pageSize);
        //查询数据
        RoleExample roleExample = new RoleExample();
        roleExample.createCriteria().andNameLike("%"+keyword+"%");
        List<Role> roleList = roleMapper.selectByExample(roleExample);
        PageInfo<Role> pageInfo = new PageInfo<>(roleList,5);
        return pageInfo;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    @Override
    public void saveRole(Role role) {
        roleMapper.insertSelective(role);
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    @Override
    public void updateRole(Role role) {
        roleMapper.updateByPrimaryKeySelective(role);
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    @Override
    public void removeRole(List<Integer> roleIdLList) {
        RoleExample example = new RoleExample();

        RoleExample.Criteria criteria = example.createCriteria();

        criteria.andIdIn(roleIdLList);

        roleMapper.deleteByExample(example);
    }

    @Override
    public List<Role> getAssignedRole(Integer adminId) {
        return roleMapper.selectAssignedRole(adminId);
    }

    @Override
    public List<Role> getUnAssignedRole(Integer adminId) {
        return roleMapper.selectUnAssignedRole(adminId);
    }
}
