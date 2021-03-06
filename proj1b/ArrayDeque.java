public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
 /*Circled ArrayDeque invariants:
 * The position of the next item to be inserted is always nextFirst
 * The position of the last item to be inserted is always nextLast
 * size is always the number of items in the ArrayDeque*/
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }
    //enlarge current size to 2 times
    private void enlarge() {
        T[] temp = (T[]) new Object[size * 2];
        if (nextFirst < nextLast) {
            //copy items in int[] this before index nextfirst into int[] temp at the same location
            System.arraycopy(items, 0, temp, 0, nextFirst + 1);
            //copy items in int[] this,
            // after index nextLast into int[] temp at the location from nextLast + size
            System.arraycopy(items, nextLast, temp, nextLast + size, size - nextLast);
            nextFirst = nextFirst + size;
        } else {
            System.arraycopy(items, 0, temp, 0, size);
            nextLast = size;
            nextFirst = temp.length - 1;
        }
        items = temp;
    }
    @Override
    public void addLast(T x) {
        //If ArrayDeque is full:
        if (size  == items.length) {
            //call enlarge method
            this.enlarge();
            items[nextLast] = x;
            nextLast += 1;
        } else if (nextLast + 1 == items.length) {
            items[nextLast] = x;
            nextLast = 0;
        } else {
            items[nextLast] = x;
            nextLast += 1;
        }
        size += 1;

    }
    @Override
    public void addFirst(T x) {
        //If ArrayDeque is full:
        if (size  == items.length) {
            //call enlarge method
            this.enlarge();
            // update nextFirst
        }
        items[nextFirst] = x;
        nextFirst = (nextFirst + items.length - 1) % items.length;
        size += 1;
    }
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    @Override
    public int size() {
        return size;
    }

    //decrease current size to half
    private void shrink() {
        T[] temp = (T[]) new Object[items.length / 2];
        //when nF is on the right side of nL
        if (nextFirst > nextLast) {
            //copy items in int[] this before index nextLast into int[] temp at the same location
            System.arraycopy(items, 0, temp, 0, nextLast);
            //copy items in int[] this,
            // after index nextLast into int[] temp at the location from nextLast + size
            System.arraycopy(items, nextFirst, temp,
                    nextFirst - items.length / 2, items.length - nextFirst);
            nextFirst = nextFirst - items.length / 2;
        } else {
            System.arraycopy(items, nextFirst, temp, 0, nextLast - nextFirst);
            nextLast = nextLast - nextFirst;
            nextFirst = 0;
        }
        items = temp;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        nextFirst = (nextFirst + 1 + items.length) % items.length;
        size--;
        T res = items[nextFirst];
        items[nextFirst] = null;
        if (items.length >= 16 && size < 0.25 * items.length) {
            this.shrink();
        }
        return res;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        nextLast = (nextLast - 1 + items.length) % items.length;
        size--;
        T res = items[nextLast];
        items[nextLast] = null;
        if (items.length >= 16 && size < 0.25 * items.length) {
            this.shrink();
        }
        return res;
    }

    @Override
    public T get(int index) {
        if ((index > size - 1) || index < 0) {
            return null;
        }
        return items[(nextFirst + index + 1) % items.length];
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(get(i) + " ");
        }
    }


}
