
public class VowelFilter extends TextReaderDecorator {

    public VowelFilter(TextReaderInterface wrappee) {
        super(wrappee);
    }

    public boolean hasNext() {
        return super.hasNext();
    }

    public String next() {
        
            String s = super.next();
            return s.replaceAll("[aeiouAEIOU]", "");
        

    }



}
