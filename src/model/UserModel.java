package model;

import dataStruct.User;
import database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserModel {
    private Connection connection;
    private PreparedStatement pst;
    private ResultSet rs;
    private ArrayList<User> userList;

    public UserModel() {
        userList = new ArrayList<>();
    }

    public boolean addUser(User user) {
        connection = new DBConnection().getConnection();
        try {
            pst = connection.prepareStatement("insert into " + Constant.DB_NAME + ".user values(?,?,?,?,?)");
            pst.setString(1, null);
            pst.setString(2, user.userName);
            pst.setString(3, user.password);
            pst.setInt(4, 0);
            if (user.isAdmin == true)
                pst.setInt(5, 1);
            else pst.setInt(5, 0);
            pst.executeUpdate();
            pst.close();
            connection.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    public boolean changePassword(String userName, String newPassword) {
        connection = new DBConnection().getConnection();
        try {
            pst = connection.prepareStatement("UPDATE " + Constant.DB_NAME + ".user SET password=? WHERE username=?");
            pst.setString(1, newPassword);
            pst.setString(2, userName);
            pst.executeUpdate();
            pst.close();
            connection.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    public boolean deleteUser(String userName) {
        connection = new DBConnection().getConnection();
        try {
            pst = connection.prepareStatement("DELETE FROM " + Constant.DB_NAME + ".user WHERE username=?");
            pst.setString(1, userName);
            pst.executeUpdate();
            pst.close();
            connection.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteUser(int id) {
        connection = new DBConnection().getConnection();
        try {
            pst = connection.prepareStatement("DELETE FROM " + Constant.DB_NAME + ".user WHERE user_id=?");
            pst.setInt(1, id);
            pst.executeUpdate();
            pst.close();
            connection.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public ArrayList<User> getUserList() {
        connection = new DBConnection().getConnection();
        try {
            pst = connection.prepareStatement("SELECT * FROM " + Constant.DB_NAME + ".user");
            rs = pst.executeQuery();
            while (rs.next()) {
                User user = new User();

                user.userID = rs.getInt(1);
                user.userName = rs.getString(2);
                user.isAdmin = rs.getBoolean(5);
                userList.add(user);
            }
            rs.close();
            pst.close();
            connection.close();
            return userList;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean getPermission(String username) {
        connection = new DBConnection().getConnection();
        try {
            pst = connection.prepareStatement("SELECT isAdmin FROM " + Constant.DB_NAME + ".user WHERE username=?");
            pst.setString(1, username);

            rs = pst.executeQuery();
            if (rs.next()) {
                if (rs.getBoolean(1)) {
                    rs.close();
                    pst.close();
                    connection.close();
                    return true;
                }
            }
            rs.close();
            pst.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public int getUserID(String username, String password) {
        int id = -1;
        connection = new DBConnection().getConnection();
        try {
            pst = connection.prepareStatement("SELECT user_id FROM " + Constant.DB_NAME + ".user WHERE username=? and password =?");
            pst.setString(1, username);
            pst.setString(2, password);
            rs = pst.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
            rs.close();
            pst.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }
    public int getUserID(String username) {
        int id = -1;
        connection = new DBConnection().getConnection();
        try {
            pst = connection.prepareStatement("SELECT user_id FROM " + Constant.DB_NAME + ".user WHERE username=?");
            pst.setString(1, username);
            rs = pst.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
            rs.close();
            pst.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }
}
