import java.util.*;

public class BreadthFirstSearch {
    private Map<String, Boolean> marked;
    private Map<String, String> edgeTo;
    private Map<String, Integer> distTo;
    private List<String> visitOrder;
    private String source;

    public BreadthFirstSearch(Graph graph, String source) {
        this.source = source;
        marked = new HashMap<>();
        edgeTo = new HashMap<>();
        distTo = new HashMap<>();
        visitOrder = new ArrayList<>();

        for (String vertex : graph.getAllVertices()) {
            marked.put(vertex, false);
            distTo.put(vertex, Integer.MAX_VALUE);
        }

        bfs(graph, source);
    }

    private void bfs(Graph graph, String source) {
        Queue<String> queue = new LinkedList<>();

        marked.put(source, true);
        distTo.put(source, 0);
        queue.add(source);
        visitOrder.add(source);

        System.out.println("Starting BFS from: " + source);
        System.out.println("Initial queue: [" + source + "]");

        while (!queue.isEmpty()) {
            String vertex = queue.poll();
            System.out.println("\nDequeued: " + vertex);
            System.out.println("Processing adjacency list: " + graph.getAdjacentVertices(vertex));

            // Visit all adjacent vertices
            for (String adjacentVertex : graph.getAdjacentVertices(vertex)) {
                if (!marked.get(adjacentVertex)) {
                    marked.put(adjacentVertex, true);
                    edgeTo.put(adjacentVertex, vertex);
                    distTo.put(adjacentVertex, distTo.get(vertex) + 1);
                    queue.add(adjacentVertex);
                    visitOrder.add(adjacentVertex);
                    System.out.println("  Visiting: " + adjacentVertex + " (distance: " + distTo.get(adjacentVertex) + ")");
                } else {
                    System.out.println("  " + adjacentVertex + " already visited, skipping");
                }
            }

            if (!queue.isEmpty()) {
                System.out.println("Queue now: " + queue);
            } else {
                System.out.println("Queue is empty - BFS complete");
            }
        }
    }

    public boolean hasPathTo(String vertex) {
        return marked.getOrDefault(vertex, false);
    }

    public List<String> pathTo(String vertex) {
        if (!hasPathTo(vertex)) {
            return null;
        }

        LinkedList<String> path = new LinkedList<>();
        for (String v = vertex; !v.equals(source); v = edgeTo.get(v)) {
            path.addFirst(v);
        }
        path.addFirst(source);
        return path;
    }

    public int distanceTo(String vertex) {
        return distTo.getOrDefault(vertex, Integer.MAX_VALUE);
    }

    public List<String> getVisitOrder() {
        return new ArrayList<>(visitOrder);
    }

    public void printResults() {
        System.out.println("\n========================================");
        System.out.println("BREADTH FIRST SEARCH (BFS) RESULTS");
        System.out.println("========================================");
        System.out.println("Source vertex: " + source);
        System.out.println("\nVisit Order: " + String.join(" → ", visitOrder));

        System.out.println("\nLevel Structure:");
        Map<Integer, List<String>> levels = new HashMap<>();
        for (String vertex : visitOrder) {
            int dist = distTo.get(vertex);
            levels.putIfAbsent(dist, new ArrayList<>());
            levels.get(dist).add(vertex);
        }

        for (int i = 0; i <= Collections.max(levels.keySet()); i++) {
            if (levels.containsKey(i)) {
                System.out.println("  Level " + i + ": " + String.join(", ", levels.get(i)));
            }
        }

        System.out.println("\nEdge-To Tree (parent relationships):");
        System.out.println("  " + source + ": (source)");
        for (String vertex : visitOrder) {
            if (!vertex.equals(source)) {
                System.out.println("  " + vertex + ": from " + edgeTo.get(vertex));
            }
        }

        System.out.println("\nDistance from " + source + ":");
        for (String vertex : visitOrder) {
            System.out.println("  " + vertex + ": " + distTo.get(vertex));
        }
        System.out.println("========================================\n");
    }
}