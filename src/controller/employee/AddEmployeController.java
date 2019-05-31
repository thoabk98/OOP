package controller.employee;

import dataStruct.Employee;
import dataStruct.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.EmployeeModel;
import model.UserModel;

import java.net.URL;
import java.util.ResourceBundle;

public class AddEmployeController implements Initializable {
    @FXML
    private TextField tfUserName;
    @FXML
    private TextField tfFullName;
    @FXML
    private TextField tfSalary;
    @FXML
    private TextField tfPassword;
    @FXML
    private Button btnClose;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void btnSaveOnClick(ActionEvent actionEvent) {
        EmployeeController employeeController = new EmployeeController();
        EmployeeModel employeeModel = new EmployeeModel();
        Employee employee = new Employee();
        employee.numOfShift = 0;
        employee.employeeName = tfFullName.getText();
        employee.payPerShift = Integer.parseInt(tfSalary.getText());
        employeeModel.addEmployee(employee);
        User user = new User();
        UserModel userModel = new UserModel();
        user.userName = tfUserName.getText();
        user.password = tfPassword.getText();
        user.isAdmin = false;
        user.status = false;
        userModel.addUser(user);
        employeeController.clearAll();
        employeeController.tblEmoyeeList.getItems().clear();
        employeeController.viewEmployeeList();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("error");
        alert.setHeaderText("Sucess : save sucess ");
        alert.setContentText("Product added successfully");
        alert.initStyle(StageStyle.UNDECORATED);
        alert.showAndWait();
    }



    @FXML
    public void btnCloseOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }
}
