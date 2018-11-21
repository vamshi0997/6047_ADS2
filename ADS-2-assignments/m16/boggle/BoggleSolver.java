import java.util.Set;
import java.util.TreeSet;
/**
 * Class for boggle solver.
 */
public class BoggleSolver {
    /**
     * trie is of type TrieST.
     */
    private TrieST<Integer> trie;
    /**
     * Q_LETTER is STRING contains 'Q'.
     */
    private static final char Q_LETTER = 'Q';
    /**
     * QU_STRING is String contains 'QU'.
     */
    private static final String QU_STRING = "QU";
    /**
     * Initializes the data structure using the given array
     * of strings as the dictionary. (You can assume each word in
     * the dictionary containsonly the uppercase letters A through Z.)
     * Time complexity for this method is O(l) where l is dictionary length.
     * Constructs the object.
     *
     * @param      dictionary  The dictionary
     */
    public BoggleSolver(final String[] dictionary) {
    //System.out.println(Arrays.toString(dictionary));
    trie = new TrieST<Integer>();
    final int eleven = 11;
    final int five = 5;
    int[] score1 = {0, 0, 0, 1, 1, 2, 2 + 1, five, eleven};
    for (String s: dictionary) {
        if (s.length() >= score1.length - 1) {
            trie.put(s, score1[score1.length - 1]);
        } else {
            trie.put(s, score1[s.length()]);
        }
    }
    }

    /**
     * Gets all valid words.
     * Returns the set of all valid words in the given Boggle board,
     * as an Iterable.
     * Time complexity for this method is O(rows * cols).
     *
     * @param      board  The board
     *
     * @return     All valid words.
     */
    public Iterable<String> getAllValidWords(final BoggleBoard board) {
        //System.out.println(board);
        Set<String> foundWords = new TreeSet<String>();
        for (int i = 0; i < board.rows(); i++) {
            for (int j = 0; j < board.cols(); j++) {
                String charSequence = addLetter("", board.getLetter(i, j));
                boolean[][] marked = new boolean[board.rows()][board.cols()];
                marked[i][j] = true;
                dfs(board, i, j, foundWords, marked, charSequence);
            }
        }
        return foundWords;
    }
    /**
     * Time complexity for this method is O(V + E).
     *
     * @param      board         The board
     * @param      i             { parameter_description }
     * @param      j             { parameter_description }
     * @param      foundWords    The found words
     * @param      marked        The marked
     * @param      charSequence  The character sequence
     */
    private void dfs(final BoggleBoard board, final int i, final int j,
        final Set<String> foundWords, final boolean[][] marked,
        final String charSequence) {
      if (isValidWord(charSequence)) {
        foundWords.add(charSequence);
      }
      for (int row = Math.max(0, i - 1); row <= Math.min(
        board.rows() - 1, i + 1); row++) {
         for (int col = Math.max(0, j - 1); col <= Math.min(
            board.cols() - 1, j + 1); col++) {
            if (marked[row][col]) {
                continue;
            }
            if (!trie.hasPrefix(charSequence)) {
                continue;
            }
            marked[row][col] = true;
            dfs(board, row, col, foundWords, marked, addLetter(
                charSequence, board.getLetter(row, col)));
            marked[row][col] = false;
         }
      }
    }
    /**
     * Adds a letter.
     *
     * @param      to      { parameter_description }
     * @param      letter  The letter
     *
     * @return     { description_of_the_return_value }
     */
    private String addLetter(final String to, final char letter) {
      if (letter == Q_LETTER) {
        return to + QU_STRING;
      } else {
        return to + letter;
      }
    }
   /**
    * is validword.
    * @param currentWord gives the currentWord.
    * @return true or false.
    */
    private boolean isValidWord(final String currentWord) {
      if (currentWord == null) {
        return false;
      }
      if (trie.contains(currentWord) && currentWord.length() > 2) {
        return true;
      }
      return false;
    }
    /**
     * Returns the score of the given word if it is in the dictionary
     * zero otherwise. (You can assume the word contains only
     * the uppercase letters A through Z.)
     * Time complexity for this method is O(L) where L is word length.
     *
     * @param      word  The word
     *
     * @return     { description_of_the_return_value }
     */
    public int scoreOf(final String word) {
        if (word == null || word.length() == 0) {
            throw new java.lang.IllegalArgumentException("board is null");
        }
        Integer score = trie.get(word);
        if (score == null) {
            return 0;
        } else {
            return score;
        }
    }
}
