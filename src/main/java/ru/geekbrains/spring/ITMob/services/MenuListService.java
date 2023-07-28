package ru.geekbrains.spring.ITMob.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.ITMob.entities.MenuList;
import ru.geekbrains.spring.ITMob.repositories.MenuListRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuListService {
    private final MenuListRepository menuListRepository;

    public List<MenuList> findAll(){
        return menuListRepository.findAll();
    }

    public Optional<MenuList> findById(long id){
        return menuListRepository.findById(id);
    }

}