package synthesizer;


public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {
    protected int fillCount;
    protected int capacity;

    public int capacity() {
        return capacity;
    }

    public int fillCount() {
        return fillCount;
    }

    //What if I implements iterator() method at this class?
    /*
    //Create a class for iterator
    public Iterator<T> iterator() {
        return new dequeSetIterator();
    }

    public class dequeSetIterator<T> implements Iterator<T>{
        @Override
       public boolean hasNext() {
            return true;
        }

        @Override
        public T next() {
            return ???
            //Here comes the problem, I have to go to the concrete class
            //to finish all these method
        }
    }
   */

}
