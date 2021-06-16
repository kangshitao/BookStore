package com.kang.dao;

import com.kang.bean.OrderItem;
import com.kang.dao.iml.OrderItemDaoImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author Kangshitao
 * @date 2021年6月15日 下午3:29
 */
public class OrderItemDaoTest {
    OrderItemDao orderItemDao = new OrderItemDaoImpl();

    @Test
    public void saveOrderItem() {
        orderItemDao.saveOrderItem(new OrderItem(null,"数据结构",1,new BigDecimal(200),new BigDecimal(200),"123456789"));
    }

    @Test
    public void queryOrderItemsByOrderId() {
    }
}