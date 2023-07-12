package ru.geekbrains.spring.ITMob.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "events")
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

    @Column(name = "event_date")
    private String event_date;

    @Column(name = "event_place")
    private String event_place;

}
