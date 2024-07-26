package com.app.TicketUK.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity representing a travel destination.
 */
@Entity
@Getter
@Setter
@Table(name = "destinations")
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String departure;

    private String arrival;

    private int segmentCount;
}