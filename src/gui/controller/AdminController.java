package gui.controller;

import gui.model.AdminModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    @FXML
    public Button XBtn;

    @FXML
    public Button deleteCustomerBtn;

    @FXML
    private Button deleteEventBtn;

    @FXML
    private Button createUserBtn, deleteUserBtn;

    @FXML
    private Button logoutBtn;

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
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
            scene.setFill(Color.TRANSPARENT);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void deleteUser(ActionEvent actionEvent) {
        Stage currentStage = (Stage) deleteUserBtn.getScene().getWindow();
        currentStage.close();
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/gui/view/deleteUserView.fxml"));
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

    public void deleteEvent(ActionEvent actionEvent) {
        Stage currentStage = (Stage) deleteEventBtn.getScene().getWindow();
        currentStage.close();
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/gui/view/deleteEventView.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.setUserData("ADMIN");
            stage.show();
            scene.setFill(Color.TRANSPARENT);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void closeBtnPressed(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void OnClickLogoutBtn(ActionEvent actionEvent) {
        Stage currentStage = (Stage) logoutBtn.getScene().getWindow();
        currentStage.close();
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/gui/view/loginView.fxml"));
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

    public void deleteCustomer(ActionEvent actionEvent) {
        Stage currentStage = (Stage) deleteCustomerBtn.getScene().getWindow();
        currentStage.close();
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/gui/view/deleteCustomerView.fxml"));
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

