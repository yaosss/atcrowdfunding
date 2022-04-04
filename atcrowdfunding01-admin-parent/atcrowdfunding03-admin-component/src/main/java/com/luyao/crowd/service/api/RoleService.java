package com.luyao.crowd.service.api;

import com.github.pagehelper.PageInfo;
import com.luyao.crowd.pojo.Role;

import java.util.List;

/**
 * @Author yao
 * @create 2022-03-31
 */
public interface RoleService {
    /**
     * 分页查询
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Role> getPageInfo(String keyword, Integer pageNum, Integer pageSize);

    /**
     * 保存角色
     * @param role
     */
    void saveRole(Role role);

    /**
     * 更新角色
     * @param role
     */
    void updateRole(Role role);

    /**
     * 删除
     * @param roleIdLList
     */
    void removeRole(List<Integer> roleIdLList);

    /**
     * 获取已分配角色
     * @param adminId
     * @return
     */
    List<Role> getAssignedRole(Integer adminId);

    /**
     * 获取未分配角色
     * @param adminId
     * @return
     */
    List<Role> getUnAssignedRole(Integer adminId);
}
