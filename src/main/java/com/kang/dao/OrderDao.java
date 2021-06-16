package com.kang.dao;

import com.kang.bean.Order;

import java.util.List;

/**
 * @author Kangshitao
 * @date 2021年6月15日 下午3:08
 */
public interface OrderDao {
    /**
     * 保存订单
     * @param order
     * @return
     */
    public int saveOrder(Order order);

    /**
     * 查询全部订单
     * @return
     */
    List<Order> queryOrders();

    /**
     * 修改订单状态
     * @param orderId
     * @param status 0未发货 1已发货 2已签收
     * @return true表示修改成功，false修改失败
     */
    boolean changeOrderStatus(int orderId,int status);

    /**
     * 根据用户id查询订单
     * @param userId
     * @return
     */
    List<Order> queryOrderByUserId(int userId);
}
