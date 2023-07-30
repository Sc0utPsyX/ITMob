package ru.gb.ITMob.social.front.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.ITMob.social.front.converters.MenuListConverter;
import ru.gb.ITMob.social.front.dtos.MenuListDto;
import ru.gb.ITMob.social.front.entities.MenuList;
import ru.gb.ITMob.social.front.exceptions.ResourceNotFoundException;
import ru.gb.ITMob.social.front.services.MenuListService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/menu_list")
@RequiredArgsConstructor
public class MenuListController {

    private final MenuListService menuListService;

    private final MenuListConverter menuListConverter;

    @GetMapping
    public List<MenuListDto> findAllMenuList() {
        return menuListService.findAll().stream().map(menuListConverter::entityToDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public MenuListDto findMenuListById(@PathVariable long id) {
        MenuList p = menuListService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Раздел меню не найден, id: " + id));
        return menuListConverter.entityToDto(p);
    }

}