package net.sumile.controler;

import net.sumile.producer.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Created by Administrator on 2017/6/14 0014.
 */
@Controller
public class ControlerM {
    @Autowired
    MessageProducer messageProducer;

    @RequestMapping(value = "/home")
    public void send(@RequestParam(value = "type", defaultValue = "1") String type,
                     @RequestParam(value = "routing_key", defaultValue = "default.order.phbj") String routing_key,
                     @RequestParam(value = "message", defaultValue = "defaultMessage") String message) {
        if ("1".equals(type)) {
            messageProducer.sendMessage(message);
        } else if ("2".equals(type)) {
            messageProducer.sendMessage2(message);
        } else if ("3".equals(type)) {
            messageProducer.sendMessage3(routing_key, message);
        }
    }
}
