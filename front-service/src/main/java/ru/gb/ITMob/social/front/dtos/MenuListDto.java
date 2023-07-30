package ru.gb.ITMob.social.front.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuListDto {
    private Long id;
    private String name;
    private String photo_link;
    private String href;

}

