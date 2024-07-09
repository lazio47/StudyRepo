public class StringType extends Type {
    public StringType(){
        super("string");
    }

    @Override
    public boolean subtype(Type other) {
        return other.name.equals("bool") | super.subtype(other);
    }
}
