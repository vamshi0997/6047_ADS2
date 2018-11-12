import java.util.Scanner;
import java.util.Arrays;
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
	 * main method is useful for handling input.
	 *
	 * @param      args  The arguments
	 */
	public static void main(final String[] args) {
		Scanner scan = new Scanner(System.in);
		int num = Integer.parseInt(scan.nextLine());
		String[] array = new String[num];
	    for(int i = 0; i < num; i++) {
	    	array[i] = scan.nextLine();
	    }
	    Quick3string qs = new Quick3string();
	    qs.sort(array);
	    System.out.println(Arrays.toString(array));
	}
}