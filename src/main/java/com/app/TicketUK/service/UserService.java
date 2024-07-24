package com.app.TicketUK.service;

import com.app.TicketUK.dto.UserModelDto;
import com.app.TicketUK.dto.UserRegistrationDto;
import com.app.TicketUK.mapper.UserModelMapper;
import com.app.TicketUK.model.User;
import com.app.TicketUK.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserModelMapper mapper;

    public UserModelDto registerUser (UserRegistrationDto userRegistrationDto) {

        User userModel = mapper.toEntity(userRegistrationDto);
        User savedUser = userRepository.save(userModel);

        return mapper.toDto(savedUser);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User userModel = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return new org.springframework.security.core.userdetails.User(userModel.getEmail(), userModel.getPassword(),new ArrayList<>());
    }
}
