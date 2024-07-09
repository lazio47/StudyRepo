public abstract class TextReaderDecorator  implements TextReaderInterface {

    private TextReaderInterface wrappee;

    public TextReaderDecorator(TextReaderInterface wrappee) {
        this.wrappee = wrappee;
    }

    public boolean hasNext() {
        return wrappee.hasNext();
    }

    public String next() {
        return wrappee.next();
    }
    
}
