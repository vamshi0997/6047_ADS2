import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
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
     * main method to drive the program.
     * @param      args  The arguments
     * Time complexity for this method is O(V + E).
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);

        // Taking synsets file name as input.
        String synsets = scan.nextLine();
        File file = new File("Files/" + synsets);

        //storing strings in integers.
        HashMap<Integer, ArrayList<String>> synset =
        new HashMap<Integer, ArrayList<String>>();

        //storing integers in strings.
        HashMap<String, ArrayList<Integer>> rsynset =
        new HashMap<String, ArrayList<Integer>>();

        try {
            Scanner scan2 = new Scanner(file);
            while (scan2.hasNextLine()) {
                ArrayList<String> templist = new ArrayList<String>();
                String[] input = scan2.nextLine().split(",");
                String[] tokens = input[1].split(" ");
                for (String str : tokens) {
                    templist.add(str);
                }
                synset.put(Integer.parseInt(input[0]), templist);
            }

            for (Integer num : synset.keySet()) {
                ArrayList<String> value = synset.get(num);
                for (String str : value) {
                    if (rsynset.containsKey(str)) {
                        ArrayList<Integer> it = rsynset.get(str);
                        it.add(num);
                        rsynset.put(str, it);
                    } else {
                        ArrayList<Integer> t = new ArrayList<Integer>();
                        t.add(num);
                        rsynset.put(str, t);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Taking hypernyms file name as input.
        String hypernyms = scan.nextLine();
        // Taking the type of testcase as input.
        String type = scan.nextLine();
        File file2 = new File("Files/" + hypernyms);

        HashMap<Integer, Integer> hypernym =
        new HashMap<Integer, Integer>();

        int vertices = synset.size();
        Digraph graph = new Digraph(vertices);
        try {
            Scanner scan1 = new Scanner(file2);
            while (scan1.hasNextLine()) {
                String[] input = scan1.nextLine().split(",");
                for (int i = 1; i < input.length; i++) {
                    graph.addEdge(Integer.parseInt(input[0]),
                        Integer.parseInt(input[i]));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        switch (type) {

        case "Graph":
            DirectedCycle dc = new DirectedCycle(graph);
            int count = 0;
            for (int i = 0; i < graph.vertices(); i++) {
                if (graph.outdegree(i) == 0) {
                    count++;
                }
            }
            if (count > 1) {
                System.out.println("Multiple roots");
                break;
            }
            if (dc.hasCycle()) {
                System.out.println("Cycle detected");
            } else {
                System.out.println(graph);
            }
            break;

        case "Queries":
            while (scan.hasNext()) {
                String[] input = scan.nextLine().split(" ");
                if (input[0].equals("null")) {
                    System.out.println("IllegalArgumentException");
                    return;
                }

                SAP short1 = new SAP(graph);
                ArrayList<Integer> array1 = rsynset.get(input[0]);
                ArrayList<Integer> array2 = rsynset.get(input[1]);
                int[] arr = short1.length(array1, array2);
                ArrayList<String> result = synset.get(arr[1]);
                String temp = result.get(0);
                System.out.print("distance = " + arr[0] + ", ancestor = ");

                for (int l = 0; l < result.size(); l++) {
                    if (l != result.size() - 1) {
                        System.out.print(result.get(l) + " ");
                    } else {
                        System.out.print(result.get(l));
                    }
                }
                System.out.println();
            }
            break;

        default:
            break;
        }
    }
}
