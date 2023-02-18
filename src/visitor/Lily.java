package visitor;

public class Lily implements Flower {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
