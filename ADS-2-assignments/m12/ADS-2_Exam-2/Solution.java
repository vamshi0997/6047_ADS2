import java.util.Scanner;
import java.util.ArrayList;
/**
 * Class for solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {

    }
    /**
     * Time complexity is O(ElogV).
     * Main method handles the input.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        int vertex = Integer.parseInt(scan.nextLine());
        int connection = Integer.parseInt(scan.nextLine());
        EdgeWeightedGraph ewg = new EdgeWeightedGraph(vertex);
        Edge ed;
        // Self loops are not allowed...
        // Parallel Edges are allowed...
        // Take the Graph input here...
        for (int i = 0; i < connection; i++) {
            String[] input = scan.nextLine().split(" ");
            ed = new Edge(Integer.parseInt(input[0]), Integer.parseInt(
                input[1]), Double.parseDouble(input[2]));
            ewg.addEdge(ed);

        }

        String caseToGo = scan.nextLine();
        switch (caseToGo) {
        case "Graph":
            //Print the Graph Object.
            System.out.println(ewg);
            break;

        case "DirectedPaths":
            String[] dinput = scan.nextLine().split(" ");
            DijkstraSP dk = new DijkstraSP(
                ewg, Integer.parseInt(dinput[0]));
            if (dk.distTo(Integer.parseInt(
                dinput[1])) == Double.POSITIVE_INFINITY) {
                System.out.println("No Path Found.");
                break;
            }
            System.out.println(dk.distTo(Integer.parseInt(dinput[1])));
            // Handle the case of DirectedPaths, where two integers are given.
            // First is the source and second is the destination.
            // If the path exists print the distTo between them.
            // Other wise print "No Path Found."
            break;

        case "ViaPaths":
            // Handle the case of ViaPaths, where three integers are given.
            // First is the source and second is the via is
            // the one where path should pass throuh.
            // third is the destination.
            // If the path exists print the distTo between them.
            // Other wise print "No Path Found."
            String[] vinput = scan.nextLine().split(" ");
            DijkstraSP dk1 = new DijkstraSP(
                ewg, Integer.parseInt(vinput[0]));
            Double d1 = dk1.distTo(Integer.parseInt(vinput[1]));
            DijkstraSP dk2 = new DijkstraSP(
                ewg, Integer.parseInt(vinput[1]));
            Double d2 = dk2.distTo(Integer.parseInt(vinput[2]));
            if (d1 == Double.POSITIVE_INFINITY
                || d2 == Double.POSITIVE_INFINITY) {
                System.out.println("No Path Found.");
                break;
            }
            System.out.println(d1 + d2);
            ArrayList<String> arr = new ArrayList<String>();
            for (Edge i: dk1.pathTo(Integer.parseInt(vinput[1]))) {
                String[] str = i.toEdge1().split(" ");
                for (int l = 0; l < str.length; l++) {
                    if (!arr.contains(str[l])) {
                        arr.add(str[l]);
                    }
                }
            }
            for (Edge j: dk2.pathTo(Integer.parseInt(vinput[2]))) {
                String[] str1 = j.toEdge1().split(" ");
                for (int k = 0; k < str1.length; k++) {
                    if (!arr.contains(str1[k])) {
                        arr.add(str1[k]);
                    }
                }
            }
            System.out.println(arr.toString().replace(
                "[", "").replace("]", "").replace(",", ""));

            System.out.println();
            break;

        default:
            break;
        }
    }
}
