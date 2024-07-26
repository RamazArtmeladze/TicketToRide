package com.app.TicketUK.controller;

import com.app.TicketUK.dto.LoginUserDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling login requests.
 */
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final UserDetailsService userDetailsService;

    private final AuthenticationManager authenticationManager;

    /**
     * Handles the login request.
     *
     * @param loginForm the login form data
     * @param session the HTTP session
     * @return a string indicating the login result
     */
    @PostMapping("/login")
    public String login(@RequestBody LoginUserDto loginForm, HttpSession session) {
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginForm.getEmail());
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginForm.getEmail(), loginForm.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            session.setAttribute("userDetails", userDetails);

            return "Login successful!";
        } catch (AuthenticationException e) {
            return "failed";
        }
    }
}

