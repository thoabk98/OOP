package controller.employee;

import dataStruct.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.EmployeeModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {
    @FXML
    private TableView<Employee> tblEmoyeeList;
    @FXML
    private TableColumn<Object, Object> clmEmployeId;
    @FXML
    private TableColumn<Object, Objects> clmEmployeName;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void viewEmployeeList() {
        EmployeeModel employeeModel= new EmployeeModel();
        ArrayList<Employee> employees = (ArrayList<Employee>) employeeModel.getEmployeeList();
        ObservableList<Employee> employeeObservableList= FXCollections.observableArrayList(employees);
        tblEmoyeeList.setItems(employeeObservableList);
        clmEmployeId.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
        clmEmployeName.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
    }
}
