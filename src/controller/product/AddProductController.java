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
    public JEditorPane lblHeader;
    private TextField tfValue;
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



    public void initialize(URL url, ResourceBundle resourceBundle) {
//        tfValue.setVisible(false);
    }


    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
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
        alert.setHeaderText("Sucess : save sucess ");
        alert.setContentText("Product added successfully");
        alert.initStyle(StageStyle.UNDECORATED);
        alert.showAndWait();
    }

}
