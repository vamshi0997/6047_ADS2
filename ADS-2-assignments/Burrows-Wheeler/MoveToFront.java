import java.util.Arrays;
import java.util.LinkedList;
import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {
    private static final int lgR = 8;

    public static void encode() {
        LinkedList<Integer> chararr1 = new LinkedList<Integer>();
        final int R = 256;
        for(int i = 0; i < R; i++) {
            chararr1.add(i);
        }
        while(!BinaryStdIn.isEmpty()) {
            int ch1 = BinaryStdIn.readChar();
            int index = chararr1.indexOf(ch1);
            BinaryStdOut.write(index, lgR);
            chararr1.remove(index);
            chararr1.add(0, ch1);
        }
        BinaryStdOut.close();
    }

    public static void decode() {
        LinkedList<Integer> chararray2 = new LinkedList<Integer>();
        final int R = 256;
        for(int i = 0; i < R; i++) {
            chararray2.add(i);
        }
        while(!BinaryStdIn.isEmpty()) {
            int index = BinaryStdIn.readChar();
            int  ch2 = chararray2.get(index);
            BinaryStdOut.write(ch2, lgR);
            chararray2.remove(index);
            chararray2.add(0, ch2);
        }
        BinaryStdOut.close();
    }

    public static void main(String[] args) {
        if      (args[0].equals("-")) encode();
        else if (args[0].equals("+")) decode();
        else throw new IllegalArgumentException("Illegal command line argument");
    }
}
