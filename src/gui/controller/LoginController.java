package gui.controller;

import be.User;
import gui.model.LoginModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField UserNameTxt;
    @FXML
    private PasswordField PasswordTxt;
    @FXML
    private Button LoginBtn;

    LoginModel loginModel;

    private User user;

    private Stage stage;

    public LoginController(){

        loginModel = new LoginModel();

    }








    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void LoginBtnPressed(ActionEvent actionEvent) {
        String username = UserNameTxt.getText().toString();
        String password = PasswordTxt.getText().toString();
        try {
            User result = loginModel.login(username, password);
            JFrame jFrame = new JFrame();
            if (result == null) {
                JOptionPane.showMessageDialog(jFrame, "LOGIN FAILED !!");
            }
            else {
                JOptionPane.showMessageDialog(jFrame, "LOGIN SUCCESFULL !!");
            }

            System.out.println(result.toString());
        } catch (NullPointerException e) {
        }
    }

}
