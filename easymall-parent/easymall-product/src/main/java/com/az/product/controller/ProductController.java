package com.az.product.controller;

import com.az.product.service.ProductService;
import com.jt.common.pojo.Product;
import com.jt.common.vo.EasyUIResult;
import com.jt.common.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product/manage")
public class ProductController {
    @Autowired
    private ProductService productService;
    //商品分页查询
    @RequestMapping("pageManage")
    public EasyUIResult queryProductsByPage(Integer page,Integer rows){
        EasyUIResult result = productService.queryProductsByPage(page,rows);
        return result;
    }

    //根据商品id查询单个商品
    @RequestMapping("item/{productId}")
    public Product queryOneProduct(@PathVariable String productId){
        return productService.queryOneProduct(productId);
    }

    @RequestMapping("save")
    public SysResult addProduct(Product product){
        try {
            SysResult sysResult =  SysResult.ok();
            sysResult.setData(product);
            productService.addProduct(product);
            return sysResult;
        }catch (Exception e){
            return SysResult.build(100,"err",null);
        }
    }

    @RequestMapping("update")
    public SysResult editProduct(Product product){
        try {
            productService.editProduct(product);
            return SysResult.ok();
        }catch (Exception e){
            return SysResult.build(201,"更新失败",null);
        }
    }
}
