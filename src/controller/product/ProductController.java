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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.ProductModel;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
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
        AddProductController apc = new AddProductController();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Object load = fxmlLoader.load(getClass().getResource("/view/AddProduct.fxml").openStream());
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            AddProductController addProductController = fxmlLoader.getController();

            Stage nStage = new Stage();
            nStage.setScene(scene);
            nStage.initModality(Modality.APPLICATION_MODAL);
            nStage.initStyle(StageStyle.TRANSPARENT);
            nStage.show();


    }
    @FXML
    private void btnDeleteOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Login Now");
        alert.setHeaderText("Confirm");
        alert.setContentText("Are you sure to delete this item \n to Confirm click ok");
        alert.initStyle(StageStyle.UNDECORATED);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            int item = tblViewCurrentStore.getSelectionModel().getSelectedItem().getProductID();
            System.out.println("Product id" + item);
            ProductModel productModel = new ProductModel();
            productModel.deleteProduct(item);
            //btnRefreshOnACtion(event);
            tblViewCurrentStore.getItems().clear();
            viewDetail();
        }

    }
    @FXML
    private void btnRefreshOnACtion(ActionEvent event) {
        tblViewCurrentStore.getItems().clear();
        viewDetail();
    }


    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
        if (!tblViewCurrentStore.getSelectionModel().isEmpty()) {
            viewSelected();
        } else {
            System.out.println("EMPTY SELECTION");
        }
    }
    private void viewSelected() {
        AddProductController apc = new AddProductController();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/AddProduct.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            AddProductController addProductController = fxmlLoader.getController();
            int item = tblViewCurrentStore.getSelectionModel().getSelectedItem().getProductID();
            Stage nStage = new Stage();
            nStage.setScene(scene);
            nStage.initModality(Modality.APPLICATION_MODAL);
            nStage.initStyle(StageStyle.TRANSPARENT);
            nStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
