package ma.ensat.projet_messagerie_hibernate.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOUtil {
    private static final String URL = "jdbc:postgresql://localhost:5432/messagerie";
    private static final String USER = "hanae";
    private static final String PASSWORD = "hanae123";

    static {
        try {
            Class.forName("org.postgresql.Driver"); // ✅ Driver PostgreSQL
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Erreur de chargement du driver JDBC PostgreSQL", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
