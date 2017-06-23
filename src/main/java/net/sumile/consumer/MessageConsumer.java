package net.sumile.consumer;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

import static java.lang.Thread.sleep;

/**
 * 功能概要：消费接收
 *
 * @author sumile
 * @since 2017年6月23日15:38:07
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
