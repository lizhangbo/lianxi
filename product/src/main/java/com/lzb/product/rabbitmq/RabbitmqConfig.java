package com.lzb.product.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
public class RabbitmqConfig {

    @Bean("queue")
    public Queue getQueue(){
        Map<String ,Object> agreement = new HashMap<String, Object>();
        agreement.put("x-dead-letter-exchange","my.dead.exchange");
        agreement.put("x-dead-letter-routing-key","my.dead.routingKey");
        return new Queue("bb.queue",true,false,false,agreement);
    }
    @Bean("deadQueue")
    public Queue deadQueue(){
        //死信队列
        return new Queue("bb.dead.queue",true);
    }

    @Bean("exchange")
    public DirectExchange getExchange(){
        return new DirectExchange("bb.exchange",true,false);
    }
    @Bean("deadExchange")
    public DirectExchange deadExchange(){
        return new DirectExchange("my.dead.exchange",true,false);
    }

    @Bean
    public Binding getBind(@Qualifier("queue") Queue queue,@Qualifier("exchange") DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("bb.routingKey");
    }

    @Bean
    public Binding deadBind(@Qualifier("deadQueue") Queue queue,@Qualifier("deadExchange") DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("my.dead.routingKey");
    }

    @Bean("backQueue")
    public Queue backQueue(){
        return new Queue("bb.back.queue",true);
    }
    @Bean("backExchange")
    public DirectExchange backExchange(){
        return new DirectExchange("my.back.exchange",true,false);
    }
    @Bean
    public Binding backBind(@Qualifier("backQueue") Queue queue, @Qualifier("backExchange") DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("my.back.routingKey");
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            public void confirm(CorrelationData correlationData, boolean b, String s) {
                if (b){
                    System.out.println("成功");
                }else{
                    System.out.println("失败");
                }
            }
        });
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            public void returnedMessage(Message message, int i, String s, String s1, String s2) {
                System.out.println("失败入队");
            }
        });
        //生产者  ，relay-to
//        rabbitTemplate.setUseTemporaryReplyQueues(false);//临时队列
//        rabbitTemplate.setReplyAddress("amq.rabbitmq.reply-to");
//        rabbitTemplate.setUserCorrelationId(true);
//        rabbitTemplate.setReceiveTimeout(1000);
        return rabbitTemplate;
    }
}
