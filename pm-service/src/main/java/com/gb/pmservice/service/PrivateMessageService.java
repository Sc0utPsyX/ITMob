package com.gb.pmservice.service;

import com.gb.pmservice.model.PrivateMessage;
import com.gb.pmservice.model.PrivateMessageStatus;
import com.gb.pmservice.repository.PrivateMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrivateMessageService {
    @Autowired
    private PrivateMessageRepository privateMessageRepository;


    public PrivateMessage save(PrivateMessage privateMessage){
        privateMessage.setMessageStatus(PrivateMessageStatus.RECEIVED);
        privateMessageRepository.save(privateMessage);
        return privateMessage;
    }

    public List<PrivateMessage> findPrivateMessageChat(String recipientId, String senderId) {
        List<PrivateMessage> messages = privateMessageRepository.findAllByrecipientIdAndSenderId(recipientId, senderId);

        if(messages.isEmpty()) {
            messages = new ArrayList<>();
        }

        return messages;
    }

    public PrivateMessage findById(Long id){
        return privateMessageRepository.findById(id).orElseThrow();
    }

}
