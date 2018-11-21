/**
 * New class to fast check (method hasPrefix()) if such prefix is in the trie.
 * @param <Value> value in key/value pair.
 */
public class TrieST<Value> {
    /**
     * Size of num.
     */
    private int num;
    /**
     * root of TST.
     */
    private Node root;
    /**
     * Class for node.
     */
    private class Node {
        /**
         * character c of type c.
         */
        private char c;
        /**
         * left, middle, and right subtries.
         */
        private Node left, mid, right;
        /**
         * value associated with string.
         */
        private Value val;
    }

    /**
     * return number of key-value pairs.
     *
     * @return     { description_of_the_return_value }
     */
    public int size() {
       return num;
    }

   /**
    * Is string key in the symbol table?.
    *
    * @param      key   The key
    *
    * @return     { description_of_the_return_value }
    */
   public boolean contains(final String key) {
       return get(key) != null;
   }

    /**
     * gets the key value.
     *
     * @param      key   The key
     *
     * @return     { description_of_the_return_value }
     */
    public Value get(final String key) {
        if (key == null) {
            throw new NullPointerException();
        }
        if (key.length() == 0) {
            throw new IllegalArgumentException("key must have length >= 1");
        }
        Node x = get(root, key, 0);
        if (x == null) {
            return null;
        }
        return x.val;
    }

    /**
     * return subtrie corresponding to given key.
     *
     * @param      x     { parameter_description }
     * @param      key   The key
     * @param      d     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
   private Node get(final Node x, final String key, final int d) {
        if (key == null) {
            throw new NullPointerException();
        }
        if (key.length() == 0) {
            throw new IllegalArgumentException("key must have length >= 1");
        }
        if (x == null) {
            return null;
        }
        char c = key.charAt(d);
        if (c < x.c) {
            return get(x.left,  key, d);
        } else if (c > x.c) {
            return get(x.right, key, d);
        } else if (d < key.length() - 1) {
            return get(x.mid,   key, d + 1);
        } else {
            return x;
        }
   }

    /**
     * Insert string s into the symbol table.
     *
     * @param      s     { parameter_description }
     * @param      val   The value
     */
   public void put(final String s, final Value val) {
        if (!contains(s)) {
            num++;
        }
        root = put(root, s, val, 0);
   }

    /**
     * put method creates new node.
     *
     * @param      x     { parameter_description }
     * @param      s     { parameter_description }
     * @param      val   The value
     * @param      d     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    private Node put(final Node x, final String s,
        final Value val, final int d) {
        char c = s.charAt(d);
        if (x == null) {
            x = new Node();
            x.c = c;
        }
        if (c < x.c) {
            x.left  = put(x.left,  s, val, d);
        } else if (c > x.c) {
            x.right = put(x.right, s, val, d);
        } else if (d < s.length() - 1)    {
            x.mid   = put(x.mid,   s, val, d + 1);
        } else {
            x.val   = val;
        }
       return x;
    }

    /**
     * Find and return longest prefix of s in TST.
     *
     * @param      s     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public String longestPrefixOf(final String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        int length = 0;
        Node x = root;
        int i = 0;
        while (x != null && i < s.length()) {
            char c = s.charAt(i);
            if (c < x.c) {
                x = x.left;
            } else if (c > x.c) {
                x = x.right;
            } else {
                i++;
                if (x.val != null) {
                    length = i;
                }
                x = x.mid;
           }
       }
       return s.substring(0, length);
    }

    /**
     * all keys in symbol table.
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<String> keys() {
        Queue<String> queue = new Queue<String>();
        collect(root, "", queue);
        return queue;
    }

   /**
    * all keys starting with given prefix.
    *
    * @param      prefix  The prefix
    *
    * @return     { description_of_the_return_value }
    */
   public Iterable<String> prefixMatch(final String prefix) {
        Queue<String> queue = new Queue<String>();
        Node x = get(root, prefix, 0);
        if (x == null) {
            return queue;
        }
        if (x.val != null) {
            queue.enqueue(prefix);
        }
        collect(x.mid, prefix, queue);
        return queue;
   }

   /**
    * all keys in subtrie rooted at x with given prefix.
    *
    * @param      x       { parameter_description }
    * @param      prefix  The prefix
    * @param      queue   The queue
    */
    private void collect(final Node x, final String prefix,
    final Queue<String> queue) {
        if (x == null) {
            return;
        }
        collect(x.left, prefix, queue);
        if (x.val != null) {
            queue.enqueue(prefix + x.c);
        }
        collect(x.mid, prefix + x.c, queue);
        collect(x.right, prefix, queue);
   }

   /**
    * return all keys matching given wildcard pattern.
    *
    * @param      pat   The pattern
    *
    * @return     { description_of_the_return_value }
    */
   public Iterable<String> wildcardMatch(final String pat) {
       Queue<String> queue = new Queue<String>();
       collect(root, "", 0, pat, queue);
       return queue;
   }

   /**
    * { function_description }.
    *
    * @param      x       { parameter_description }
    * @param      prefix  The prefix
    * @param      i       { parameter_description }
    * @param      pat     The pattern
    * @param      q       The quarter
    */
    private void collect(final Node x, final String prefix,
    final int i, final String pat, final Queue<String> q) {
        if (x == null) {
            return;
        }
        char c = pat.charAt(i);
        if (c == '.' || c < x.c) {
            collect(x.left, prefix, i, pat, q);
        }
        if (c == '.' || c == x.c) {
            if (i == pat.length() - 1 && x.val != null) {
            q.enqueue(prefix + x.c);
           }
           if (i < pat.length() - 1) {
            collect(x.mid, prefix + x.c, i + 1, pat, q);
           }
       }
       if (c == '.' || c > x.c) {
        collect(x.right, prefix, i, pat, q);
       }
   }

   /**
    * has TST word with this prefix?
    * Determines if it has prefix.
    *
    * @param      prefix  The prefix
    *
    * @return     True if has prefix, False otherwise.
    */
   public boolean hasPrefix(final String prefix) {
      Node prefixNode = get(root, prefix, 0);
      if (prefixNode == null) {
        return false;
      }
      if (prefixNode.val != null) {
        return true;
      }
      if (prefixNode.left == null && prefixNode.mid == null
        && prefixNode.right == null) {
        return false;
      }
      return true;
   }
}
