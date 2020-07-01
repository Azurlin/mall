package com.az.product.mapper;

import com.jt.common.pojo.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {
    int selectCount();
    List<Product> selectProductsByPage(@Param("start")int start,@Param("rows")Integer rows);
    Product selectProductById(String productId);
    void insertProduct(Product product);
    void updateProductById(Product product);
}
