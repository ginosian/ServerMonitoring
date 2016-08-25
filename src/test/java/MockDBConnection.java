import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by marta.ginosyan on 8/24/2016.
 */
public class MockDBConnection {

    private Connection connection = null;

    public Connection getConnection() {
        if (connection != null)
            return connection;
        else {
            try {
                String driver = "com.mysql.jdbc.Driver";
                String url = "jdbc:mysql://localhost:3306/test_server_monitoring";
                String user = "root";
                String password = "";
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
                return connection;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
