package Connection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDB {

    // static reference to itself
    private static ConnectionDB instance = new ConnectionDB();
    String url = "jdbc:postgresql://127.0.0.1:5432/QuanLySinhVien";
    String user = "postgres";
    String password = "ngkhai99";
    
    // private constructor
    private ConnectionDB() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionDB getInstance() {
        return instance;
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }
    
}
