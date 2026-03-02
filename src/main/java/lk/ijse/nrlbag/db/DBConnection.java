package lk.ijse.nrlbag.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private final Connection conn ;
    private static DBConnection dbc;

    private DBConnection() throws SQLException {
        String DB_URL = "jdbc:mysql://localhost:3306/nrlbag";
        String DB_USERNAME = "root";
        String DB_PASSWORD = "mysql";

        conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }

    public static DBConnection getInstance() throws SQLException {
        if(dbc == null) {
            dbc = new DBConnection();
        }
        return dbc;
    }

    public Connection getConnection() {
        return conn;
    }

}
