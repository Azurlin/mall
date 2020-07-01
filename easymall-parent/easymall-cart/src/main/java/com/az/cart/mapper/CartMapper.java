package com.az.cart.mapper;

import com.jt.common.pojo.Cart;

import java.util.List;

public interface CartMapper {
    List<Cart> selectCartByUserid(String userId);
    Cart selectCartByUseridAndProductid(Cart cart);

    void updateNumByUseridAndProductid(Cart cart);

    void insertCart(Cart cart);

    void deleteCartByUseridAndProductid(Cart cart);
}
