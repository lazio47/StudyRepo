
import java.util.Collection;

public class RemoveCommand<E> implements Command {

    private Collection<E> collection;
    private E element;

    public RemoveCommand(Collection<E> collection, E element) {
        this.collection = collection;
        this.element = element;
    }

    @Override
    public boolean execute() {
        return collection.remove(element);
    }

    @Override
    public boolean undo() {
        return collection.add(element);
    }

}
