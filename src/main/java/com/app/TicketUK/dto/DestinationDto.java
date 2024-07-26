package com.app.TicketUK.dto;

import com.app.TicketUK.model.Destination;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class DestinationDto {
    private Long id;
    private String departure;
    private String arrival;
    private int segmentCount;

    public DestinationDto(Destination destination) {
        this.id = destination.getId();
        this.departure = destination.getDeparture();
        this.arrival = destination.getArrival();
        this.segmentCount = destination.getSegmentCount();
    }

}
