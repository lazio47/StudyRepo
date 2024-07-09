public class IntegerType extends Type {
    public IntegerType() {
       super("int");
    }

    @Override
    public boolean subtype(Type other) {
        return other.name.equals("int") | super.subtype(other);
    }
 }
 
 