package com.geekbrains.userservice.mappers;

import com.geekbrains.userservice.entities.UserDetails;
import com.geekbrains.userservice.models.UserDetailsDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserDetailsMapper {

    UserDetailsMapper MAPPER = Mappers.getMapper(UserDetailsMapper.class);

    UserDetailsDto toDto(UserDetails userDetails);
}
