package com.lzb.customer.controller;

import com.lzb.customer.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FeignController {

    @Autowired(required = false)
    private ProductService productService;

    @RequestMapping("/getProduct")
    public Object getProduct() {
        List list = productService.getProduct();
        return list;
    }
}
