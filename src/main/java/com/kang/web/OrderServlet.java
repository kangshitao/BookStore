package com.kang.web; /**
 * @author Kangshitao
 * @date 2021年6月15日 下午3:56
 */

import com.kang.bean.Cart;
import com.kang.bean.Order;
import com.kang.bean.User;
import com.kang.service.OrderService;
import com.kang.service.iml.OrderServiceImpl;
import com.kang.utils.JdbcUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderServlet", value = "/orderServlet")
public class OrderServlet extends BaseSevlet {
    OrderService orderService = new OrderServiceImpl();

    /**
     * 生成订单
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        if(cart.getItems().isEmpty()){ //空购物车，则重定向到购物车界面
            response.sendRedirect(request.getContextPath()+"/pages/cart/cart.jsp");
            return;
        }
        User user = (User)request.getSession().getAttribute("user");
        //如果用户未登录，请求登陆
        if(user==null){
            request.getRequestDispatcher("pages/user/login.jsp").forward(request,response);
        }else {
            String orderId = orderService.createOrder(cart, user.getId());
            request.getSession().setAttribute("orderId",orderId);
            //重定向，防止重复提交
            response.sendRedirect(request.getContextPath()+"/pages/cart/checkout.jsp");
        }
    }
    /**
     * 查询当前系统的所有订单（仅限管理员）
     * @return
     */
    protected List<Order> showAllOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return null;
    }

    /**
     * 对订单发货（管理员）
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void sendOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    /**
     * 查看指定订单详情
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void showOrderDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * 查看用户订单
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    protected List<Order> showMyOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return null;
    }

    /**
     * 签收订单（用户）
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void receiveOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
