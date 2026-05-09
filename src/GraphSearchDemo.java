/**
 * Task 3: Implementation of DFS and BFS algorithms
 *
 * This program implements Depth First Search and Breadth First Search
 * for the graph given in Task 1.
 *
 * Graph Adjacency Lists:
 * A: C B D
 * B: A C E G
 * C: A B D
 * D: C A
 * E: G F B
 * F: G E
 * G: F B
 *
 * Source Node: A
 */
public class GraphSearchDemo {

    public static void main(String[] args) {
        System.out.println("ADS Assignment 4 - Task 3: DFS and BFS Implementation");

        Graph graph = createGraphFromTask1();

        System.out.println("Building graph from Task 1...\n");
        graph.printGraph();

        System.out.println("DEPTH FIRST SEARCH (DFS)");

        DepthFirstSearch dfs = new DepthFirstSearch(graph, "A");
        dfs.printResults();

        System.out.println("BREADTH FIRST SEARCH (BFS)");

        BreadthFirstSearch bfs = new BreadthFirstSearch(graph, "A");
        bfs.printResults();

        System.out.println("COMPARISON WITH TASKS 1 AND 2");

        System.out.println("Task 1 (DFS Manual Trace): A → C → B → E → G → F → D");
        System.out.println("Task 3 (DFS Implementation): " + String.join(" → ", dfs.getVisitOrder()));

        boolean dfsMatch = dfs.getVisitOrder().equals(java.util.Arrays.asList("A", "C", "B", "E", "G", "F", "D"));
        System.out.println("DFS Match: " + (dfsMatch ? "✓ PASSED" : "✗ FAILED") + "\n");

        System.out.println("Task 2 (BFS Manual Trace): A → C → B → D → E → G → F");
        System.out.println("Task 3 (BFS Implementation): " + String.join(" → ", bfs.getVisitOrder()));

        boolean bfsMatch = bfs.getVisitOrder().equals(java.util.Arrays.asList("A", "C", "B", "D", "E", "G", "F"));
        System.out.println("BFS Match: " + (bfsMatch ? "✓ PASSED" : "✗ FAILED") + "\n");

        if (dfsMatch && bfsMatch) {
            System.out.println("All implementations match manual traces successfully!");
        }
    }

    private static Graph createGraphFromTask1() {
        Graph graph = new Graph(7);

        String[] vertices = {"A", "B", "C", "D", "E", "F", "G"};
        for (String vertex : vertices) {
            graph.addVertex(vertex);
        }

        // A: C B D
        addEdgesInOrder(graph, "A", new String[]{"C", "B", "D"});

        // B: A C E G (A already added, add C E G)
        addEdgesInOrder(graph, "B", new String[]{"C", "E", "G"});

        // C: A B D (all already added in proper order)
        addEdgesInOrder(graph, "C", new String[]{"D"});

        // D: C A (all already added)
        // No new edges needed

        // E: G F B
        addEdgesInOrder(graph, "E", new String[]{"G", "F"});

        // F: G E (all already added)
        // No new edges needed

        // G: F B (all already added)
        // No new edges needed

        return graph;
    }

    private static void addEdgesInOrder(Graph graph, String vertex, String[] neighbors) {
        for (String neighbor : neighbors) {
            graph.addEdge(vertex, neighbor);
        }
    }
}