public class CapitalizationFilter extends TextReaderDecorator {

    public CapitalizationFilter(TextReaderInterface wrappee) {
        super(wrappee);
    }

    public boolean hasNext() {
        return super.hasNext();
    }

    public String next() {
        int len;
        
            String s = super.next();
            len = s.length();

            Character.toUpperCase(s.charAt(0));
            Character.toUpperCase(s.charAt(len - 1));
            for (int i = 1; i < len - 1; i++) {
                Character.toLowerCase(s.charAt(i));
            }
            return s;
    }
    
}
