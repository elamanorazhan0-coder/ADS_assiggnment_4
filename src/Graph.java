import java.util.*;

public class Graph {
    private final int vertices;
    private final Map<String, List<String>> adjacencyList;

    public Graph(int vertices) {
        this.vertices = vertices;
        this.adjacencyList = new HashMap<>();
    }

    public void addVertex(String vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(String vertex1, String vertex2) {
        adjacencyList.putIfAbsent(vertex1, new ArrayList<>());
        adjacencyList.putIfAbsent(vertex2, new ArrayList<>());

        if (!adjacencyList.get(vertex1).contains(vertex2)) {
            adjacencyList.get(vertex1).add(vertex2);
        }
        if (!adjacencyList.get(vertex2).contains(vertex1)) {
            adjacencyList.get(vertex2).add(vertex1);
        }
    }

    public List<String> getAdjacentVertices(String vertex) {
        return adjacencyList.getOrDefault(vertex, new ArrayList<>());
    }

    public Set<String> getAllVertices() {
        return adjacencyList.keySet();
    }

    public void printGraph() {
        System.out.println("Graph Adjacency List:");
        for (String vertex : adjacencyList.keySet()) {
            System.out.print(vertex + ": ");
            List<String> neighbors = adjacencyList.get(vertex);
            for (int i = 0; i < neighbors.size(); i++) {
                System.out.print(neighbors.get(i));
                if (i < neighbors.size() - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}