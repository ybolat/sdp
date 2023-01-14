package singleton;

import lombok.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Data
public class SingleObject {

    private static final SingleObject instance = new SingleObject();
    private Connection conn = null;
    private Statement stmt = null;


    private SingleObject() {
    }

    public static SingleObject getInstance() {
        return instance;
    }

    public void connect() throws SQLException {

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/flower_store",
                            "postgres", "123");
            stmt = conn.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}
