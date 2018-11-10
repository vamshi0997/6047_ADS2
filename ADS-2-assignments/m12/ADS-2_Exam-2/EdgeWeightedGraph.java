/**
 * Class for edge weighted graph.
 */
public class EdgeWeightedGraph {
    /**
     * line separator.
     */
    private static final String NEWLINE = System.getProperty("line.separator");
    /**
     * vertices.
     *
     */

    private final int vert;
    /**
     * edges.
     */
    private int edge;
    /**
     * bags class of edge type.
     */
    private Bag<Edge>[] adj;

    /**
     * Initializes an empty edge-weighted graph with
     * {@code V} vertices and 0 edges.
     *
     * @param  vert1 the number of vertices
     * @throws IllegalArgumentException if {@code V < 0}
     */
    public EdgeWeightedGraph(final int vert1) {

        this.vert = vert1;
        this.edge = 0;
        adj = (Bag<Edge>[]) new Bag[vert];
        for (int v = 0; v < vert; v++) {
            adj[v] = new Bag<Edge>();
        }
    }




    /**
     * The time complexity is O(1).
     * Returns the number of vertices in this edge-weighted graph.
     *
     * @return the number of vertices in this edge-weighted graph
     */
    public int vert() {
        return vert;
    }

    /**
     * The time complexity is O(1).
     * Returns the number of edges in this edge-weighted graph.
     *
     * @return the number of edges in this edge-weighted graph
     */
    public int edge() {
        return edge;
    }



    /**
     * The time complexity is O(1).
     * Adds an edge.
     *
     * @param      e     { Edge }
     */
    public void addEdge(final Edge e) {
        int v = e.either();
        int w = e.other(v);
        // validateVertex(v);
        // validateVertex(w);
        adj[v].add(e);
        adj[w].add(e);
        edge++;
    }

    /**
     * The time complexity is O(1).
     * Returns the edges incident on vertex {@code v}.
     *
     * @param  v the vertex
     * @return the edges incident on vertex {@code v} as an Iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<Edge> adj(final int v) {
        // validateVertex(v);
        return adj[v];
    }

    /**
     * The time complexity is O(1).
     * Returns the degree of vertex {@code v}.
     *
     * @param  v the vertex
     * @return the degree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int degree(final int v) {
        // validateVertex(v);
        return adj[v].size();
    }

    /**
     * The time complexity is O(V + E).
     * Returns all edges in this edge-weighted graph.
     * To iterate over the edges in this edge-weighted graph,
     * use foreach notation:
     * {@code for (Edge e : G.edges())}.
     *
     * @return all edges in this edge-weighted graph, as an iterable
     */
    public Iterable<Edge> edges() {
        Bag<Edge> list = new Bag<Edge>();
        for (int v = 0; v < vert; v++) {
            int selfLoops = 0;
            for (Edge e : adj(v)) {
                if (e.other(v) > v) {
                    list.add(e);
                } else if (e.other(v) == v) {
                    if (selfLoops % 2 == 0) {
                        list.add(e);
                    }
                    selfLoops++;
                }
            }
        }
        return list;
    }

    /**
     * The time complexity is O(V+E).
     * Returns a string representation of the object.
     *
     * @return     String representation of the object.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(vert + " " + "vertices"
            + " " + edge + " " + "edges" + NEWLINE);
        for (int v = 0; v < vert; v++) {
            s.append(v + ": ");
            for (Edge e : adj[v]) {
                s.append(e + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}
