import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
public final class Solution {
	private Solution() {

	}
	public static void main(String[] args) {
		HashMap<Integer, ArrayList<String>> hash1 = new HashMap<Integer, ArrayList<String>>();
		HashMap<String, ArrayList<Integer>> hash2 = new HashMap<String, ArrayList<Integer>>();
        Scanner scan = new Scanner(System.in);
        String file1 = scan.nextLine();
        String file2 = scan.nextLine();
        String content;
        String content1;
        try {
        	content = new String(Files.readAllBytes(Paths.get("Files/" + file1)));
        	content1 = new String(Files.readAllBytes(Paths.get("Files/" + file2)));

        	String[] input = content.split("\n");
        	for(String s: input) {
        		String[] s1 = s.split(",");
        		for(int i = 1; i < s1.length-1; i++) {
        			ArrayList<String> arr = new ArrayList<String>();
        			arr.add(s1[i]);
        			hash1.put(Integer.parseInt(s1[0]), arr);
        		}
        	}
        	for(Integer a: hash1.keySet()) {
        		ArrayList<Integer> arr1 = new ArrayList<Integer>();
        		for(String n: hash1.get(a)) {
        			arr1.add(a);
        			hash2.put(n,arr1);
        		}
        		//System.out.println(a + " " + hash1.get(a).toString().replace("[", "").replace("]","").trim());
        	}
        	for(String a: hash2.keySet()) {
        		System.out.println(a + " " + hash2.get(a));
        	}
            Digraph d = new Digraph(hash2.size());
        	String[] input1 = content1.split("\n");
        	// for(String i1: input1) {
        	// 	String[] s2 = i1.split(",");
        	// 	d.addEdge(Integer.parseInt(s2[0]), Integer.parseInt(s2[1]));
        	// }
         //    System.out.println(d.toString());
        } catch(Exception e) {
        	System.out.println("No file");
        }
	}
}