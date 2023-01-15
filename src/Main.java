import facadepattern.Facade;
import facadepattern.FacadeServer;
import observer.Editor;
import observer.EmailNotificationListener;
import observer.LogListener;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

        Editor editor = new Editor();
        editor.events.subscribe("created", new LogListener("Alimzhan"));
        editor.events.subscribe("created", new EmailNotificationListener("alimkenz02@gmail.com"));
        editor.setStatusCreated();

        Editor editor2 = new Editor();
        editor2.events.subscribe("delivered", new LogListener("Erasyl"));
        editor2.events.subscribe("delivered", new EmailNotificationListener("erabeast@gmail.com"));
        editor2.setStatusDelivered();

        FacadeServer facadeServer = new FacadeServer();

        Facade facade = new Facade(facadeServer);
        facade.start();
    }
}
