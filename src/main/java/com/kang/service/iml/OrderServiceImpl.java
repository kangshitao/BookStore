package com.kang.service.iml;

import com.kang.bean.*;
import com.kang.dao.BookDao;
import com.kang.dao.OrderDao;
import com.kang.dao.OrderItemDao;
import com.kang.dao.iml.BookDaoImpl;
import com.kang.dao.iml.OrderDaoImpl;
import com.kang.dao.iml.OrderItemDaoImpl;
import com.kang.service.OrderService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Kangshitao
 * @date 2021年6月15日 下午3:45
 */
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public String createOrder(Cart cart, int userId) {
        //订单号。要求唯一
        String orderId = System.currentTimeMillis()+""+userId;
        Order order = new Order(orderId,new Date(),cart.getTotalPrice(),0,userId);
        orderDao.saveOrder(order);
        //遍历购物车中每一个商品项，转为orderItem对象,保存到数据库
        for(Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()){
            CartItem cartItem = entry.getValue();
            OrderItem orderItem = new OrderItem(null,cartItem.getName(),cartItem.getCount(),
                    cartItem.getPrice(),cartItem.getTotalPrice(),orderId);
            orderItemDao.saveOrderItem(orderItem);
            //更新book的销量和库存信息
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales() + cartItem.getCount());
            book.setStock( book.getStock() - cartItem.getCount());
            bookDao.updateBook(book);
        }
        //订单生成以后，清空购物车
        cart.clear();
        return orderId;
    }

    @Override
    public List<Order> showAllOrders() {
        return null;
    }

    @Override
    public void sendOrder(int orderId) {

    }

    @Override
    public void showOrderDetail(int orderId) {

    }

    @Override
    public List<Order> showMyOrders(int userId) {
        return null;
    }

    @Override
    public void receiveOrder(int orderId) {

    }
}
