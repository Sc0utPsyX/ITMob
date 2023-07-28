package ru.geekbrains.spring.ITMob.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "menu_list")
@NoArgsConstructor
public class MenuList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "photo_link")
    private String photo_link;

    @Column(name = "href")
    private String href;

}