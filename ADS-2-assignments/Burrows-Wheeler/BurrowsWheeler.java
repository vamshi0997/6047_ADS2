
import java.util.Arrays;
import java.util.HashMap;
public class BurrowsWheeler {
    private static final int logR  = 8;
	private static CircularSuffixArray cs;

    public static void transform() {
    	String s = BinaryStdIn.readString();
    	CircularSuffixArray cs = new CircularSuffixArray(s);
    	for (int i = 0; i < cs.length(); ++i) {
            if (cs.index(i) == 0) {
                BinaryStdOut.write(i);
                break;
            }
        }
    	for (int i = 0; i < cs.length(); ++i) {
            int nindex = cs.index(i);
            if (nindex == 0) {
                BinaryStdOut.write(s.charAt(s.length() - 1), logR);
            }
            else {
                BinaryStdOut.write(s.charAt(nindex - 1), logR);
            }
        }
    	BinaryStdOut.close();

    }

    public static void inverseTransform() {
        int first = BinaryStdIn.readInt();
        String s = BinaryStdIn.readString();
        String[] t = s.split("");
        HashMap<String, Queue<Integer>> map
        = new HashMap<String, Queue<Integer>>();
        for (int i = 0; i < t.length; ++i) {
            if (!map.containsKey(t[i])) {
                map.put(t[i], new Queue<Integer>());
            }
            map.get(t[i]).enqueue(i);
        }

        Arrays.sort(t);
        int[] next = new int[t.length];
        for (int i = 0; i < next.length; ++i) {
            next[i] = map.get(t[i]).dequeue();
        }

        for (int i = 0; i < next.length; ++i) {
            BinaryStdOut.write(t[first], 8);
            first = next[first];
        }
        BinaryStdOut.close();
    }

    public static void main(String[] args) {
        if      (args[0].equals("-")) transform();
        else if (args[0].equals("+")) inverseTransform();
        else throw new IllegalArgumentException("Illegal command line argument");
    }
}
