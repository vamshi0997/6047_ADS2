import java.util.Scanner;
import java.util.HashMap;
/**
 * Solution class.
 */
public final class Solution {
    /**
     * default solution constructor.
     */
    private Solution() {

    }
    /**
     * time complexity is O(E + V).
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        HashMap<String, Integer> rail =
        new HashMap<String, Integer>();
        String[] line = scan.nextLine().split(" ");
        int noofstations = Integer.parseInt(line[0]);
        int noofconnections = Integer.parseInt(line[1]);
        String[] stations = scan.nextLine().split(" ");
        for (int k = 0; k < stations.length; k++) {
            rail.put(stations[k], k);
        }

        Edge e;
        EdgeWeightedGraph ewg =
        new EdgeWeightedGraph(stations.length);
        for (int i = 0; i < noofconnections; i++) {
            String[] elements = scan.nextLine().split(" ");
            e = new Edge(rail.get(elements[0]), rail.get(
                elements[1]), Double.parseDouble(elements[2]));
            ewg.addEdge(e);
        }

        int input = Integer.parseInt(scan.nextLine());
        DijkstraSP dk;
        for (int j = 0; j < input; j++) {
            String[] sd = scan.nextLine().split(" ");
            int source = rail.get(sd[0]);
            dk = new DijkstraSP(ewg, source);
            System.out.println((int) dk.distance(rail.get(sd[1])));
        }
    }
}
