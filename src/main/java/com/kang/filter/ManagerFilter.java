package com.kang.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Kangshitao
 * @date 2021年6月15日 下午9:36
 */
public class ManagerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Object user = request.getSession().getAttribute("user");
        if(user==null){ //如果用户未登录，则请求转发到登陆界面
            request.getRequestDispatcher("/pages/user/login.jsp").forward(servletRequest,servletResponse);
        }else{ //只有登陆以后才放行
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
