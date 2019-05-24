package database;

import model.Constant;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private Connection connection;

    public DBConnection() {
        connection = getConnection();
    };

    public Connection getConnection() {
        String dbName = Constant.DB_NAME;
        String userName = "root";
        String password = "hthoa137";

        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost/"+dbName,userName,password);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }
}
