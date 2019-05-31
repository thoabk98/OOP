/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.setting;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import dataStruct.Employee;

import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;

import javafx.scene.control.Alert;
import model.EmployeeModel;
import model.UserModel;

public class SettingController implements Initializable {

    @FXML
    private TextField tfUserName;
    @FXML
    private TextField tfFullName;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        BooleanBinding boolenBinding = tfFullName.textProperty().isEmpty();

    }

    @FXML
    private void btnSaveOnAction(ActionEvent event) {


    }

    @FXML
    private void hlChangePasswordOnClick(ActionEvent event) throws IOException {




                    PassChangeController pcc = new PassChangeController();

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/view/PassChange.fxml"));
                    loader.load();
                    Parent root = loader.getRoot();
                    Scene scene = new Scene(root);
                    scene.setFill(new Color(0, 0, 0, 0));
                    PassChangeController passChangeController = loader.getController();
                    passChangeController.getUser(tfUserName.getText());
                    Stage nStage = new Stage();
                    nStage.setScene(scene);
                    nStage.setTitle("Registration -StoreKeeper");
                    nStage.initModality(Modality.APPLICATION_MODAL);
                    nStage.initStyle(StageStyle.TRANSPARENT);
                    nStage.show();


        }



    public void showDetails(String userName) {
        UserModel userModel = new UserModel();
        int item = userModel.getUserID(userName);
        EmployeeModel employeeController = new EmployeeModel();
        Employee employee = employeeController.getEmployee(item);
        tfUserName.setText(userName);
        tfFullName.setText(employee.getEmployeeName());

    }


    private boolean nullCheck() {
        boolean notNull;

        if (tfFullName.getText().trim().length() == 0) {
            notNull = false;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("ERROR ");
            alert.setContentText("Please Fill all required fields");
            alert.initStyle(StageStyle.UNDECORATED);
        } else {
            notNull = true;
            System.out.println("Not Null");
        }
        return notNull;
    }

}
