package ru.gb.ITMob.social.userservice.services;

import ru.gb.ITMob.social.userservice.models.*;

public interface UserService {

    AuthResponse authenticate(AuthRequest authRequest);
    AuthResponse register(UserRegReq userRegReq, String token);

    UserDto update(UserUpdateReq userUpdateReq, String token);
    UserDto changePassword(UserPassChgReq userPassChgReq, String token);
    UserDto viewUser(Long id, String token);
    Boolean deleteUser(Long id, String token);
}
