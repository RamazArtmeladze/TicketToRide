package com.app.TicketUK.dto;


import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object for login user information.
 */
@Getter
@Setter
public class LoginUserDto {
    private String email;

    private String password;
}