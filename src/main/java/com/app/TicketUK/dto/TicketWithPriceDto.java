package com.app.TicketUK.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object for ticket with price information.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class TicketWithPriceDto {
    private int totalSegments;

    private int price;

    private String currency;
}