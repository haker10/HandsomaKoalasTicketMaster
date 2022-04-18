package gui.controller;

import gui.model.CreateNewUserModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateNewUserController implements Initializable {

    @FXML
    private ChoiceBox<String> typeOfUserChoiceBox;

    @FXML
    private TextField newUsernameTxt;

    @FXML
    private TextField newPasswordTxt;

    @FXML
    private javafx.scene.control.Button createUserBtn;

    @FXML
    private Label TypeOfUser;


    CreateNewUserModel createNewUserModel;

    public CreateNewUserController(){

        createNewUserModel = new CreateNewUserModel();

    }

    private void loadData(){
        typeOfUserChoiceBox.getItems().add("ADMIN");
        typeOfUserChoiceBox.getItems().add("COORDINATOR");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loadData();

    }

    public void HandleCreateUser(ActionEvent actionEvent) {
        JFrame jFrame = new JFrame();
        try{
            if (typeOfUserChoiceBox.getValue() == null || newUsernameTxt.getText().isEmpty() || newPasswordTxt.getText().isEmpty())
                JOptionPane.showMessageDialog(jFrame, "FIELD IS EMPTY !!\nPLEASE TRY AGAIN!!");
            else {
                createNewUserModel.createUser(typeOfUserChoiceBox.getValue(), newUsernameTxt.getText(), newPasswordTxt.getText());
                JOptionPane.showMessageDialog(jFrame, "USER CREATED !!");
                Stage currentStage = (Stage) createUserBtn.getScene().getWindow();
                currentStage.close();
                Parent root = FXMLLoader.load(getClass().getResource("/gui/view/adminView.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(scene);
                stage.show();
                scene.setFill(Color.TRANSPARENT);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void closeBtnPressed(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void backBtnPressed(ActionEvent actionEvent) {

        Stage currentStage = (Stage) TypeOfUser.getScene().getWindow();
        currentStage.close();
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
}
