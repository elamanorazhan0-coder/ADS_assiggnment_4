import java.util.*;

public class DijkstraShortestPath {

    private static class VertexDistance implements Comparable<VertexDistance> {
        String vertex;
        int distance;

        public VertexDistance(String vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(VertexDistance other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    private Map<String, Integer> distances;
    private Map<String, String> predecessors;
    private Set<String> visited;
    private String source;

    public DijkstraShortestPath(WeightedGraph graph, String source) {
        this.source = source;
        this.distances = new HashMap<>();
        this.predecessors = new HashMap<>();
        this.visited = new HashSet<>();

        dijkstra(graph, source);
    }

    private void dijkstra(WeightedGraph graph, String source) {
        PriorityQueue<VertexDistance> priorityQueue = new PriorityQueue<>();

        for (String vertex : graph.getAllVertices()) {
            distances.put(vertex, Integer.MAX_VALUE);
        }
        distances.put(source, 0);

        priorityQueue.add(new VertexDistance(source, 0));

        System.out.println("║            DIJKSTRA'S ALGORITHM EXECUTION TRACE            ║");

        System.out.println("Initial State:");
        System.out.println("  Source: " + source);
        System.out.println("  Distance to " + source + ": 0");
        System.out.println("  Distance to all others: ∞\n");

        int step = 1;

        while (!priorityQueue.isEmpty()) {
            VertexDistance current = priorityQueue.poll();
            String currentVertex = current.vertex;

            if (visited.contains(currentVertex)) {
                continue;
            }

            visited.add(currentVertex);

            System.out.println("Step " + step + ": Select vertex '" + currentVertex +
                    "' (distance = " + distances.get(currentVertex) + ")");
            System.out.println("  Visited vertices: " + visited);

            for (WeightedGraph.Edge edge : graph.getEdges(currentVertex)) {
                String neighbor = edge.getDestination();

                if (!visited.contains(neighbor)) {
                    int newDistance = distances.get(currentVertex) + edge.getWeight();
                    int oldDistance = distances.get(neighbor);

                    if (newDistance < oldDistance) {
                        distances.put(neighbor, newDistance);
                        predecessors.put(neighbor, currentVertex);
                        priorityQueue.add(new VertexDistance(neighbor, newDistance));

                        System.out.println("  Updated: " + neighbor +
                                " from " + (oldDistance == Integer.MAX_VALUE ? "∞" : oldDistance) +
                                " to " + newDistance +
                                " (via " + currentVertex + ")");
                    } else {
                        System.out.println("  Checked: " + neighbor +
                                " (current " + (oldDistance == Integer.MAX_VALUE ? "∞" : oldDistance) +
                                " ≤ " + newDistance + ", no update)");
                    }
                }
            }

            System.out.println("  Current distances: " + getDistancesString());
            System.out.println();
            step++;
        }

        System.out.println("Algorithm complete!\n");
    }

    public int getDistance(String vertex) {
        return distances.getOrDefault(vertex, Integer.MAX_VALUE);
    }

    public List<String> getPath(String destination) {
        if (!predecessors.containsKey(destination) && !destination.equals(source)) {
            return null; // No path exists
        }

        LinkedList<String> path = new LinkedList<>();
        String current = destination;

        while (current != null) {
            path.addFirst(current);
            current = predecessors.get(current);
        }

        return path;
    }

    public void printResults(String destination) {
        System.out.println("SHORTEST PATH RESULTS");

        System.out.println("From: " + source);
        System.out.println("To: " + destination);
        System.out.println();

        System.out.println("Shortest distances from " + source + ":");
        for (Map.Entry<String, Integer> entry : distances.entrySet()) {
            String vertex = entry.getKey();
            int distance = entry.getValue();
            String distStr = (distance == Integer.MAX_VALUE) ? "∞" : String.valueOf(distance);
            System.out.println("  " + vertex + ": " + distStr);
        }
        System.out.println();

        List<String> path = getPath(destination);
        if (path != null) {
            System.out.println("Shortest path from " + source + " to " + destination + ":");
            System.out.println("  Path: " + String.join(" → ", path));
            System.out.println("  Total distance: " + getDistance(destination));
            System.out.println();

            System.out.println("Path breakdown:");
            for (int i = 0; i < path.size() - 1; i++) {
                String from = path.get(i);
                String to = path.get(i + 1);
                int segmentDist = getDistance(to) - getDistance(from);
                System.out.println("  " + (i + 1) + ". " + from + " → " + to + ": " + segmentDist);
            }
            System.out.println("  Total: " + getDistance(destination));
        } else {
            System.out.println("No path exists from " + source + " to " + destination);
        }

        System.out.println("CALCULATION COMPLETE");
    }

    private String getDistancesString() {
        StringBuilder sb = new StringBuilder("{");
        List<String> entries = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : distances.entrySet()) {
            String dist = (entry.getValue() == Integer.MAX_VALUE) ? "∞" : String.valueOf(entry.getValue());
            entries.add(entry.getKey() + "=" + dist);
        }
        sb.append(String.join(", ", entries));
        sb.append("}");
        return sb.toString();
    }
}