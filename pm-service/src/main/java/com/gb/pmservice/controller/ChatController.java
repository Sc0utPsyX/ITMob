package com.gb.pmservice.controller;

import com.gb.pmservice.model.PrivateMessage;
import com.gb.pmservice.service.PrivateMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ChatController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private PrivateMessageService privateMessageService;

    @MessageMapping("/chat")
    public void processMessage(@Payload PrivateMessage privateMessage) {
        PrivateMessage saved = privateMessageService.save(privateMessage);
        messagingTemplate.convertAndSendToUser(
                String.valueOf(privateMessage.getRecipientId()), "/queue/updates-" + privateMessage.getSenderId(), saved
                );
    }


    @GetMapping("/messages/{recipientId}/{senderId}")
    public ResponseEntity<?> findChatMessages ( @PathVariable String recipientId,
                                                @PathVariable String senderId) {
        return ResponseEntity
                .ok(privateMessageService.findPrivateMessageChat(Long.getLong(recipientId), Long.getLong(senderId)));
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<?> findMessage ( @PathVariable String id) {
        return ResponseEntity
                .ok(privateMessageService.findById(Long.getLong(id)));
    }
}
