package jdbc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {
    public static Connection getNewConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/Home_budget_automation";
        String userName = "postgres";
        String password = "38gjgeuftd";
        Connection connection = DriverManager.getConnection(url,userName, password);

        if (connection.isValid(1)) {
            System.out.println("Connection successful");
        }
        return connection;
    }
}
