package com.gb.pmservice.controller;

import com.gb.pmservice.model.PrivateMessage;
import com.gb.pmservice.model.PrivateMessageStatus;
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
        privateMessage.setMessageStatus(PrivateMessageStatus.SENT);
        PrivateMessage savedMessage = privateMessageService.save(privateMessage);
        messagingTemplate.convertAndSendToUser(
                privateMessage.getRecipientId(), "/queue/messages" + privateMessage.getSenderId(), savedMessage
                );
    }


    @GetMapping("/messages/{recipientId}/{senderId}")
    public ResponseEntity<?> findChatMessages ( @PathVariable String recipientId,
                                                @PathVariable String senderId) {
        return ResponseEntity
                .ok(privateMessageService.findPrivateMessageChat(recipientId, senderId));
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<?> findMessage ( @PathVariable String id) {
        return ResponseEntity
                .ok(privateMessageService.findById(Long.getLong(id)));
    }
}
