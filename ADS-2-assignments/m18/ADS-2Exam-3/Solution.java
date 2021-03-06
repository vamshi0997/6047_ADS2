import java.util.Scanner;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;

public class Solution {

	// Don't modify this method.
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String cases = scan.nextLine();

		switch (cases) {
		case "loadDictionary":
			// input000.txt and output000.txt
			BinarySearchST<String, Integer> hash = loadDictionary("/Files/t9.csv");
			while (scan.hasNextLine()) {
				String key = scan.nextLine();
				System.out.println(hash.get(key));
			}
			break;

		case "getAllPrefixes":
			// input001.txt and output001.txt
			T9 t9 = new T9(loadDictionary("/Files/t9.csv"));
			while (scan.hasNextLine()) {
				String prefix = scan.nextLine();
				for (String each : t9.getAllWords(prefix)) {
					System.out.println(each);
				}
			}
			break;

		case "potentialWords":
			// input002.txt and output002.txt
			t9 = new T9(loadDictionary("/Files/t9.csv"));
			int count = 0;
			while (scan.hasNextLine()) {
				String t9Signature = scan.nextLine();
				for (String each : t9.potentialWords(t9Signature)) {
					count++;
					System.out.println(each);
				}
			}
			if (count == 0) {
				System.out.println("No valid words found.");
			}
			break;

		case "topK":
			// input003.txt and output003.txt
			t9 = new T9(loadDictionary("/Files/t9.csv"));
			Bag<String> bag = new Bag<String>();
			int k = Integer.parseInt(scan.nextLine());
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				bag.add(line);
			}
			for (String each : t9.getSuggestions(bag, k)) {
				System.out.println(each);
			}

			break;

		case "t9Signature":
			// input004.txt and output004.txt
			t9 = new T9(loadDictionary("/Files/t9.csv"));
			bag = new Bag<String>();
			k = Integer.parseInt(scan.nextLine());
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				for (String each : t9.t9(line, k)) {
					System.out.println(each);
				}
			}
			break;

		default:
			break;

		}
	}

	// Don't modify this method.
	public static String[] toReadFile(String file) {
		In in = new In(file);
		return in.readAllStrings();
	}

	public static BinarySearchST<String, Integer> loadDictionary(String file) {
		BinarySearchST<String, Integer>  st = new BinarySearchST<String, Integer>();

		// your code goes here
		String[] file1 = toReadFile(file);
		String[] lfile = new String[file1.length];
		for (int i = 0; i < file1.length; i++) {
			lfile[i] = file1[i].toLowerCase();
		}
		for (String s: lfile) {
			if(st.contains(s)) {
				st.put(s, st.get(s) + 1);
			} else {
				st.put(s, 1);
			}
		}
		return st;
	}

}

class T9 {
	TST<Integer> trie;
	BinarySearchST<String, Integer> st1;
	HashMap<Character, String> hmap;
	Nwords nw;
	MaxPQ<Nwords> m;

	public T9(BinarySearchST<String, Integer> st) {
		// your code goes here
		this.st1 = st;
		trie = new TST<Integer>();
		for (String str: st.keys()) {
			trie.put(str, st.get(str));
		}
	}

	// get all the prefixes that match with given prefix.
	public Iterable<String> getAllWords(String prefix) {
		// your code goes here
		Queue<String> queue = new Queue<String>();
        for (String str: trie.keysWithPrefix(prefix)) {
            queue.enqueue(str);
        }
		return queue;
	}

	public Iterable<String> potentialWords(String t9Signature) {

		//your code goes here
		hmap = new HashMap<Character, String>();
		//Mapping the alphabets to numbers.
	 	hmap.put('2', "abc");
        hmap.put('3', "def");
        hmap.put('4', "ghi");
        hmap.put('5', "jkl");
        hmap.put('6', "mno");
        hmap.put('7', "pqrs");
        hmap.put('8', "tuv");
        hmap.put('9', "wxyz");
		ArrayList<String> al1 = new ArrayList<String>();
        ArrayList<String> al2 = new ArrayList<String>();
        al1.add("");
        for (int i = 0; i < t9Signature.length(); i++) {
            for (String str : al1) {
                String str1 = hmap.get(t9Signature.charAt(i));
                for (int j = 0; j < str1.length(); j++)
                    al2.add(str + str1.charAt(j));
            }
            al1 = al2;
            al2 = new ArrayList<String>();
            }
        ArrayList<String> res = new ArrayList<String>();
        for (int i = 0; i < al1.size(); i++) {
        	if (trie.contains(al1.get(i))) {
        		res.add(al1.get(i));
        	}
        }
		return res;
	}

	// return all possibilities(words), find top k with highest frequency.
	public Iterable<String> getSuggestions(Iterable<String> words, int k) {
		// your code goes here

		m = new MaxPQ<Nwords>();
		Queue<String> queue = new Queue<String>();
		for (String str: words) {
			nw = new Nwords(str, st1.get(str));
			m.insert(nw);
		}
		String[] nsort = new String[k];
		for(int i = 0; i < k; i++) {
			Nwords n = m.delMax();
			nsort[i] = n.getWord();
		}
		Arrays.sort(nsort);
		for (String s: nsort) {
            queue.enqueue(s);
		}
		return queue;
	}

	// final output
	// Don't modify this method.
	public Iterable<String> t9(String t9Signature, int k) {
		return getSuggestions(potentialWords(t9Signature), k);
	}
}
