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
    private List<SaleProduct> productList;

    public SaleProductModel() {
        productList = new ArrayList<>();
    }

    public boolean addProduct(SaleProduct product) {
        connection = new DBConnection().getConnection();
        try {
            pst = connection.prepareStatement("insert into " + Constant.DB_NAME + ".sale_product values(?,?,?,?,?,?,?)");
            pst.setInt(1, product.productID);
            pst.setString(2, product.name);
            pst.setString(3, product.category);
            pst.setDouble(4, product.sellingPrice);
            pst.setDouble(5, product.originalPrice);
            pst.setInt(6, product.quantity);
            pst.setDate(7, product.paidTime);
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
                    "selling_price_atm=?, original_price_atm=?, quantity=?, paid_time=? WHERE product_id=?");
            pst.setString(1, product.name);
            pst.setString(2, product.category);
            pst.setDouble(3, product.sellingPrice);
            pst.setDouble(4, product.originalPrice);
            pst.setInt(5, product.quantity);
            pst.setDate(6, product.paidTime);
            pst.setInt(7, product.productID);
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
            pst = connection.prepareStatement("DELETE FROM " + Constant.DB_NAME + ".sale_product WHERE product_id=?");
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

    public List<SaleProduct> getProductList() {
        connection = new DBConnection().getConnection();
        try {
            pst = connection.prepareStatement("SELECT * FROM " + Constant.DB_NAME + ".sale_product");
            rs = pst.executeQuery();
            while (rs.next()) {
                SaleProduct product = new SaleProduct();

                product.productID = rs.getInt(1);
                product.name = rs.getString(2);
                product.category = rs.getString(3);
                product.sellingPrice = rs.getDouble(4);
                product.originalPrice = rs.getDouble(5);
                product.quantity = rs.getInt(6);
                product.paidTime = rs.getDate(7);
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
