package com.luyao.crowd.controller;

import com.luyao.crowd.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author yao
 * @create 2022-03-21
 */
@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;
    @RequestMapping
    public String mainPage(){
        return "main";
    }
}
