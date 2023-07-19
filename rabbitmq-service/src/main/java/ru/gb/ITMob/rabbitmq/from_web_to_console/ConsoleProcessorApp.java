package ru.gb.ITMob.rabbitmq.from_web_to_console;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class ConsoleProcessorApp {
    // На сервере создаете webToConsoleExchanger и webToConsoleQueue

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            System.out.println("Received Message '" + new String(delivery.getBody(), "UTF-8"));
        };
        channel.basicConsume("webToConsoleQueue", true, deliverCallback, consumerTag -> {
        });
    }
}