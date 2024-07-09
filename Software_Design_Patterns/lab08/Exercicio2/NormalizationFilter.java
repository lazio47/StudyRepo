import java.text.Normalizer;


public class NormalizationFilter extends TextReaderDecorator {

    public NormalizationFilter(TextReaderInterface wrappee) {
        super(wrappee);
    }

    public boolean hasNext() {
        return super.hasNext();
    }

    public String next() {

        String s = super.next();
        return Normalizer.normalize(s, Normalizer.Form.NFKD).replaceAll("\\p{M}", "").replaceAll("\\p{Punct}", "");

    }

}
