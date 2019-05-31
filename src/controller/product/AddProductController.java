package controller.product;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import dataStruct.Product;
import model.ProductModel;
import javafx.fxml.Initializable;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;


public class AddProductController implements Initializable{
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnClose;
    @FXML
    private TextField tfProductName;
    @FXML
    private TextField tfProductQuantity;
    @FXML
    private TextField tfProductPursesPrice;
    @FXML
    private TextField tfProductSellPrice;
    @FXML
    private TextField tfProductSize;
    @FXML
    private TextField tfProductColor;
    @FXML
    private TextField tfProductBrand;
    @FXML
    private TextField tfProductCategory;

    private int id;

    public void initialize(URL url, ResourceBundle resourceBundle) {
//        tfValue.setVisible(false);
    }


    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    private boolean checkInput() {
        if (tfProductSize.getText().isEmpty()
                || tfProductCategory.getText().isEmpty()
                || tfProductBrand.getText().isEmpty()
                || tfProductSellPrice.getText().isEmpty()
                || tfProductName.getText().isEmpty()
                || tfProductQuantity.getText().isEmpty()
                || tfProductPursesPrice.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setHeaderText("ERROR : NULL FOUND");
            alert.setContentText("Please fill all required field");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();

            return false;
        }

        return true;

    }

    @FXML
    private void btnSaveOnAction(ActionEvent event) {
        Product newProduct = new Product();
        newProduct.name = tfProductName.getText();
        newProduct.inStock = Integer.parseInt(tfProductQuantity.getText());
        newProduct.brand = tfProductBrand.getText();
        newProduct.category = tfProductCategory.getText();
        newProduct.color = tfProductColor.getText();
        newProduct.size = Integer.parseInt(tfProductSize.getText());
        newProduct.originalPrice = Double.parseDouble(tfProductPursesPrice.getText());
        newProduct.sellingPrice = Double.parseDouble(tfProductSellPrice.getText());
        ProductModel productModel = new ProductModel();
        productModel.addProduct(newProduct);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("error");
        alert.setHeaderText("Success : save success ");
        alert.setContentText("Product added successfully");
        alert.initStyle(StageStyle.UNDECORATED);
        alert.showAndWait();
    }

    @FXML
    private void btnUpdateOnAction(ActionEvent actionEvent) {
        if (checkInput()) {
            Product newProduct = new Product();
            newProduct.productID = id;
            newProduct.name = tfProductName.getText();
            newProduct.inStock = Integer.parseInt(tfProductQuantity.getText());
            newProduct.brand = tfProductBrand.getText();
            newProduct.category = tfProductCategory.getText();
            newProduct.color = tfProductColor.getText();
            newProduct.size = Integer.parseInt(tfProductSize.getText());
            newProduct.originalPrice = Double.parseDouble(tfProductPursesPrice.getText());
            newProduct.sellingPrice = Double.parseDouble(tfProductSellPrice.getText());
            ProductModel productModel = new ProductModel();
            productModel.updateProduct(newProduct);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("error");
            alert.setHeaderText("Success : update success ");
            alert.setContentText("Product updated successfully");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
        }
    }

    public void setupUpdate (Product product) {
        id = product.productID;
        tfProductName.setText(product.name);
        tfProductBrand.setText(product.brand);
        tfProductCategory.setText(product.category);
        tfProductColor.setText(product.color);
        tfProductPursesPrice.setText(String.valueOf(product.originalPrice));
        tfProductSellPrice.setText(String.valueOf(product.sellingPrice));
        tfProductSize.setText(String.valueOf(product.size));
        tfProductQuantity.setText(String.valueOf(product.inStock));
        btnSave.setVisible(false);
        btnUpdate.setVisible(true);
    }
}
