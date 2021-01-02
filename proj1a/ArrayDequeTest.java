public class ArrayDequeTest {
    public static void main(String[] args) {
        ArrayDeque a = new ArrayDeque();
        a.addLast(1);
        //System.out.println(a.items.length);

        a.addLast(2);
        a.addFirst("a");
        a.addLast(3);
        a.addFirst("b");
        a.addFirst("c");
        a.addFirst("d");
        a.addFirst("e");

        a.addLast(5);
        a.addLast(6);
        a.addLast(7);
        a.addLast(8);
        a.addLast(99);
        a.addLast(9);
        a.addLast(11);
        a.addFirst("q");
        a.addFirst("qq");
        a.get(100);
        System.out.println(a.get(5));
        a.printDeque();

        a.removeFirst();
        a.removeFirst();
        a.removeFirst();
        a.removeFirst();
        a.removeFirst();
        a.removeLast();
        a.removeLast();
        a.removeLast();
        a.removeLast();
        a.removeLast();
        a.removeLast();
        a.removeLast();
        a.removeLast();



    }
}
