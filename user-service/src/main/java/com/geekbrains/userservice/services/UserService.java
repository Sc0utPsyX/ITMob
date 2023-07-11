package com.geekbrains.userservice.services;

import com.geekbrains.userservice.models.UserDto;
import com.geekbrains.userservice.models.UserPassChgReq;
import com.geekbrains.userservice.models.UserRegReq;
import com.geekbrains.userservice.models.UserUpdateReq;

public interface UserService {
    UserDto register(UserRegReq userRegReq);
    UserDto update(UserUpdateReq userUpdateReq);
    UserDto changePassword(UserPassChgReq userPassChgReq);
    UserDto viewUser(String username);
}
