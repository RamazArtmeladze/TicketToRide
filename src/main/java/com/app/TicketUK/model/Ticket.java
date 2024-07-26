package com.app.TicketUK.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;
    private String fromCity;
    private String toCity;
    private int segments;
    private int price;
    private String currency;
    private int travellerAmount;
    private String traveller;
    private Long userId;
}
