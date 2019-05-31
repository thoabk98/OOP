package model;

import dataStruct.MonthlyRevenue;
import database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MonthlyRevenueModel {
    private Connection connection;
    private PreparedStatement pst;
    private ResultSet rs;
    private ArrayList<MonthlyRevenue> revenueList;

    public MonthlyRevenueModel() {
        revenueList = new ArrayList<>();
    }

    public boolean addMonthlyRevenue(MonthlyRevenue revenue) {
        connection = new DBConnection().getConnection();
        try {
            pst = connection.prepareStatement("insert into " + Constant.DB_NAME + ".monthly_revenue values(?,?,?,?)");
            pst.setString(1, revenue.timeID);
            pst.setDouble(2, revenue.employeeExpence);
            pst.setDouble(3, revenue.otherExpence);
            pst.setDouble(4, revenue.revenue);
            pst.executeUpdate();
            pst.close();
            connection.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    public boolean updateMonthlyRevenue(MonthlyRevenue revenue) {
        connection = new DBConnection().getConnection();
        try {
            pst = connection.prepareStatement("UPDATE " + Constant.DB_NAME + ".monthly_revenue SET employee_expense=?, " +
                    "other_expenses=?, revenue=? WHERE time_id=?");
            pst.setDouble(1, revenue.employeeExpence);
            pst.setDouble(2, revenue.otherExpence);
            pst.setDouble(3, revenue.revenue);
            pst.setString(4, revenue.timeID);
            pst.executeUpdate();
            pst.close();
            connection.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    public boolean deleteRevenue(String timeID) {
        connection = new DBConnection().getConnection();
        try {
            pst = connection.prepareStatement("DELETE FROM " + Constant.DB_NAME + ".monthly_revenue WHERE time_id=?");
            pst.setString(1, timeID);
            pst.executeUpdate();
            pst.close();
            connection.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public ArrayList<MonthlyRevenue> getRevenueList() {
        connection = new DBConnection().getConnection();
        try {
            pst = connection.prepareStatement("SELECT * FROM " + Constant.DB_NAME + ".monthly_revenue");
            rs = pst.executeQuery();
            while (rs.next()) {
                MonthlyRevenue revenue = new MonthlyRevenue();

                revenue.timeID = rs.getString(1);
                revenue.employeeExpence = rs.getDouble(2);
                revenue.otherExpence = rs.getDouble(3);
                revenue.revenue = rs.getDouble(4);
                revenueList.add(revenue);
            }
            rs.close();
            pst.close();
            connection.close();
            return revenueList;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
