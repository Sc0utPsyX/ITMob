package ru.gb.ITMob.social.userservice.mappers;

import ru.gb.ITMob.social.userservice.entities.UserDetails;
import ru.gb.ITMob.social.userservice.models.UserDetailsDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserDetailsMapper {

    UserDetailsMapper MAPPER = Mappers.getMapper(UserDetailsMapper.class);

    UserDetailsDto toDto(UserDetails userDetails);

    @BeanMapping(
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
    )
    @Mapping(target = "userId", ignore = true)
    void toEntity(UserDetailsDto userDetailsDto, @MappingTarget UserDetails userDetails);

}
