package com.lin.controler;

import com.lin.producer.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

import static java.lang.Thread.sleep;

/**
 * Created by Administrator on 2017/6/14 0014.
 */
@Controller
public class ControlerM {
    @Autowired
    MessageProducer messageProducer;

    @RequestMapping(value = "/home")
    public void send() {
        int i = 0;
        while (true) {
            try {
                messageProducer.sendMessage("home" + i++);
                sleep(2000);
            } catch (InterruptedException e) {

            }
        }
    }
}
