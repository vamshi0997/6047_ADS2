import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Arrays;
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
        	ArrayList<String> arr;
        	for(String s: input) {
        		String[] s1 = s.split(",");
        		String[] s2 = s1[1].split(" ");
        		arr = new ArrayList<String>();
        		for(int i = 0; i < s2.length; i++) {
        			arr.add(s2[i]);
        			hash1.put(Integer.parseInt(s1[0]), arr);
        		}
        	}

        	for(Integer a: hash1.keySet()) {
                ArrayList<Integer> arr1 = new ArrayList<Integer>();;
        		for(String n: hash1.get(a)) {
        			arr1.add(a);
        			if(hash2.containsKey(n)) {
                        hash2.get(n).add(a);
                    } else {
                        hash2.put(n,arr1);    
                    }
                    
        		}
        	}

            Digraph d = new Digraph(hash2.size());

        	String[] input1 = content1.split("\n");



        	for(String i1: input1) {
        		try {
        		String[] s2 = i1.split(",");
        		    for(int j = 1; j < s2.length; j++) {
                       d.addEdge(Integer.parseInt(s2[0]), Integer.parseInt(s2[j]));
        		    }
        	    } catch (Exception e) {
        	    }
        	}

            // System.out.println(hash1);
            // System.out.println();
            // System.out.println();
            // System.out.println();
            // System.out.println();
            // System.out.println(hash2);



        	String query = scan.nextLine();
        	if(query.equals("Graph")) {
        		int count = 0;
        		DirectedCycle dc = new DirectedCycle(d);
        		if(dc.hasCycle()) {
        			System.out.println("Cycle detected");
        			return;
        		}
        		for(int k = 0; k < d.V(); k++) {
        			int outdegree = d.outdegree(k);
        			if(outdegree == 0) {
						count++;
					}
        		}
        		if(count > 1) {
        			System.out.println("Multiple roots");
        			return;
        		}
                System.out.println(d);
        	} else if(query.equals("Queries")) {

        		while(scan.hasNext()) {
        		String[] qinput = scan.nextLine().split(" ");
        		if(qinput[0].equals("null")) {
        			System.out.println("IllegalArgumentException");
        			return;
        		}

        		
        		ArrayList<Integer> nar = hash2.get(qinput[0]);
        		ArrayList<Integer> n1ar = hash2.get(qinput[1]);

        		int min = Integer.MAX_VALUE;
        		int shortest = 0;
                int ancestor = 0;
        		for(int i = 0; i < nar.size(); i++) {
        			for(int j = 0; j < n1ar.size(); j++) {
                        BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(d, nar.get(i));
                        BreadthFirstDirectedPaths bfs1 = new BreadthFirstDirectedPaths(d, n1ar.get(j));
                        for(int l = 0; l < d.V(); l++) {
                            if(bfs.hasPathTo(l) && bfs1.hasPathTo(l)) {
                                shortest = bfs.distTo(l) + bfs1.distTo(l);
                                if(shortest < min) {
                        	        min = shortest;
                        	        ancestor = l;
                                }
                            }
                        }
                    }
        		}
        		System.out.println(
        			"distance = " + min + ", ancestor = " + hash1.get(ancestor).toString().replace(
        				"[","").replace("]","").replace(",", ""));
                }
        }

        } catch(Exception e) {
        	e.printStackTrace();
        	System.out.println(e);
        }
	}
}