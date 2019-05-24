package model;

import dataStruct.Product;
import database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductModel {
    private Connection connection;
    private PreparedStatement pst;
    private ResultSet rs;
    private ArrayList<Product> productList;

    public ProductModel() {
        productList = new ArrayList<>();
    }

    public boolean addProduct(Product product) {
        connection = new DBConnection().getConnection();
        try {
            pst = connection.prepareStatement("insert into " + Constant.DB_NAME + ".product values(?,?,?,?,?,?,?,?,?)");
            pst.setString(1, null);
            pst.setString(2, product.name);
            pst.setString(3, product.category);
            pst.setDouble(4, product.sellingPrice);
            pst.setDouble(5, product.originalPrice);
            pst.setInt(6, product.inStock);
            pst.setString(7, product.brand);
            pst.setInt(8, product.size);
            pst.setString(9, product.color);
            pst.executeUpdate();
            pst.close();
            connection.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    public boolean updateProduct(Product product) {
        connection = new DBConnection().getConnection();
        try {
            pst = connection.prepareStatement("UPDATE " + Constant.DB_NAME + ".product SET name=?, category=?, " +
                    "selling_price=?, original_price=?, in-stock=?, brand=?, size=?, color=? WHERE product_id=?");
            pst.setString(1, product.name);
            pst.setString(2, product.category);
            pst.setDouble(3, product.sellingPrice);
            pst.setDouble(4, product.originalPrice);
            pst.setInt(5, product.inStock);
            pst.setString(6, product.brand);
            pst.setInt(7, product.size);
            pst.setString(8, product.color);
            pst.setInt(9, product.productID);
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
            pst = connection.prepareStatement("DELETE FROM " + Constant.DB_NAME + ".product WHERE product_id=?");
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

    public ArrayList<Product> getProductList() {
        connection = new DBConnection().getConnection();
        try {
            pst = connection.prepareStatement("SELECT * FROM " + Constant.DB_NAME + ".product");
            rs = pst.executeQuery();
            while (rs.next()) {
                Product product = new Product();

                product.productID = rs.getInt(1);
                product.name = rs.getString(2);
                product.category = rs.getString(3);
                product.sellingPrice = rs.getDouble(4);
                product.originalPrice = rs.getDouble(5);
                product.inStock = rs.getInt(6);
                product.brand = rs.getString(7);
                product.size = rs.getInt(8);
                product.color = rs.getString(9);
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
