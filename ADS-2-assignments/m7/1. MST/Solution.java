import java.util.Scanner;
/**
 * Solution class.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {

    }
    /**
     * Main class is used to handle input and other classes.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        int vertex = scan.nextInt();
        int edge = scan.nextInt();
        EdgeWeightedGraph ewg = new EdgeWeightedGraph(vertex);
        Edge e;
        //KruskalMST;
        for (int i = 0; i < edge; i++) {
            int vert1 = scan.nextInt();
            int vert2 = scan.nextInt();
            double weight1 = scan.nextDouble();
            e = new Edge(vert1, vert2, weight1);
            ewg.addEdge(e);
        }
        KruskalMST km = new KruskalMST(ewg);
        System.out.format("%.5f", km.weight());
    }
}
