/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;

import dataStruct.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.image.Image;
import javafx.stage.StageStyle;
import model.UserModel;

public class LoginController implements Initializable {

    @FXML
    private TextField tfUserName;

    @FXML
    private Button btnUserNameTfClear;
    @FXML
    private Button btnPassFieldClear;
    @FXML
    private PasswordField pfUserPassword;


    @FXML
    private Button btnLogin;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        BooleanBinding boolenBinding = tfUserName.textProperty().isEmpty()
                .or(pfUserPassword.textProperty().isEmpty());

        btnLogin.disableProperty().bind(boolenBinding);

    }


    @FXML
    private void btnLogin(ActionEvent event) throws IOException {


        UserModel userModel = new UserModel();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/home.fxml"));
        loader.load();
        Parent parent = loader.getRoot();
        Scene adminPanelScene = new Scene(parent);
        Stage adminPanelStage = new Stage();
        adminPanelStage.setMaximized(true);

            if (isValidCondition()) {

                if (userModel.getUserID(tfUserName.getText(),pfUserPassword.getText()) != -1) {
                    HomeController apControl = loader.getController();
                    apControl.btnHomeOnClick(event);
                    apControl.permission(tfUserName.getText());
                    apControl.viewDetails(tfUserName.getText());
                    adminPanelStage.setScene(adminPanelScene);
                    adminPanelStage.getIcons().add(new Image("/image/icon.png"));
                    adminPanelStage.show();

                    Stage stage = (Stage) btnLogin.getScene().getWindow();
                    stage.close();
                } else {
                    System.out.println(userModel.getUserID(tfUserName.getText(),pfUserPassword.getText()));
                    System.out.println("Password Not Match");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Password Not Match");
                    alert.setHeaderText("Error : Name or Pass Not matched");
                    alert.setContentText("User Name or Password not matched \ntry Again");
                    alert.initStyle(StageStyle.UNDECORATED);
                    alert.showAndWait();
                }

            }
    }

    private boolean isValidCondition() {
        boolean validCondition = false;
        if (tfUserName.getText().trim().isEmpty()
                || pfUserPassword.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING :");
            alert.setHeaderText("WARNING !!");
            alert.setContentText("Please Fill Text Field And Password Properly");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();

            validCondition = false;
        } else {
            validCondition = true;
        }
        return validCondition;
    }

    @FXML
    private void pfUserNameOnHitEnter(ActionEvent event) {
        try {
            btnLogin(event);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void pfUserPassOnHitEnter(ActionEvent event) {
        try {
            btnLogin(event);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }




    public void btnUserNameTfClearOnClick(ActionEvent actionEvent) {
        btnUserNameTfClear.setText("");
    }

    public void btnPassFieldClearOnClick(ActionEvent actionEvent) {
        btnPassFieldClear.setText("");
    }
}
