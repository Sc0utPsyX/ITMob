package com.geekbrains.userservice.mappers;

import com.geekbrains.userservice.entities.Right;
import com.geekbrains.userservice.entities.User;
import com.geekbrains.userservice.models.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = UserDetailsMapper.class)
public interface UserMapper {

    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "rights", target = "rights", qualifiedByName = "rightListToStringList")
    UserDto toDto(User user);

    @Named("rightListToStringList")
    default List<String> rightListToStringList(List<Right> rights) {
        return rights.stream().map(Right::getName).toList();
    }

}
