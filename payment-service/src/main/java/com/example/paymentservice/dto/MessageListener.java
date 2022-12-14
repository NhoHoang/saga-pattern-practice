package com.example.paymentservice.dto;

import com.example.paymentservice.config.MQConfig;
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
    @RabbitListener(queues = "queue_to_services")
    public void listener(CustomMessage message) {
        System.out.println("Message from user-service: " + message);
        CustomMessage sendingMessage = new CustomMessage();
//        sendingMessage.setMessageId(UUID.randomUUID().toString());
        sendingMessage.setMessage("Payment have received message");
        template.convertAndSend(MQConfig.EXCHANGE, MQConfig.ROUTING_KEY, sendingMessage);
    }

}