package com.app.TicketUK.mapper;

import com.app.TicketUK.dto.UserModelDto;
import com.app.TicketUK.dto.UserRegistrationDto;

import com.app.TicketUK.model.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Component
public class UserModelMapper {
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User toEntity (UserRegistrationDto userRegistrationDto) {
        return User.builder()
                .name(userRegistrationDto.getName())
                .email(userRegistrationDto.getEmail())
                .password(passwordEncoder.encode(userRegistrationDto.getPassword()))
                .build();
    }
    public UserModelDto toDto (User user) {
        return UserModelDto.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
