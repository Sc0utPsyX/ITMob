package com.geekbrains.userservice.services;

import com.geekbrains.userservice.models.*;

public interface UserService {

    AuthResponse authenticate(AuthRequest authRequest);
    AuthResponse register(UserRegReq userRegReq, String token);

    UserDto update(UserUpdateReq userUpdateReq, String token);
    UserDto changePassword(UserPassChgReq userPassChgReq, String token);
    UserDto viewUser(Long id, String token);
}
