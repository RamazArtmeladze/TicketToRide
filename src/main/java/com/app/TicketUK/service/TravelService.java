package com.app.TicketUK.service;

import com.app.TicketUK.dto.*;
import com.app.TicketUK.model.Destination;
import com.app.TicketUK.model.Ticket;
import com.app.TicketUK.repository.SegmentRepository;
import com.app.TicketUK.repository.TicketRepository;
import com.app.TicketUK.service.DijkstraAlgorithm.Edge;
import com.app.TicketUK.service.DijkstraAlgorithm.Graph;
import com.app.TicketUK.service.DijkstraAlgorithm.Node;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.app.TicketUK.service.DijkstraAlgorithm.findShortestPath;

@Service
@RequiredArgsConstructor
public class TravelService {

    private final SegmentRepository segmentRepository;
    private final TicketRepository ticketRepository;
    private final UserDataService userDataService;

    public List<DestinationDto> getSegments() {
        return segmentRepository.findAll()
                .stream()
                .map(DestinationDto::new)
                .toList();
    }

    public DestinationDto addSegment(DestinationDto destinationDto) {
        Destination destination = new Destination();
        destination.setId(destinationDto.getId());
        destination.setArrival(destinationDto.getArrival());
        destination.setDeparture(destinationDto.getDeparture());
        destination.setSegmentCount(destinationDto.getSegmentCount());
        Destination savedDestination = segmentRepository.save(destination);

        return new DestinationDto(savedDestination);
    }

    public TicketWithPrice calculateOptimalTravelCost(TicketSearchDto ticketSearchDto) {
        List<Destination> destinations = segmentRepository.findAll();

        Graph graph = new DijkstraAlgorithm.Graph(destinations);
        Node sourceNode = graph.getNode(ticketSearchDto.getDeparture());
        Node targetNode = graph.getNode(ticketSearchDto.getArrival());

        List<Node> shortestPath = findShortestPath(graph, sourceNode, targetNode);

        int totalDistance = 0;

        for (int i = 0; i < shortestPath.size() - 1; i++) {
            Node current = shortestPath.get(i);
            Node next = shortestPath.get(i + 1);

            for (Edge edge : current.getDestinations()) {
                if (edge.target().equals(next)) {
                    totalDistance += edge.weight();
                    break;
                }
            }
        }

        int ticketPrice = calculateTicketPrice(totalDistance);

        return new TicketWithPrice(totalDistance, ticketPrice, "GBP");
    }

    private static int calculateTicketPrice(int totalDistance) {
        int divisionResult = totalDistance / 3;
        int divisionRemainder = totalDistance % 3;

        return divisionResult * 10 + ((divisionRemainder == 1) ? 5 : (divisionRemainder == 2) ? 7 : 0);
    }

    public TicketPurchase saveTicket(TicketDto ticketDto) {
        int change = ticketDto.getTravellerAmount() - ticketDto.getPrice();
        Long userId = userDataService.getAuthenticatedUserID().getUserId();

        if (change >= 0) {
            ticketRepository.save(Ticket.builder()
                    .departure(ticketDto.getDeparture())
                    .arrival(ticketDto.getArrival())
                    .segmentCount(ticketDto.getSegmentCount())
                    .price(ticketDto.getPrice())
                    .currency("GBP")
                    .travellerAmount(ticketDto.getTravellerAmount())
                    .traveller(ticketDto.getTraveller())
                    .userId(userId)
                    .build());


            return new PurchaseSuccess("Success", change, "GBP");
        } else {
            return new PurchaseFailure("Failure", Math.abs(change), "GBP");
        }
    }
}