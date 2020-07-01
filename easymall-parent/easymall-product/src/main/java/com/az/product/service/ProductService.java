package com.az.product.service;

import com.az.product.mapper.ProductMapper;
import com.jt.common.pojo.Product;
import com.jt.common.vo.EasyUIResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private ProductMapper productMapper;

    public EasyUIResult queryProductsByPage(Integer page,Integer rows){
        //封装一个EasyUIResult
        EasyUIResult result = new EasyUIResult();
        int total=productMapper.selectCount();
        result.setTotal(total);
        int start=(page-1)*rows;
        List<Product> pList = productMapper.selectProductsByPage(start,rows);
        result.setRows(pList);
        return result;
    }

    public Product queryOneProduct(String productId) {
        return productMapper.selectProductById(productId);
    }

    public void addProduct(Product product){
        String productId = UUID.randomUUID().toString();
        product.setProductId(productId);
        //主动缓存
        productMapper.insertProduct(product);
    }

    public void editProduct(Product product){
        productMapper.updateProductById(product);
    }
}
