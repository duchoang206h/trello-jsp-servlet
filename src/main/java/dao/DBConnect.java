package dao;

import java.sql.*;

public class DBConnect {
    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/trello", "root", "DGjvkQJxE5NyerpH");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
