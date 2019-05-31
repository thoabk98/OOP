package controller.employee;

import com.sun.prism.paint.Color;
import com.sun.prism.paint.ImagePattern;
import controller.HomeController;
import dataStruct.Employee;
import dataStruct.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jdk.jfr.Event;
import jdk.jfr.Label;
import model.EmployeeModel;
import model.ProductModel;
import model.UserModel;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.*;

public class EmployeeController implements Initializable {
    @FXML
    public TableView<Employee> tblEmoyeeList;
    @FXML
    private TableColumn<Object, Object> clmEmployeId;
    @FXML
    private TableColumn<Object, Objects> clmEmployeName;
    @FXML
    private TextField tfUserName;
    @FXML
    private TextField tfSalaryperShift;
    @FXML
    public Button btnDelete;
    @FXML
    public Button btnNewEmployee;
    @FXML  private Button btnSalary;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void viewEmployeeList() {
        EmployeeModel employeeModel = new EmployeeModel();
        ArrayList<Employee> employees = employeeModel.getEmployeeList();
        ObservableList<Employee> employeeObservableList = FXCollections.observableArrayList(employees);
        tblEmoyeeList.setItems(employeeObservableList);
        clmEmployeId.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
        clmEmployeName.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
    }

    public void tblEmloyeOnClick(MouseEvent mouseEvent) {
        setselectedView();
    }

    private void setselectedView() {
        clearAll();
        Employee employeeList = tblEmoyeeList.getSelectionModel().getSelectedItem();
        if (employeeList != null) {
            tfUserName.setText(employeeList.getEmployeeName());
            tfSalaryperShift.setText(String.valueOf(employeeList.payPerShift));
        }

    }

    @FXML
    private void btnDeleteOnClick() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Login Now");
        alert.setHeaderText("Confirm");
        alert.setContentText("Are you sure to delete this Employee \n to Confirm click ok");
        alert.initStyle(StageStyle.UNDECORATED);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            int item = tblEmoyeeList.getSelectionModel().getSelectedItem().getEmployeeID();
            EmployeeModel employeeModel = new EmployeeModel();
            employeeModel.deleteEmployee(item);
            tblEmoyeeList.getItems().clear();
            clearAll();
            viewEmployeeList();
        }
    }

    @FXML
    private void btnSalaryOnClick() throws IOException {

        FXMLLoader fXMLLoader = new FXMLLoader();
        Object load = fXMLLoader.load(getClass().getResource("/view/Salary.fxml").openStream());
        Parent parent = fXMLLoader.getRoot();
        Scene scene = new Scene(parent);
        SalaryController salaryController = fXMLLoader.getController();
        salaryController.employee = tblEmoyeeList.getSelectionModel().getSelectedItem();
        System.out.println(salaryController.employee.employeeID);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();

    }

    public void clearAll() {
        tfSalaryperShift.clear();
        tfUserName.clear();
    }


    public void btnNewEmployeeOnClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader fXMLLoader = new FXMLLoader();
        Object load = fXMLLoader.load(getClass().getResource("/view/AddEmployee.fxml").openStream());
        Parent parent = fXMLLoader.getRoot();
        Scene scene = new Scene(parent);
        AddEmployeController addEmployeController = fXMLLoader.getController();
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }
    public void permission(String name) {

        UserModel userModel = new UserModel();
        if(userModel.getPermission(name) == false){
           btnNewEmployee.setDisable(true);
           btnDelete.setDisable(true);
           btnSalary.setDisable(true);
        }

    }
}
