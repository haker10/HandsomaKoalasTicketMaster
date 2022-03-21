package gui.controller;

import be.User;
import gui.model.DeleteUserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

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
        int chosenUserId = ((User) userChoiceBox.getSelectionModel().getSelectedItem()).getId();
        try{
            if (userChoiceBox.getValue() == null){
                JOptionPane.showMessageDialog(jFrame, "FIELD IS EMPTY !!\nPLEASE TRY AGAIN!!");
            }
            else {
                deleteUserModel.deleteUser(chosenUserId);
                JOptionPane.showMessageDialog(jFrame, "USER DELETED !!");
                Stage currentStage = (Stage) deleteBtn.getScene().getWindow();
                currentStage.close();
                Parent root = FXMLLoader.load(getClass().getResource("/gui/view/adminView.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setTitle("Admin Page");
                stage.setScene(scene);
                stage.show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loadData();

    }
}
