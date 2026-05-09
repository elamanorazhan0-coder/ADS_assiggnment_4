import java.util.*;

public class WeightedGraph {

    public static class Edge {
        private String destination;
        private int weight;

        public Edge(String destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }

        public String getDestination() {
            return destination;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public String toString() {
            return destination + "(" + weight + ")";
        }
    }

    private Map<String, List<Edge>> adjacencyList;

    public WeightedGraph() {
        this.adjacencyList = new HashMap<>();
    }

    public void addVertex(String vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(String vertex1, String vertex2, int weight) {
        adjacencyList.putIfAbsent(vertex1, new ArrayList<>());
        adjacencyList.putIfAbsent(vertex2, new ArrayList<>());

        adjacencyList.get(vertex1).add(new Edge(vertex2, weight));
        adjacencyList.get(vertex2).add(new Edge(vertex1, weight));
    }

    public List<Edge> getEdges(String vertex) {
        return adjacencyList.getOrDefault(vertex, new ArrayList<>());
    }

    public Set<String> getAllVertices() {
        return adjacencyList.keySet();
    }

    public void printGraph() {
        System.out.println("Weighted Graph (Adjacency List):");
        System.out.println("================================");
        for (String vertex : adjacencyList.keySet()) {
            System.out.print(vertex + " → ");
            List<Edge> edges = adjacencyList.get(vertex);
            for (int i = 0; i < edges.size(); i++) {
                System.out.print(edges.get(i));
                if (i < edges.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
        System.out.println("================================\n");
    }
}