package com.app.TicketUK.mapper;

import com.app.TicketUK.dto.UserModelDto;
import com.app.TicketUK.dto.UserRegistrationDto;

import com.app.TicketUK.model.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Mapper for User model and User DTOs.
 */
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UserModelMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Maps a UserRegistrationDto to a User entity.
     *
     * @param userRegistrationDto the user registration DTO
     * @return the User entity
     */
    public User toEntity(UserRegistrationDto userRegistrationDto) {
        return User.builder()
                .name(userRegistrationDto.getName())
                .email(userRegistrationDto.getEmail())
                .password(passwordEncoder.encode(userRegistrationDto.getPassword()))
                .build();
    }

    /**
     * Maps a User entity to a UserModelDto.
     *
     * @param user the User entity
     * @return the UserModelDto
     */
    public UserModelDto toDto(User user) {
        return UserModelDto.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}