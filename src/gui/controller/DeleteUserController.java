package gui.controller;

import be.User;
import gui.model.DeleteUserModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class DeleteUserController implements Initializable {

    @FXML
    private ChoiceBox<?> userChoiceBox;

    @FXML
    private javafx.scene.control.Button deleteBtn;


    DeleteUserModel deleteUserModel;

    public DeleteUserController(){

        deleteUserModel = new DeleteUserModel();

    }

    private void loadData(){
        userChoiceBox.getItems().addAll(deleteUserModel.getAllUsers());
    }

    public void deleteUser(ActionEvent actionEvent) {
        JFrame jFrame = new JFrame();
        try{
            if (userChoiceBox.getValue() == null){
                JOptionPane.showMessageDialog(jFrame, "FIELD IS EMPTY !!\nPLEASE TRY AGAIN!!");
            }
            else {
                deleteUserModel.deleteUser(((User) userChoiceBox.getSelectionModel().getSelectedItem()).getId());
                JOptionPane.showMessageDialog(jFrame, "USER DELETED !!");
                Stage currentStage = (Stage) deleteBtn.getScene().getWindow();
                currentStage.close();
                Parent root = FXMLLoader.load(getClass().getResource("/gui/view/adminView.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(scene);
                stage.show();
                scene.setFill(Color.TRANSPARENT);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loadData();

    }

    public void closeBtnPressed(ActionEvent actionEvent) {
        Platform.exit();
    }
}
