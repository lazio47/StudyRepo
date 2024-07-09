import java.util.ArrayList;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        Collection<Integer> collection = new ArrayList<>();
        Invoker invoker = new Invoker();

       
        Command addCommand = new AddCommand<>(collection, 5);
        invoker.setCommand(addCommand);
        if (invoker.executeCommand()) {
            System.out.println("Elemento 5 adicionado: " + collection);
        }

       
        if (invoker.undoCommand()) {
            System.out.println("Elemento 5 removido: " + collection);
        }

        
        collection.add(10); 
        Command removeCommand = new RemoveCommand<>(collection, 10);
        invoker.setCommand(removeCommand);
        if (invoker.executeCommand()) {
            System.out.println("Elemento 10 removido: " + collection);
        }

        if (invoker.undoCommand()) {
            System.out.println("Elemento 10 adicionado: " + collection);
        }
    }
}
