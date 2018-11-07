import java.util.ArrayList;
/**
 * Class for sap.
 */
public class SAP {
    /**
     * dgraph of the type Digraph.
     */
    private Digraph dgraph;
    /**
     * Constructs the object.
     * @param      g     Digraph.
     */
    public SAP(final Digraph g) {
        dgraph = g;
    }
    /**
     * This finds out the shortest ancestral path between two vertices.
     * @param      array1     ArrayList.
     * @param      array2     ArrayList.
     * @return     integer array.
     * Time complexity for this method is
     * O(N^2 * M) where array1 is length of
     * array list and array2 is length of array list
     * and array1 is no of vertices.
     */
    public int[] length(final ArrayList<Integer> array1,
        final ArrayList<Integer> array2) {
        int min = dgraph.vertices();
        int temp = 0;
        for (int i = 0; i < array1.size(); i++) {
            for (int j = 0; j < array2.size(); j++) {
                BreadthFirstDirectedPaths bfs1 =
                new BreadthFirstDirectedPaths(dgraph, array1.get(i));
                BreadthFirstDirectedPaths bfs2 =
                new BreadthFirstDirectedPaths(dgraph, array2.get(j));
                for (int k = 0; k < dgraph.vertices(); k++) {
                    if (bfs1.hasPathTo(k) && bfs2.hasPathTo(k)) {
                        int distance = bfs1.distTo(k) + bfs2.distTo(k);
                        if (distance < min) {
                            min = distance;
                            temp = k;
                        }
                    }
                }
            }
        }
        int[] total = {min, temp};
        return total;
    }
}
