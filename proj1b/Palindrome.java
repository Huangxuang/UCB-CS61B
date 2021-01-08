public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> d = new ArrayDeque();
        for (int i = 0; i < word.length(); i++) {
            d.addLast(word.charAt(i));
        }
        return d;
    }

    public boolean isPalindrome(String word) {
        if (word.length() < 2) {
            return true;
        }
        Deque d = wordToDeque(word);
        Deque temp = wordToDeque(word);
       //Create a temp deque, add char fro the end;
        for (int i = 0; i < word.length(); i++) {
            if (d.removeFirst() == temp.removeLast()) {
                continue;
            }
            break;
        }
        return (d.size() == 0);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> d = wordToDeque(word);
        while (d.size() > 1) {
            if (!cc.equalChars(d.removeFirst(), d.removeLast())) {
                return false;
            }
        }
        return true;
    }
}
