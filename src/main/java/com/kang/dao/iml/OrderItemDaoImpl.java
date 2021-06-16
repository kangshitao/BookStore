package com.kang.dao.iml;

import com.kang.bean.OrderItem;
import com.kang.dao.OrderItemDao;

import java.util.List;

/**
 * @author Kangshitao
 * @date 2021年6月15日 下午3:19
 */
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`,`count`, `price`,`total_price`, `order_id`) values(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),
                orderItem.getTotalPrice(),orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> queryOrderItemsByOrderId(int orderId) {
        return null;
    }
}
