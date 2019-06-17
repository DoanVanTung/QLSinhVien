package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    // static reference to itself
    private static ConnectionDB instance = new ConnectionDB();
    String url = "jdbc:sqlserver://localhost:1433;database=QuanLySinhVien;";
    String user = "sa";
    String password = "123";
    
    // private constructor
    private ConnectionDB() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
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

    // Test connection database
//    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        System.out.println(getInstance().getConnection());
//    }
}
