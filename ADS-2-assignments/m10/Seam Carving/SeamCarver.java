import java.lang.Math;
public class SeamCarver {
	Picture pic;
	// create a seam carver object based on the given picture
	public SeamCarver(Picture picture) {
		this.pic = new Picture(picture);

	}
	// current picture
	public Picture picture() {
		return new Picture(pic);
	}
	// width of current picture
	public int width() {
		return this.pic.width();
	}

	// height of current picture
	public int height() {
		return this.pic.height();
	}

	// energy of pixel at column x and row y
	public double energy(int x, int y) {
		int w = width();
		int h = height();
		if (x == 0 || x == w - 1) {
			return 1000;
		}
		if (y == 0 || y == h - 1) {
			return 1000;
		}
		int rx = pic.get(x, y + 1).getRed() - pic.get(x, y - 1).getRed();
		int gx = pic.get(x, y + 1).getGreen() - pic.get(x, y - 1).getGreen();
		int bx = pic.get(x, y + 1).getBlue() - pic.get(x, y - 1).getBlue();
		int newx = (rx * rx) + (gx * gx) + (bx * bx);
		int ry = pic.get(x + 1, y).getRed() - pic.get(x - 1, y).getRed();
		int gy = pic.get(x + 1, y).getGreen() - pic.get(x - 1, y).getGreen();
		int by = pic.get(x + 1, y).getBlue() - pic.get(x - 1, y).getBlue();
		int newy = (ry * ry) + (gy * gy) + (by * by);
		return Math.sqrt(newx + newy);
	}

	// sequence of indices for horizontal seam
	public int[] findHorizontalSeam() {
		return new int[0];
	}

	// sequence of indices for vertical seam
	public int[] findVerticalSeam() {
		return new int[0];
	}

	// remove horizontal seam from current picture
	public void removeHorizontalSeam(int[] seam) {

	}

	// remove vertical seam from current picture
	public void removeVerticalSeam(int[] seam) {

	}
}