package com.app.TicketUK.service;

import com.app.TicketUK.dto.FindTicketDto;
import com.app.TicketUK.dto.FindTicketModelDto;
import com.app.TicketUK.dto.SaveTicketDto;
import com.app.TicketUK.dto.SaveTicketModelDto;
import com.app.TicketUK.model.Ticket;
import com.app.TicketUK.repository.SegmentRepository;
import com.app.TicketUK.model.Segment;
import com.app.TicketUK.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TravelService {

    private final SegmentRepository segmentRepository;
    private final TicketRepository ticketRepository;
    private final UserDataService userDataService;

    public List<Segment> getSegments() {
        return segmentRepository.findAll();
    }

    public Segment addSegment(Segment segment) {
        return segmentRepository.save(segment);
    }

    public FindTicketModelDto calculateOptimalTravelCost(FindTicketDto findTicketDto) {
        List<Segment> segments = segmentRepository.findAll();

        DijkstraAlgorithm.Graph graph = new DijkstraAlgorithm.Graph(segments); // create nodes with edges
        DijkstraAlgorithm.Node sourceNode = graph.getNode(findTicketDto.getFromCity());
        DijkstraAlgorithm.Node targetNode = graph.getNode(findTicketDto.getToCity());

        List<DijkstraAlgorithm.Node> shortestPath = DijkstraAlgorithm.findShortestPath(graph, sourceNode, targetNode);

        int totalDistance = 0;

        for (int i = 0; i < shortestPath.size() - 1; i++) {

            DijkstraAlgorithm.Node current = shortestPath.get(i);
            DijkstraAlgorithm.Node next = shortestPath.get(i + 1);

            for (DijkstraAlgorithm.Edge edge : current.getEdges()) {
                if (edge.target().equals(next)) {
                    totalDistance += edge.weight();
                    break;
                }
            }
        }

        int ticketPrice = calculateTicketPrice(totalDistance);

        return findTicket(totalDistance, ticketPrice);
    }

    private static int calculateTicketPrice(int totalDistance) {
        int higher = totalDistance / 3;
        int remainder = totalDistance % 3;

        int firstPart = higher * 10;
        int secondPart = (remainder == 1) ? 5 : (remainder == 2) ? 7 : 0;

        int ticketPrice = firstPart + secondPart;

        return ticketPrice;
    }

    private FindTicketModelDto findTicket(int totalDistance, int ticketPrice) {

        FindTicketModelDto ticket = FindTicketModelDto.builder()
                .segmentCount(totalDistance)
                .price(ticketPrice)
                .currency("GBP")
                .build();
        return ticket;
    }

    public SaveTicketModelDto saveTicket(SaveTicketDto saveTicketDto) {
        int change = saveTicketDto.getTravellerAmount() - saveTicketDto.getPrice();
        Long userId = userDataService.getAuthenticatedUserID().getUserId();

        if (change >= 0) {
            SaveTicketModelDto ticket = SaveTicketModelDto.builder()
                    .result("Success")
                    .change(change)
                    .currency("GBP")
                    .build();

            Ticket savedTicket = Ticket.builder()
                    .fromCity(saveTicketDto.getFromCity())
                    .toCity(saveTicketDto.getToCity())
                    .segments(saveTicketDto.getSegments())
                    .price(saveTicketDto.getPrice())
                    .currency("GBP")
                    .travellerAmount(saveTicketDto.getTravellerAmount())
                    .traveller(saveTicketDto.getTraveller())
                    .userId(userId)
                    .build();
            ticketRepository.save(savedTicket);

            return ticket;
        }else {
            SaveTicketModelDto ticket = SaveTicketModelDto.builder()
                    .result("failure")
                    .change(change)
                    .currency("GBP")
                    .build();

            return ticket;
        }
    }
}
