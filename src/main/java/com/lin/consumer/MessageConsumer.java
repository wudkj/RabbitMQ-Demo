package com.lin.consumer;

import com.lin.producer.MessageProducer;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import sun.nio.cs.StandardCharsets;

import java.nio.charset.Charset;

import static java.lang.Thread.sleep;

/**
 * 功能概要：消费接收
 *
 * @author linbingwen
 * @since 2016年1月15日
 */
public class MessageConsumer implements ChannelAwareMessageListener {

    private Logger logger = LoggerFactory.getLogger(MessageConsumer.class);

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        System.out.println("接收到Queue：" + new String(message.getBody()));
        try {
            sleep(3000);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (InterruptedException e) {

        }
//        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
