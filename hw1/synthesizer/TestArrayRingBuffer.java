package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
        //arb.dequeue();
        arb.enqueue("abc");
        arb.enqueue('b');
        arb.enqueue(1);
        arb.enqueue(3);
        arb.enqueue("bcd");
        arb.enqueue('c');

        for (Object i : arb) {
            System.out.println(i);
        }
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);

    }
} 
