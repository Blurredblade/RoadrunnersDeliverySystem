package Models;

import UI.Map.MapController;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Map {
    DirectedGraph graph;
    @FXML private TextArea console;
    public String str;
    public String file;

    public void setFilePath(String filepath) { file = filepath; }

    // Map API
    public void findshortestPath(int start, int finish) {
        String file2 = "src/Models/Intersection.csv";
        try {
            graph = new DirectedGraph(file2); // Test file
            //graph = new DirectedGraph(file); // Select a file from different location
            System.out.println("Map file " + file); // Test output
            System.out.print("Intersections = {Adjacent Intersections}\n");
            System.out.print(graph);
            Dijkstra shortestPath = new Dijkstra(graph);

            // Get the results from the shortest path calculation
            ArrayList<Integer> sourceToDest = shortestPath.Dijkstra(start, finish);
            ArrayList<Integer> returnTrip = shortestPath.Dijkstra(finish, start);

            // Print out result
            String rst1 = "\nShortest Path from Source to Destination: \nFrom" + start + " -> " + finish + ": " + sourceToDest + "\n";
            System.out.print(rst1); setString(rst1);
            String rst2 = "\nShortest Path from Return Trip: \nFrom" + finish + " -> " + start + ": " + returnTrip + "\n";
            System.out.print(rst2); setString(rst2);
            if (sourceToDest.size() == 1) {
                String rst3 = "\nThe destination is not reachable.";
                System.out.println(rst3); setString(rst3);
            }

            // **** Total round trip distance calculation
            int howFar = distance(sourceToDest, returnTrip);
            String rst4 = "\nThe distance to destination and back is " + howFar + " blocks.";
            System.out.println(rst4); setString(rst4);

            // **** Total price calculation
            double cost = price(howFar);
            String rst5 = "\nThe price for the round trip is $" + cost + ".";
            System.out.println(rst5); setString(rst5);

            // **** Estimated Time calculation
            double estTime = estDeliveryTime(sourceToDest.size() - 1);
            String rst6 = "\nThe estimated time to for the delivery is " + estTime + " hours.";
            System.out.println(rst6); setString(rst6);


            setResultString(rst1, rst2, rst4, rst5, rst6);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setResultString(String rst1, String rst2, String rst4, String rst5, String rst6) {
        str = rst1 + rst2 + rst4 + rst5 + rst6;
    }

    // Distance between A and B by blocks
    public int distance(ArrayList<Integer> sourceToDest, ArrayList<Integer> returnTrip) {
        int distance = (sourceToDest.size() - 1) + (returnTrip.size() - 1);
        return distance;
    }

    // Price calculated for the round trip from A to B
    public double price(int distance) {
        DecimalFormat df = new DecimalFormat("#.##");
        double price = 0.7 * distance;
        String formatted = df.format(price);
        return Double.valueOf(formatted);
    }

    // Assuming each block takes an hour to go through from HQ to destination
    public double estDeliveryTime(int distance) {
        double estTime = 1 * distance;
        return estTime;
    }

    public void closeIntersection(int node) {
        graph.removeIntersection(node);
        String rst7 = "\nRemoved intersection #" + node;
        System.out.println(rst7); setString(rst7);
    }

    public void openIntersection(int node) {
        graph.openIntersection(node);
        String rst8 = "\nOpening intersection #" + node;
        System.out.println(rst8); setString(rst8);
    }
    public void printMapToConsole() {
        System.out.println(graph);
        setString("" + graph);
    }

    @FXML
    public void setString(String string) {
        str = string;
    }

    @FXML
    public String appendText() {
        return str;
    }
}
