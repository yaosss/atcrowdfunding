package com.luyao.crowd.controller;

import com.github.pagehelper.PageInfo;
import com.luyao.crowd.pojo.Role;
import com.luyao.crowd.service.api.RoleService;
import com.luyao.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author yao
 * @create 2022-03-31
 */
@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;
    @ResponseBody
    @RequestMapping("/role/get/page/info")
    public ResultEntity<PageInfo<Role>> getPageInfo(@RequestParam(value = "keyword",defaultValue = "") String keyword,
                                                    @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                                    @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize){
        //获取分页数据
        PageInfo<Role> pageInfo = roleService.getPageInfo(keyword, pageNum, pageSize);
        //封装到ResultEntity对象中返回，如果上面操作抛出异常，交给异常映射机制处理（DispatcherServlet）
        return ResultEntity.successWithData(pageInfo);
    }
    //保存
    @ResponseBody
    @RequestMapping("/role/save.json")
    public ResultEntity<String> saveRole(Role role){
        roleService.saveRole(role);
        return ResultEntity.successWithoutData();
    }
    //更新
    @ResponseBody
    @RequestMapping("/role/update.json")
    public ResultEntity<String> updateRole(Role role){
        roleService.updateRole(role);
        return ResultEntity.successWithoutData();
    }
    //删除
    @ResponseBody
    @RequestMapping("/role/remove/id/array.json")
    public ResultEntity<String> removeByRoleIdArray(@RequestBody List<Integer> roleIdList) {

        roleService.removeRole(roleIdList);

        return ResultEntity.successWithoutData();
    }
}
