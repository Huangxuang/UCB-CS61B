import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    } //Uncomment this class once you've created your Palindrome class.

    @Test
    public void testIsPalindrome() {
        CharacterComparator cc = new OffByOne();
        CharacterComparator cc2 = new OffByN(4);
        String a = "ab";
        String b = "flake";
        String c = "Abcba";
        assertTrue(palindrome.isPalindrome(a, cc));
        assertTrue(palindrome.isPalindrome(b, cc));
        assertFalse(palindrome.isPalindrome(c, cc));
    }
}
