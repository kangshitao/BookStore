package com.kang.web; /**
 * @author Kangshitao
 * @date 2021年6月13日 下午5:49
 */

import com.kang.bean.Book;
import com.kang.bean.Page;
import com.kang.service.BookService;
import com.kang.service.iml.BookServiceImpl;
import com.kang.utils.WebUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BookServlet", value = "/manager/bookServlet")
public class BookServlet extends BaseSevlet {
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

        page.setUrl("manager/bookServlet?action=page");
        //3.保存page对象到request域
        request.setAttribute("page",page);
        //4.请求转发到pages/manager/book_manager.jsp页面
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
    }

    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"),0);
        pageNo += 1;

        //1.获取请求的参数，封装为Book对象
        Book book = WebUtils.copyParamToBean(request.getParameterMap(),new Book());
        //2.调用BookService.addBook参数，添加图书
        bookService.addBook(book);
        //3.重定向到book_manager界面
        //不能用请求转发，因为请求转发是一次请求，如果页面刷新，会重复添加
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=page&pageNo="+pageNo);
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        bookService.deleteBookById(id);
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=page&pageNo="+request.getParameter("pageNo"));
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Book book = WebUtils.copyParamToBean(request.getParameterMap(), new Book());
        bookService.updateBook(book);
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=page&pageNo="+request.getParameter("pageNo"));
    }

    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.通过BookService查询全部图书
        List<Book> books = bookService.queryBooks();
        //2.把全部图书保存到Request域中
        request.setAttribute("books",books);
        //3.请求转发到/pages/manager/book_manager.jsp页面
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
    }

    protected void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Book book = bookService.queryBookById(id);
        request.setAttribute("book",book);
        request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request,response);
    }
}
