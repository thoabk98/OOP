package controller.revenue;

import dataStruct.MonthlyRevenue;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.MonthlyRevenueModel;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewRevenueController implements Initializable {
    private MonthlyRevenue monthlyRevenue;
    private ArrayList<MonthlyRevenue> monthlyRevenues;
    private MonthlyRevenueModel revenueModel;
    private ObservableList<MonthlyRevenue> monthlyRevenueObservableList;

    @FXML
    private TableView<MonthlyRevenue> tblRevenueView;
    @FXML
    private TableColumn tblClmTime;
    @FXML
    private TableColumn tblClmEmployeeExpense;
    @FXML
    private TableColumn tblClmOtherExpenses;
    @FXML
    private TableColumn tblClmRevenue;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        monthlyRevenue = new MonthlyRevenue();
        monthlyRevenues = new ArrayList<>();
        revenueModel = new MonthlyRevenueModel();

    }

    public void viewList() {
        monthlyRevenues = revenueModel.getRevenueList();
        monthlyRevenueObservableList = FXCollections.observableArrayList(monthlyRevenues);
        tblRevenueView.setItems(monthlyRevenueObservableList);
        tblClmTime.setCellValueFactory(new PropertyValueFactory<>("timeID"));
        tblClmEmployeeExpense.setCellValueFactory(new PropertyValueFactory<>("employeeExpence"));
        tblClmOtherExpenses.setCellValueFactory(new PropertyValueFactory<>("otherExpence"));
        tblClmRevenue.setCellValueFactory(new PropertyValueFactory<>("revenue"));

    }

    public void btnAddRevenueOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/AddRevenue.fxml").openStream());
        Parent parent = fXMLLoader.getRoot();
        Scene scene = new Scene(parent);
        scene.setFill(new Color(0, 0, 0, 0));
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }


    public void btnRefreshOnAction(ActionEvent actionEvent) {
        monthlyRevenues.removeAll(monthlyRevenues);
        tblRevenueView.getItems().clear();
        viewList();
    }

    @FXML
    private void btnDeleteOnAction(ActionEvent actionEvent) {
        if (monthlyRevenues.size() != 0) {
            monthlyRevenue = tblRevenueView.getSelectionModel().getSelectedItem();
            revenueModel.deleteRevenue(monthlyRevenue.timeID);
            monthlyRevenues.remove(tblRevenueView.getSelectionModel().getSelectedIndex());
            tblRevenueView.getItems().remove(tblRevenueView.getSelectionModel().getSelectedItem());
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws IOException {
        if (!tblRevenueView.getSelectionModel().isEmpty()) {
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.load(getClass().getResource("/view/AddRevenue.fxml").openStream());
            Parent parent = fXMLLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));

            monthlyRevenue = tblRevenueView.getSelectionModel().getSelectedItem();
            AddRevenueController addRevenueController = fXMLLoader.getController();
            addRevenueController.lblHeader.setText("Update Revenue");
            addRevenueController.tfTimeID.setText(monthlyRevenue.timeID);
            addRevenueController.tfTimeID.setEditable(false);
            addRevenueController.monthlyRevenue.employeeExpence = monthlyRevenue.employeeExpence;
            addRevenueController.tfOtherExpenses.setText(String.valueOf(monthlyRevenue.otherExpence));

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
}
