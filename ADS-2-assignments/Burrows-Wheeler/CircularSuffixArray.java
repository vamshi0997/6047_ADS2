//import edu.princeton.cs.algs4.Queue;
import java.util.Arrays;
import java.util.Comparator;
public class CircularSuffixArray {
    private String inp;
    private Integer[] ind;
    public CircularSuffixArray(String s) {
        if(s == null) {
            throw new IllegalArgumentException("inp index is invalid.");
        }
        inp = s;
        ind = new Integer[length()];
        for(int i  = 0 ; i < ind.length; i++) {
            ind[i] = i;
        }
        Arrays.sort(ind, new Comparator<Integer>() {
            @Override
            public int compare(Integer first1, Integer second1) {
                int first = first1;
                int second = second1;
                for(int i = 0; i < inp.length(); i++) {
                    char a = inp.charAt(first);
                    char b = inp.charAt(second);
                    if( a < b) {
                        return -1;
                    } else if(a > b) {
                        return 1;
                    }
                    ++first;
                    if(first == inp.length()) {
                        first = 0;
                    }
                    ++second;
                    if(second == inp.length()) {
                        second = 0;
                    }
                 }
                 return 0;
            }
        });
    }

    public int length() {
        return inp.length();
    }

    /**
     * returns index of ith sorted suffix
     *
     * @param i
     *            the index of the ith sorted suffix
     * @return
     */
    public int index(int i) {
        if (i < 0 || i >= inp.length()) {
            throw new IllegalArgumentException("inp index is invalid.");
        }
        return ind[i];
    }
}
