package com.kang.dao.iml;

import com.kang.bean.Order;
import com.kang.dao.OrderDao;

import java.util.List;

/**
 * @author Kangshitao
 * @date 2021年6月15日 下午3:18
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) values(?,?,?,?,?)";
        return update(sql, order.getOrderId(), order.getCreateTime(), order.getPrice(), order.getStatus(), order.getUserId());
    }

    @Override
    public List<Order> queryOrders() {
        return null;
    }

    @Override
    public boolean changeOrderStatus(int orderId, int status) {
        return false;
    }

    @Override
    public List<Order> queryOrderByUserId(int userId) {
        return null;
    }
}
