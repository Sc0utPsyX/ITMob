package com.gb.pmservice.repository;

import com.gb.pmservice.model.PrivateMessage;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrivateMessageRepository extends JpaRepository<PrivateMessage, Long> {
    @Override
    List<PrivateMessage> findAll();

    List<PrivateMessage> findAllByrecipientIdAndSenderId(String recipientId, String senderId);
}
