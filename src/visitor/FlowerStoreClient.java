package visitor;

import java.util.ArrayList;
import java.util.List;

public class FlowerStoreClient {
    public static void main(String[] args) {
        List<Flower> flowers = new ArrayList<>();
        flowers.add(new Rose());
        flowers.add(new Lily());
        Visitor visitor = new PriceDisplayVisitor();
        for (Flower flower : flowers) {
            flower.accept(visitor);
        }
    }
}
