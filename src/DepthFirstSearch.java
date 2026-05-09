import java.util.*;

public class DepthFirstSearch {
    private Map<String, Boolean> marked;
    private Map<String, String> edgeTo;
    private List<String> visitOrder;
    private String source;

    public DepthFirstSearch(Graph graph, String source) {
        this.source = source;
        marked = new HashMap<>();
        edgeTo = new HashMap<>();
        visitOrder = new ArrayList<>();

        // Initialize all vertices as not visited
        for (String vertex : graph.getAllVertices()) {
            marked.put(vertex, false);
        }

        // Perform DFS from source
        dfs(graph, source);
    }

    private void dfs(Graph graph, String vertex) {
        // Mark current vertex as visited
        marked.put(vertex, true);
        visitOrder.add(vertex);

        System.out.println("Visiting: " + vertex);

        // Visit all adjacent vertices
        for (String adjacentVertex : graph.getAdjacentVertices(vertex)) {
            if (!marked.get(adjacentVertex)) {
                edgeTo.put(adjacentVertex, vertex);
                dfs(graph, adjacentVertex);
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

    public List<String> getVisitOrder() {
        return new ArrayList<>(visitOrder);
    }

    public void printResults() {
        System.out.println("\n========================================");
        System.out.println("DEPTH FIRST SEARCH (DFS) RESULTS");
        System.out.println("========================================");
        System.out.println("Source vertex: " + source);
        System.out.println("\nVisit Order: " + String.join(" → ", visitOrder));

        System.out.println("\nEdge-To Tree (parent relationships):");
        System.out.println("  " + source + ": (source)");
        for (String vertex : visitOrder) {
            if (!vertex.equals(source)) {
                System.out.println("  " + vertex + ": from " + edgeTo.get(vertex));
            }
        }
        System.out.println("========================================\n");
    }
}