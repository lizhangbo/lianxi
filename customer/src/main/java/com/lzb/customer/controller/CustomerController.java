package com.lzb.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired(required = false)
    private RestTemplate restTemplate;

    @RequestMapping("/getProduct")
    public Object getProduct() {
        List list = restTemplate.exchange("http://PRODUCT/product/list", HttpMethod.GET,HttpEntity.EMPTY, List.class).getBody();
        return list;
    }
    public void test2(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
        context.refresh();
        
    }
}
