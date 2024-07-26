package com.app.TicketUK.controller;

import com.app.TicketUK.dto.UserModelDto;
import com.app.TicketUK.dto.UserRegistrationDto;
import com.app.TicketUK.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling user-related requests.
 */
@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * Registers a new user.
     *
     * @param userRegistrationDto the user registration data
     * @return the registered user
     */
    @PostMapping("/userRegistration")
    public ResponseEntity <UserModelDto> registerUser (@RequestBody UserRegistrationDto userRegistrationDto) {
        return new ResponseEntity<>(
                userService.registerUser(userRegistrationDto),
                HttpStatus.CREATED);
    }
}