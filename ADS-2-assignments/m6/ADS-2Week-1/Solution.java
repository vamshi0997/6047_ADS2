import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * Class for page rank.
 */
class PageRank {
	private Digraph d;
	HashMap<Integer, Double> map;
	/**
	 * Constructs the object.
	 *
	 * @param      d     { parameter_description }
	 */
	PageRank(Digraph d1) {
    this.d = d1;
    map = new HashMap<Integer, Double>();
	}

	public String toString() {
		String s1 = "";
		int vertex = d.V();
		Digraph d1 = d.reverse();
		//System.out.println(d1);
		for(int i = 0; i < 1000; i ++) {
		for(int l = 0; l < d.V(); l++) {
			Double sum = (double)0;
			String[] str = d1.adjv(l).split(" ");
			for(String s: str) {
                try {
                    int k = Integer.parseInt(s);
                    if(map.containsKey(k)) {
                    	sum += (double)map.get(k)/d.outdegree(k);
                    	map.put(l, sum);
                    	//System.out.println(l + " " + map.get(k));
                    } else {
                        sum = (double)1/(d.outdegree(k) * vertex);
                        map.put(l, sum);
                        //System.out.println("i" + sum);
                    }
                }
                catch(Exception e) {
                }
			}
		}
	}
	//System.out.println(l + "-" +sum);
	//System.out.println(map);
	for(Integer m: map.keySet()) {
		System.out.println(m + " - " + map.get(m));
	}
	return s1;
	}
	
}

class WebSearch {

}

/**
 * Class for solution.
 */
public class Solution {
	/**
	 * Constructs the object.
	 */
	private Solution() {

	}
	/**
	 * main method is used to handle input.
	 *
	 * @param      args  The arguments
	 */
	public static void main(String[] args) {
		// read the first line of the input to get the number of vertices
		Scanner scan = new Scanner(System.in);
		int vertex = Integer.parseInt(scan.nextLine());
        Digraph d = new Digraph(vertex);

		// iterate count of vertices times
		// to read the adjacency list from std input
		// and build the graph
		for(int i = 0; i < vertex; i++) {
			String[] input = scan.nextLine().split(" ");
			for(int j = 1; j < input.length; j++) {
                d.addEdge(Integer.parseInt(input[0]), Integer.parseInt(input[j]));
			}
		}
		System.out.println(d);
		//Digraph d1 = d.reverse();
		//System.out.println(d1);
		
		
		// Create page rank object and pass the graph object to the constructor
		PageRank pr = new PageRank(d);
		System.out.println(pr);
		
		// print the page rank object
		// double intialpr = (double)1/vertex;
		// System.out.println(intialpr);
		//System.out.println(1/d.V());
		// for(int i = 0; i < d.V(); i++) {
		// 	String[] str = d1.adjv(i).split(" ");
		// 	int prr = 
		// }
		//
		// for(int l = 0; l < d.V(); l++) {
		// 	double sum = 0;
		// 	String[] str = d1.adjv(l).split(" ");
		// 	for(String s: str) {
  //               try {
  //                   int k = Integer.parseInt(s);
  //                   //System.out.println(d.outdegree(k) + " " + intialpr);
  //                   sum += (double)1/(d.outdegree(k) * vertex);
  //                   //System.out.println(sum);
  //               }
  //               catch(Exception e) {
  //               	//sum += 0;
  //               }
		// 	}
		// 	System.out.println(l + " " +sum);
  //    //       for(k = 0; k < 1000; k++) {
  //    //       	    pr.prk(l);
		//    // }
		// }



		// This part is only for the final test case
		
		// File path to the web content
		String file = "WebContent.txt";
		
		// instantiate web search object
		// and pass the page rank object and the file path to the constructor
		
		// read the search queries from std in
		// remove the q= prefix and extract the search word
		// pass the word to iAmFeelingLucky method of web search
		// print the return value of iAmFeelingLucky
		
	}
}
