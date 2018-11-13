import java.awt.Color;
import java.lang.Math;
/**
 * Class for seam carver.
 */
public class SeamCarver {
    /**
     *the picture object.
     */
    private Picture pic;
    /**
     *the width of image.
     */
    private int width;
    /**
     *the height of pixel.
     */
    private int height;
    /**
     *the constructor to initialize.
     *
     * @param      pic   The picture
     */
    public SeamCarver(final Picture picture) {
        this.pic = picture;
        width = pic.width();
        height = pic.height();
    }
    /**
     *the method will return the picture.
     *object.
     * @return picture object.
     */
    public Picture picture() {
        return pic;
    }
    /**
     *this method will return the width.
     *of image.
     * @return width of pixel
     */
    public int width() {
        return width;
    }
    /**
     *height of current picture
     *
     * @return height of image.
     */
    public int height() {
        return height;
    }
    /**
     *energy of pixel at column x and rows y
     *
     * @param      x  x coordinate
     * @param      y   y coordinate
     *
     * @return energy of pixel.
     */
    public double energy(int x, int y) {
        int w = width();
        int h = height();
        if (x == 0 || x == w - 1) {
            return 1000;
        }
        if (y == 0 || y == h - 1) {
            return 1000;
        }
        int rx = Math.abs(pic.get(x, y + 1).getRed() - pic.get(x, y - 1).getRed());
        int gx = Math.abs(pic.get(x, y + 1).getGreen() - pic.get(x, y - 1).getGreen());
        int bx = Math.abs(pic.get(x, y + 1).getBlue() - pic.get(x, y - 1).getBlue());
        int newx = (rx * rx) + (gx * gx) + (bx * bx);
        int ry = Math.abs(pic.get(x + 1, y).getRed() - pic.get(x - 1, y).getRed());
        int gy = Math.abs(pic.get(x + 1, y).getGreen() - pic.get(x - 1, y).getGreen());
        int by = Math.abs(pic.get(x + 1, y).getBlue() - pic.get(x - 1, y).getBlue());
        int newy = Math.abs((ry * ry) + (gy * gy) + (by * by));
        return Math.sqrt(newx + newy);
    }
    /**sequence of indices for horizontal seam
     *
     *time complexity is O(w*h)
     *w is the width and h is the height
     * @return  sequence of indices of horizontal seam
     */
    public int[] findHorizontalSeam() {
        int[][] edgeTo = new int[height][width];
        double[][] distTo = new double[height][width];
        reset(distTo);
        for (int rows = 0; rows < height; rows++) {
            distTo[rows][0] = 1000;
        }
        // this is for relaxation.
        for (int columns = 0; columns < width - 1; columns++) {
            for (int rows = 0; rows < height; rows++) {
                relaxHorizontal(rows, columns, edgeTo, distTo);
            }
        }
        double minDist = Double.MAX_VALUE;
        int minRow = 0;
        for (int rows = 0; rows < height; rows++) {
            if (minDist > distTo[rows][width - 1]) {
                minDist = distTo[rows][width - 1];
                minRow = rows;
            }
        }
        int[] indices = new int[width];
        //to find the horizontal seam.
        for (int columns = width - 1, rows = minRow; columns >= 0; columns--) {
            indices[columns] = rows;
            rows -= edgeTo[rows][columns];
        }
        return indices;
    }
    private void relaxHorizontal(int rows, int columns, int[][] edgeTo, double[][] distTo) {
        int nextCol = columns + 1;
        for (int i = -1; i <= 1; i++) {
            int nextRow = rows + i;
            if (nextRow < 0 || nextRow >= height) continue;
            if (i == 0) {
                if(distTo[nextRow][nextCol] >= distTo[rows][columns]  + energy(nextCol, nextRow)) {
                    distTo[nextRow][nextCol] = distTo[rows][columns]  + energy(nextCol, nextRow);
                    edgeTo[nextRow][nextCol] = i;
                }
            }
            if (distTo[nextRow][nextCol] > distTo[rows][columns]  + energy(nextCol, nextRow)) {
                distTo[nextRow][nextCol] = distTo[rows][columns]  + energy(nextCol, nextRow);
                edgeTo[nextRow][nextCol] = i;
            }
        }
    }
    /**
     *this method is to find the vertical seam.
     *first of all find the shortest path from top to.
     *bottom.
     *time complexity is O(w*h)
     *w is the width and h is the height
     * @return sequence of indices for vertical seam.
     */
    public int[] findVerticalSeam() {
        double[][] energy = new double[height][width];
        int[][] edgeTo = new int[height][width];
        double[][] distTo = new double[height][width];
        reset(distTo);
        int[] indices = new int[height];
        if (width == 1 || height == 1) {
            return indices;
        }
        for (int i = 0; i < width; i++) {
            distTo[0][i] = 1000.0;
        }
        // this is for relaxation.
        for (int i = 0; i < height - 1; i++) {
            for (int j = 0; j < width; j++) {
                relaxVertical(i, j, edgeTo, distTo);
            }
        }
        // calculating from last rows
        // column wise
        double minDist = Double.MAX_VALUE;
        int minCol = 0;
        for (int columns = 0; columns < width; columns++) {
            if (minDist > distTo[height - 1][columns]) {
                minDist = distTo[height - 1][columns];
                minCol = columns;
            }
        }
        //indices values of shortest path.
        for (int rows = height -1, columns = minCol; rows >= 0; rows--) {
            indices[rows] = columns;
            columns -= edgeTo[rows][columns];
        }
        indices[0] = indices[1];
        return indices;
    }
    /**
     *time complexity is O(W * H)
     *W is the width of image
     *H is the height of image
     * @param      distTo  The distance to
     */
    private void reset(double[][] distTo) {
        /**
         *reset all the values to maxvalue.
         */
        for (int i = 0; i < distTo.length; i++) {
            for (int j = 0; j < distTo[i].length; j++) {
                distTo[i][j] = Double.MAX_VALUE;
            }
        }
    }
    private void relaxVertical(int rows, int columns, int[][] edgeTo, double[][] distTo) {
        int nextRow = rows + 1;
        for (int i = -1; i <= 1; i++) {
            int nextCol = columns + i;
            if (nextCol < 0 || nextCol >= width) {
                continue;
            }
            //spl case for bottom element.
            if(i == 0) {
                if(distTo[nextRow][nextCol] >= distTo[rows][columns]
                    + energy(nextCol, nextRow)) {
                distTo[nextRow][nextCol] = distTo[rows][columns] + energy(nextCol, nextRow);
                edgeTo[nextRow][nextCol] = i;
                }
            }
            if (distTo[nextRow][nextCol] > distTo[rows][columns] + energy(nextCol, nextRow)) {
                distTo[nextRow][nextCol] = distTo[rows][columns] + energy(nextCol, nextRow);
                edgeTo[nextRow][nextCol] = i;
            }
        }
    }
    // remove horizontal seam from current picture
    //time complexity is O(width * height)
    public void removeHorizontalSeam(int[] seam) {
        //handle exceptions
    for (int columns = 0; columns < width; columns++) {
        for (int rows = seam[columns]; rows < height - 1; rows++) {
            this.pic.set(columns, rows, this.pic.get(columns, rows + 1));
        }
    }
    height--;
    }
    // remove vertical seam from current picture
    //time complexity is O(width * height)
    public void removeVerticalSeam(int[] seam) {
    for (int rows = 0; rows < height; rows++) {
        for (int columns = seam[rows]; columns < width - 1; columns++) {
        this.pic.set(columns, rows, this.pic.get(columns + 1, rows));
        }
    }
    width--;
    }
}
