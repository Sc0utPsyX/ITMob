package com.geekbrains.userservice.models;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class PrivacySettingDto {

    Boolean showAge;
    Boolean openProfile;

    /**
     * Получать сообщения от подписчиков
     */
    Boolean getInvitationFromSubscribers;

    /**
     * Получать сообщения от подписок
     */
    Boolean getInvitationFromSubscriptions;

}
