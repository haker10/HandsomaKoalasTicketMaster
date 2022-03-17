package gui.controller;

import be.User;
//import gui.model.LoginModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField UserNameTxt;
    @FXML
    private PasswordField PasswordTxt;
    @FXML
    private Button LoginBtn;

    //LoginModel loginModel;

    private User user;

    private Stage stage;

    public LoginController(){

        //loginModel = new LoginModel();

    }

    public User getUser(){
        return user;
    }







    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void LoginBtnPressed(ActionEvent actionEvent) {
        if (UserNameTxt.getText().equals("username") && PasswordTxt.getText().equals("password")) {
            System.out.println("Login Success");
        }
        else {
            System.out.println("Login Failed");
        }
    }

    public void UserNameTxt(ActionEvent actionEvent) {
        // to do;
    }

    public void PasswordTxt(ActionEvent actionEvent) {
        // to do;
    }
}
