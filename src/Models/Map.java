package Models;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;


public class Map {
    static int vertices, edges;
    static int Total = 1;
    static int vertex_nr;
    final static int inf = 10000; // infinite

    static int[][] adjM = {
            {0, 1, inf, inf, inf, inf, inf, inf},
            {1, 0, 1, inf, inf, 1, 1, inf},
            {inf, 1, 0, 1, inf, 1, 1, inf},
            {inf, inf, 1, 0, 1, inf, inf, inf},
            {inf, inf, inf, 1, 0, inf, inf, inf},
            {inf, 1, 1, inf, inf, 0, 1, 1},
            {inf, 1, 1, inf, inf, 1, 0, 1},
            {inf, inf, inf, inf, inf, 1, 1, 0}
    };

    static boolean[] visit = {false, false, false, false, false, false, false, false};
    static int[] dist = {inf, inf, inf, inf, inf, inf, inf, inf};
    static int[] route = new int[8];

    public void calculate() {
        try {
            String path = "src/Models/Input.txt";
            File getfile = new File(path);
            Scanner file = new Scanner(getfile);
            //Retrieve number of vertices and edges then creates the adj_matrix
            vertices = file.nextInt();
            edges = file.nextInt();
            int[][] adj_matrix2 = new int[vertices + 1][vertices + 1];
            System.out.println("Vertices: " + vertices + " Edges: " + edges + "\n");

            printCompanyNames(file);
            runDijkstra(adj_matrix2);
        } catch (FileNotFoundException e) {
            System.out.println("Sorry, the file is not found. Please try again or edit to the correct filepath on Line 33 in Models.Map.");
        }
    }

    public void printCompanyNames(Scanner file) {
        //Create Array for retrieving the names that belong to the Max Clique
        String[] getNames = new String[vertices];
        //Create Matrix that hold the degree counts
        Integer[][] countDegree = new Integer[vertices][2];
        //For some reason, file.nextLine(); reads a blank line before reading the actual line of input between "allice" and "1"
        String skipLine = file.nextLine();//skipLine is to get the empty Line or space
        //Save the names into an array and while preparing the countDegree Matrix to recieve input
        for (int count = 0; count < getNames.length; count++) {
            //Retireve the names from file and store it into the getNames Matrix
            String input = file.nextLine();
            System.out.println("Input: " + input);
            getNames[count] = input;
            countDegree[count][0] = count + 1;
            countDegree[count][1] = 0;
        }
        System.out.println("\nGet Names: " + Arrays.toString(getNames));
    }

    public void runDijkstra(int[][] adj_matrix2) {
        for (int z = 0; z < vertices; z++) {
            vertex_nr = z;
            dijkstra(vertex_nr);
            for (int i = vertex_nr; i < 8; i++) { routing(0, i, adj_matrix2); }
            System.out.println();
        }
    }

    static int shortest() {
        int min = inf;
        int position = 0;
        for (int i = 0; i < 8; i++) {
            if (dist[i] < min && !visit[i]) {
                min = dist[i];
                position = i;
            }
        }
        return position;
    }

    static void dijkstra(int start) {
        for (int i = 0; i < 8; i++) {
            dist[i] = adjM[start][i];
        }
        visit[start] = true;
        dist[start] = 0;
        for (int i = 0; i < 6; i++) {
            int current = shortest();
            visit[current] = true;
            for (int j = 0; j < 8; j++) {
                if (!visit[j]) {
                    if (dist[current] + adjM[current][j] < dist[j]) {
                        dist[j] = dist[current] + adjM[current][j];
                        route[j] = current;
                    }
                }
            }
        }
    }

    static void routing(int start, int end, int[][] adj_matrix2) {
        int tmp = route[end];
        int[] find = new int[8];
        int cnt = 1;
        find[0] = end;
        find[cnt] = tmp;
        while (tmp != start) {
            find[++cnt] = route[tmp];
            tmp = route[tmp];
        }
        int shortestPath = 0;
        int getCol = 0;
        System.out.print("Shortest path from " + (vertex_nr) + " to " + (end) + ": ");
        for (int i = cnt; i > 0; i--) {
            System.out.print("(" + (vertex_nr) + " -> " + (find[i - 1]) + ")");
            getCol = i;
            shortestPath++;
        }
        adj_matrix2[vertex_nr][find[getCol - 1]] = shortestPath;
        Total = Total + shortestPath;
        System.out.println();
    }
}
