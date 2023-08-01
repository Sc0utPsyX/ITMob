package ru.gb.ITMob.social.front.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.ITMob.social.front.dtos.MenuListDto;
import ru.gb.ITMob.social.front.entities.MenuList;
import ru.gb.ITMob.social.front.services.MenuListService;

@Component
@RequiredArgsConstructor
public class MenuListConverter {
    public MenuListDto entityToDto(MenuList menuList) {
        return new MenuListDto(menuList.getId(), menuList.getName(), menuList.getPhoto_link(), menuList.getHref(), menuList.isVisibility());
    }

    public MenuList dtoToEntity(MenuListDto menuListDto) {
        MenuList p = new MenuList();
        p.setId(menuListDto.getId());
        p.setName(menuListDto.getName());
        p.setPhoto_link(menuListDto.getPhoto_link());
        p.setHref(menuListDto.getHref());
        p.setVisibility(menuListDto.isVisibility());
        return p;
    }
}
