package com.geekbrains.userservice.services;

import com.geekbrains.userservice.entities.Right;
import com.geekbrains.userservice.entities.User;
import com.geekbrains.userservice.entities.UserDetails;
import com.geekbrains.userservice.mappers.UserMapper;
import com.geekbrains.userservice.models.*;
import com.geekbrains.userservice.repositories.RightRepository;
import com.geekbrains.userservice.repositories.UserDetailsRepository;
import com.geekbrains.userservice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final UserDetailsRepository userDetailsRepository;
    private final RightRepository rightRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserDetailsByLogin(String login)
            throws UsernameNotFoundException {

        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Login not found."));

        List<GrantedAuthority> authorities = user.getRights().stream()
                .map(right -> new SimpleGrantedAuthority(right.getName()))
                .map(sga -> (GrantedAuthority) sga)
                .toList();

        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), authorities);

    }

    @Override
    @Transactional
    public UserDto register(UserRegReq userRegReq) {

        String login = userRegReq.getLogin();
        String password = userRegReq.getPassword();
        String email = userRegReq.getEmail();
        List<String> prefRoles = userRegReq.getRights();

        Optional<User> userByLogin = userRepository.findByLogin(login);
        Optional<UserDetails> userByEmail = userDetailsRepository.findByEmail(email);

        //input data validation
        if (login == null || "".equals(login)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login cannot be empty");
        }
        if (userByLogin.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with this login already exists");
        }
        if (password == null || "".equals(password)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password cannot be empty");
        }
        if (email == null || "".equals(email)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email cannot be empty");
        }
        if (userByEmail.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with this email already exists");
        }
        if (prefRoles == null || prefRoles.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Register request must contain at least one role");
        }

        List<Right> rights = new ArrayList<>();

        userRegReq
                .getRights()
                .forEach( prefRole-> {
                    Right right = rightRepository.findByName(prefRole)
                            .orElseThrow(() ->
                                    new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid role in user reg request."));
                    rights.add(right);
                });

        User user = new User()
                .builder()
                .setId(null)
                .setLogin(login)
                .setPassword(bCryptPasswordEncoder.encode(password))
                .setUsername(login)
                .setUserDetails(null)
                .setRights(rights)
                .setCreateDate(LocalDateTime.of(1970,1,1,1,1)) //db-backed
                .setRegisterDate(null) //@CreationTimestamp or LocalDateTime.now()
                .setActive(true)
                .build();

        user = userRepository.save(user);

        UserDetails userDetails = new UserDetails()
                .builder()
                .setUserId(user.getId())
                .setEmail(email)
                .setSex(null)
                .setCity(null)
                .setAddress(null)
                .setBirthDate(null)
                .setAbout(null)
                .build();

        userDetails = userDetailsRepository.save(userDetails);

        user = user.builder().setUserDetails(userDetails).build();

        return UserMapper.MAPPER.toDto(user);
    }

    @Override
    public UserDto update(UserUpdateReq userUpdateReq) {
        return null;
    }

    @Override
    public UserDto changePassword(UserPassChgReq userPassChgReq) {
        return null;
    }

    @Override
    public UserDto viewUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Username not found"));

        return UserMapper.MAPPER.toDto(user);
    }
}
