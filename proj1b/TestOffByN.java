import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {

    CharacterComparator cc = new OffByN(4);

    @Test
    public void testEqualChars() {
        assertTrue(cc.equalChars('a','e'));

    }
}
