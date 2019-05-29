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
        String unicode = "?useUnicode=yes&characterEncoding=UTF-8";
        String url = "jdbc:mysql://localhost/" + dbName + unicode;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url,userName,password);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }
}
