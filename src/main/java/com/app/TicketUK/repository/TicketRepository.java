package com.app.TicketUK.repository;

import com.app.TicketUK.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for managing Ticket entities.
 */
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
