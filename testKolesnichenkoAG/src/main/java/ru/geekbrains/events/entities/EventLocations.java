package ru.geekbrains.events.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(schema = "itmob", name = "event_location")
public class EventLocations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "address")
    private String address;

    /*@OneToMany(mappedBy = "location")
    private List<Event> events;*/

    /*@Column(name = "coordinates")
    private Point coordinates;*/

    @Column(name = "detail")
    private String detail;
}
