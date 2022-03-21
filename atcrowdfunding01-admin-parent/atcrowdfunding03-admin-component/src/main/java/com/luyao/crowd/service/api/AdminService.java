package com.luyao.crowd.service.api;

import com.luyao.crowd.pojo.Admin;

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
}
