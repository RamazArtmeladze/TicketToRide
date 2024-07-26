package com.app.TicketUK.service;

import com.app.TicketUK.model.Destination;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

public class DijkstraAlgorithm {

    @Getter
    @Setter
    @ToString
    public static class Node {
        private final String cityName;
        private final List<Edge> destinations;
        private int distance = Integer.MAX_VALUE;
        private Node previous;

        public Node(String cityName) {
            this.cityName = cityName;
            this.destinations = new ArrayList<>();
        }

        public void addEdge(Node targetNode, int weight) {
            destinations.add(new Edge(this, targetNode, weight));
        }
    }

    public record Edge(Node source, Node target, int weight) {
    }

    public static class Graph {

        private final Map<String, Node> nodes;

        public Graph(List<Destination> destinations) {
            nodes = new HashMap<>(); // value is null

            for (Destination destination : destinations) {
                // create node in nodes hashMap for each segment
                nodes.putIfAbsent(destination.getDeparture(), new Node(destination.getDeparture()));
                nodes.putIfAbsent(destination.getArrival(), new Node(destination.getArrival()));

                Node fromNode = nodes.get(destination.getDeparture());
                Node toNode = nodes.get(destination.getArrival());
                int weight = destination.getSegmentCount();

                //add edge to node
                fromNode.addEdge(toNode, weight);
                toNode.addEdge(fromNode, weight);
            }
        }

        public Node getNode(String cityName) {
            return nodes.get(cityName);
        }

        public Collection<Node> getNodes() {
            return nodes.values();
        }
    }

    public static List<Node> findShortestPath(Graph graph, Node departureNode, Node arrivalNode) {
        PriorityQueue<Node> frontier = new PriorityQueue<>(Comparator.comparingInt(Node::getDistance));
        Set<Node> exploredNodes = new HashSet<>();
        Map<Node, Integer> distances = new HashMap<>();

        for (Node node : graph.getNodes()) {
            distances.put(node, node.getDistance());
        }

        distances.put(departureNode, 0);  // update distance for departureNode node
        departureNode.setDistance(0); // set distance for departureNode
        frontier.add(departureNode); // add departureNode in frontier

        while (!frontier.isEmpty()) {
            // retrieve and remove node with the smallest distance from frontier (from city)  and then return this value
            Node currentNode = frontier.poll();

            if (currentNode.equals(arrivalNode)) {
                List<Node> path = new ArrayList<>();

                for (Node node = arrivalNode; node != null; node = node.getPrevious()) {
                    path.add(node);
                }
                Collections.reverse(path);
                return path;
            }

            if (exploredNodes.contains(currentNode)) continue;
            exploredNodes.add(currentNode);

            for (Edge edge : currentNode.getDestinations()) {
                Node neighbor = edge.target();

                int newDist = distances.get(currentNode) + edge.weight();

                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    neighbor.setDistance(newDist);
                    neighbor.setPrevious(currentNode);
                    frontier.add(neighbor);
                }
            }
        }

        return Collections.emptyList();
    }
}