package com.example.userservice.dto;

import com.example.userservice.config.MQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

//    @RabbitListener(queues = MQConfig.QUEUE)
    @RabbitListener(queues = "queue_to_user")
    public void checkResponseMessage(CustomMessage message) {
        System.out.println("Message from services: " + message);
    }

}