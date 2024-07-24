package com.app.TicketUK.repository;

import com.app.TicketUK.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Long> {
    Optional<User> findByEmail(String email);

    @Query("SELECT u.balance FROM User u WHERE u.userId = :userId")
    int findBalance(@Param("userId") Long userId);
}
