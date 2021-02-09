package com.pro.mxpro.commons;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter implements SessionNames {

    private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);
    
    @Override
    public boolean preHandle(HttpServletRequest request, 
    	HttpServletResponse response,
        Object handler) throws Exception {
        HttpSession session = request.getSession();
        
        if(session.getAttribute(LOGIN) == null) {
            logger.info("current user is not logined");
            // �α������� ���� ������� ��� �α��� �������� �̵�
            response.sendRedirect("/login");
            return false;
        }
        // �α����� ������� ��� Controller ȣ��
        return true;
    }
}