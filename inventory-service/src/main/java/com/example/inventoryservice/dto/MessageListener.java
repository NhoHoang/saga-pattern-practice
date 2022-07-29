package com.example.inventoryservice.dto;


import com.example.inventoryservice.config.MQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MessageListener {

    RabbitTemplate template;

    public MessageListener(RabbitTemplate template) {
        this.template = template;
    }

    //    @RabbitListener(queues = MQConfig.QUEUE)
    @RabbitListener(queues = "message_queue")
    public void listener(CustomMessage message) {
        System.out.println("tin nhắn nhận được từ user-service: " + message);
        CustomMessage sendingMessage = new CustomMessage();
//        sendingMessage.setMessageId(UUID.randomUUID().toString());
        sendingMessage.setMessage("Service 2 đã nhận được message");
        template.convertAndSend(MQConfig.EXCHANGE, MQConfig.ROUTING_KEY, sendingMessage);
    }

}