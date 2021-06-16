package com.kang.dao;

import com.kang.bean.OrderItem;

import java.util.List;

/**
 * @author Kangshitao
 * @date 2021年6月15日 下午3:15
 */
public interface OrderItemDao {
    /**
     * 保存订单
     * @param orderItem
     */
    int saveOrderItem(OrderItem orderItem);

    /**
     * 根据订单号查询订单明细
     * @param orderId
     * @return
     */
    List<OrderItem> queryOrderItemsByOrderId(int orderId);
}
