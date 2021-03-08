package com.lzb.customer.service;

import com.lzb.customer.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "PRODUCT",configuration = FeignClientConfig.class)
public interface ProductService {

    @RequestMapping("/product/list")
    public List<String> getProduct();
}
