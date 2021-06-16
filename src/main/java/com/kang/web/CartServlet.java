package com.kang.web; /**
 * @author Kangshitao
 * @date 2021年6月14日 下午10:49
 */

import com.google.gson.Gson;
import com.kang.bean.Book;
import com.kang.bean.Cart;
import com.kang.bean.CartItem;
import com.kang.service.BookService;
import com.kang.service.iml.BookServiceImpl;
import com.kang.utils.WebUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "CartServlet", value = "/cartServlet")
public class CartServlet extends BaseSevlet {
    private BookService bookService = new BookServiceImpl();

    /**
     * 添加一个指定商品到购物车
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = WebUtils.parseInt(request.getParameter("id"),0); //获取图书编号
        //获取图书信息
        Book book = bookService.queryBookById(id);
        //将book对象转化为CartItem对象
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        //添加到购物车.只有session中没有cart对象时才会创建新Cart对象
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        if(cart == null){
            cart = new Cart();
            request.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);
        //最后一个添加的商品名称
        request.getSession().setAttribute("lastName",cartItem.getName());
        //重定向到原来商品所在的页面
        response.sendRedirect(request.getHeader("Referer"));
    }

    /**
     * 使用ajax请求实现添加购物车的功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxAddItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = WebUtils.parseInt(request.getParameter("id"),0); //获取图书编号
        //获取图书信息
        Book book = bookService.queryBookById(id);
        //将book对象转化为CartItem对象
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        //添加到购物车.只有session中没有cart对象时才会创建新Cart对象
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        if(cart == null){
            cart = new Cart();
            request.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);
        //最后一个添加的商品名称
        request.getSession().setAttribute("lastName",cartItem.getName());

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("totalCount",cart.getTotalCount());
        resultMap.put("lastName",cartItem.getName());
        String gson = new Gson().toJson(resultMap);
        response.getWriter().write(gson);
    }

    /**
     * 从购物车中删除某件商品
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = WebUtils.parseInt(request.getParameter("id"),0); //获取图书编号
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        if(cart != null){
            cart.deleteItem(id);
            //重定向到原来商品所在的页面
            response.sendRedirect(request.getHeader("Referer"));
        }
    }

    /**
     * 清空购物车
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        if(cart != null){
            cart.clear();
            response.sendRedirect(request.getHeader("Referer"));
        }
    }

    /**
     * 修改购物车商品数量
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = WebUtils.parseInt(request.getParameter("id"),0);
        int count = WebUtils.parseInt(request.getParameter("count"),1);
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        if(cart != null){
            cart.updateCount(id,count);
            response.sendRedirect(request.getHeader("Referer"));
        }
    }

}
