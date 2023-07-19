package ru.gb.ITMob.rabbitmq.from_console_to_web;

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
    public static final String EXCHANGE_FOR_PROCESSING_TASK = "processingExchanger";
    public static final String QUEUE_WITH_PROCESSING_TASK_RESULTS = "processingResultsQueue";

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

//    @Bean
//    public Queue resultsQueue() {
//        return new Queue(QUEUE_WITH_PROCESSING_TASK_RESULTS, true, false, false);
//    }

    @GetMapping("/{message}")
    public String sendMessage(@PathVariable String message) {
        rabbitTemplate.convertAndSend(SpringRabbitmqApp.EXCHANGE_FOR_PROCESSING_TASK, null, "Task from Server: " + message);
        return "OK";
    }

    @Bean
    public SimpleMessageListenerContainer containerForTopic(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUEUE_WITH_PROCESSING_TASK_RESULTS);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    public MessageListenerAdapter listenerAdapter(SimpleMessageReceiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringRabbitmqApp.class, args);
    }

}