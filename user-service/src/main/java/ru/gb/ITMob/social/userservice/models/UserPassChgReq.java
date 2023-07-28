package ru.gb.ITMob.social.userservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPassChgReq {
    private String login;
    private String oldPassword;
    private String newPassword;
}
