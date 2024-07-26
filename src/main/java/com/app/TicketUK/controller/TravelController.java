package com.app.TicketUK.controller;


import com.app.TicketUK.dto.*;
import com.app.TicketUK.service.TravelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Controller for handling travel-related requests.
 */
@RestController
@RequestMapping("/travel")
public class TravelController {

    @Autowired
    private TravelService travelService;

    /**
     * Retrieves all travel segments.
     *
     * @return a list of all travel segments
     */
    @GetMapping("/all")
    public ResponseEntity<List<DestinationDto>> getAllSegments() {
        return ResponseEntity.ok(travelService.getSegments());
    }

    /**
     * Adds a new travel segment.
     *
     * @param destinationDto the travel segment data
     * @return the added travel segment
     */
    @PostMapping("/add-segment")
    public ResponseEntity<DestinationDto> addSegment(@RequestBody DestinationDto destinationDto) {
        return ResponseEntity.ok(travelService.addSegment(destinationDto));
    }

    /**
     * Calculates the cost of a ticket based on the travel segment.
     *
     * @param ticketSearchDto the ticket search data
     * @return the ticket with price details
     */
    @PostMapping("/find-ticket")
    public ResponseEntity<TicketWithPriceDto> calculateCost(@RequestBody TicketSearchDto ticketSearchDto) {
        TicketWithPriceDto cost = travelService.calculateOptimalTravelCost(ticketSearchDto);
        return ResponseEntity.ok(cost);
    }

    /**
     * Saves a ticket purchase.
     *
     * @param ticketDto the ticket data
     * @return the saved ticket purchase
     */
    @PostMapping("/save-ticket")
    public ResponseEntity<TicketPurchase> saveTicket(@RequestBody TicketDto ticketDto) {
        TicketPurchase savedTicket = travelService.saveTicket(ticketDto);
        return ResponseEntity.ok(savedTicket);
    }
}