package ru.geekbrains.spring.ITMob.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "event_types")
@NoArgsConstructor
public class EventTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "photo_link")
    private String photo_link;

    @Column(name = "description")
    private String description;

    @Column(name = "description_full")
    private String description_full;

    @Column(name = "create_date")
    private Timestamp create_date;

    @Column(name = "is_default")
    private Boolean is_default;

}

