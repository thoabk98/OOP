package controller.setting;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.UserModel;

import java.net.URL;
import java.util.ResourceBundle;

public class PassChangeController implements Initializable {
    @FXML
    private PasswordField pfCurrentPass;
    @FXML
    private PasswordField pfNewPass;
    @FXML
    private PasswordField pfRePass;
    @FXML
    private Button btnClose;
    private String Username;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    public void getUser(String name){
        this.Username = name;
    }




    @FXML
    private void btnChangePassOnAction(ActionEvent event) {
        UserModel userModel = new UserModel();

        if (userModel.getUserID(Username,pfCurrentPass.getText())!= -1 && pfNewPass.getText().equals(pfRePass.getText())) {
            userModel.changePassword(Username,pfNewPass.getText());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("SUCCESS");
            alert.setHeaderText("SUCCESS ");
            alert.setContentText("Change Password Successfully");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("ERROR ");
            alert.setContentText("Invalid password");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
        }

    }

    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void pfOnAction(ActionEvent event) {
        btnChangePassOnAction(event);
    }


}

