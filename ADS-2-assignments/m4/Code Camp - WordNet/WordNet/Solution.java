import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
public final class Solution {
	private Solution() {

	}
	public static void main(String[] args) {
		HashMap<Integer, ArrayList<String>> hash1 = new HashMap<Integer, ArrayList<String>>();
		HashMap<Integer, String> hash2 = new HashMap<Integer, String>();
        Scanner scan = new Scanner(System.in);
        String file1 = scan.nextLine();
        String file2 = scan.nextLine();
        String content;
        String content1;
        try {
        	content = new String(Files.readAllBytes(Paths.get("Files/" + file1)));
        	content1 = new String(Files.readAllBytes(Paths.get("Files/" + file2)));
        	//System.out.println(content);
        	String[] st = content.split("\n");
        	for(String s: st) {
        		String[] s1 = s.split(",");
        		for(int i = 1; i < s1.length; i++) {
        			ArrayList<String> arr = new ArrayList<String>();
        			arr.add(s1[i]);
        			hash1.put(Integer.parseInt(s1[0]), arr);
        		}
        	}
        	for(Integer a: hash1.keySet()) {
        		System.out.println(a + " " + hash1.get(a).toString().replace("[", "").replace("]","").trim());
        	}
        } catch(Exception e) {
        	System.out.println("No file");
        }
	}
}