package com.app.TicketUK.repository;

import com.app.TicketUK.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for managing User entities.
 */
public interface UserRepository extends JpaRepository <User, Long> {
    Optional<User> findByEmail(String email);
}
