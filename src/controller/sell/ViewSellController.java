package controller.sell;

import dataStruct.Product;
import dataStruct.SaleProduct;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import model.SaleProductModel;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewSellController implements Initializable {

    private Product product;
    private ProductModel productModel;
    private SaleProduct saleProduct;
    private SaleProductModel saleProductModel;
    private ArrayList<SaleProduct> saleProducts;
    private ObservableList<SaleProduct> saleProductObservableList;

    @FXML
    private TableView<SaleProduct> tblSellView;
    @FXML
    private TableColumn<Object, Object> tblClmName;
    @FXML
    private TableColumn<Object, Object> tblClmPaidTime;
    @FXML
    private TableColumn<Object, Object> tblClmOriginPrice;
    @FXML
    private TableColumn<Object, Object> tblClmSellPrice;
    @FXML
    private TableColumn<Object, Object> tblClmQuantity;
    @FXML
    private TableColumn<Object, Object> tblClmCategory;
    @FXML
    private TableColumn<Object, Object> tblClmTotalPrice;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        product = new Product();
        productModel = new ProductModel();
        saleProduct = new SaleProduct();
        saleProductModel = new SaleProductModel();
        saleProducts = new ArrayList<>();
    }

    @FXML
    private void btnSellOrderOnAction(ActionEvent event) {
        FXMLLoader fXMLLoader = new FXMLLoader();
        try {
            fXMLLoader.load(getClass().getResource("/view/SellOrder.fxml").openStream());
            Parent parent = fXMLLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(SellOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void viewList() {
        saleProducts = saleProductModel.getProductList();
        System.out.println(saleProducts.get(0).name);
        saleProductObservableList = FXCollections.observableArrayList(saleProducts);
        tblSellView.setItems(saleProductObservableList);
        tblClmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblClmCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        tblClmPaidTime.setCellValueFactory(new PropertyValueFactory<>("paidTime"));
        tblClmOriginPrice.setCellValueFactory(new PropertyValueFactory<>("originalPrice"));
        tblClmSellPrice.setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));
        tblClmQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        tblClmTotalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
    }

    @FXML
    private void btnRefreshOnAction(ActionEvent event) {
        if (saleProducts.size() != 0) saleProducts.clear();
        tblSellView.getItems().clear();
        viewList();
    }

    @FXML
    private void btnDeleteOnAction(ActionEvent actionEvent) {
        if (saleProducts.size() != 0) {
            saleProduct = tblSellView.getSelectionModel().getSelectedItem();
            product = productModel.searchProduct(saleProduct.productID);
            productModel.updateInStock(product.productID, product.inStock + saleProduct.quantity);
            saleProductModel.deleteProduct(saleProduct.saleID);
            saleProducts.remove(tblSellView.getSelectionModel().getSelectedIndex());
            tblSellView.getItems().remove(tblSellView.getSelectionModel().getSelectedItem());
        }
    }
}
