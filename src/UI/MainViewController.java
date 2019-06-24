package UI;

import Models.DirectedGraph;
import Models.Map;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import UI.Login.LoginManager;

import java.io.IOException;

/** Controls the main application screen */
public class MainViewController {
    @FXML private Button logoutButton;
    @FXML private Label  sessionLabel;

    public void initialize() {}

    public void initSessionID(final LoginManager loginManager, String sessionID) {
        sessionLabel.setText(sessionID);
        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                loginManager.logout();
            }
        });
        testMap();
    }

    public void testMap() {
        DirectedGraph graph;
        try {
            //String file = "C:\\Users\\diane\\Desktop\\Dijkstra\\src\\Map2.txt";
            String file = "src/Models/Intersection.csv";
            graph = new DirectedGraph(file);
            System.out.print("Intersecctions = {Adjacent Intersections}\n");
            System.out.print(graph);
            System.out.print("\n");
            Map shortestPath = new Map(graph);
            System.out.print("TESTS\n");
            System.out.print("Test 1: 35 -> 32: " + shortestPath.Dijkstra(35, 32) + "\n");
            graph.removeIntersection(8);
            System.out.print("Test 2: 8 -> 14: " + shortestPath.Dijkstra(8, 14) + "\n");
            System.out.print("Test 3: 31 -> 37: " + shortestPath.Dijkstra(31, 37) + "\n");
            System.out.print("Test 4: 49 -> 3: " + shortestPath.Dijkstra(49, 3) + "\n");
            System.out.print("Test 5: 1 -> 7: " + shortestPath.Dijkstra(1, 7) + "\n");
            System.out.print("Test 6: 7 -> 3: " + shortestPath.Dijkstra(7, 3) + "\n");
            System.out.print("Test 7: 49 -> 7: " + shortestPath.Dijkstra(49, 7) + "\n");
            System.out.print("Test 8: 21 -> 17: " + shortestPath.Dijkstra(21, 17) + "\n");
            System.out.print("Test 9: 8 -> 12: " + shortestPath.Dijkstra(8, 12) + "\n");
            System.out.print("Test 10: 1 -> 43: " + shortestPath.Dijkstra(1, 43) + "\n");
        } catch (IOException e) {}
    }

}
