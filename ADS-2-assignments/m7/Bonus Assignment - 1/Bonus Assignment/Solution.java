import java.util.Scanner;
public final class Solution {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int vertex = scan.nextInt();
        int edge = scan.nextInt();
        EdgeWeightedGraph ewg = new EdgeWeightedGraph(vertex);
        Edge e;
        for ( int i = 0; i < edge; i++) {
        	int vert1 = scan.nextInt();
        	int vert2 = scan.nextInt();
        	int weigh = scan.nextInt();
            e = new Edge(vert1, vert2, weigh);
            ewg.addEdge(e);
        }
        KruskalMST km = new KruskalMST(ewg);
        System.out.println(km.weight());
    }
}