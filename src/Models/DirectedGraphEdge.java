package Models;

public class DirectedGraphEdge {
    private int nodeA, nodeB;
    private double blocks;

    public DirectedGraphEdge(int source, int destination, double distance) {
        this.nodeA = source;
        this.nodeB = destination;
        this.blocks = distance;
    }

    public int fromNode() { return nodeA; }
    public int toNode() { return nodeB; }
    public double distance() { return blocks; }

    @Override
    public String toString() { return "" + nodeB; }
}