/**
 * Class for percolate.
 */
class Percolate {
    /**
     * variable to keep count value.
     */
    private int count;
    /**
     * to take 2 dimentional array.
     */
    private int[][] matrix;
    /**
     * to keep size.
     */
    private int size;
    /**
     * object for weighted addEdge class.
     */
    private Graph g;
    /**
     * cc of type CC.
     */
    private CC c;
    /**
     * Constructs the object.
     * @param      vertex  The size
     */
    Percolate(final int vertex) {
        matrix = new int[vertex][vertex];
        count = 0;
        g = new Graph((vertex * vertex) + 2);
        c = new CC(g);
        this.size = vertex;
    }
    /**
     * function to check whether the element has to be connected or not.
     * @param      rows   The value of rows
     * @param      columns   The value of column
     * Time complexity for this method is O(1).
     */
    void open(final int rows, final int columns) {
        matrix[rows][columns] = 1;
        count++;
        if (rows == 0) {
            g.addEdge(0, component(rows, columns));
        }
        if (rows == size - 1) {
            g.addEdge((size * size) + 1, component(rows, columns));
        }
        if (rows + 1 < size && matrix[rows][columns] == 1) {
            g.addEdge(
                component(rows + 1, columns), component(rows, columns));
        }
        if (rows - 1 >= 0 && matrix[rows - 1][columns] == 1) {
            g.addEdge(
                component(rows - 1, columns), component(rows, columns));
        }
        if (columns - 1 >= 0 && matrix[rows][columns - 1] == 1) {
            g.addEdge(component(rows, columns - 1), component(rows, columns));
        }
        if (columns + 1 < size && matrix[rows][columns + 1] == 1) {
            g.addEdge(
                component(rows, columns + 1), component(rows, columns));
        }
    }
    /**
     * to get the component at the particular rows and column.
     * @param      i     rows index is given.
     * @param      j     column index is given
     * @return     return type is int
     * Time complexity for this method is O(1).
     */
    int component(final int i, final int j) {
        return (i) * size + j;
    }
    /**
     * Determines if open.
     * @param      rows   The rows
     * @param      columns   The columns
     * @return     True if open, False otherwise.
     * Time complexity for this method is O(1).
     */
    boolean isOpen(final int rows, final int columns) {
        return matrix[rows][columns] == 1;
    }
    /**
     * Determines if full.
     * @param      rows   The rows
     * @param      columns   The columns
     * @return     True if full, False otherwise.
     * Time complexity for this method is O(1).
     */
    boolean isFull(final int rows, final int columns) {
        return matrix[rows][columns] == 0;
    }
    /**
     * to get the number of open sites.
     * @return     integer is returned.
     * Time complexity for this method is O(1).
     */
    int numberofopensites() {
        return count;
    }
    /**
     * function to check if the matrix percolates or not.
     * @return     True if percolates, False otherwise.
     * Time complexity for this method is O(1).
     */
    boolean percolates() {
        c = new CC(g);
        return c.connected(0, (size * size) + 1);
    }
}
