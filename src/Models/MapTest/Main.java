package Models.MapTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

public class Main {
    // This is a test BFS Shortest Path, it still needs some work but should be done tonight, should be run separately
    static int NodeNum = 1;

    public static void main(String[] args) {
        Graph g = new Graph(98);

        // MIGHT HAVE TO FIX THIS
        String csvFile = "src/Intersection.csv";
        String line = "";
        String cvsSplitBy = ",";


        // WEST AND EAST ONLY
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] intersections = line.split(cvsSplitBy);
                System.out.print("Street Name [" +
                        " N/S Street Name= " +  intersections[0] + " , Street direction=" + intersections[1] +
                        " W/E Street Name= " + intersections[2] + " , Street direction=" + intersections[3] + "]");
                switch(intersections[1]) {
                    case "0": // One way East A0 -> A1
                        System.out.print(" E One Way\n");
                        int i = 0;
                        while (i < 6 ) {
                            System.out.println("Added Edge[ " + NodeNum + ", " + (NodeNum + 1) + "]");
                            g.addEdge(NodeNum, NodeNum++);
                            System.out.println("node Num: " + NodeNum);
                            i++;
                        }
                        NodeNum++;
                        System.out.println("NEXT ROW: node Num: " + NodeNum);
                        break;
                    case "1": // One Way West
                        System.out.print(" W One Way\n");
                        int j = 0;
                        while (j < 6 ) {
                            System.out.println("Added Edge[ " + (NodeNum + 1) + ", " + NodeNum + "]");
                            g.addEdge(NodeNum++, NodeNum);
                            System.out.println("node Num: " + NodeNum);
                            j++;
                        }
                        NodeNum++;
                        System.out.println("NEXT ROW: node Num: " + NodeNum);
                        break;
                    case "4": // W/E Two Way
                        System.out.print(" W/E Two Way\n");
                        int k = 0;
                        while (k < 6 ) {
                            int DontChangeNodeNum = NodeNum + 1;
                            System.out.println("Added Edge[ " + (NodeNum + 1) + ", " + NodeNum + "]");
                            g.addEdge(DontChangeNodeNum , NodeNum);
                            System.out.println("Added Edge[ " + NodeNum + ", " + (NodeNum + 1) + "]");
                            g.addEdge(NodeNum, DontChangeNodeNum );
                            NodeNum++; // Now update NodeNum
                            System.out.println("node Num: " + NodeNum);
                            k++;

                        }
                        NodeNum++;
                        System.out.println("NEXT ROW: node Num: " + NodeNum);
                        break;
                    case "6": //Obstacle
                        break;
                    default: break;
                }
            }
        } catch (IOException e) { e.printStackTrace(); }
        System.out.println("END OF EAST AND WEST STREETS");


        NodeNum = 1; // Reset Node Num
        int colCount = 1;
        // NORTH AND WEST ONLY
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] intersections = line.split(cvsSplitBy);
                System.out.print("Street Name [" +
                        " N/S Street Name= " +  intersections[0] + " , Street direction=" + intersections[1] +
                        " W/E Street Name= " + intersections[2] + " , Street direction=" + intersections[3] + "]");
                switch(intersections[3]) {
                    case "2": //One Way North
                        System.out.print(" N One Way\n");
                        System.out.print(" S One Way\n");
                        NodeNum = colCount;
                        int l = 0;
                        int newNodeNum3;
                        while (l < 6 ) {
                            System.out.println("Added Edge[ " + (NodeNum + 7) + ", " + NodeNum + "]");
                            newNodeNum3 = NodeNum + 7;
                            g.addEdge(newNodeNum3, NodeNum);
                            NodeNum = NodeNum + 7;
                            System.out.println("node Num: " + NodeNum);
                            l++;
                        }
                        NodeNum++;
                        System.out.println("NEXT ROW: node Num: " + NodeNum);
                        colCount++;
                        break;
                    case "3": //One Way South
                        System.out.print(" S One Way\n");
                        NodeNum = colCount;
                        int m = 0;
                        int newNodeNum;
                        while (m < 6 ) {
                            System.out.println("Added Edge[ " + NodeNum + ", " + (NodeNum + 7) + "]");
                            newNodeNum = NodeNum + 7;
                            g.addEdge(NodeNum, newNodeNum);
                            NodeNum = NodeNum + 7;
                            System.out.println("node Num: " + NodeNum);
                            m++;
                        }
                        NodeNum++;
                        System.out.println("NEXT ROW: node Num: " + NodeNum);
                        colCount++;
                        break;
                    case "5": // N/S Two Way
                        System.out.print(" W/E Two Way\n");
                        NodeNum = colCount;
                        int n = 0;
                        int newNodeNum2;
                        while (n < 6 ) {
                            System.out.println("Added Edge[ " + NodeNum + ", " + (NodeNum + 7) + "]");
                            newNodeNum2 = NodeNum + 7;
                            g.addEdge(NodeNum, newNodeNum2);
                            System.out.println("Added Edge[ " + (NodeNum + 7) + ", " + NodeNum + "]");
                            g.addEdge(newNodeNum2, NodeNum);
                            NodeNum = NodeNum + 7;
                            System.out.println("node Num: " + NodeNum);
                            n++;
                        }
                        NodeNum++;
                        System.out.println("NEXT ROW: node Num: " + NodeNum);
                        colCount++;
                        break;
                    case "6": //Obstacle
                        break;
                    default: break;
                }
            }
        } catch (IOException e) { e.printStackTrace(); }
        System.out.println("END OF NORTH AND SOUTH STREETS");

        System.out.println("Following is Breadth First Traversal "+ "(starting from vertex 1)");
        g.BFS(25);
    }
}


class Graph {
    private int V;   // No. of vertices
    private LinkedList<Integer>[] adj; //Adjacency Lists

    // Constructor
    Graph(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i) adj[i] = new LinkedList();
    }

    void addEdge(int v,int w) { adj[v].add(w); }
    void BFS(int s) {
        boolean[] visited = new boolean[V];
        LinkedList<Integer> queue = new LinkedList<Integer>();
        visited[s]=true;
        queue.add(s);
        while (queue.size() != 0) {
            s = queue.poll();
            System.out.print(s+" ");
            Iterator<Integer> i = adj[s].listIterator();
            while (i.hasNext()) {
                int n = i.next();
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }
}