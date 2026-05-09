public class ScottishRoadNetwork {

    public static void main(String[] args) {
        System.out.println("ADS Assignment 4 - Task 5: Dijkstra's Algorithm");
        System.out.println("Scottish Road Network Analysis");

        WeightedGraph roadNetwork = createScottishRoadNetwork();

        System.out.println("Building Scottish Road Network...\n");
        roadNetwork.printGraph();

        String source = "Edinburgh";
        String destination = "Dundee";

        System.out.println("Finding shortest path from " + source + " to " + destination + "...\n");

        DijkstraShortestPath dijkstra = new DijkstraShortestPath(roadNetwork, source);
        dijkstra.printResults(destination);

        System.out.println("VERIFICATION WITH TASK 4");

        System.out.println("Task 4 (Manual Calculation):");
        System.out.println("  Shortest Path: Edinburgh → Stirling → Perth → Dundee");
        System.out.println("  Total Distance: 150");
        System.out.println();

        System.out.println("Task 5 (Dijkstra Implementation):");
        System.out.println("  Shortest Path: " + String.join(" → ", dijkstra.getPath(destination)));
        System.out.println("  Total Distance: " + dijkstra.getDistance(destination));
        System.out.println();

        boolean pathMatches = dijkstra.getPath(destination).equals(
                java.util.Arrays.asList("Edinburgh", "Stirling", "Perth", "Dundee")
        );
        boolean distanceMatches = dijkstra.getDistance(destination) == 150;

        if (pathMatches && distanceMatches) {
            System.out.println("Implementation matches Task 4 manual calculation!");
        } else {
            System.out.println("Warning: Results differ from Task 4");
        }

        System.out.println("║          SHORTEST PATHS FROM EDINBURGH TO ALL CITIES       ║");

        String[] cities = {"Glasgow", "Stirling", "Perth", "Dundee"};
        for (String city : cities) {
            if (!city.equals(source)) {
                System.out.println(source + " to " + city + ":");
                System.out.println("  Path: " + String.join(" → ", dijkstra.getPath(city)));
                System.out.println("  Distance: " + dijkstra.getDistance(city));
                System.out.println();
            }
        }
    }

    private static WeightedGraph createScottishRoadNetwork() {
        WeightedGraph graph = new WeightedGraph();

        String[] cities = {"Edinburgh", "Glasgow", "Stirling", "Perth", "Dundee"};
        for (String city : cities) {
            graph.addVertex(city);
        }

        graph.addEdge("Glasgow", "Stirling", 50);
        graph.addEdge("Glasgow", "Edinburgh", 70);
        graph.addEdge("Stirling", "Perth", 40);
        graph.addEdge("Stirling", "Edinburgh", 50);
        graph.addEdge("Perth", "Dundee", 60);
        graph.addEdge("Edinburgh", "Perth", 100);

        return graph;
    }
}