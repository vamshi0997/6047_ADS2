import java.util.Scanner;
import java.util.Arrays;

public class Solution {
	public static void main(String[] args) {
		String[] words = loadWords();
		//System.out.println(Arrays.toString(words));
		//Your code goes here...
		TST<Integer> t = new TST<Integer>();
		int value = 0;
		for (String s: words) {
			for (int i = 0; i < s.length(); i++) {
              t.put(s.substring(i), value);
              value++;  
			}
		}
		Scanner scan = new Scanner(System.in);
		String prefix = scan.nextLine();

		//System.out.println(prefix);
		for (String k : t.keysWithPrefix(prefix)) {
			System.out.println(k);
		}
	}

	public static String[] loadWords() {
		In in = new In("/Files/dictionary-algs4.txt");
		String[] words = in.readAllStrings();
		return words;
	}
}