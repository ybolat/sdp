package visitor;

public class PriceDisplayVisitor implements Visitor {
    @Override
    public void visit(Rose rose) {
        System.out.println("Price of a rose: $2.00");
    }
    @Override
    public void visit(Lily lily) {
        System.out.println("Price of a lily: $1.50");
    }
}
