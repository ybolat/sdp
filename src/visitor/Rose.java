package visitor;

public class Rose implements Flower {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
