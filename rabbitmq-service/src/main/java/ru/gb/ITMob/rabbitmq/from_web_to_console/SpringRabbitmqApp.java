package ru.gb.ITMob.rabbitmq.from_web_to_console;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringRabbitmqApp {
    // На сервере создаете webToConsoleExchanger и webToConsoleQueue
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/{message}")
    public String sendMessage(@PathVariable String message) {
        rabbitTemplate.convertAndSend("webToConsoleExchanger", null, "Task from Server: " + message);
        return "OK";
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringRabbitmqApp.class, args);
    }
}