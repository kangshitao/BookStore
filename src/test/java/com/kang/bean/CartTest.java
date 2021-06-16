package com.kang.bean;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author Kangshitao
 * @date 2021年6月14日 下午10:30
 */
public class CartTest {
    Cart cart = new Cart();
    @Test
    public void addItem() {
        cart.addItem(new CartItem(1,"Java",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"Java",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"C++",1,new BigDecimal(100),new BigDecimal(100)));
        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
        cart.addItem(new CartItem(1,"Java",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"Java",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"C++",1,new BigDecimal(100),new BigDecimal(100)));
        cart.deleteItem(1);
        System.out.println(cart);
    }

    @Test
    public void clear() {
        cart.addItem(new CartItem(1,"Java",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"Java",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"C++",1,new BigDecimal(100),new BigDecimal(100)));
        cart.clear();
        System.out.println(cart);
    }

    @Test
    public void updateCount() {
        cart.addItem(new CartItem(1,"Java",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"Java",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"C++",1,new BigDecimal(100),new BigDecimal(100)));
        cart.updateCount(1,5);
        System.out.println(cart);
    }
}