import java.util.Scanner;
import java.util.HashMap;
/**
 * Class for page rank.
 */
class PageRank {
    /**
     * d is of digraph type.
     */
    private Digraph d;
    /**
     * map is of type of Hashmap.
     */
    private HashMap<Integer, Double> map;
    /**
     * Constructs the object.
     *
     * @param      d1     { parameter_description }
     */
    PageRank(final Digraph d1) {
    this.d = d1;
    map = new HashMap<Integer, Double>();
    }
    /**
     * Returns a string representation of the object.
     *
     * @return     String representation of the object.
     */
    public String toString() {
        String s1 = "";
        int vertex = d.vertices();
        Digraph d1 = this.d.reverse();

        for (int i = 0; i < vertex; i++) {
            map.put(i, 1.0 / (double) vertex);
        }
        final int number = 998;
        for (int i = 1; i < number; i++) {
            double[] temp = new double[vertex];
            for (int l = 0; l < d.vertices(); l++) {
                double sum = 0;
                String[] str = d1.adjv(l).split(" ");
                for (String s: str) {
                    try {
                        int k = Integer.parseInt(s);
                        sum += (double) map.get(k) / (double) d.outdegree(k);
                    } catch (Exception e) {
                        sum += (double) 0;
                    }
                }
                temp[l] = sum;
            }

            for (int j = 0; j < temp.length; j++) {
                map.put(j, temp[j]);
            }

        }
        for (int i = 0; i < d.vertices(); i++) {
            if (map.containsKey(i)) {
                System.out.println(i + " - " + map.get(i));
            } else {
                System.out.println(i + " - " + (double) 0);
            }
        }
        return s1;
        }
}
/**
 * Class for web search.
 */
class WebSearch {

}

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
     * main method is used to handle input.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        // read the first line of the input
        //to get the number of vertices
        Scanner scan = new Scanner(System.in);
        int vertex = Integer.parseInt(scan.nextLine());
        Digraph d = new Digraph(vertex);

        // iterate count of vertices times
        // to read the adjacency list from std input
        // and build the graph
        for (int i = 0; i < vertex; i++) {
            String[] input = scan.nextLine().split(" ");
            for (int j = 1; j < input.length; j++) {
                d.addEdge(Integer.parseInt(
                    input[0]), Integer.parseInt(input[j]));
            }
        }
        System.out.println(d);

        for (int i = 0; i < d.vertices(); i++) {
            if (d.outdegree(i) == 0) {
                for (int j = 0; j < d.vertices(); j++) {
                    if (i != j) {
                        d.addEdge(i, j);
                    }
                }
            }
        }

        // Create page rank object and pass
        //the graph object to the constructor
        PageRank pr = new PageRank(d);
        System.out.println(pr);

        // print the page rank object
        // This part is only for the final test case

        // File path to the web content
        String file = "WebContent.txt";

        // instantiate web search object
        // and pass the page rank object
        // and the file path to the constructor
        // read the search queries from std in
        // remove the q= prefix and extract the search word
        // pass the word to iAmFeelingLucky method of web search
        // print the return value of iAmFeelingLucky
    }
}
