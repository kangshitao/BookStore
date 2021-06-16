package com.kang.web;

import com.kang.bean.Book;
import com.kang.bean.Page;
import com.kang.service.BookService;
import com.kang.service.iml.BookServiceImpl;
import com.kang.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Kangshitao
 * @date 2021年6月14日 下午4:27
 */
@WebServlet(name = "ClientBookServlet", value = "/client/bookServlet")
public class ClientBookServlet extends BaseSevlet{
    private BookService bookService = new BookServiceImpl();

    /**
     * 处理分页功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求的参数 pageNo和pageSize
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        //2.调用BookService.page(pageNo,pageSize)：page对象
        Page<Book> page = bookService.page(pageNo,pageSize);
        page.setUrl("client/bookServlet?action=page");
        //3.保存page对象到request域
        request.setAttribute("page",page);
        //4.请求转发
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request,response);
    }

    protected void pageByPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.parseInt(request.getParameter("min"), 0);
        int max = WebUtils.parseInt(request.getParameter("max"), Integer.MAX_VALUE);
        Page<Book> page = bookService.pageByPrice(pageNo,pageSize,min,max);

        StringBuilder sb = new StringBuilder("client/bookServlet?action=pageByPrice");
        //如果有价格，则将其添加到分页条参数中
        if(request.getParameter("min")!=null){
            sb.append("&min=").append(request.getParameter("min"));
        }

        if(request.getParameter("max")!=null){
            sb.append("&max=").append(request.getParameter("max"));
        }
        page.setUrl(sb.toString());

        request.setAttribute("page",page);

        request.getRequestDispatcher("/pages/client/index.jsp").forward(request,response);
    }
}
