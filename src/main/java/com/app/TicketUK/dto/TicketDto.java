package com.app.TicketUK.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class TicketDto {
    private String departure;
    private String arrival;
    private int segmentCount;
    private int price;
    private String currency;
    private int travellerAmount;
    private String traveller;
}
