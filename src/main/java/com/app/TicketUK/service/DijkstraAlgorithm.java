package com.app.TicketUK.service;

import com.app.TicketUK.model.Segment;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

public class DijkstraAlgorithm {

    @Getter @Setter
    @ToString
    public static class Node {
        private final String cityName;
        private final List<Edge> edges;
        private int distance = Integer.MAX_VALUE;
        private Node previous;

        public Node(String cityName) {
            this.cityName = cityName;
            this.edges = new ArrayList<>();
        }

        public void addEdge(Node targetNode, int weight) {
            edges.add(new Edge(this, targetNode, weight));
        }
    }

    public record Edge(Node source, Node target, int weight) {
    }

    public static class Graph {
        private final Map<String, Node> nodes;
        public Graph(List<Segment> segments) {
            nodes = new HashMap<>(); // value is null

            for (Segment segment : segments) {
                // create node in nodes hashMap for each segment
                nodes.putIfAbsent(segment.getFromCity(), new Node(segment.getFromCity()));
                nodes.putIfAbsent(segment.getToCity(), new Node(segment.getToCity()));

                Node fromNode = nodes.get(segment.getFromCity());
                Node toNode = nodes.get(segment.getToCity());
                int weight = segment.getSegmentCount();

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

    public static List<Node> findShortestPath(Graph graph, Node source, Node target) {
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(Node::getDistance));
        Map<Node, Integer> distances = new HashMap<>();
        Set<Node> visited = new HashSet<>();


        for (Node node : graph.getNodes()) {
            distances.put(node, node.getDistance());
        }


        distances.put(source, 0);  // update distance for source node
        source.setDistance(0); // set distance for source
        queue.add(source); // add source in queue

        while (!queue.isEmpty()) {
            // retrieve and remove node with the smallest distance from queue (from city)  and then return this value
            Node current = queue.poll();

            if (current.equals(target)) {
                List<Node> path = new ArrayList<>();

                for (Node node = target; node != null; node = node.getPrevious()) {
                    path.add(node);
                }
                Collections.reverse(path);
                return path;
            }

            if (visited.contains(current)) continue;
            visited.add(current);


            for (Edge edge : current.getEdges()) {
                Node neighbor = edge.target();

                int newDist = distances.get(current) + edge.weight();

                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    neighbor.setDistance(newDist);
                    neighbor.setPrevious(current);
                    queue.add(neighbor);
                }
            }
        }

        return Collections.emptyList();
    }
}

