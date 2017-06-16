package com.lin.consumer;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

import static java.lang.Thread.sleep;

/**
 * 功能概要：消费接收
 *
 * @author linbingwen
 * @since 2016年1月15日
 */
public class MessageConsumerQueue2 implements ChannelAwareMessageListener {


    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        System.out.println("接收到Queue2：" + new String(message.getBody()));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
