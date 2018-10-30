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
     * main method is used to handle the inputs.
     *
     * @param      args  The command line arguments.
     */
    public static void main(final String[] args) {
    	Scanner scan = new Scanner(System.in);
    	int vertex = scan.nextInt();
    	Percolate p = new Percolate(vertex);
        while (scan.hasNext()) {
        	int rows = scan.nextInt();
        	int columns = scan.nextInt();
            p.open(rows - 1, columns - 1);
        }
        System.out.println(p.percolates());
	}
}
