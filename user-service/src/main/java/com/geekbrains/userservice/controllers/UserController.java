package com.geekbrains.userservice.controllers;

import com.geekbrains.userservice.models.*;
import com.geekbrains.userservice.services.TokenService;
import com.geekbrains.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/auth")
    @ResponseStatus(HttpStatus.OK)
    public AuthResponse authenticate(@RequestBody AuthRequest authRequest) {
        return userService.authenticate(authRequest);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public AuthResponse register(
            @RequestBody UserRegReq userRegReq,
            @RequestHeader(required = false, name = "authorization") String token
    )
    {
        return userService.register(userRegReq, token);
    }

    @GetMapping("/profile")
    public UserDto getUser(
            @RequestHeader(name = "authorization") String token,
            @RequestParam(required = false, name = "user_id") Long userId
    )
    {
        return userService.viewUser(userId, token);
    }

    @PutMapping("/profile")
    @ResponseStatus(HttpStatus.OK)
    public UserDto update(
            @RequestHeader(name = "authorization") String token,
            @RequestBody UserUpdateReq userUpdateReq
    )
    {
        return userService.update(userUpdateReq, token);
    }

    @PutMapping("/password")
    @ResponseStatus(HttpStatus.OK)
    public UserDto changePassword(
            @RequestHeader(name = "authorization") String token,
            @RequestBody UserPassChgReq userPassChgReq
    )
    {
        return userService.changePassword(userPassChgReq, token);
    }

    @DeleteMapping("/profile")
    @ResponseStatus(HttpStatus.OK)
    public Boolean delete(
            @RequestHeader(name = "authorization")String token,
            @RequestParam(required = false, name = "user_id") Long userId
    )
    {
        return userService.deleteUser(userId, token);
    }


}
