package com.luyao.crowd.controller;

import com.luyao.crowd.pojo.Auth;
import com.luyao.crowd.service.api.AuthService;
import com.luyao.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @Author yao
 * @create 2022-04-03
 */
@Controller
public class AuthController {
    @Autowired
    private AuthService authService;
    @ResponseBody
    @RequestMapping("/assign/get/all/auth")
    public ResultEntity<List<Auth>> getAllAuth() {

        List<Auth> authList = authService.getAll();
        return ResultEntity.successWithData(authList);
    }
    @ResponseBody
    @RequestMapping("/assign/get/assigned/auth/id")
    public ResultEntity<List<Integer>> getAssignAuthIdByRoleId(@RequestParam("roleId") Integer roleId) {

        List<Integer> authIdList = authService.getAssignedAuthIdByRoleId(roleId);
        return ResultEntity.successWithData(authIdList);
    }
    @ResponseBody
    @RequestMapping("/assign/do/role/assign/auth")
    public ResultEntity<String>  saveRoleAuthRelathinship(@RequestBody Map<String, List<Integer>> map) {

        authService.saveRoleAuthRelationship(map);
        return ResultEntity.successWithoutData();
    }
}
