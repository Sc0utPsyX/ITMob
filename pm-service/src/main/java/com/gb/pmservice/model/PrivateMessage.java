package com.gb.pmservice.model;

import jakarta.persistence.*;
import lombok.Builder;
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
    private Long recipientId;

    @Column(name = "sender_id")
    private Long senderId;

    @Column(name = "message")
    private String message;

    @Column(name = "create_date")
    @CreationTimestamp
    private LocalDateTime createDate;
 // вот сюда я бы еще статус бы прикрутил сообщения
}
