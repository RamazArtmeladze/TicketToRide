package com.app.TicketUK.service;

import com.app.TicketUK.model.User;
import com.app.TicketUK.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Service for handling user data operations.
 */
@Service
@RequiredArgsConstructor
public class UserDataService {
    private final UserRepository userRepository;

    /**
     * Retrieves the authenticated user's ID.
     *
     * @return the authenticated user
     */
    public User getAuthenticatedUserID() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepository.findByEmail(userEmail).orElseThrow();
    }
}
