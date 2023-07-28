package ru.geekbrains.spring.ITMob.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "events")
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_types_id")
    private EventTypes event_types_id;

    @Column(name = "event_types_name")
    private String event_types_name;

    @Column(name = "title")
    private String title;
    @Column(name = "author")
    private String author;
    @Column(name = "description")
    private String description;

    @Column(name = "event_date")
    private Timestamp event_date;

    @Column(name = "event_place")
    private String event_place;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
