package com.app.TicketUK.controller;


import com.app.TicketUK.dto.FindTicketDto;
import com.app.TicketUK.dto.FindTicketModelDto;
import com.app.TicketUK.dto.SaveTicketDto;
import com.app.TicketUK.dto.SaveTicketModelDto;
import com.app.TicketUK.model.Segment;
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

    @PostMapping("/find-ticket")
    public ResponseEntity<FindTicketModelDto> calculateCost(@RequestBody FindTicketDto findTicketDto) {
        FindTicketModelDto cost = travelService.calculateOptimalTravelCost(findTicketDto);
        return ResponseEntity.ok(cost);
    }

    @PostMapping("/save-ticket")
    public ResponseEntity<SaveTicketModelDto> saveTicket(@RequestBody SaveTicketDto saveTicketDto) {
        SaveTicketModelDto savedTicket = travelService.saveTicket(saveTicketDto);
        return ResponseEntity.ok(savedTicket);
    }
}