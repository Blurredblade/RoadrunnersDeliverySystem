package Models;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Map {
    public void findshortestPath(int source, int destination) {
        DirectedGraph graph;
        String file = "src/Models/Intersection.csv";
        try {
            graph = new DirectedGraph(file);
            System.out.print("Intersecctions = {Adjacent Intersections}\n");
            System.out.print(graph);
            Dijkstra shortestPath = new Dijkstra(graph);

            // Get the results from the shortest path calculation
            ArrayList<Integer> sourceToDest = shortestPath.Dijkstra(source, destination);
            ArrayList<Integer> returnTrip = shortestPath.Dijkstra(destination, source);

            // Print out result
            System.out.print("Source to Destination: " + source + " -> " + destination + ": " + sourceToDest + "\n");
            System.out.print("Return Trip: " + destination + " -> " + source + ": " + returnTrip + "\n");
            if (sourceToDest.size() == 1) System.out.println("The destination is not reachable.");

            // **** Total round trip distance calculation
            int howFar = distance(sourceToDest, returnTrip);
            System.out.println("The distance to destination and back is " + howFar + " blocks.");

            // **** Total price calculation
            double cost = price(howFar);
            System.out.println("The price for the round trip is $" + cost + ".");

            // **** Estimated Time calculation
            double estTime = estDeliveryTime(sourceToDest.size() - 1);
            System.out.println("The estimated time to for the delivery is " + estTime + " hours.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Distance between A and B by blocks
    public int distance(ArrayList<Integer> sourceToDest, ArrayList<Integer> returnTrip) {
        int distance = (sourceToDest.size() - 1) + (returnTrip.size() - 1);
        return distance;
    }

    // Price calculated for the round trip from A to B
    private double price(int distance) {
        DecimalFormat df = new DecimalFormat("#.##");
        double price = 0.7 * distance;
        String formatted = df.format(price);
        return Double.valueOf(formatted);
    }

    // Assuming each block takes an hour to go through from HQ to destination
    private double estDeliveryTime(int distance) {
        double estTime = 1 * distance;
        return estTime;
    }


}
