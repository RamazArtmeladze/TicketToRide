package com.app.TicketUK.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class TicketSearchDto {
    private String departure;
    private String arrival;
}
