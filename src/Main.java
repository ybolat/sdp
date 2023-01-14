import facadepattern.Facade;
import facadepattern.FacadeServer;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

        FacadeServer facadeServer = new FacadeServer();

        Facade facade = new Facade(facadeServer);
        facade.start();
    }
}
