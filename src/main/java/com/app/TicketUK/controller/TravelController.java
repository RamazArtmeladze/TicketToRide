package com.app.TicketUK.controller;


import com.app.TicketUK.dto.*;
import com.app.TicketUK.service.TravelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/travel")
public class TravelController {

    @Autowired
    private TravelService travelService;

    @GetMapping("/all")
    public ResponseEntity<List<DestinationDto>> getAllSegments() {
        return ResponseEntity.ok(travelService.getSegments());
    }

    @PostMapping("/add-segment")
    public ResponseEntity<DestinationDto> addSegment(@RequestBody DestinationDto destinationDto) {
        return ResponseEntity.ok(travelService.addSegment(destinationDto));
    }

    @PostMapping("/find-ticket")
    public ResponseEntity<TicketWithPriceDto> calculateCost(@RequestBody TicketSearchDto ticketSearchDto) {
        TicketWithPriceDto cost = travelService.calculateOptimalTravelCost(ticketSearchDto);
        return ResponseEntity.ok(cost);
    }

    @PostMapping("/save-ticket")
    public ResponseEntity<TicketPurchase> saveTicket(@RequestBody TicketDto ticketDto) {
        TicketPurchase savedTicket = travelService.saveTicket(ticketDto);
        return ResponseEntity.ok(savedTicket);
    }
}