package com.geekbrains.userservice.services;

import com.geekbrains.userservice.entities.User;
import com.geekbrains.userservice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

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
}
