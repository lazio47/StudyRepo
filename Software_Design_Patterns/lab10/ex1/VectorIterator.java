import java.util.Iterator;

public class VectorIterator<T> implements Iterator<T> {
    private int cursor;
    private final VectorGeneric<T> vector;

    public VectorIterator(VectorGeneric<T> vector) {
        this.vector = vector;
        this.cursor = 0;
    }

    @Override
    public boolean hasNext() {
        return cursor < vector.totalElem();
    }

    @Override
    public T next() {
        if (!hasNext())
            return null;
        return vector.getElem(cursor++);
    }
}
