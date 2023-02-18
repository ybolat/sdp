package bridge;

public class RoseBouquet extends FlowerBouquet {
    public RoseBouquet(FlowerColor color, int price) {
        super(color, price);
    }

    @Override
    public void arrange() {
        System.out.println("Arranging roses of color " + color + " with price $" + price);
    }
}
