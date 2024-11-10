import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/hospital_management"; // Database URL
    private static final String USER = "root"; // Change to your MySQL username
    private static final String PASSWORD = "Srmist@23"; // Change to your MySQL password

    // Method to establish connection to the database
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
