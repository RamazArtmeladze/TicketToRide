package com.app.TicketUK.repository;

import com.app.TicketUK.model.Destination;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SegmentRepository extends JpaRepository<Destination, Long> {
}