//import java.util.NoSuchElementException;
/**
 * Class for digraph.
 */
public class Digraph {
    /**
     * Newline for every line.
     */
    private static final String NEWLINE =
    System.getProperty("line.separator");
    /**
     * number of vertices in this digraph.
     */
    private final int vertices;
    /**
     * number of edges in this digraph.
     */
    private int edges;
    /**
     * adj[v] = adjacency list for vertex v.
     */
    private Bag<Integer>[] adj;
    /**
     * indegree[v] = indegree of vertex v.
     */
    private int[] indegree;
    /**
     * Initializes an empty digraph with <em>V</em> vertices.
     *
     * @param  vertices1 the number of vertices
     * @throws IllegalArgumentException if {@code V < 0}
     */
    public Digraph(final int vertices1) {
        if (vertices1 < 0) {
            throw new IllegalArgumentException(
                "Number of vertices in a Digraph must be nonnegative");
        }
        this.vertices = vertices1;
        this.edges = 0;
        indegree = new int[vertices1];
        adj = (Bag<Integer>[]) new Bag[vertices1];
        for (int v = 0; v < vertices1; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    // /**
    //  * Initializes a digraph from the specified input stream.
    //  *
    //  * @param  in the input stream
    //  * @throws IllegalArgumentException
    //  * if the endpoints of any edge are not in prescribed range
    //  * @throws IllegalArgumentException
    //  * if the number of vertices or edges is negative
    //  * @throws IllegalArgumentException
    //  * if the input stream is in the wrong format
    //  */
    // public Digraph(final In in) {
    //     try {
    //         this.vertices = in.readInt();
    //         if (vertices < 0) {
    //             throw new IllegalArgumentException(
    //                 "number of vertices in a Digraph must be nonnegative");
    //         }
    //         indegree = new int[vertices];
    //         adj = (Bag<Integer>[]) new Bag[vertices];
    //         for (int v = 0; v < vertices; v++) {
    //             adj[v] = new Bag<Integer>();
    //         }
    //         int edges = in.readInt();
    //         if (edges < 0) {
    //             throw new IllegalArgumentException(
    //                 "number of edges in a Digraph must be nonnegative");
    //         }
    //         for (int i = 0; i < edges; i++) {
    //             int v = in.readInt();
    //             int w = in.readInt();
    //             addEdge(v, w);
    //         }
    //     } catch (NoSuchElementException edges) {
    //         throw new IllegalArgumentException(
    //             "invalid input format in Digraph constructor", edges);
    //     }
    // }

    /**
     * Initializes a new digraph that is a deep copy of the specified digraph.
     *
     * @param  g the digraph to copy
     */
    public Digraph(final Digraph g) {
        this(g.vertices());
        this.edges = g.edges();
        for (int v = 0; v < vertices; v++) {
            this.indegree[v] = g.indegree(v);
        }
        for (int v = 0; v < g.vertices(); v++) {
            // reverse so that adjacency list is in same order as original
            Stack<Integer> reverse = new Stack<Integer>();
            for (int w : g.adj[v]) {
                reverse.push(w);
            }
            for (int w : reverse) {
                adj[v].add(w);
            }
        }
    }

    /**
     * Returns the number of vertices in this digraph.
     *
     * @return the number of vertices in this digraph
     */
    public int vertices() {
        return vertices;
    }

    /**
     * Returns the number of edges in this digraph.
     *
     * @return the number of edges in this digraph
     */
    public int edges() {
        return edges;
    }


    /**
     * validate vertex.
     *
     * @param      v     vertex.
     */
    private void validateVertex(final int v) {
        if (v < 0 || v >= vertices) {
            throw new IllegalArgumentException(
                "vertex " + v + " is not between 0 and " + (vertices - 1));
        }
    }

    /**
     * Adds the directed edge v→w to this digraph.
     *
     * @param  v the tail vertex
     * @param  w the head vertex
     * @throws IllegalArgumentException unless both
     * {@code 0 <= v < V} and {@code 0 <= w < V}
     */
    public void addEdge(final int v, final int w) {
        validateVertex(v);
        validateVertex(w);
        adj[v].add(w);
        indegree[w]++;
        edges++;
    }

    /**
     * Returns the vertices adjacent from vertex in this digraph.
     *
     * @param  v the vertex
     * @return the vertices adjacent from vertex.
     * @throws IllegalArgumentException unless
     */
    public Iterable<Integer> adj(final int v) {
        validateVertex(v);
        return adj[v];
    }

    /**
     * Returns the number of directed edges incident from vertex {@code v}.
     * This is known as the <em>outdegree</em> of vertex {@code v}.
     *
     * @param  v the vertex
     * @return the outdegree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int outdegree(final int v) {
        validateVertex(v);
        return adj[v].size();
    }

    /**
     * Returns the number of directed edges incident to vertex {@code v}.
     * This is known as the <em>indegree</em> of vertex {@code v}.
     *
     * @param  v the vertex
     * @return the indegree of vertex.
     * @throws IllegalArgumentException unless.
     */
    public int indegree(final int v) {
        validateVertex(v);
        return indegree[v];
    }

    /**
     * Returns the reverse of the digraph.
     *
     * @return the reverse of the digraph
     */
    public Digraph reverse() {
        Digraph reverse = new Digraph(vertices);
        for (int v = 0; v < vertices; v++) {
            for (int w : adj(v)) {
                reverse.addEdge(w, v);
            }
        }
        return reverse;
    }
    /**
     * return the adjacent vertices in string format.
     *
     * @param      v     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public String adjv(final int v) {
        StringBuilder s = new StringBuilder();
        for (int w : adj[v]) {
            s.append(String.format("%d ", w));
        }
        return s.toString();
    }

    /**
     * Returns a string representation of the graph.
     *
     * @return the number of vertices.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(vertices + " vertices, " + edges + " edges " + NEWLINE);
        for (int v = 0; v < vertices; v++) {
            s.append(String.format("%d: ", v));
            for (int w : adj[v]) {
                s.append(String.format("%d ", w));
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}
