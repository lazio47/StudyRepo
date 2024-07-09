import java.util.ListIterator;

public class VectorListIterator<T> implements ListIterator<T> {
    private int cursor;
    private int lastRet = -1;
    private final VectorGeneric<T> vector;
    protected T[] vec;	

    public VectorListIterator(VectorGeneric<T> vector, int index) {
        this.vector = vector;
        this.cursor = index;
    }

    @Override
    public boolean hasNext() {
        return cursor < vector.totalElem();
    }

    @Override
    public T next() {
        if (!hasNext())
            return null; 
        lastRet = cursor;
        return vector.getElem(cursor++);
    }

    @Override
    public boolean hasPrevious() {
        return cursor > 0;
    }

    @Override
    public T previous() {
        if (!hasPrevious())
            return null; 
        lastRet = --cursor;
        return vector.getElem(cursor);
    }

    @Override
    public int nextIndex() {
        return cursor;
    }

    @Override
    public int previousIndex() {
        return cursor - 1;
    }

    @Override
    public void remove() {
        if (lastRet < 0)
            throw new IllegalStateException();
        vector.removeElem(vector.getElem(lastRet));
        cursor = lastRet;
        lastRet = -1;
    }

    public T[] getVec() {
        return vec;
    }

    @Override
public void set(T e) {
    if (lastRet < 0)
        throw new IllegalStateException();
    vec[lastRet] = e;
}


    @Override
    public void add(T e) {
        vector.addElem(e);
        cursor++;
        lastRet = -1;
    }
}
