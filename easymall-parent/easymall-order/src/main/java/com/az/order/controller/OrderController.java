package com.az.order.controller;

import com.az.order.service.OrderService;
import com.jt.common.pojo.Order;
import com.jt.common.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
//订单的添加 查询删除
@RestController
@RequestMapping("order/manage")
public class OrderController {
    //新增订单数据
    @Autowired
    private OrderService orderService;
    @RequestMapping("save")
    public SysResult addOrder(Order order){
        try {
            orderService.addOrder(order);
            return SysResult.ok();
        }catch (Exception e){
            e.printStackTrace();
            return SysResult.build(201,"新增失败",null);
        }
    }
    @RequestMapping("query/{userId}")
    public List<Order> queryMyOrders(@PathVariable String userId){
        return orderService.queryMyOrders(userId);
    }
    @RequestMapping("delete/{orderId}")
    public SysResult delteOrder(@PathVariable String orderId){
        try {
            orderService.deleteOrder(orderId);
            return SysResult.ok();
        }catch (Exception e){
            e.printStackTrace();
            return SysResult.build(201,"删除失败",null);
        }
    }
}
