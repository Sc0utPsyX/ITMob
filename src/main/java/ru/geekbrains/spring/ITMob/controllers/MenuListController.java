package ru.geekbrains.spring.ITMob.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.spring.ITMob.dtos.MenuListDto;
import ru.geekbrains.spring.ITMob.entities.MenuList;
import ru.geekbrains.spring.ITMob.exceptions.ResourceNotFoundException;
import ru.geekbrains.spring.ITMob.services.MenuListService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/menu_list")
@RequiredArgsConstructor
public class MenuListController {

    private final MenuListService menuListService;

    @GetMapping
    public List<MenuListDto> findAllMenuList() {
        return menuListService.findAll().stream().map(p -> new MenuListDto(p.getId(), p.getName(), p.getPhoto_link(), p.getHref())).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public MenuListDto findMenuListById(@PathVariable long id) {
        MenuList p = menuListService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Раздел меню не найден, id: " + id));
        return new MenuListDto(p.getId(), p.getName(), p.getPhoto_link(), p.getHref());
    }

}