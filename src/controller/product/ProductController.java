package controller.product;

import controller.product.AddProductController;
import dataStruct.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.ProductModel;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProductController implements Initializable {
    public TextField tfSearch;
    @FXML
    private TableView<Product> tblViewCurrentStore;
    @FXML
    private TableColumn<Object, Object> tblClmProductId;
    @FXML
    private TableColumn<Object, Object> tblClmProductName;
    @FXML
    private TableColumn<Object, Object> tblClmProductquantity;

    @FXML
    private TableColumn<Object, Object> tblClmProductBrand;
    @FXML
    private TableColumn<Object, Object> tblClmProductCatagory;
    @FXML
    private TableColumn<Object, Object> tblClmProductPursesPrice;
    @FXML
    private TableColumn<Object, Object> tblClmProductSellPrice;
    @FXML

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
        tblClmProductCatagory.setCellValueFactory(new PropertyValueFactory<>("category"));
        tblClmProductSellPrice.setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));
        tblClmProductPursesPrice.setCellValueFactory(new PropertyValueFactory<>("originalPrice"));
        tblClmProductquantity.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        tblClmProductBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));

    }
    @FXML
    private void btnAddNewOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Object load = fxmlLoader.load(getClass().getResource("/view/AddProduct.fxml").openStream());
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));

            Stage nStage = new Stage();
            nStage.setScene(scene);
            nStage.initModality(Modality.APPLICATION_MODAL);
            nStage.initStyle(StageStyle.TRANSPARENT);
            nStage.show();
    }


    public void btnUpdateOnAction(ActionEvent actionEvent) throws IOException {
        Product product;
        if (!tblViewCurrentStore.getSelectionModel().isEmpty()) {

            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.load(getClass().getResource("/view/AddProduct.fxml").openStream());
            Parent parent = fXMLLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));

            product = tblViewCurrentStore.getSelectionModel().getSelectedItem();
            AddProductController addProductController = fXMLLoader.getController();
            addProductController.setupUpdate(product);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
        }

        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("ERROR");
            alert.setContentText("Please choose an item to edit");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
        }
    }

    public void btnRefreshOnAction(ActionEvent actionEvent) {
        tblViewCurrentStore.getItems().clear();
        viewDetail();
    }


    public void btnDeleteOnAction(ActionEvent actionEvent) {
        if (!tblViewCurrentStore.getSelectionModel().isEmpty()) {
            ProductModel productModel = new ProductModel();
            Product product = tblViewCurrentStore.getSelectionModel().getSelectedItem();
            productModel.deleteProduct(product.productID);
            tblViewCurrentStore.getItems().remove(tblViewCurrentStore.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    private void tfSearchOnKeyReleased(KeyEvent event) {
        ProductModel productModel = new ProductModel();
        ArrayList<Product> products = productModel.searchProduct(tfSearch.getText());
        ObservableList<Product> productObservableList = FXCollections.observableArrayList(products);

        tblViewCurrentStore.setItems(productObservableList);

        tblClmProductId.setCellValueFactory(new PropertyValueFactory<>("productID"));
        tblClmProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblClmProductCatagory.setCellValueFactory(new PropertyValueFactory<>("category"));
        tblClmProductSellPrice.setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));
        tblClmProductPursesPrice.setCellValueFactory(new PropertyValueFactory<>("originalPrice"));
        tblClmProductquantity.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        tblClmProductBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
    }
}
