package com.az.cart.controller;

import com.az.cart.service.CartService;
import com.jt.common.pojo.Cart;
import com.jt.common.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//购物车的增删改查
@RestController
@RequestMapping("/cart/manage")
public class CartController {
    @Autowired
    private CartService cartService;
    //根据userid查询购物车list
    @RequestMapping("query")
    public List<Cart> queryMyCarts(String userId){
        return cartService.queryMyCarts(userId);
    }
    //新增商品
    @RequestMapping("save")
    public SysResult addCart(Cart cart){
        try {
            cartService.addCart(cart);
            return SysResult.ok();
        }catch (Exception e){
            e.printStackTrace();
            return SysResult.build(201,"新增购物车失败",null);
        }
    }
    @RequestMapping("query")
    public SysResult updateNum(Cart cart){
        try{
            cartService.updateNum(cart);
            return SysResult.ok();
        }catch (Exception e){
            e.printStackTrace();
            return SysResult.build(201,"更新num失败",null);
        }
    }
    //删除购物车商品数据
    @RequestMapping("delete")
    public SysResult deleteCart(Cart cart){
        //cart中只包含2个数据 userId productId
        try{
            cartService.deleteCart(cart);
            return SysResult.ok();
        }catch (Exception e){
            e.printStackTrace();
            return SysResult.build(201,"删除购物车失败",null);
        }
    }
}
