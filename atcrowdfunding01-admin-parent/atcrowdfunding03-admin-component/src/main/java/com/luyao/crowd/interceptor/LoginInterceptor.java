package com.luyao.crowd.interceptor;

import com.luyao.crowd.exception.AccessForbiddenException;
import com.luyao.crowd.pojo.Admin;
import com.luyao.crowd.util.CrowdCourtConst;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author yao
 * @create 2022-03-28
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取session对象
        HttpSession session = request.getSession();
        //获取admin对象
        Admin admin = (Admin) session.getAttribute(CrowdCourtConst.ATTR_NAME_LOGIN_ADMIN);
        //判断admin是否为空
        if (admin==null){
            //抛出异常
            throw new AccessForbiddenException(CrowdCourtConst.MESSAGE_ACCESS_FORBIDDEN);
        }
        //如果admin对象不为空则放行
        return true;
    }
}
