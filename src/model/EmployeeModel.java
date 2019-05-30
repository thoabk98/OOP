package model;

import dataStruct.Employee;
import database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeModel {

    private Connection connection;
    private PreparedStatement pst;
    private ResultSet rs;
    private List<Employee> employeeList;

    public EmployeeModel() {
        employeeList = new ArrayList<>();
    }

    public boolean addEmployee(Employee employee) {
        connection = new DBConnection().getConnection();
        try {
            pst = connection.prepareStatement("insert into " + Constant.DB_NAME + ".employee values(?,?,?,?)");
            pst.setInt(1, employee.employeeID);
            pst.setString(2, employee.employeeName);
            pst.setInt(3, employee.numOfShift);
            pst.setInt(4, employee.payPerShift);
            pst.executeUpdate();
            pst.close();
            connection.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    public boolean changeNumOfShift(int id, int numberOfShift) {
        connection = new DBConnection().getConnection();
        try {
            pst = connection.prepareStatement("UPDATE " + Constant.DB_NAME + ".employee SET number_of_shifts=? WHERE employeeID=?");
            pst.setInt(1, numberOfShift);
            pst.setInt(2, id);
            pst.executeUpdate();
            pst.close();
            connection.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    public boolean changePayPerShift(int id, int payPerShift) {
        connection = new DBConnection().getConnection();
        try {
            pst = connection.prepareStatement("UPDATE " + Constant.DB_NAME + ".employee SET pay_per_shift=? WHERE employeeID=?");
            pst.setInt(1, payPerShift);
            pst.setInt(2, id);
            pst.executeUpdate();
            pst.close();
            connection.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    public boolean changeEmployeeName(int id, String newEmployeeName) {
        connection = new DBConnection().getConnection();
        try {
            pst = connection.prepareStatement("UPDATE " + Constant.DB_NAME + ".employee SET employee_name=? WHERE employeeID=?");
            pst.setString(1, newEmployeeName);
            pst.setInt(2, id);
            pst.executeUpdate();
            pst.close();
            connection.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    public boolean deleteEmployee(int id) {
        connection = new DBConnection().getConnection();
        try {
            pst = connection.prepareStatement("DELETE FROM " + Constant.DB_NAME + ".employee WHERE employeeID=?");
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
    public  Employee getEmployee(int id){
        connection = new DBConnection().getConnection();
        try {
            pst = connection.prepareStatement("SELECT * FROM " + Constant.DB_NAME + ".employee ? WHERE employeeID ="+id);
            rs = pst.executeQuery();
            Employee employee = new Employee();
                employee.employeeID = rs.getInt(1);
                employee.employeeName = rs.getString(2);
                employee.numOfShift = rs.getInt(3);
                employee.payPerShift = rs.getInt(4);
            rs.close();
            pst.close();
            connection.close();
            return employee;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public List<Employee> getEmployeeList() {
        connection = new DBConnection().getConnection();
        try {
            pst = connection.prepareStatement("SELECT * FROM " + Constant.DB_NAME + ".employee");
            rs = pst.executeQuery();
            while (rs.next()) {
                Employee employee = new Employee();

                employee.employeeID = rs.getInt(1);
                employee.employeeName = rs.getString(2);
                employee.numOfShift = rs.getInt(3);
                employee.payPerShift = rs.getInt(4);
                employeeList.add(employee);
            }
            rs.close();
            pst.close();
            connection.close();
            return employeeList;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
