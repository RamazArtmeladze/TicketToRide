package com.app.TicketUK.service;

import com.app.TicketUK.model.Ticket;
import com.app.TicketUK.model.User;
import com.app.TicketUK.repository.SegmentRepository;
import com.app.TicketUK.model.Segment;
import com.app.TicketUK.repository.TicketRepository;
import com.app.TicketUK.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TravelService {

    private final SegmentRepository segmentRepository;
    private final TicketRepository ticketRepository;
    private final UserDataService userDataService;
    private final UserRepository userRepository;

    public List<Segment> getSegments() {
        return segmentRepository.findAll();
    }

    public Segment addSegment(Segment segment) {
        return segmentRepository.save(segment);
    }

    public Ticket calculateOptimalTravelCost(String fromCity, String toCity) {
        List<Segment> segments = segmentRepository.findAll();

        DijkstraAlgorithm.Graph graph = new DijkstraAlgorithm.Graph(segments); // create nodes with edges
        DijkstraAlgorithm.Node sourceNode = graph.getNode(fromCity);
        DijkstraAlgorithm.Node targetNode = graph.getNode(toCity);

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

        return createTicket(fromCity, toCity, totalDistance, ticketPrice);
    }

    private static int calculateTicketPrice(int totalDistance) {
        int higher = totalDistance / 3;
        int remainder = totalDistance % 3;

        int firstPart = higher * 10;
        int secondPart = (remainder == 1) ? 5 : (remainder == 2) ? 7 : 0;

        int ticketPrice = firstPart + secondPart;
        return ticketPrice;
    }

    private Ticket createTicket(String fromCity, String toCity, int totalDistance, int ticketPrice) {
        Long userId = userDataService.getAuthenticatedUserID().getUserId();
        int balance = userRepository.findBalance(userId);

        if (ticketPrice <= balance) {
            Ticket ticket = Ticket.builder()
                    .fromCity(fromCity)
                    .toCity(toCity)
                    .segmentCount(totalDistance)
                    .price(ticketPrice)
                    .userId(userId)
                    .build();

            ticketRepository.save(ticket);

            User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
            user.setBalance(balance - ticketPrice);
            userRepository.save(user);

            return ticket;
        } else {
            throw new RuntimeException("you don't have balance to purchase the ticket.");
        }
    }
}
