package com.app.TicketUK.dto;

import com.app.TicketUK.model.Destination;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object for Destination.
 */
@Getter
@Setter
@AllArgsConstructor
@Builder
public class DestinationDto {
    private Long id;

    private String departure;

    private String arrival;

    private int segmentCount;

    /**
     * Constructs a DestinationDto from a Destination model.
     *
     * @param destination the destination model
     */
    public DestinationDto(Destination destination) {
        this.id = destination.getId();
        this.departure = destination.getDeparture();
        this.arrival = destination.getArrival();
        this.segmentCount = destination.getSegmentCount();
    }

}
