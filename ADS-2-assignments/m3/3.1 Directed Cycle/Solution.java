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
	 * main method is used to hand the input.
	 *
	 * @param      args  The arguments
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int vertex = scan.nextInt();
		int edges = scan.nextInt();
		Digraph d = new Digraph(vertex);
		for (int i = 0; i < edges; i++) {
            d.addEdge(scan.nextInt(), scan.nextInt());
		}
		DirectedCycle dc = new DirectedCycle(d);
		System.out.println(dc.hasCycle());
	}
}