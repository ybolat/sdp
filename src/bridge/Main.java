package bridge;

public class Main {
    public static void main(String[] args) {
        FlowerColor red = new Red();
        FlowerColor pink = new Pink();

        FlowerBouquet redRoses = new RoseBouquet(red, 20);
        redRoses.arrange();

        FlowerBouquet pinkRoses = new RoseBouquet(pink, 25);
        pinkRoses.arrange();
    }
}
