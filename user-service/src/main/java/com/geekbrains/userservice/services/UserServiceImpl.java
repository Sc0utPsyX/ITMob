package com.geekbrains.userservice.services;

import com.geekbrains.userservice.entities.PrivacySetting;
import com.geekbrains.userservice.entities.Right;
import com.geekbrains.userservice.entities.User;
import com.geekbrains.userservice.entities.UserDetails;
import com.geekbrains.userservice.mappers.UserMapper;
import com.geekbrains.userservice.models.*;
import com.geekbrains.userservice.repositories.PrivacySettingRepository;
import com.geekbrains.userservice.repositories.RightRepository;
import com.geekbrains.userservice.repositories.UserDetailsRepository;
import com.geekbrains.userservice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserDetailsRepository userDetailsRepository;
    private final RightRepository rightRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final PrivacySettingRepository privacySettingRepository;

    @Override
    public AuthResponse authenticate(@RequestBody AuthRequest authRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getLogin(),
                            authRequest.getPassword()
                    )
            );

            String token = tokenService.generateToken(authentication);

            return new AuthResponse(token);

        } catch (BadCredentialsException ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Bad credentials.");
        }
    }

    @Override
    @Transactional
    public AuthResponse register(UserRegReq userRegReq, String token) {

        String login = userRegReq.getLogin();
        String password = userRegReq.getPassword();
        String email = userRegReq.getEmail();
        boolean isAdmin = false;

        List<Right> rights = new ArrayList<>();

        //admin can set rights during registration
        if (token != null && tokenService.hasRight(token, "admin")) {
            isAdmin = true;
            List<String> prefRoles = userRegReq.getRights();

            //rights validation
            if (prefRoles == null || prefRoles.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Register request must contain at least one role");
            }

            prefRoles
                    .forEach( prefRole-> {
                        Right right = rightRepository.findByName(prefRole)
                                .orElseThrow(() ->
                                        new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid role in user reg request."));
                        rights.add(right);
                    });

        } else {
            Right userRight = rightRepository.findByName("user")
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Right 'User' cannot be found in DB"));
            rights.add(userRight);
        }

        Optional<User> userByLogin = userRepository.findByLogin(login);
        Optional<UserDetails> userByEmail = userDetailsRepository.findByEmail(email);

        //input data validation
        if (login == null || "".equals(login)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login cannot be empty");
        }
        if (userByLogin.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with this login already exists");
        }
        // TODO: 17.07.2023 check if password is secure
        if (password == null || "".equals(password)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password cannot be empty");
        }
        if (email == null || "".equals(email)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email cannot be empty");
        }
        if (userByEmail.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with this email already exists");
        }

        User user = new User()
                .builder()
                .setId(null)
                .setLogin(login)
                .setPassword(bCryptPasswordEncoder.encode(password))
                .setUsername(login)
                .setUserDetails(null)
                .setRights(rights)
                .setCreateDate(null) //@CreationTimestamp
                .setModifyDate(null) //@UpdateTimestamp
                .setActive(true)
                .setRegConfirmed(false)
                .setPrivacySetting(privacySettingRepository.save(new PrivacySetting()))
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

        user.builder().setUserDetails(userDetails).build();

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login, password)
        );

        //admin doesn't get token
        if (isAdmin) {
            return new AuthResponse(null);
        } else {
            return new AuthResponse(tokenService.generateToken(authenticate));
        }
    }

    @Override
    @Transactional
    public UserDto update(UserUpdateReq userUpdateReq, String token) {

        UserDto userDto = userUpdateReq.getUserDto();
        Long userId = userDto.getId();
        String login = userDto.getLogin();
        String username = userDto.getUsername();
        List<String> prefRoles = userDto.getRights();
        UserDetailsDto userDetailsDto = userDto.getUserDetails();
        boolean isAdmin = tokenService.hasRight(token, "admin");

        String email = null;
        if (userDetailsDto != null) {
            email = userDetailsDto.getEmail();
        }

        //validate token
        checkExpiration(token);

        //token id != target id && not admin
        if (!tokenService.getId(token).equals(userId) && !isAdmin) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized access.");
        }

        //prevent user from changing those parameters
        if (!isAdmin) {
            userDto.setLogin(null);
            userDto.setActive(null);
            userDto.setRegConfirmed(null);
        }

        //input data validation
        if (isAdmin && (login == null || "".equals(login))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login cannot be empty");
        }
        if (userId == null || userId <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid id in reg request");
        }
        if (username == null || "".equals(username)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username cannot be empty");
        }
        if (email == null || "".equals(email)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email cannot be empty");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with this id not found"));

        //check if username is new and not taken
        if (!user.getUsername().equals(username) && userRepository.findByUsername(username).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username is already registered");
        }
        //check if email is new and not taken
        if (user.getUserDetails() != null &&
                !user.getUserDetails().getEmail().equals(email) &&
                userDetailsRepository.findByEmail(email).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is already registered");
        }
        //check if admin is changing login to already registered one
        if (!user.getLogin().equals(login) && userRepository.findByLogin(login).isPresent()) { //&& isAdmin (less secure)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login is already registered");
        }

        //persistence
        user = UserMapper.MAPPER.updateUser(userDto, user);

        //only admin can set rights
        if (isAdmin && prefRoles != null) {
            List<Right> rights = new ArrayList<>();

            if (prefRoles.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Update request must contain at least one role");
            }

            prefRoles
                    .forEach(prefRole -> {
                        Right right = rightRepository.findByName(prefRole)
                                .orElseThrow(() ->
                                        new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid rights in request.")
                                );
                        rights.add(right);
                    });
            user.setRights(rights);
        }

        return UserMapper.MAPPER.toDto(user);

    }

    @Override
    @Transactional
    public UserDto changePassword(UserPassChgReq userPassChgReq, String token) {

        boolean isAdmin = tokenService.hasRight(token, "admin");
        //login instead of id for security purposes
        String login = userPassChgReq.getLogin();
        String oldPassword = userPassChgReq.getOldPassword();
        String newPassword = userPassChgReq.getNewPassword();

        //validate token
        checkExpiration(token);

        //token login != target login && not admin
        if (!tokenService.getLogin(token).equals(login) && !isAdmin) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized access");
        }

        //validate input data
        if (login == null || "".equals(login)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid id in request");
        }
        if (!isAdmin && (oldPassword == null || "".equals(oldPassword))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Old password cannot be empty");
        }
        // TODO: 17.07.2023 check if password is secure
        if (newPassword == null || "".equals(newPassword)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "New password cannot be empty");
        }
        if (newPassword.equals(oldPassword)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Passwords must be different");
        }

        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with this login not found"));

        //admin can change password without knowing the old one
        if (isAdmin || bCryptPasswordEncoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(bCryptPasswordEncoder.encode(newPassword));
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized access");
        }

        return UserMapper.MAPPER.toDto(user);
    }

    @Override
    public UserDto viewUser(Long id, String token) {
        User user = getUserById(id, token);
        return UserMapper.MAPPER.toDto(user);
    }

    @Override
    @Transactional
    public Boolean deleteUser(Long id, String token) {
        User user = getUserById(id, token);
        //id exists 100% here so delete is successful in any case
        userRepository.deleteById(user.getId());
        return true;
    }

    @Override
    @Transactional
    public PrivacySettingDto getPrivacySetting(String token) {
        PrivacySetting privacySetting = getUserById(token).getPrivacySetting();
        return PrivacySettingDto.builder()
                .showAge(privacySetting.getShowAge())
                .openProfile(privacySetting.getOpenProfile())
                .getInvitationFromSubscribers(privacySetting.getGetInvitationFromSubscribers())
                .getInvitationFromSubscriptions(privacySetting.getGetInvitationFromSubscriptions())
                .build();
    }
    @Override
    @Transactional
    public void changePrivacySettings(String token, PrivacySettingDto privacySettingDto) {
        PrivacySetting privacySetting = getUserById(token).getPrivacySetting();
        privacySetting.setShowAge(privacySettingDto.getShowAge());
        privacySetting.setOpenProfile(privacySettingDto.getOpenProfile());
        privacySetting.setGetInvitationFromSubscribers(privacySettingDto.getGetInvitationFromSubscribers());
        privacySetting.setGetInvitationFromSubscriptions(privacySettingDto.getGetInvitationFromSubscriptions());
    }

    @Transactional
    //transactional methods must be overridable so it`s protected not private
    protected User getUserById(Long id, String token) {

        checkExpiration(token);

        Long targetId;

        if (id == null) {
            targetId = tokenService.getId(token);
        } else if (tokenService.hasRight(token, "admin")) {
            targetId = id;
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized access");
        }

        if (targetId <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid id in request");
        }

        return userRepository.findById(targetId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with this id not found"));
    }

    public void checkExpiration(String token) {
        // TODO: 19.07.2023 make all exceptions more informative for service clients (lesson #4 example)
        // TODO: 19.07.2023 make validating process through hibernate validators
        // TODO: 19.07.2023 make proper logging 
        if (tokenService.isExpired(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token is expired");
        }
    }

    private User getUserById(String token) {
        return getUserById(null, token);
    }

}
