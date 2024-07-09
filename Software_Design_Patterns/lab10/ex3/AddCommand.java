

import java.util.Collection;


public class AddCommand<E> implements Command {

    private Collection<E> collection;
    private E element;

    public AddCommand(Collection<E> collection, E element) {
        this.collection = collection;
        this.element = element;
    }

    @Override
    public boolean execute() {
        return collection.add(element);
    }

    @Override
    public boolean undo() {
        return collection.remove(element);
    }

    
    
}
