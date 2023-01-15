package observer;

public class EmailNotificationListener implements EventListener{
    private String email;

    public EmailNotificationListener(String email) {
        this.email = email;
    }
    @Override
    public void update(String status, String eventType) {
        System.out.println("Email to " + email + "Your orderStatus changed to " + status);
    }
}
