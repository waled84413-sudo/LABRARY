


package Database;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=LibraryDB;encrypt=true;trustServerCertificate=true";
    private static final String USER = "your_username";
    private static final String PASSWORD = "your_password";
    private static Connection connection;

    private DatabaseConnection() {
        throw new Error("Unresolved compilation problem: \n\tThe public type DatabaseConnection must be defined in its own file\n");
    }

    public static Connection getConnection() throws SQLException {
        throw new Error("Unresolved compilation problem: \n");
    }

    public static void closeConnection() {
        throw new Error("Unresolved compilation problem: \n");
    }
}
