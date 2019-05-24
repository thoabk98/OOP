package controller;

import dataStruct.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ProductModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProductController implements Initializable {
    @FXML
    private TableView<Product> tblViewCurrentStore;
    @FXML
    private TableColumn<Object, Object> tblClmProductId;
    @FXML
    private TableColumn<Object, Object> tblClmProductName;
    @FXML
    private TableColumn<Object, Object> tblClmProductquantity;
    @FXML
    private TableColumn<Object, Object> tblClmProductUnit;
    @FXML
    private TableColumn<Object, Object> tblClmProductRMA;
    @FXML
    private TableColumn<Object, Object> tblClmProductSupplyer;
    @FXML
    private TableColumn<Object, Object> tblClmProductBrand;
    @FXML
    private TableColumn<Object, Object> tblClmProductCatagory;
    @FXML
    private TableColumn<Object, Object> tblClmProductPursesPrice;
    @FXML
    private TableColumn<Object, Object> tblClmProductSellPrice;
    @FXML
    private TableColumn<Object, Object> tblClmProductdate;
    @FXML
    private TableColumn<Object, Object> tblClmProductAddBy;
    @FXML
    private TableColumn<Object, Object> tblClmProductdescription;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void viewDetail() {
        ProductModel productModel = new ProductModel();
        ArrayList<Product> products = productModel.getProductList();
        ObservableList<Product> productObservableList = FXCollections.observableArrayList(products);

        tblViewCurrentStore.setItems(productObservableList);

        tblClmProductId.setCellValueFactory(new PropertyValueFactory<>("productID"));
        tblClmProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblClmProductquantity.setCellValueFactory(new PropertyValueFactory<>("category"));
        tblClmProductdescription.setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));
        tblClmProductBrand.setCellValueFactory(new PropertyValueFactory<>("originalPrice"));
        tblClmProductCatagory.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        tblClmProductUnit.setCellValueFactory(new PropertyValueFactory<>("brand"));
        tblClmProductPursesPrice.setCellValueFactory(new PropertyValueFactory<>("size"));
        tblClmProductSellPrice.setCellValueFactory(new PropertyValueFactory<>("color"));

    }

}
