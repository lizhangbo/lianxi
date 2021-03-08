package com.lzb.customer.listener;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class MessageListener {
    @RabbitListener(queues = "bb.back.queue")
    public String process1(@Payload String message, @Header(AmqpHeaders.DELIVERY_TAG)long deliveryTag, Channel channel){
        log.info("接收消息"+message+deliveryTag);
        if (deliveryTag>8){
            try {
                channel.basicNack(deliveryTag,false,false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            channel.basicAck(deliveryTag,true);//最好批量提交
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "哈哈哈，我处理完了";
    }
    @RabbitListener(queues = "bb.queue")
    public void process2(@Payload String message, @Header(AmqpHeaders.DELIVERY_TAG)long deliveryTag, Channel channel){
        log.info("接收消息a"+message+deliveryTag);
        if (deliveryTag==8){
            try{
                int i=1/0;
            }catch (Exception e){
                try {
                    System.out.println("加入死信队列"+deliveryTag);
                    channel.basicNack(deliveryTag,false,false);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        try {
            channel.basicAck(deliveryTag,true);//最好批量提交
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = "bb.dead.queue")
    public void deadMessage(@Payload String message, @Header(AmqpHeaders.DELIVERY_TAG)long deliveryTag, Channel channel){
        System.out.println("死信队列"+message+","+deliveryTag);
    }
}
