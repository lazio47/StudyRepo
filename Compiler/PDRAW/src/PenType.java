public class PenType extends Type {
    public PenType(){
        super("pen");
    }

    @Override
    public boolean subtype(Type other) {
        return other.name.equals("position") | other.name.equals("color") | super.subtype(other);
    }
}
