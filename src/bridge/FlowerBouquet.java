package bridge;

public abstract class FlowerBouquet {
    protected FlowerColor color;
    protected int price;

    public FlowerBouquet(FlowerColor color, int price) {
        this.color = color;
        this.price = price;
    }

    public abstract void arrange();

    public void setColor(FlowerColor color) {
        this.color = color;
    }

    public FlowerColor getColor() {
        return color;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
