package ru.geekbrains.events.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Data
@Table(schema = "itmob", name = "events")
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "event")
    private String event;

    @Column(name = "description")
    private String description;

    @Column(name = "create_date")
    @CreationTimestamp
    private LocalDateTime createData;

    //@ManyToOne
    @Column(name = "owner_id") // должна быть связь с id пользователя ?? вроде должна
    private Long ownerId;

    @Column(name = "event_date")
    private Date eventDate;

    @ManyToOne
    @JoinColumn(name = "event_type")
    private Type eventType;

    @ManyToOne
    @JoinColumn(name = "event_location")
    private Location eventLocation;
}
