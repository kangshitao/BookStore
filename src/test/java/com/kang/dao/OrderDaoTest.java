package com.kang.dao;

import com.kang.bean.Order;
import com.kang.dao.iml.OrderDaoImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author Kangshitao
 * @date 2021年6月15日 下午3:24
 */
public class OrderDaoTest {

    OrderDaoImpl orderDao = new OrderDaoImpl();
    @Test
    public void saveOrder() {
        orderDao.saveOrder(new Order("123456789",new Date(),new BigDecimal(100),0,6));
    }

    @Test
    public void queryOrders() {
    }

    @Test
    public void changeOrderStatus() {
    }

    @Test
    public void queryOrderByUserId() {
    }
}