package com.kang.bean;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Kangshitao
 * @date 2021年6月14日 下午10:15
 */
public class Cart {
   // private Integer totalCount;
//    private BigDecimal totalPrice;
    private Map<Integer,CartItem> items = new LinkedHashMap<>();

    public Cart() {
    }

    public Cart( Map<Integer,CartItem> items) {
        this.items = items;
    }

    public Integer getTotalCount() {
        Integer totalCount = 0;
        for(Map.Entry<Integer,CartItem> entry: items.entrySet()){
            totalCount += entry.getValue().getCount();
        }
        return totalCount;
    }


    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for(Map.Entry<Integer,CartItem> entry: items.entrySet()){
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }


    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }


    /**
     * 添加一个商品项
     * @param cartItem
     */
    public void addItem(CartItem cartItem){
        //如果购物车中已存在此商品，则修改数量和金额,否则直接添加
        CartItem item = items.get(cartItem.getId());
        if(item == null){
            items.put(cartItem.getId(),cartItem);
        }else{
            item.setCount(item.getCount()+1); //数量累加
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount()))); //更新金额
        }
    }

    /**
     * 删除商品项
     * @param id
     */
    public void deleteItem(Integer id){
        items.remove(id);
    }

    /**
     * 清空购物车
     */
    public void clear(){
        items.clear();
    }

    /**
     * 修改商品数量
     * @param id
     * @param count
     */
    public void updateCount(Integer id,Integer count){
        CartItem item = items.get(id);
        if(item != null){
            item.setCount(count); //数量累加
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount()))); //更新金额
        }
    }
}
