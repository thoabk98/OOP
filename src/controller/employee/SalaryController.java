package controller.employee;

import dataStruct.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import model.EmployeeModel;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;


public class SalaryController implements Initializable
{
    public Employee employee;
    @FXML
    private TextField tfNumOfShift;
    @FXML
    private TextField tfsalary;
    public int employeeSalaryID;
    @FXML
    private Button btnClose;
    @FXML
    public void btnSaveOnAction(ActionEvent actionEvent) {
        EmployeeModel employeeModel = new EmployeeModel();
        int salary = employee.payPerShift * Integer.parseInt(tfNumOfShift.getText());
        tfsalary.setText(String.valueOf(salary));
        employeeModel.changeNumOfShift(employee.employeeID,Integer.parseInt(tfNumOfShift.getText()));
    }

    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        employee = new Employee();
    }
}
