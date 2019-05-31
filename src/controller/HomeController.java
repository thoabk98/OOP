package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import controller.employee.EmployeeController;
import controller.product.ProductController;
import controller.revenue.ViewRevenueController;
import controller.sell.ViewSellController;
import controller.setting.SettingController;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.UserModel;


public class HomeController implements Initializable {
    @FXML
    private StackPane acContent;
    @FXML
    private ScrollPane leftSideBarScroolPan;
    @FXML
    private ToggleButton sideMenuToogleBtn;
    @FXML
    private ImageView imgMenuBtn;
    @FXML
    private BorderPane appContent;
    @FXML
    private Button btnLogOut;
    @FXML
    private AnchorPane acDashBord;
    @FXML
    private AnchorPane acHead;
    @FXML
    private AnchorPane acMain;

    @FXML
    private Button btnHome;
    @FXML
    private ImageView imgHomeBtn;
    @FXML
    private Button btnStore;
    @FXML
    private ImageView imgStoreBtn;
    @FXML
    private Button btnEmplopye;
    @FXML
    private ImageView imgEmployeBtn;
    @FXML
    private Button btnSell;
    @FXML
    private ImageView imgSellBtn;
    @FXML
    private Button btnRevenue;
    @FXML
    private ImageView imgRevenueBtn;
    @FXML
    private Button btnSettings;
    @FXML
    private ImageView imgSettingsBtn;
    @FXML
    private Label lblUsrName;
    @FXML
    private Label lblRoleAs;

    @FXML
    private Circle imgUsrTop;
    @FXML
    private Circle circleImgUsr;
    @FXML
    private Label lblUserId;

    Image menuImage = new Image("/icon/menu.png");
    Image menuImageRed = new Image("/icon/menuRed.png");
    Image image;

    String defaultStyle = "-fx-border-width: 0px 0px 0px 5px;"
            + "-fx-border-color:none";

    String activeStyle = "-fx-border-width: 0px 0px 0px 5px;"
            + "-fx-border-color:#FF4E3C";

