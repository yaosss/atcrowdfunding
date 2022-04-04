package com.luyao.crowd.controller;

import com.github.pagehelper.PageInfo;
import com.luyao.crowd.exception.LoginFailedException;
import com.luyao.crowd.pojo.Admin;
import com.luyao.crowd.service.api.AdminService;
import com.luyao.crowd.util.CrowdCourtConst;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * @Author yao
 * @create 2022-03-21
 */
@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;
    //登陆验证
    @RequestMapping("/admin/to/login")
    public String adminLogin(@Param("loginAcct") String loginAcct,
                             @Param("userPswd") String userPswd,
                             HttpSession session){
        //验证登录账号，如果返回admin对象则登陆成功，若为null则抛出登陆异常
        Admin adminByLoginAcct = adminService.getAdminByLoginAcct(loginAcct, userPswd);
        //将登陆成功的账号放入session中
        session.setAttribute(CrowdCourtConst.ATTR_NAME_LOGIN_ADMIN, adminByLoginAcct);
        return "redirect:/admin/to/main/page";//避免跳转到后台主页面在刷新浏览器导致重复提交浏览器表单，采用重定向。
    }
    //登陆退出
    @RequestMapping("/damin/do/logout")
    public String adminLogOut(HttpSession session){
        session.invalidate();
        //返回登陆页面
        return "redirect:/admin/to/login/page";
    }
    //查询分页
    @RequestMapping("/admin/get/page")
    public String getPageInfo(@RequestParam(value = "keyword",defaultValue = "") String keyword,
                              @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                              @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                              Model model){
        PageInfo<Admin> pageInfo = adminService.getPageInfo(keyword, pageNum, pageSize);
        model.addAttribute(CrowdCourtConst.ATTR_NAME_PAGE_INFO,pageInfo);
        model.addAttribute("keyword",keyword);
        return "admin-page";
    }
    //删除记录
    @RequestMapping("/admin/remove")
    public String remove(@RequestParam("adminId") Integer adminId ,
                         @RequestParam("pageNum")Integer pageNum,
                         @RequestParam("keyword")String keyword,
                         HttpSession session){
        //检查是否是当前账号
        Admin attribute = (Admin) session.getAttribute(CrowdCourtConst.ATTR_NAME_LOGIN_ADMIN);
        if (attribute.getId()==adminId){
            throw new LoginFailedException(CrowdCourtConst.MESSAGE_REMOVE_USER_ACCT);
        }
        adminService.remove(adminId);

        return "redirect:/admin/get/page?pageNum="+pageNum+"&keyword="+keyword;
    }
    //保存新增
    @RequestMapping("/admin/save")
    public String save(Admin admin){

        adminService.saveAdmin(admin);
        return "redirect:/admin/get/page?pageNum="+Integer.MAX_VALUE;
    }
    @RequestMapping("/admin/to/edit/page")
    public String editPage(@RequestParam("adminId")Integer adminId,
                           @RequestParam("pageNum") Integer pageNum,
                           @RequestParam("keyword") String keyword,
                           Model model){
        Admin admin = adminService.queryById(adminId);
        model.addAttribute(admin);
        model.addAttribute("pageNum",pageNum);
        model.addAttribute("keyword",keyword);
        return "admin-edit";
    }
    @RequestMapping("/admin/edit")
    public String edit(Admin admin,
                       @RequestParam("pageNum") Integer pageNum,
                       @RequestParam("keyword") String keyword){
        adminService.edit(admin);
        return "redirect:/admin/get/page?pageNum="+pageNum+"&keyword="+keyword;
    }
}
