import java.util.Set;
import java.util.TreeSet;
public class BoggleSolver {
	private TrieST<Integer> trie;
	private static final char Q_LETTER = 'Q';
    private static final String QU_STRING = "QU";
	// Initializes the data structure using the given array of strings as the dictionary.
	// (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
	public BoggleSolver(String[] dictionary) {
    //System.out.println(Arrays.toString(dictionary));
    trie = new TrieST<Integer>();
    int[] score1 = {0, 0, 0, 1, 1, 2, 3, 5, 11};
    for (String s: dictionary) {
    	if (s.length() >= score1.length - 1) {
    		trie.put(s, score1[score1.length - 1]);
    	} else {
    		trie.put(s, score1[s.length()]);
    	}
    }
	}

	// Returns the set of all valid words in the given Boggle board, as an Iterable.
	public Iterable<String> getAllValidWords(BoggleBoard board) {
		//System.out.println(board);
		Set<String> foundWords = new TreeSet<String>();
		for(int i = 0; i < board.rows(); i++) {
			for(int j = 0; j < board.cols(); j++) {
				String charSequence = addLetter("", board.getLetter(i, j));
				boolean[][] marked = new boolean[board.rows()][board.cols()];
				marked[i][j] = true;
                dfs(board, i, j, foundWords, marked, charSequence);
				
			}
		}
		return foundWords;
	}

	public void dfs(BoggleBoard board, int i, int j, Set<String> foundWords, boolean[][] marked, String charSequence) {
      if (isValidWord(charSequence) ) {
      	foundWords.add(charSequence);
      }
      for (int row = Math.max(0, i - 1); row <= Math.min(board.rows() - 1, i + 1); row++) {
         for (int col = Math.max(0, j - 1); col <= Math.min(board.cols() - 1,j + 1); col++) {
            if (marked[row][col]) continue;
            if (!trie.hasPrefix(charSequence)) continue;
            
            marked[row][col] = true;
            dfs(board, row, col, foundWords, marked, addLetter(charSequence, board.getLetter(row, col)));
            
            marked[row][col] = false;
         }
      } 
    }

    private String addLetter(String to, char letter) {
      if (letter == Q_LETTER) return to + QU_STRING;
      else return to + letter;
    }
   
   private boolean isValidWord(String currentWord) {
      if (currentWord == null) return false;
      if (trie.contains(currentWord) && currentWord.length() > 2) return true;
      else return false;
   }

	// Returns the score of the given word if it is in the dictionary, zero otherwise.
	// (You can assume the word contains only the uppercase letters A through Z.)
	public int scoreOf(String word) {
		if (word == null || word.length() == 0) 
            throw new java.lang.IllegalArgumentException("board is null");
        Integer score = trie.get(word);
        if (score == null)
            return 0;
        else 
            return score;
	}
}