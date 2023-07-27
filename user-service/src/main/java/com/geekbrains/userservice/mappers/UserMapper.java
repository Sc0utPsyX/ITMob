package com.geekbrains.userservice.mappers;

import com.geekbrains.userservice.entities.Right;
import com.geekbrains.userservice.entities.User;
import com.geekbrains.userservice.models.UserDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = UserDetailsMapper.class)
public interface UserMapper {

    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "rights", target = "rights", qualifiedByName = "rightListToStringList")
    UserDto toDto(User user);

    @Mapping(target = "rights", ignore = true)
    @Mapping(target = "id", ignore = true) //even admin cannot modify those
    @Mapping(target = "createDate", ignore = true) //
    @Mapping(target = "modifyDate", ignore = true) //
    @BeanMapping(
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
    )
    User updateUser(UserDto userDto, @MappingTarget User user);

    @Named("rightListToStringList")
    default List<String> rightListToStringList(List<Right> rights) {
        return rights.stream().map(Right::getName).toList();
    }

}
