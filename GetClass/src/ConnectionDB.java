import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    private static final String URL = "jdbc:sqlite:GetclassDB.db";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("Conexión establecida con éxito");
        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
        }
        return conn;
    }
}
