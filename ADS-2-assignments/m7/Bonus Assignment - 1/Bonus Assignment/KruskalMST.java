/**
 * Class for kruskal mst.
 */
public class KruskalMST {
    /**
     * float.
     */
    private static final double FLOATING_POINT_EPSILON
    = 1E-12;
     /**
      * weight.
      */
    private double weight;
    /**
     * queue.
     */// weight of MST
    private Queue<Edge> mst = new Queue<Edge>();
    // edges in MST

    /**
     * The time complexity is O(ElogE).
     * Compute a minimum spanning tree (or forest).
     *  of an edge-weighted graph.
     * @param gra the edge-weighted graph.
     */
    public KruskalMST(final EdgeWeightedGraph gra) {
        // more efficient to build heap by passing array of edges
        MinPQ<Edge> pq = new MinPQ<Edge>();
        for (Edge e : gra.edges()) {
            pq.insert(e);
        }

        // run greedy algorithm
        UF uf = new UF(gra.vert());
        while (!pq.isEmpty() && mst.size() < gra.vert() - 1) {
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);
            if (!uf.connected(v, w)) { // v-w does not create a cycle
                uf.union(v, w);  // merge v and w components
                mst.enqueue(e);  // add edge e to mst
                weight += e.weight();
            }
        }

        // check optimality conditions
        // assert check(gra);
    }

    /**
     * The time complexity is O(E).
     * Returns the edges in a minimum spanning tree (or forest).
     * @return the edges in a minimum spanning tree (or forest) as
     *    an iterable of edges
     */
    public Iterable<Edge> edges() {
        return mst;
    }

    /**
     * The time complexity is O(1).
     * Returns the sum of the edge weights.
     *  in a minimum spanning tree (or forest).
     * @return the sum of the edge.
     *  weights in a minimum spanning tree (or forest).
     */
    public double weight() {
        return weight;
    }
}
