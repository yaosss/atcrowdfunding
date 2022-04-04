package com.luyao.crowd.service.impl;

import com.luyao.crowd.mapper.MenuMapper;
import com.luyao.crowd.pojo.Menu;
import com.luyao.crowd.pojo.MenuExample;
import com.luyao.crowd.service.api.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author yao
 * @create 2022-04-03
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Transactional(readOnly = true)//事务注解查询
    @Override
    public List<Menu> getAll() {
        List<Menu> menuList = menuMapper.selectByExample(new MenuExample());
        return menuList;
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    @Override
    public void saveMenu(Menu menu) {
        menuMapper.insert(menu);
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    @Override
    public void updateMenu(Menu menu) {
        menuMapper.updateByPrimaryKeySelective(menu);
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    @Override
    public void removeMenu(Integer id) {
        menuMapper.deleteByPrimaryKey(id);
    }
}
