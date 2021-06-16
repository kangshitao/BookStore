package com.kang.filter;

import com.kang.utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;
import java.rmi.RemoteException;

/**
 * @author Kangshitao
 * @date 2021年6月16日 上午11:15
 */
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        /*
            要确保所有操作要么都成功，要么都失败，就必须要使用数据库的事务。
            要确保所有操作都在同一个事务内，必须确保所有操作都使用同一个Connection连接对象。
            使用ThreadLocal确保所有操作都使用同一个Connection对象（前提必须是在同一个线程）
            ThreadLocal可以为当前线程关联一个数据，这个数据是这个线程独享的，是线程安全的。

            使用过滤器，为所有的servlet程序都设置
        */
        try {
            filterChain.doFilter(servletRequest,servletResponse);
            JdbcUtils.commitAndClose(); //提交事务
        } catch (Exception e) {
            JdbcUtils.rollbackAndClose(); //回滚事务
            e.printStackTrace();
            throw new RemoteException(); //将异常抛给Tomcat服务器，统一展示错误页面
        }
    }

    @Override
    public void destroy() {

    }
}
