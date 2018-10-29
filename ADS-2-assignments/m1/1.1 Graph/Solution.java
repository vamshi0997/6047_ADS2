import java.util.Scanner;
import java.util.HashMap;
// interface Graph {
//     public int V();
//     public int E();
//     public void addEdge(int v, int w);
//     public Iterable<Integer> adj(int v);
//     public boolean hasEdge(int v, int w);
// }

public final class Solution {
    private Solution() {

    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String type = scan.nextLine();
        int vertex = Integer.parseInt(scan.nextLine());
        int edge = Integer.parseInt(scan.nextLine());
        System.out.println(vertex + " vertices, " + edge + " edges ");
        if(vertex == 0 || edge == 0) {
            System.out.println("No edges");
            return;
        }
        Graph g = new Graph(vertex);
        // System.out.println(vertex + " vertices");
        // System.out.println(edge + " edges");
        String[] input = scan.nextLine().split(",");
        HashMap<Integer, String> map = new HashMap<Integer, String>();

        for(int i = 0; i < vertex; i++) {
            map.put(i, input[i]);
        }

        for(int j = 0; j < edge; j++) {
            String[] edgeinput = scan.nextLine().split(" ");
            g.addEdge(Integer.parseInt(edgeinput[0]), Integer.parseInt(edgeinput[1]));
        }

    StringBuilder s = new StringBuilder();
    //s.append(vertex + " vertices, " + edge + " edges " + "\n");
    for(int i = 0; i < vertex; i++) {
        s.append(map.get(i) + ": ");
        for(int j : g.adj(i)) {
             s.append(map.get(j) + " ");
            //System.out.println(j);
        }
        s.append("\n");
    }



        System.out.println(s);
    }
}

