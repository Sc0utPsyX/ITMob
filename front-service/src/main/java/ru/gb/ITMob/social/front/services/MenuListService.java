package ru.gb.ITMob.social.front.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.ITMob.social.front.entities.MenuList;
import ru.gb.ITMob.social.front.repositories.MenuListRepository;

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