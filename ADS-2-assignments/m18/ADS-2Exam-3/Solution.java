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
	HashMap<Integer, ArrayList<String>> hmap;
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
		// your code goes here
        hmap = new HashMap<Integer, ArrayList<String>>();
        ArrayList<String> arr;
        for(int i = 2; i <= 9; i++) {
        	arr = new ArrayList<String>();
        	if (i == 2) {
            arr.add("a");
            arr.add("b");
            arr.add("c");
            }
            if (i == 3) {
                arr.add("d");
                arr.add("e");
                arr.add("f");
            }
            if(i == 4) {
                arr.add("g");
                arr.add("h");
                arr.add("i");
            }
            if(i == 5) {
                arr.add("j");
                arr.add("k");
                arr.add("l");
            }
            if(i == 6) {
                arr.add("m");
                arr.add("n");
                arr.add("o");
            }
            if(i == 7) {
                arr.add("p");
                arr.add("q");
                arr.add("r");
                arr.add("s");
            }
            if(i == 8) {
                arr.add("t");
                arr.add("u");
                arr.add("v");
            }
            if(i == 9) {
                arr.add("w");
                arr.add("x");
                arr.add("y");
                arr.add("z");
            }
            hmap.put(i, arr);
        }
            
            String[] sign = t9Signature.split("");
            ArrayList<String> nar = new ArrayList<String>();
            //String[] nar = new String[sign.length * sign.length];
            //System.out.println(hmap);
            // for(String s: sign) {
            // 	System.out.println(s);
            // }
            for(int i = 0; i < sign.length; i++) {
            	ArrayList<String> ar1 = hmap.get(Integer.parseInt(sign[i]));

            	for (String s: ar1) {
            		if(nar.size() >= 0) {
            			for(String s1: nar) {
            				String chec = s1 + s;
            				nar.add(chec);
            			}
            		} else {
                        nar.add(s);
            		}
            	}
            }
            System.out.println(nar);
            Queue<String> queue = new Queue<String>();
            for(String s: nar) {
                if(trie.contains(s)) {
                    queue.enqueue(s);
                }
            }
		return queue;
	}

	// return all possibilities(words), find top k with highest frequency.
	public Iterable<String> getSuggestions(Iterable<String> words, int k) {
		// your code goes here
		// for (String s: st1.keys()) {
		// 	System.out.println(s + "-" + st1.get(s));
		// }
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
