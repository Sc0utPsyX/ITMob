package com.geekbrains.userservice.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailsService {
    UserDetails loadUserDetailsByLogin(String login) throws UsernameNotFoundException;

}
