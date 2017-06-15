package com.lin.producer;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

/**
 * 功能概要：消息产生,提交到队列中去
 *
 * @author linbingwen
 * @since 2016年1月15日
 */
@Service
public class MessageProducer {

    private Logger logger = LoggerFactory.getLogger(MessageProducer.class);

    @Resource
    private AmqpTemplate amqpTemplate;

    public void sendMessage(Object message) {
        System.out.println("发送："+message);
        amqpTemplate.convertAndSend("exchange", message);
    }

}
