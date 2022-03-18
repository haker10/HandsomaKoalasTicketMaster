package gui.controller;

import be.User;
import gui.model.LoginModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
            }
            else {
                if (result.getTypeOfUser().equals("ADMIN")){
                    System.out.println("ADMIN");
                    try{
                        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/adminView.fxml"));
                        Stage stage = new Stage();
                        Scene scene = new Scene(root);
                        stage.setTitle("Admin Page");
                        stage.setScene(scene);
                        stage.show();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                else if (result.getTypeOfUser().equals("COORDINATOR")){
                    System.out.println("COORDINATOR");
                    try{
                        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/coordinatorView.fxml"));
                        Stage stage = new Stage();
                        Scene scene = new Scene(root);
                        stage.setTitle("Coordinator Page");
                        stage.setScene(scene);
                        stage.show();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }

            System.out.println(result.toString());
        } catch (Exception e) {
        }
    }

}
