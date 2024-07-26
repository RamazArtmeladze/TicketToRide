package com.app.TicketUK.repository;

import com.app.TicketUK.model.Destination;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for managing Destination entities.
 */
public interface SegmentRepository extends JpaRepository<Destination, Long> {
}