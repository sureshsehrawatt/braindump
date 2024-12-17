package Java.mySql.src;

import java.sql.*;
import java.util.Properties;

public class MySqlConnection {
    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydatabase"; // Correct JDBC URL format

    // Database credentials
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String MAX_POOL = "250"; // Max pooled statements

    // Static variables for the connection and properties
    private static Connection connection;
    private static final Properties properties = createProperties(); // Create properties at class initialization

    // Method to create and return database properties
    private static Properties createProperties() {
        Properties props = new Properties(); // Create a new Properties object
        props.setProperty("user", USERNAME); // Set the username
        props.setProperty("password", PASSWORD); // Set the password
        props.setProperty("MaxPooledStatements", MAX_POOL); // Set max pooled statements
        return props; // Return the properties
    }

    // Method to get a database connection
    public static Connection getConnection() throws SQLException {
        // Check if connection is null or closed
        if (connection == null || connection.isClosed()) {
            try {
                // Load the JDBC driver
                Class.forName(JDBC_DRIVER);
                // Establish the connection using the properties
                connection = DriverManager.getConnection(DB_URL, properties);
            } catch (ClassNotFoundException e) {
                // Throw an SQLException if the driver is not found
                throw new SQLException("JDBC Driver not found", e);
            }
        }
        return connection; // Return the established connection
    }

    // Method to execute a SQL query
    public static void executeQuery(String query) {
        // Use try-with-resources for automatic resource management
        try (Connection connection = getConnection(); // Get the connection
             PreparedStatement preparedStatement = connection.prepareStatement(query); // Prepare the SQL query
             ResultSet resultSet = preparedStatement.executeQuery()) { // Execute the query and get results

            // Loop through the results
            while (resultSet.next()) {
                // Print the value of the first column in each row
                System.out.println(resultSet.getString(1));
            }
        } catch (SQLException e) {
            // Print any SQL exceptions that occur
            e.printStackTrace();
        }
    }
}
