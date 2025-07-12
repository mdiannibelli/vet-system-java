package config;

import exceptions.ConfigurationException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection("jdbc:h2:./veterinaryDB", "admin", "admin");
            }
            return connection;
        } catch (SQLException e) {
            throw new ConfigurationException("No se pudo establecer conexión con la base de datos", e);
        }
    }

}
