package net.sumile.producer;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * 功能概要：消息产生,提交到队列中去
 */
@Service
public class MessageProducer {

    private Logger logger = LoggerFactory.getLogger(MessageProducer.class);

    @Resource
    private RabbitTemplate amqpTemplate;
    @Resource
    private RabbitTemplate amqpTemplate2;
    @Resource
    private RabbitTemplate amqpTemplate3;

    public void sendMessage(Object message) {
        System.out.println("1发送：" + message);
        amqpTemplate.convertAndSend(message);
    }

    public void sendMessage2(Object message) {
        System.out.println("2发送：" + message);
        amqpTemplate2.convertAndSend(message);
    }

    public void sendMessage3(final String routingKey, final Object message) {
        System.out.println("3发送：[" + message + "] routing-key = [" + routingKey + "]");
        amqpTemplate3.convertAndSend(routingKey, message);
//        for (int i = 1; i < 3000000; i++) {
//            System.out.println("3发送：[" + message + "] routing-key = [" + routingKey + "]" + i);
//            amqpTemplate3.convertAndSend(routingKey, message);
//        }
    }
}
