package com.luyao.crowd.controller;

import com.luyao.crowd.pojo.Role;
import com.luyao.crowd.service.api.AdminService;
import com.luyao.crowd.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author yao
 * @create 2022-04-03
 */
@Controller
public class AssignController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;


    @RequestMapping("/assign/to/assign/role/page")
    public String toAssignRolePage(@RequestParam("adminId") Integer adminId,
                                   @RequestParam("pageNum") Integer pageNum,
                                   @RequestParam("keyword") String keyword,
                                   Model model) {

        // 1.查询已分配的角色
        List<Role> assignedRoleList = roleService.getAssignedRole(adminId);

        // 2. 查询未分配的角色
        List<Role> unAssignedRoleList = roleService.getUnAssignedRole(adminId);

        // 3.存入模型
        model.addAttribute("assignedRoleList", assignedRoleList);
        model.addAttribute("unAssignedRoleList", unAssignedRoleList);
        model.addAttribute("adminId",adminId);
        model.addAttribute("pageNum",pageNum);
        model.addAttribute("keyword",keyword);
        return "assign-role";
    }
    @RequestMapping("/assign/do/role/assign")
    public String saveAdminRoleRelationship(@RequestParam("adminId") Integer adminId,
                                            @RequestParam("pageNum") Integer pageNum,
                                            @RequestParam("keyword") String keyword,
                                            //允许用户在页面上取消所有角色，故该参数可以不提交
                                            @RequestParam(value = "roleIdList", required = false) List<Integer> roleIdList)  {

        adminService.saveAdminRoleRelationship(adminId, roleIdList);

        return "redirect:/admin/get/page?pageNum="+pageNum+"&keyword="+keyword;
    }
}

