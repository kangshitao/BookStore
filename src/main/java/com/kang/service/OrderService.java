package com.kang.service;

import com.kang.bean.Cart;
import com.kang.bean.Order;

import java.util.List;

/**
 * @author Kangshitao
 * @date 2021年6月15日 下午3:34
 */
public interface OrderService {
    /**
     * 为指定的用户生成订单
     * @param cart
     * @param userId
     * @return 返回生成的订单号
     */
    public String createOrder(Cart cart,int userId);

    /**
     * 查询当前系统的所有订单（仅限管理员）
     * @return
     */
    List<Order> showAllOrders();

    /**
     * 对订单发货（管理员）
     * @param orderId
     */
    void sendOrder(int orderId);

    /**
     * 查看指定订单详情
     * @param orderId
     */
    void showOrderDetail(int orderId);

    /**
     * 查看用户订单
     * @param userId
     * @return
     */
    List<Order> showMyOrders(int userId);

    /**
     * 签收订单（用户）
     * @param orderId
     */
    void receiveOrder(int orderId);
}
