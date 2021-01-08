public interface Deque<T> {
    void addLast(T x);
    void addFirst(T x);
    boolean isEmpty();
    int size();
    T removeFirst();
    T removeLast();
    T get(int index);
    void printDeque();
}
