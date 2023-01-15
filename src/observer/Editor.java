package observer;

public class Editor {
    public EventHandler events;
    private String status;

    public Editor() {
        this.events = new EventHandler("created", "on the way", "delivered");
    }

    public void setStatusCreated() {
        events.notify("created", "created");
    }

    public void setStatusOnTheWay() {
        events.notify("on the way", "on the way");
    }

    public void setStatusDelivered() {
        events.notify("delivered", "delivered");
    }
}
