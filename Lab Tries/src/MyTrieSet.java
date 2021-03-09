import java.util.*;

/**Each Node contains letter, map to all child nodes and a color
 * */

public class MyTrieSet implements TrieSet61B {
    private Node root;

    private class Node {
        //private char ch;
        //If current node is an end key, mark it blue
        private boolean isBlue;
        private Map<Character,Node> next;

        public Node(boolean b) {
            //ch = a;
            isBlue = b;
            next = new HashMap<>();

        }
    }
    public MyTrieSet() {
        root = new Node( false);
    }

    @Override
    public void clear() {
        root = new Node(false);
    }

    @Override
    public boolean contains(String key) {
        Node temp = root;
        for (int i = 0; i < key.length(); i++) {
            char currentChar = key.charAt(i);
            if (!temp.next.containsKey(currentChar)) {
                return false;
            }
            //update root to next node
            temp = temp.next.get(currentChar);
        }
        return true;
    }
    /** for each char in the string key:
     *      Find the first char which root doesn't contain,add them
     *      For the last char, mark it blue
     * */
    @Override
    public void add(String key) {
        if (key.length() < 1 || key == null) {
            return;
        }
        Node temp = root;
        for (int i = 0; i < key.length(); i++ ) {
            char currentChar = key.charAt(i);

            if (!temp.next.containsKey(currentChar)) {
                temp.next.put(currentChar,new Node(false));
            }
            temp = temp.next.get(currentChar);
        }
        temp.isBlue = true;
    }
    /**Create new empty list res,
     * for each character in the prefix:
     * if root contains all of them, make a new node temp at the last character @ prefix
     * if temp.isBlue == true, add it to res;
     * for each character c in prefix.next.keySet():
     *  call prefixHelper(res,c,prefix.next)
     *
     *
     * */
    @Override
    public List<String> keysWithPrefix(String prefix) {
        Node temp = root;
        List<String> res = new ArrayList<>();
        for (int i = 0; i < prefix.length(); i++) {
            char currentChar = prefix.charAt(i);
            if (!temp.next.containsKey(currentChar)) {
                throw new IllegalArgumentException("no such prefix exist");
            }
            temp = temp.next.get(currentChar);
        }
        prefixHelper(res, prefix, temp);

//        if (temp.isBlue == true) {
//            res.add(prefix);
//        }
//        for (char c : temp.next.keySet()) {
//            String s = prefix + c;
//            prefixHelper(res, s, temp.next.get(c));
//        }

        return res;
    }
    /**prefixHelper(List l, String s, node P)
     * if p.isBlue :
     *      l.add(s)
     * For character c in p.next.keys()
     * call prefixHelper(l,s + c,p.next.get(c))
     *
     * */
    private void prefixHelper(List res, String s, Node p) {
        //String updatedS = s +
        if (p.isBlue == true) {
            res.add(s);
        }
        for (char c : p.next.keySet()) {
            prefixHelper(res, s+c, p.next.get(c));
        }
    }

    @Override
    public String longestPrefixOf(String key) {
        return null;
    }
}

