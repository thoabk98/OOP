package controller.revenue;

import dataStruct.Employee;
import dataStruct.MonthlyRevenue;
import dataStruct.SaleProduct;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.EmployeeModel;
import model.MonthlyRevenueModel;
import model.SaleProductModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddRevenueController implements Initializable {

    public MonthlyRevenue monthlyRevenue;
    private MonthlyRevenueModel revenueModel;

    public TextField tfOtherExpenses;
    public TextField tfTimeID;
    public Label lblHeader;
    @FXML
    private Button btnClose;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        monthlyRevenue = new MonthlyRevenue();
        revenueModel = new MonthlyRevenueModel();
    }

    @FXML
    private void btnSaveOnAction(ActionEvent actionEvent) {
        if (checkInput()) {
            double profit = profitCaculating();
            monthlyRevenue.otherExpence = Double.parseDouble(tfOtherExpenses.getText().trim());
            monthlyRevenue.timeID = tfTimeID.getText().trim();

            if (lblHeader.getText().equals("Update Revenue")) {
                monthlyRevenue.revenue = profit - monthlyRevenue.otherExpence - monthlyRevenue.employeeExpence;
                revenueModel.updateMonthlyRevenue(monthlyRevenue);

            }
            else  {
                monthlyRevenue.employeeExpence = employeeExpenseCaculating();
                monthlyRevenue.revenue = profit - monthlyRevenue.otherExpence - monthlyRevenue.employeeExpence;
                revenueModel.addMonthlyRevenue(monthlyRevenue);
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Updated");
            alert.setContentText("Updated Successfully");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
        }
    }

    private double profitCaculating() {
        double profit = 0;
        SaleProductModel saleProductModel = new SaleProductModel();
        ArrayList<SaleProduct> saleProducts = saleProductModel.getProductList(tfTimeID.getText().trim());
        for (SaleProduct temp : saleProducts) {
            profit += temp.quantity * (temp.sellingPrice - temp.originalPrice);
        }
        return profit;
    }

    private double employeeExpenseCaculating() {
        double employeeExpense = 0;
        EmployeeModel employeeModel = new EmployeeModel();
        ArrayList<Employee> employees = employeeModel.getEmployeeList();
        System.out.println(employees.get(0).employeeName);
        for (Employee temp : employees) {
            employeeExpense += temp.numOfShift * temp.payPerShift;
            employeeModel.changeNumOfShift(temp.employeeID, 0);
        }
        return employeeExpense;
    }

    private boolean checkInput() {
        if (tfOtherExpenses.getText().trim().matches("") || tfTimeID.getText().trim().matches("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("ERROR");
            alert.setContentText("Please fill in all required fields");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
            return false;
        }
        return true;
    }

    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }
}
