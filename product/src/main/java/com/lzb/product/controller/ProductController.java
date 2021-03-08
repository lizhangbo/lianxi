package com.lzb.product.controller;

import com.netflix.discovery.converters.Auto;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.Delivery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    public RabbitTemplate rabbitTemplate;

    @RequestMapping("sendMsg")
    public void sendMsg(String exchange,String routeKey,String content) {
        System.out.println("发消息到mq");
//        productController.sendMsg("bb.exchange","bb.routingKey","nnnnn");
        exchange = "bb.exchange";
        routeKey = "bb.routingKey";
        content="nnnnn";
        rabbitTemplate.convertAndSend(exchange,routeKey,content);
    }
    @RequestMapping("sendMsgByCount")
    public void sendMsgByCount(int count) {
        System.out.println("发消息到mq");
//        productController.sendMsg("bb.exchange","bb.routingKey","nnnnn");
        for (int i = 0; i < count; i++) {
            String exchange = "bb.exchange";
            String routeKey = "bb.routingKey";
            String content="nnnnn";
            rabbitTemplate.convertAndSend(exchange,routeKey,content);
        }
    }


    @RequestMapping("sendAndReceive")
    public void sendAndReceive(){
        String xx="我的消息";
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setCorrelationId("12345");//唯一id
        Message message = new Message(xx.getBytes(),messageProperties);
        Message receive = rabbitTemplate.sendAndReceive("bb.back.exchange", "bb.back.routingKey", message);
        System.out.println("设置啥："+receive);
    }


    @RequestMapping("/list")
    public Object queryList(){
        System.out.println("产品列表");
        List list= new ArrayList();
        list.add(3);
        list.add(4);
        return list;
    }

}
