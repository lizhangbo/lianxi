package com.lzb.product;

import com.lzb.product.controller.ProductController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProductApplication.class)
class ProductApplicationTests {

    @Autowired
    ProductController productController;

    @Test
    void contextLoads() {
       for (int i=0;i<10;i++){
           productController.sendMsg("bb.exchange","bb.routingKey","nnnnn");
       }
    }


}
