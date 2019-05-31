package model;

import dataStruct.SaleProduct;
import database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SaleProductModel {
    private Connection connection;
    private PreparedStatement pst;
    private ResultSet rs;
    private ArrayList<SaleProduct> productList;

    public SaleProductModel() {
        productList = new ArrayList<>();
    }

    public boolean addProduct(SaleProduct product) {
        connection = new DBConnection().getConnection();
        try {
            pst = connection.prepareStatement("insert into " + Constant.DB_NAME + ".sale_product values(?,?,?,?,?,?,?,?)");
            pst.setInt(1, product.saleID);
            pst.setInt(2, product.productID);
            pst.setString(3, product.name);
            pst.setString(4, product.category);
            pst.setDouble(5, product.sellingPrice);
            pst.setDouble(6, product.originalPrice);
            pst.setInt(7, product.quantity);
            pst.setDate(8, product.paidTime);
            pst.executeUpdate();
            pst.close();
            connection.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    public boolean updateProduct(SaleProduct product) {
        connection = new DBConnection().getConnection();
        try {
            pst = connection.prepareStatement("UPDATE " + Constant.DB_NAME + ".sale_product SET name=?, category=?, " +
                    "selling_price_atm=?, original_price_atm=?, quantity=?, paid_time=? WHERE sale_id=?");
            pst.setString(1, product.name);
            pst.setString(2, product.category);
            pst.setDouble(3, product.sellingPrice);
            pst.setDouble(4, product.originalPrice);
            pst.setInt(5, product.quantity);
            pst.setDate(6, product.paidTime);
            pst.setInt(7, product.saleID);
            pst.executeUpdate();
            pst.close();
            connection.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    public boolean deleteProduct(int id) {
        connection = new DBConnection().getConnection();
        try {
            pst = connection.prepareStatement("DELETE FROM " + Constant.DB_NAME + ".sale_product WHERE sale_id=?");
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

    public ArrayList<SaleProduct> getProductList() {
        connection = new DBConnection().getConnection();
        try {
            pst = connection.prepareStatement("SELECT * FROM " + Constant.DB_NAME + ".sale_product");
            rs = pst.executeQuery();
            while (rs.next()) {
                SaleProduct product = new SaleProduct();
                product.saleID = rs.getInt(1);
                product.productID = rs.getInt(2);
                product.name = rs.getString(3);
                product.category = rs.getString(4);
                product.sellingPrice = rs.getDouble(5);
                product.originalPrice = rs.getDouble(6);
                product.quantity = rs.getInt(7);
                product.paidTime = rs.getDate(8);
                product.totalPrice = product.sellingPrice * product.quantity;
                productList.add(product);
            }
            rs.close();
            pst.close();
            connection.close();
            return productList;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<SaleProduct> getProductList(String yearMonth) {
        connection = new DBConnection().getConnection();
        try {
            pst = connection.prepareStatement("SELECT * FROM " + Constant.DB_NAME + ".sale_product " +
                    "WHERE paid_time >= ? AND paid_time <= ?");
            pst.setString(1, yearMonth + "-01");
            pst.setString(2, yearMonth + "-31");
            rs = pst.executeQuery();
            while (rs.next()) {
                SaleProduct product = new SaleProduct();
                product.saleID = rs.getInt(1);
                product.productID = rs.getInt(2);
                product.name = rs.getString(3);
                product.category = rs.getString(4);
                product.sellingPrice = rs.getDouble(5);
                product.originalPrice = rs.getDouble(6);
                product.quantity = rs.getInt(7);
                product.paidTime = rs.getDate(8);
                product.totalPrice = product.sellingPrice * product.quantity;
                productList.add(product);
            }
            rs.close();
            pst.close();
            connection.close();
            return productList;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
