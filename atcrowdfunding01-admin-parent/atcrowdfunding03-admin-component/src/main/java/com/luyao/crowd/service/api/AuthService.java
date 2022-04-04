package com.luyao.crowd.service.api;

import com.luyao.crowd.pojo.Auth;

import java.util.List;
import java.util.Map;

/**
 * @Author yao
 * @create 2022-04-03
 */
public interface AuthService {
    List<Auth> getAll();

    /**
     * 获取已分配的权限
     * @param roleId
     * @return
     */
    List<Integer> getAssignedAuthIdByRoleId(Integer roleId);

    /**
     * 更新权限
     * @param map
     */
    void saveRoleAuthRelationship(Map<String, List<Integer>> map);
}
