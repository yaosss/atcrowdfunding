package com.luyao.crowd.service.impl;

import com.luyao.crowd.mapper.AuthMapper;
import com.luyao.crowd.pojo.Auth;
import com.luyao.crowd.service.api.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Author yao
 * @create 2022-04-03
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthMapper authMapper;
    @Transactional(readOnly = true)
    @Override
    public List<Auth> getAll() {
        return authMapper.selectByExample(null);
    }
    @Transactional(readOnly = true)
    @Override
    public List<Integer> getAssignedAuthIdByRoleId(Integer roleId) {
        return authMapper.selectAssignedAuthIdByRoleId(roleId);
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    @Override
    public void saveRoleAuthRelationship(Map<String, List<Integer>> map) {

        // 1.获取roleId的值
        List<Integer> roleIdList = map.get("roleId");
        Integer roleId = roleIdList.get(0);

        // 2. 删除旧的关联数据
        authMapper.deleteOldRelationship(roleId);

        // 3.获取 authIdList
        List<Integer> authIdList = map.get("authIdArray");

        // 4.判断 authIdList是否有效
        if (authIdList != null && authIdList.size()>0) {
            authMapper.insertNewRelationship(roleId,authIdList);
        }
    }
}
