package com.az.cart.service;

import com.az.cart.mapper.CartMapper;
import com.jt.common.pojo.Cart;
import com.jt.common.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CartService {
    @Autowired(required = false)
    private CartMapper cartMapper;
    public List<Cart> queryMyCarts(String userId){
        return cartMapper.selectCartByUserid(userId);
    }
    @Autowired
    private RestTemplate template;
    public void addCart(Cart cart){
        //新增购物车，一定是新增insert吗，先判断当前购物车中是否已有该数据
        //没有该商品 就是insert有该商品就update修改商品数量
        //通过数据查询从数据库查询已有
        Cart existCart = cartMapper.selectCartByUseridAndProductid(cart);
        if(existCart!=null){
            //说明购物车商品在表格中已存在
            //把已存在的商品num+新增商品num
            cart.setNum(cart.getNum()+existCart.getNum());
            cartMapper.updateNumByUseridAndProductid(cart);
        }else {
            //需要新增一个cart对象到数据库
            //cart对象中只有userId productId num 没有productName
            //RestTemplate 发送微服务调用 请求商品系统的单个商品查询功能
            String url = "http://productservice/product/manage/item/"+cart.getProductId();
            Product p = template.getForObject(url,Product.class);
            if (p!=null){
                cart.setProductName(p.getProductName());
                cart.setProductPrice(p.getProductPrice());
                cart.setProductImage(p.getProductImgurl());
                cartMapper.insertCart(cart);
            }else {
                throw new RuntimeException("未查询到商品数据");
            }
        }
    }
    public void updateNum(Cart cart){
        cartMapper.updateNumByUseridAndProductid(cart);
    }
    public void deleteCart(Cart cart){
        cartMapper.deleteCartByUseridAndProductid(cart);
    }
}
