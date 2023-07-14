package ru.geekbrains.events.entities;

import lombok.Data;
import org.springframework.data.geo.Point;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(schema = "itmob", name = "event_location")
public class EventLocation {

    @Id
    @Column(name = "event_id")
    private Long eventId;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "location")
    private List<Event> events;

    @Column(name = "coordinates")
    private Point coordinates;

    @Column(name = "detail")
    private String detail;
}
