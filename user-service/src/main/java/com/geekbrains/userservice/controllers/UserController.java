package com.geekbrains.userservice.controllers;

import com.geekbrains.userservice.models.AuthRequest;
import com.geekbrains.userservice.models.AuthResponse;
import com.geekbrains.userservice.models.UserDto;
import com.geekbrains.userservice.models.UserRegReq;
import com.geekbrains.userservice.security.JwtUtility;
import com.geekbrains.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtility jwtUtility;
    private final UserService userService;


    @GetMapping("/auth")
    public AuthResponse authenticate(@RequestBody AuthRequest authRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getLogin(),
                            authRequest.getPassword()
                    )
            );
            String token = jwtUtility.generateToken(authentication);

            return new AuthResponse(token);

        } catch (BadCredentialsException ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Bad credentials.");
        }
    }


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public UserDto register(@RequestBody UserRegReq userRegReq) {
        return userService.register(userRegReq);
    }

    @GetMapping("/{username}")
    public UserDto getUser(@PathVariable String username) {
        return userService.viewUser(username);
    }






}