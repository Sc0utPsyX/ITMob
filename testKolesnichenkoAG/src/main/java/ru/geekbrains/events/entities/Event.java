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

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "create_date")
    @CreationTimestamp
    private LocalDateTime createData;

    //@OneToOne
    @Column(name = "owner_id") // должна быть связь с id пользователя ?? вроде должна
    private Long ownerId;

    @Column(name = "event_date")
    private Date eventDate;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;

    @ManyToOne
    @JoinColumn(name = "event_location")
    private Locations location;
}
