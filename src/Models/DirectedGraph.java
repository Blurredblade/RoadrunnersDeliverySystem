package Models;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class DirectedGraph {
    static int NodeNum = 1;
    private HashMap<Integer, ArrayList<DirectedGraphEdge>> adjList = new HashMap();

    // Create an Adjacency list for calculation
    public DirectedGraph(String file) throws IOException { generateEdges(file); }

    // Returns adjacency list size
    public int size() { return adjList.size(); }

    // Returns a list of edges going out fromN that intersections
    public ArrayList<DirectedGraphEdge> streetsConnectingTo(int intersection) { return adjList.get(intersection); }

    // Returns list of all intersections and its edges going out from that intersection
    public ArrayList<DirectedGraphEdge> edges() {
        ArrayList list = new ArrayList<DirectedGraphEdge>();

        for (int intersection : adjList.keySet()) {
            ArrayList<DirectedGraphEdge> currentStreet = adjList.get(intersection);
            for (DirectedGraphEdge street : currentStreet) { list.add(street); }
        }
        return list;
    }

    // Returns intersections' streets
    public Iterable<Integer> intersectionStreets() {
        HashSet edgeInfo = new HashSet();
        for (DirectedGraphEdge edge : edges()) {
            edgeInfo.add(edge.fromNode());
            edgeInfo.add(edge.toNode());
        }
        return edgeInfo;
    }

    // Remove an Intersection
    public void removeIntersection(int node) { //remove Node
        if (adjList.containsKey(node)) {
            //adjList.entrySet().removeIf(e -> e.getValue().equals("6"));
            /**
            Object[] arr = adjList.get(node).toArray();
            for (int f = 0; f < arr.length; f++) {
                System.out.println(arr[f] + " " +node);
                adjList.remove(arr[f], node);
            };
             **/
            //System.out.println("TEEEST: " + adjList.get(node));
            adjList.remove(node);
        } else {
            System.out.println("Sorry, that intersection does not exist.");
        }
    }

    // Create Graph Edges
    private void generateEdges(String file) {
        String line = "";
        String cvsSplitBy = ",";
        // *** Processing East-West Streets Only *** (Columns 1: "Street Name" and Column 2: "Street Type" in intersectionStreets.csv)
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((line = br.readLine()) != null) {
                String[] intersections = line.split(cvsSplitBy);
                switch(intersections[1]) {
                    case "0": // One way East
                        int i = 0;
                        int newNodeNum4;
                        // Processing
                        while (i < 6 ) {
                            newNodeNum4 = NodeNum+1;
                            addEdge(new DirectedGraphEdge(NodeNum, newNodeNum4, 1));
                            NodeNum = NodeNum +1;
                            i++;
                        }
                        // Update Counts
                        NodeNum++;
                        break;
                    case "1": // One Way West
                        int j = 0;
                        int newNodeNum5;
                        // Processing
                        while (j < 6 ) {
                            newNodeNum5 = NodeNum+1;
                            addEdge(new DirectedGraphEdge(newNodeNum5, NodeNum, 1));
                            NodeNum = NodeNum +1;
                            j++;
                        }
                        // Update Counts
                        NodeNum++;
                        break;
                    case "4": // W/E Two Way
                        int k = 0;
                        // Processing
                        while (k < 6 ) {
                            int DontChangeNodeNum = NodeNum + 1;
                            addEdge(new DirectedGraphEdge(DontChangeNodeNum , NodeNum, 1));
                            addEdge(new DirectedGraphEdge(NodeNum, DontChangeNodeNum, 1));
                            NodeNum++; // Now update NodeNum
                            k++;

                        }
                        // Update Counts
                        NodeNum++;
                        break;
                    case "6": //Obstacle
                        break;
                    default: break;
                }
            }
        } catch (IOException e) { e.printStackTrace(); }


        // *** Processing North-South Streets Only *** (Columns 3: "Street Name" and Column 4: "Street Type" in intersectionStreets.csv)
        NodeNum = 1; // Reset Node Number
        int colCount = 1;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((line = br.readLine()) != null) {
                String[] intersections = line.split(cvsSplitBy);
                switch(intersections[3]) {
                    case "2": //One Way North ;
                        NodeNum = colCount;
                        int l = 0;
                        int newNodeNum3;
                        // Processing
                        while (l < 6 ) {
                            newNodeNum3 = NodeNum + 7;
                            addEdge(new DirectedGraphEdge(newNodeNum3, NodeNum, 1));
                            NodeNum = NodeNum + 7;
                            l++;
                        }
                        // Update Counts
                        NodeNum++;
                        colCount++;
                        break;
                    case "3": //One Way South
                        NodeNum = colCount;
                        int m = 0;
                        int newNodeNum1;
                        // Processing
                        while (m < 6 ) {
                            newNodeNum1 = NodeNum + 7;
                            addEdge(new DirectedGraphEdge(NodeNum, newNodeNum1, 1));
                            NodeNum = NodeNum + 7;
                            m++;
                        }
                        // Update Counts
                        NodeNum++;
                        colCount++;
                        break;
                    case "5": // N/S Two Way
                        NodeNum = colCount;
                        int n = 0;
                        int newNodeNum2;
                        // Processing
                        while (n < 6 ) {
                            newNodeNum2 = NodeNum + 7;
                            addEdge(new DirectedGraphEdge(NodeNum, newNodeNum2, 1));
                            addEdge(new DirectedGraphEdge(newNodeNum2, NodeNum, 1));
                            NodeNum = NodeNum + 7;
                            n++;
                        }
                        // Update Counts
                        NodeNum++;
                        colCount++;
                        break;
                    case "6": //Obstacle
                        break;
                    default: break;
                }
            }
        } catch (IOException e) { e.printStackTrace(); }
    }

    // Add Edge toNode Graph
    public void addEdge(DirectedGraphEdge newEdge) {
        if (!adjList.containsKey(newEdge.fromNode())) adjList.put(newEdge.fromNode(), new ArrayList<DirectedGraphEdge>());
        ArrayList<DirectedGraphEdge> currentEdges = adjList.get(newEdge.fromNode());
        boolean edgeExists = false;
        for (int i = 0; i < currentEdges.size(); i++) {
            if (currentEdges.get(i).toNode() == newEdge.toNode()) {
                currentEdges.set(i, newEdge);
                edgeExists = true;
                break;
            }
        }
        if (!edgeExists) currentEdges.add(newEdge);
        adjList.put(newEdge.fromNode(), currentEdges);
    }

    // Convert toNode string and print it
    public String toString() {
        String string = "";
        for (int from : adjList.keySet()) {
            ArrayList<DirectedGraphEdge> currentEdges = adjList.get(from);
            string += from + " = {";
            //if (currentEdges.size() == 0) string += "-,";
            for (DirectedGraphEdge edge : currentEdges) string += edge.toNode() + " ";
            string += "}\n";
        }
        return string;
    }
}
