package com.luyao.crowd;

import com.luyao.crowd.mapper.AdminMapper;
import com.luyao.crowd.mapper.RoleMapper;
import com.luyao.crowd.pojo.Admin;
import com.luyao.crowd.pojo.Role;
import com.luyao.crowd.service.api.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Author yao
 * @create 2022-03-19
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringJUnitConfig(locations = "classpath:spring-persist-mybatis.xml")
public class test {
    @Autowired
    private DataSource dataSource;

    @Test
    public void  testConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }
    @Autowired
    private AdminMapper adminMapper;

    @Test
    public void testInsertAdmin(){
        Admin admin;
        admin = new Admin(null,"Rachel","123123","rui","rui@qq.com",null);
        int count = adminMapper.insert(admin);
        System.out.println(count);
    }
    @Autowired
    private AdminService adminService;
    @Test
    public void testInsert(){
        Admin admin;
        admin = new Admin(null,"Rachel","123123","rui","rui@qq.com",null);
        adminService.saveAdmin(admin);
    }
    @Autowired
    private RoleMapper roleMapper;
    @Test
    public void addRole(){
        for (int i = 0; i < 25; i++) {
            roleMapper.insertSelective(new Role(null,"role"+i));
        }
    }
}
