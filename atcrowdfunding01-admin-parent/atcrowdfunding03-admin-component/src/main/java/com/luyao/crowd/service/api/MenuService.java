package com.luyao.crowd.service.api;

import com.luyao.crowd.pojo.Menu;

import java.util.List;

/**
 * @Author yao
 * @create 2022-04-03
 */
public interface MenuService {
    /**
     * 获取所有节点
     * @return
     */
    List<Menu> getAll();

    /**
     * 添加新的节点
     * @param menu
     */
    void saveMenu(Menu menu);

    /**
     * 更新节点
     * @param menu
     */
    void updateMenu(Menu menu);

    /**
     * 删除节点
     * @param id
     */
    void removeMenu(Integer id);
}
