package gui.controller;

import gui.model.AdminModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    @FXML
    private Button createUserBtn;

    AdminModel adminModel;

    public AdminController(){

        adminModel = new AdminModel();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void createUser(ActionEvent actionEvent) {
        Stage currentStage = (Stage) createUserBtn.getScene().getWindow();
        currentStage.close();
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/gui/view/createNewUserView.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Create New User");
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
