package gui.controller;

import be.User;

import gui.model.LoginModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    public Label Title;
    @FXML
    private TextField UserNameTxt;
    @FXML
    private PasswordField PasswordTxt;
    @FXML
    private Button LoginBtn;
    @FXML
    ImageView image;

    LoginModel loginModel;

    public LoginController(){

        loginModel = new LoginModel();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void LoginBtnPressed(ActionEvent actionEvent) {
        String username = UserNameTxt.getText().toString();
        String password = PasswordTxt.getText().toString();
        Stage currentStage = (Stage) LoginBtn.getScene().getWindow();
        currentStage.close();
        try {
            User result = loginModel.login(username, password);
            JFrame jFrame = new JFrame();
            if (result == null) {
                JOptionPane.showMessageDialog(jFrame, "LOGIN FAILED !!");
                Parent root = FXMLLoader.load(getClass().getResource("/gui/view/loginView.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(scene);
                stage.show();
                scene.setFill(Color.TRANSPARENT);

            }
            else {
                if (result.getTypeOfUser().equals("ADMIN")){
                    try{
                        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/adminView.fxml"));
                        Stage stage = new Stage();
                        Scene scene = new Scene(root);
                        stage.initStyle(StageStyle.TRANSPARENT);
                        stage.setScene(scene);
                        stage.show();
                        scene.setFill(Color.TRANSPARENT);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                else if (result.getTypeOfUser().equals("COORDINATOR")){
                    try{
                        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/coordinatorView.fxml"));
                        Stage stage = new Stage();
                        Scene scene = new Scene(root);
                        stage.initStyle(StageStyle.TRANSPARENT);
                        stage.setScene(scene);
                        stage.show();
                        scene.setFill(Color.TRANSPARENT);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    public void closeBtnPressed(ActionEvent actionEvent) {
        Platform.exit();
    }

}
