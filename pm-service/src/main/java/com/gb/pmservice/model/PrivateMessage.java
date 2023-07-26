package com.gb.pmservice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(schema = "itmob", name = "user_messages")
public class PrivateMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "recipient_id")
    private String recipientId;

    @Column(name = "sender_id")
    private String senderId;

    @Column(name = "message")
    private String message;

    @Column(name = "create_date")
    @CreationTimestamp
    private LocalDateTime createDate;

    @Column(name = "message_status")
    private PrivateMessageStatus messageStatus;
}
