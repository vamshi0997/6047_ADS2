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
        	}

            Digraph d = new Digraph(hash2.size());

        	String[] input1 = content1.split("\n");

        	for(String i1: input1) {
        		try {
        		String[] s2 = i1.split(",");
        		for(int j = 1; j < s2.length; j++) {
        			//System.out.println("s2 " + s2[0] + " s2() " + s2[j]);
                    d.addEdge(Integer.parseInt(s2[0]), Integer.parseInt(s2[j]));
        		}
        	} catch (Exception e) {
        	}
        	}

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
        		
        		ArrayList<Integer> narr = hash2.get(qinput[0]);
        		ArrayList<Integer> n1arr = hash2.get(qinput[1]);
        		

        		int min = d.V();
        		int dist = 0;
        		int dist1 = 0;
        		int shortest = 0;
                int ancestor = 0;
                int san = 0;
        		


        		for(Integer nar: narr) {
        			for(Integer n1ar: n1arr) {
                        BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(d, nar);
                        BreadthFirstDirectedPaths bfs1 = new BreadthFirstDirectedPaths(d, n1ar);

                        for(int l = 0; l < d.V(); l++) {
                            if(bfs.hasPathTo(l) && bfs.hasPathTo(l)) {
                                dist = bfs.distTo(l);
                                //System.out.println("1 " + dist);
                                dist1 = bfs1.distTo(l);
                                //System.out.println("2 " + dist1);
                                shortest = dist + dist1;
                                ancestor = l;

                                if(shortest < min && shortest >= 0) {
                        	        min = shortest;
                        	        //System.out.println("min" + " " + min);
                        	        san = ancestor;
                                }

                            }

                        }

         			}
        		}

                 //System.out.println();
        		System.out.println(
        			"distance = " + min + ", ancestor = " + hash1.get(san).toString().replace(
        				"[","").replace("]",""));
        		

        		

                }
        	}
        } catch(Exception e) {
        	System.out.println(e);
        }
	}
}