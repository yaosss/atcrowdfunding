package com.luyao.crowd.service.api;

import com.github.pagehelper.PageInfo;
import com.luyao.crowd.pojo.Admin;

import java.util.List;

/**
 * @Author yao
 * @create 2022-03-20
 */
public interface AdminService {
    /**
     * 保存用户
     * @param admin
     */
    void saveAdmin(Admin admin);

    /**
     * 增加用户
     * @param id
     * @return
     */
    Admin queryById(Integer id);

    /**
     * 用户登陆查询
     * @param loginAcct
     * @param userPswd
     * @return
     */
    Admin getAdminByLoginAcct(String loginAcct,String userPswd);

    /**
     * 分页查询
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Admin> getPageInfo(String keyword,Integer pageNum,Integer pageSize);

    /**
     * 删除
     * @param adminId
     */
    void remove(Integer adminId);

    /**
     * 修改
     * @param admin
     */
    void edit(Admin admin);

    /**
     * 分配角色
     * @param adminId
     * @param roleIdList
     */
    void saveAdminRoleRelationship(Integer adminId, List<Integer> roleIdList);
}
