package com.app.TicketUK.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Data Transfer Object for user registration information.
 */
@Builder
@Getter
@AllArgsConstructor
public class UserRegistrationDto {
    private String name;

    private String email;

    private String password;
}
