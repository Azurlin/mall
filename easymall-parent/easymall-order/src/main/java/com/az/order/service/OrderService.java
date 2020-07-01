package com.az.order.service;

import com.az.order.mapper.OrderMapper;
import com.jt.common.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    //注入mapper对象
    @Autowired(required = false)
    private OrderMapper orderMapper;
    public void addOrder(Order order){
        String orderId = UUID.randomUUID().toString();
        int payState=0;
        Date createTime = new Date();
        order.setOrderId(orderId);
        order.setOrderPaystate(payState);
        order.setOrderTime(createTime);
        orderMapper.insertOrder(order);
    }
    //查询
    public List<Order> queryMyOrders(String userId){
        return orderMapper.selectOrderByUserid(userId);
    }
    public void deleteOrder(String orderId){
        orderMapper.deleteOrderById(orderId);
    }
}
