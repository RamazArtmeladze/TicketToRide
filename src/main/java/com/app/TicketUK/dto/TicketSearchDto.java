package com.app.TicketUK.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object for ticket search information.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class TicketSearchDto {
    private String departure;

    private String arrival;
}
