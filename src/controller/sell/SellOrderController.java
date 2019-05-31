package controller.sell;

import dataStruct.Product;
import dataStruct.SaleProduct;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.ProductModel;
import model.SaleProductModel;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SellOrderController implements Initializable {
    @FXML
    private Button btnClose;
    @FXML
    private TextField tfProductId;
    @FXML
    private TextField tfProductName;
    @FXML
    private TextField tfBrand;
    @FXML
    private TextField tfCategory;
    @FXML
    private TextField tfQuantity;
    @FXML
    private TextField tfSellPrice;
    @FXML
    private Label lblPurschaseDate;
    @FXML
    private Label lblCurrentQuantity;
    @FXML
    private Label lblItemCost;
    @FXML
    private Label lblTotal;
    @FXML
    public Label lblTotalItems;
    @FXML
    private TableView<SaleProduct> tblSellPreList;
    @FXML
    private TableColumn<Object, Object> tblClmProductName;
    @FXML
    private TableColumn<Object, Object> tblClmSellPrice;
    @FXML
    private TableColumn<Object, Object> tblClmQuantity;
    @FXML
    private TableColumn<Object, Object> tblClmTotalPrice;


    private ArrayList<SaleProduct> preSellList;
    private Product currrentProduct;
    private ProductModel productModel;

    public SellOrderController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblPurschaseDate.setText(String.valueOf(LocalDate.now()));
        currrentProduct = new Product();
        productModel = new ProductModel();
        preSellList = new ArrayList<>();
    }

    @FXML
    public void tfProductIdOnAction(ActionEvent event) {
        if (tfProductId.getText().isEmpty()) {
            clearAll();
        } else {
            int id = Integer.valueOf(tfProductId.getText().trim());
            currrentProduct = productModel.searchProduct(id);
            if (currrentProduct != null) {
                lblCurrentQuantity.setText(String.valueOf(currrentProduct.inStock));
                tfBrand.setText(currrentProduct.brand);
                tfProductName.setText(currrentProduct.name);
                tfSellPrice.setText(String.valueOf(currrentProduct.sellingPrice));
                tfCategory.setText(currrentProduct.category);
                lblItemCost.setText("0.0");
            }
        }
    }

    @FXML
    private void btnAddToCartOnAction(ActionEvent event) {
        if (checkInput()) {
            Date date = Date.valueOf(LocalDate.now());
            SaleProduct saleProduct = new SaleProduct();

            saleProduct.productID = currrentProduct.productID;
            saleProduct.quantity = Integer.parseInt(tfQuantity.getText());
            saleProduct.sellingPrice = currrentProduct.sellingPrice;
            saleProduct.originalPrice = currrentProduct.originalPrice;
            saleProduct.name = currrentProduct.name;
            saleProduct.category = currrentProduct.category;
            saleProduct.paidTime = date;
            saleProduct.inStock = currrentProduct.inStock;
            saleProduct.totalPrice = saleProduct.sellingPrice * saleProduct.quantity;
            preSellList.add(saleProduct);
            viewAll();
            sumTotalCost();
            clearAll();
        }
    }

    public boolean checkInput() {
        if (tfQuantity.getText().trim().matches("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("ERROR");
            alert.setContentText("Please fill in the quantity");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
            return false;
        }

        return true;
    }

    @FXML
    private void tfQuantityOnKeyReleased(KeyEvent event) {
        if (!tfQuantity.getText().isEmpty()) {
            String uncheck = tfQuantity.getText().trim();
            for (char c: uncheck.toCharArray()) {
                if (!Character.isDigit(c)) {
                    tfQuantity.clear();
                    return;
                }
            }

            int quantity = Integer.parseInt(tfQuantity.getText());
            if (quantity > currrentProduct.inStock) {
                tfQuantity.clear();
            } else {
                String netPrice = String.valueOf(currrentProduct.sellingPrice * quantity);
                lblItemCost.setText(netPrice);
            }
        }
    }

    public void viewAll() {
        ObservableList<SaleProduct> saleProductObservableList = FXCollections.observableArrayList(preSellList);
        tblSellPreList.setItems(saleProductObservableList);
        tblClmProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblClmQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        tblClmSellPrice.setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));
        tblClmTotalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
    }

    private void sumTotalCost() {
        double sum = 0;
        int items = preSellList.size();

        for (int i = 0; i < items; i++) {
            sum = sum + preSellList.get(i).totalPrice;
        }

        String totalCost = String.valueOf(sum);
        lblTotal.setText(totalCost);
        String totalItem = String.valueOf(items);
        lblTotalItems.setText(totalItem);
    }

    private void clearAll() {
        tfBrand.clear();
        tfProductId.clear();
        tfCategory.clear();
        tfSellPrice.clear();
        tfQuantity.clear();
        tfProductName.clear();
        lblCurrentQuantity.setText(null);
        lblItemCost.setText(null);
    }

    @FXML
    private void btnClearSelectedOnAction(ActionEvent event) {
        if (preSellList.size() != 0) {
            preSellList.remove(tblSellPreList.getSelectionModel().getSelectedIndex());
            tblSellPreList.getItems().removeAll(tblSellPreList.getSelectionModel().getSelectedItems());
            sumTotalCost();
        }
    }

    @FXML
    private void btnSellOnAction(ActionEvent event) {
        int newInstock;
        SaleProductModel saleProductModel = new SaleProductModel();
        if (!preSellList.isEmpty()) {
            for (int i = 0; i < preSellList.size(); i++) {
                saleProductModel.addProduct(preSellList.get(i));
                newInstock = preSellList.get(i).inStock - preSellList.get(i).quantity;
                productModel.updateInStock(preSellList.get(i).productID, newInstock);
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Sold");
            alert.setContentText("Sold Successfully");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();

            tblSellPreList.getItems().clear();
            lblTotal.setText(null);
        }
    }

    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }
}