    Image home = new Image("/icon/home.png");
    Image homeRed = new Image("/icon/homeRed.png");
    Image stock = new Image("/icon/stock.png");
    Image stockRed = new Image("/icon/stockRed.png");
    Image sell = new Image("/icon/sell.png");
    Image sellRed = new Image("/icon/sellRed.png");
    Image employee = new Image("/icon/employe.png");
    Image employeeRed = new Image("/icon/employeRed.png");
    Image setting = new Image("/icon/settings.png");
    Image settingRed = new Image("/icon/settingsRed.png");
    Image revenue = new Image("/icon/sell2.png");
    Image revenueRed = new Image("/icon/sell2Red.png");


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image usrImg = new Image("/icon/d.png");
        imgUsrTop.setFill(new ImagePattern(usrImg));
        circleImgUsr.setFill(new ImagePattern(usrImg));

    }

    @FXML
    private void btnLogOut(ActionEvent event) throws IOException {
        acContent.getChildren().clear();
        acContent.getChildren().add(FXMLLoader.load(getClass().getResource("/view/Login.fxml")));
        acDashBord.getChildren().clear();
        acHead.getChildren().clear();
        acHead.setMaxHeight(0.0);
    }

    @FXML
    public void btnHomeOnClick(ActionEvent event) {
        homeActive();
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            fxmlLoader.load(getClass().getResource("/view/home.fxml").openStream());
        } catch (IOException e) {

        }
        AnchorPane root = fxmlLoader.getRoot();
        acContent.getChildren().clear();
        acContent.getChildren().add(root);

        System.out.println(lblUsrName.getText());
        System.out.println(lblUserId.getText());

    }

    @FXML
    public void btnRevenueOnClick(ActionEvent e) {
        revenueActive();

        try {
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.load(getClass().getResource("/view/Revenue.fxml").openStream());

            ViewRevenueController viewRevenueController = fXMLLoader.getController();
            viewRevenueController.viewList();

            AnchorPane anchorPane = fXMLLoader.getRoot();
            acContent.getChildren().clear();
            acContent.getChildren().add(anchorPane);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnSellOnClick(ActionEvent event) throws IOException {
        sellActive();

        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/Sell.fxml").openStream());

        ViewSellController viewSellController = fXMLLoader.getController();
        viewSellController.viewList();
        AnchorPane anchorPane = fXMLLoader.getRoot();
        acContent.getChildren().clear();
        acContent.getChildren().add(anchorPane);

    }
    @FXML
    private void btnStoreOnClick(ActionEvent event) throws IOException {
        sotreActive();

        ProductController pc = new ProductController();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/Product.fxml").openStream());
        ProductController productController = fXMLLoader.getController();
        productController.viewDetail();

        AnchorPane anchorPane;
        anchorPane = fXMLLoader.getRoot();

        acContent.getChildren().clear();

        acContent.getChildren().add(anchorPane);
    }

    @FXML
    private void btnEmplopyeOnClick(ActionEvent e) throws IOException {
        employeeActive();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/Employee.fxml").openStream());
        EmployeeController employeeController = fXMLLoader.getController();
        employeeController.viewEmployeeList();
        employeeController.permission(lblUsrName.getText());
        AnchorPane anchorPane;
        anchorPane = fXMLLoader.getRoot();

        acContent.getChildren().clear();

        acContent.getChildren().add(anchorPane);
    }

    @FXML
    private void btnSettingOnClick(ActionEvent e) throws IOException {
        settingsActive();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.load(getClass().getResource("/view/Setting.fxml").openStream());
        SettingController settingController = fxmlLoader.getController();
        settingController.showDetails(lblUsrName.getText());
        AnchorPane anchorPane = fxmlLoader.getRoot();

        acContent.getChildren().clear();

        acContent.getChildren().add(anchorPane);

    }

    private void homeActive() {
        imgHomeBtn.setImage(homeRed);
        imgStoreBtn.setImage(stock);
        imgSellBtn.setImage(sell);
        imgEmployeBtn.setImage(employee);
        imgSettingsBtn.setImage(setting);
        imgRevenueBtn.setImage(revenue);
        btnHome.setStyle(activeStyle);
        btnStore.setStyle(defaultStyle);
        btnSell.setStyle(defaultStyle);
        btnEmplopye.setStyle(defaultStyle);
        btnSettings.setStyle(defaultStyle);
        btnRevenue.setStyle(defaultStyle);
    }

    private void sotreActive() {
        imgHomeBtn.setImage(home);
        imgStoreBtn.setImage(stockRed);
        imgSellBtn.setImage(sell);
        imgEmployeBtn.setImage(employee);
        imgSettingsBtn.setImage(setting);
        imgRevenueBtn.setImage(revenue);
        btnHome.setStyle(defaultStyle);
        btnStore.setStyle(activeStyle);
        btnSell.setStyle(defaultStyle);
        btnEmplopye.setStyle(defaultStyle);
        btnSettings.setStyle(defaultStyle);
        btnRevenue.setStyle(defaultStyle);
    }

    private void sellActive() {
        imgHomeBtn.setImage(home);
        imgStoreBtn.setImage(stock);
        imgSellBtn.setImage(sellRed);
        imgEmployeBtn.setImage(employee);
        imgSettingsBtn.setImage(setting);
        imgRevenueBtn.setImage(revenue);
        btnHome.setStyle(defaultStyle);
        btnStore.setStyle(defaultStyle);
        btnSell.setStyle(activeStyle);
        btnEmplopye.setStyle(defaultStyle);
        btnSettings.setStyle(defaultStyle);
        btnRevenue.setStyle(defaultStyle);
    }

    private void employeeActive() {
        imgHomeBtn.setImage(home);
        imgStoreBtn.setImage(stock);
        imgSellBtn.setImage(sell);
        imgEmployeBtn.setImage(employeeRed);
        imgSettingsBtn.setImage(setting);
        imgRevenueBtn.setImage(revenue);
        btnHome.setStyle(defaultStyle);
        btnStore.setStyle(defaultStyle);
        btnSell.setStyle(defaultStyle);
        btnEmplopye.setStyle(activeStyle);
        btnSettings.setStyle(defaultStyle);
        btnRevenue.setStyle(defaultStyle);
    }

    private void settingsActive() {
        imgHomeBtn.setImage(home);
        imgStoreBtn.setImage(stock);
        imgSellBtn.setImage(sell);
        imgEmployeBtn.setImage(employee);
        imgSettingsBtn.setImage(settingRed);
        imgRevenueBtn.setImage(revenue);
        btnHome.setStyle(defaultStyle);
        btnStore.setStyle(defaultStyle);
        btnSell.setStyle(defaultStyle);
        btnEmplopye.setStyle(defaultStyle);
        btnSettings.setStyle(activeStyle);
        btnRevenue.setStyle(defaultStyle);
    }

    private void revenueActive() {
        imgHomeBtn.setImage(home);
        imgStoreBtn.setImage(stock);
        imgSellBtn.setImage(sell);
        imgEmployeBtn.setImage(employee);
        imgSettingsBtn.setImage(setting);
        imgRevenueBtn.setImage(revenueRed);
        btnHome.setStyle(defaultStyle);
        btnStore.setStyle(defaultStyle);
        btnSell.setStyle(defaultStyle);
        btnEmplopye.setStyle(defaultStyle);
        btnSettings.setStyle(defaultStyle);
        btnRevenue.setStyle(activeStyle);
    }

    public void permission(String name) {
        EmployeeController employeeController = new EmployeeController();

        UserModel userModel = new UserModel();
        if (userModel.getPermission(name) == false) {
            btnRevenue.setDisable(true);
        }

    }

    public void viewDetails(String name) {
        lblUsrName.setText(name);
        UserModel userModel = new UserModel();
        if (userModel.getPermission(name) == false) {
            lblRoleAs.setText("Employee");
        } else {
            lblRoleAs.setText("Adimin");
        }
    }
}
