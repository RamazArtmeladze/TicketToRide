package com.app.TicketUK.controller;


import com.app.TicketUK.model.Segment;
import com.app.TicketUK.model.Ticket;
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
    public ResponseEntity<List<Segment>> getAllSegments() {
        return ResponseEntity.ok(travelService.getSegments());
    }

    @PostMapping("/add-segment")
    public ResponseEntity<Segment> addSegment(@RequestBody Segment segment) {
        Segment savedSegment = travelService.addSegment(segment);
        return ResponseEntity.ok(savedSegment);
    }

    @GetMapping("/calculate-cost")
    public ResponseEntity<Ticket> calculateCost(@RequestParam String fromCity, @RequestParam String toCity) {
        Ticket cost = travelService.calculateOptimalTravelCost(fromCity, toCity);
        return ResponseEntity.ok(cost);
    }
}