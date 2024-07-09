

public class Invoker {

    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public boolean executeCommand() {
        return command.execute();
    }

    public boolean undoCommand() {
        return command.undo();
    }
    
}
