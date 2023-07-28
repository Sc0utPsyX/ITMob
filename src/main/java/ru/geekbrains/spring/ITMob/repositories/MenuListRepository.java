package ru.geekbrains.spring.ITMob.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.spring.ITMob.entities.MenuList;

public interface MenuListRepository extends JpaRepository<MenuList, Long> {
}
