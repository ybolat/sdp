package observer;

public class LogListener implements EventListener{
    private String username;

    public LogListener(String username) {
        this.username = username;
    }

    @Override
    public void update(String status, String eventType) {
        System.out.println("Save to log " + username + "'s current order Status changed to " + status);
    }
}
