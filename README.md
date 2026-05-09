TASK 3: DFS and BFS Implementation in Java
===========================================

This Java project implements Depth First Search (DFS) and Breadth First Search (BFS)
algorithms for the graph specified in Task 1.

FILES INCLUDED:
---------------
1. Graph.java - Graph data structure using adjacency lists
2. DepthFirstSearch.java - DFS implementation (based on Sedgewick & Wayne, page 537)
3. BreadthFirstSearch.java - BFS implementation (based on Sedgewick & Wayne, page 539)
4. GraphSearchDemo.java - Main program that runs both algorithms and compares results

COMPILATION INSTRUCTIONS:
--------------------------
1. Navigate to the src directory:
   cd src

2. Compile all Java files:
   javac *.java

3. Run the program:
   java GraphSearchDemo

EXPECTED OUTPUT:
----------------
The program will:
1. Build the graph from Task 1 with adjacency lists
2. Perform DFS starting from vertex A
3. Perform BFS starting from vertex A
4. Compare the outputs with manual traces from Tasks 1 and 2
5. Display whether the implementation matches the expected results

GRAPH SPECIFICATION (from Task 1):
-----------------------------------
A: C B D
B: A C E G
C: A B D
D: C A
E: G F B
F: G E
G: F B

EXPECTED RESULTS:
-----------------
DFS Visit Order: A → C → B → E → G → F → D
BFS Visit Order: A → C → B → D → E → G → F

Both should match Tasks 1 and 2 respectively.

VERIFICATION:
-------------
The program includes automatic verification that compares:
- Task 3 DFS output with Task 1 manual trace
- Task 3 BFS output with Task 2 manual trace

If both match, you will see: "✓ All implementations match manual traces successfully!"

REQUIREMENTS:
-------------
- Java JDK 8 or higher
- No external libraries required (uses only java.util)

AUTHOR:
-------
ADS Assignment 4 - Task 3
Graph Search Algorithms Implementation
