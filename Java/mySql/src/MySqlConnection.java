import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySqlConnection {
    private static final String JDBCDriver = "com.mysql.cj.jdbc.Driver";
    private static final String DriverManagerURL = "http://localhost:8080";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String MAX_POOL = "250";

    private Connection connection;
    private Properties properties;

    private Properties getProperties(){
        try {
            if (properties == null) {
                properties = new Properties();
                properties.setProperty("user", USERNAME);
                properties.setProperty("password", PASSWORD);
                properties.setProperty("MaxPooledStatements", MAX_POOL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }

    public Connection setConnection(){
        try {
            if (connection == null) {
                Class.forName(JDBCDriver);
                connection = DriverManager.getConnection(DriverManagerURL, getProperties());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}