import static org.junit.Assert.*;
import org.junit.Test;

public class HorribleSteve {
    @Test
    public static void main(String [] args) {
        int i = 0;
        for (int j = 0; i < 500; ++i, ++j) {
            //System.out.println("i = " + i);
            //System.out.println("j = " + j);
            assertTrue("check this 1 st condition!!", i ==j );
            assertTrue("check this 2 st condition!!", Flik.isSameNumber(i, j));
            if (!Flik.isSameNumber(i, j)) {
                assertTrue("check this 3rd condition!!", Flik.isSameNumber(i, j));
                break; // break exits the for loop!
            }
        }
        System.out.println("i is " + i);
    }
}
