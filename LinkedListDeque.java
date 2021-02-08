public class LinkedListDeque<T> {

    //Create the naked Nested Class DoubleLinkedNote
    private class DoubleLinkedNode {
        private DoubleLinkedNode next;
        private DoubleLinkedNode previous;
        private T item;

        DoubleLinkedNode(T x, DoubleLinkedNode previous, DoubleLinkedNode next) {
            item = x;
            this.next = next;
            this.previous = previous;
        }
    }

    // start of LinkedListDeque
    /*Invariants
    * The sentinel reference always points to a sentinel node
    * The first node is always at sentinel.next
    * The size variable is always the number of total items that have been added*/
    private DoubleLinkedNode sentinel;
    private int size;

    // Create a empty LinkedListDeque
    public LinkedListDeque() {
        sentinel = new DoubleLinkedNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.previous = sentinel;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(T x) {
        DoubleLinkedNode temp = sentinel.next;
        sentinel.next = new DoubleLinkedNode(x, sentinel, sentinel.next);
        temp.previous = sentinel.next;
        size += 1;
    }

    public void addLast(T x) {
        DoubleLinkedNode temp = sentinel.previous;
        sentinel.previous = new DoubleLinkedNode(x, sentinel.previous, sentinel);
        temp.next = sentinel.previous;
        size += 1;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (size != 0) {
            DoubleLinkedNode p = sentinel;
            while (p.next != sentinel) {
                System.out.print(p.item + " ");
                p = p.next;
            }
        }
        System.out.println("Empty !");
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        DoubleLinkedNode temp = sentinel.next;
        sentinel.next.next.previous = sentinel;
        sentinel.next = sentinel.next.next;
        //delete the removed node from memory
        temp.next = null;
        temp.previous = null;
        size -= 1;
        return temp.item;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        DoubleLinkedNode temp = sentinel.previous;
        sentinel.previous.previous.next = sentinel;
        sentinel.previous = sentinel.previous.previous;
        //delete the removed node from memory
        temp.next = null;
        temp.previous = null;
        size -= 1;
        return temp.item;
    }

    public T get(int index) {
        if ((index > size - 1) || index < 0) {
            return null;
        }
        DoubleLinkedNode temp = sentinel;
        for (int i = 0; i <= index; i++) {
            temp = temp.next;
        }
        return temp.item;
    }

    /* This is the help class to do getRecursive
    *return a DoubleLinkedNode temp point to the ith item
     */
    private DoubleLinkedNode getItem(DoubleLinkedNode temp, int index) {

        if (index == 0) {
            return temp;
        }
        return getItem(temp.next, index - 1);
    }

    public T getRecursive(int index) {
        if ((index > size - 1) || index < 0) {
            return null;
        }
        //I need a helper method
        DoubleLinkedNode res = this.getItem(sentinel.next, index);
        return res.item;
    }




}
