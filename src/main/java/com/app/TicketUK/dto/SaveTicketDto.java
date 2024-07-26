package com.app.TicketUK.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class SaveTicketDto {
    private String fromCity;
    private String toCity;
    private int segments;
    private int price;
    private String currency;
    private int travellerAmount;
    private String traveller;
}
