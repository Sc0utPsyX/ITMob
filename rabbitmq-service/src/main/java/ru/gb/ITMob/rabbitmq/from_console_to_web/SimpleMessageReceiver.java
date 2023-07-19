package ru.gb.ITMob.rabbitmq.from_console_to_web;

import org.springframework.stereotype.Component;

@Component
public class SimpleMessageReceiver {
    public void receiveMessage(byte[] message) {
        System.out.println("Received from topic <" + new String(message) + ">");
    }
}