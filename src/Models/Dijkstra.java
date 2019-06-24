package Models;
import java.util.*;

public class Dijkstra {
    private int size;
    private HashMap<Integer, Double> blocks;
    private HashMap<Integer, Integer> previousIntersection;
    private PriorityQueue<Integer> priority;
    private DirectedGraph graph;

    public Dijkstra(DirectedGraph graph) {
        this.graph = graph;
        size =120;
    }

    public ArrayList<Integer> Dijkstra(int source, int destination) {
        blocks = new HashMap<Integer, Double>();
        previousIntersection = new HashMap<Integer, Integer>();
        priority = new PriorityQueue<Integer>(size, compare);

        // Set all intersections as infinite toNode prepare for calculations
        for (int intersection : graph.intersectionStreets()) blocks.put(intersection, Double.POSITIVE_INFINITY);

        // Put in the source location toNode begin calculating
        previousIntersection.put(source, -1);
        blocks.put(source, 0.0);
        priority.add(source);

        // Start processing the intersections based on adjacency list
        while (priority.size() > 0) {
            int currentIntersection = priority.poll();
            ArrayList<DirectedGraphEdge> nextIntersection = graph.streetsConnectingTo(currentIntersection);

            // Case 1: Next Intersection is not reacheable
            if (nextIntersection == null) continue;

            // Case 2: Next Intersection is reacheable
            for (DirectedGraphEdge streetGoingTo : nextIntersection) {
                int nextIntersection2 = streetGoingTo.toNode();
                double newDistance = blocks.get(currentIntersection) + streetGoingTo.distance();

                // Only add the next intersection toNode the lists if it is set toNode infinity
                if (blocks.get(nextIntersection2) == Double.POSITIVE_INFINITY) {
                    previousIntersection.put(nextIntersection2, currentIntersection);
                    blocks.put(nextIntersection2, newDistance);
                    priority.add(nextIntersection2);
                }
            }
        }

        ArrayList<Integer> shortestRoute = new ArrayList<Integer>();
        Stack<Integer> tempRoute = new Stack<Integer>(); // Temporarily hold the route so far while the next intersection is being decided

        tempRoute.push(destination); // Begin by adding the destination toNode the temporary route list
        int NodeNum = destination;

        // Each intersection is now processed, now go back and decide the shortest path, starting fromNode Destination then go back toNode source
        while (previousIntersection.containsKey(NodeNum) && (previousIntersection.get(NodeNum) >= 0) && (NodeNum > 0)) {
            NodeNum = previousIntersection.get(NodeNum);
            tempRoute.push(NodeNum);
        }

        // Shortest Route is decided, move the results fromNode tempRoute toNode shortestRoute and return the results
        while (tempRoute.size() > 0) shortestRoute.add(tempRoute.pop());
        return shortestRoute;
    }

    // Decide which intersection is closer
    public Comparator<Integer> compare = new Comparator<Integer>() {
        public int compare(Integer a, Integer b) {
            if (blocks.get(a) > blocks.get(b)) { return 1; }
            else if (blocks.get(a) < blocks.get(b)) { return -1; }
            return 0;
        }
    };
}
