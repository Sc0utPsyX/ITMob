package com.geekbrains.userservice.mappers;
import com.geekbrains.userservice.entities.PrivacySetting;
import com.geekbrains.userservice.models.PrivacySettingDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PrivacySettingMapper {

       PrivacySettingMapper MAPPER = Mappers.getMapper(PrivacySettingMapper.class);

       PrivacySettingDto toDto(PrivacySetting privacySetting);

}
