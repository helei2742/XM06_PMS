package org.xm06.pms.interceptor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.xm06.pms.exception.NotLoginException;
import org.xm06.pms.service.UserService;
import org.xm06.pms.utils.LoginUtil;
import org.xm06.pms.vo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoLoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    UserService userService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                              Object handler) throws Exception{
        Integer id = LoginUtil.releaseUserIdFromCookie(request);
        System.out.println("cookie"+"===="+id);
        User user = userService.selectByPrimaryKey(id);
        if(null == id || null == user){
            throw new NotLoginException();
        }
        return true;
    }
}
