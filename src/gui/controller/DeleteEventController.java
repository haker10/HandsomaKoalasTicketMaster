package gui.controller;

import be.Event;
import gui.model.DeleteEventModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class DeleteEventController implements Initializable {

    @FXML
    private ChoiceBox<?> eventChoiceBox;

    @FXML
    private Button deleteBtn;

    DeleteEventModel deleteEventModel;

    String type;

    public DeleteEventController(){

        deleteEventModel = new DeleteEventModel();

    }

    private void loadData(){

        Platform.runLater(() -> {
            deleteEventModel = new DeleteEventModel();
            Stage currentStage = (Stage) deleteBtn.getScene().getWindow();
            type = (String) currentStage.getUserData();
        });

        eventChoiceBox.getItems().addAll(deleteEventModel.getAllEvents());

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loadData();

    }

    public void deleteEvent(ActionEvent actionEvent) {
        JFrame jFrame = new JFrame();
        try{
            if (eventChoiceBox.getValue() == null){
                JOptionPane.showMessageDialog(jFrame, "FIELD IS EMPTY !!\nPLEASE TRY AGAIN!!");
            }
            else {
                if (type == "ADMIN") {
                    deleteEventModel.deleteEvent(((Event) eventChoiceBox.getSelectionModel().getSelectedItem()).getId());
                    JOptionPane.showMessageDialog(jFrame, "EVENT DELETED !!");
                    Stage currentStage = (Stage) deleteBtn.getScene().getWindow();
                    currentStage.close();
                    Parent root = FXMLLoader.load(getClass().getResource("/gui/view/adminView.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    stage.setTitle("Admin Page");
                    stage.setScene(scene);
                    stage.show();
                }
                else if (type == "COORDINATOR"){
                    deleteEventModel.deleteEvent(((Event) eventChoiceBox.getSelectionModel().getSelectedItem()).getId());
                    JOptionPane.showMessageDialog(jFrame, "EVENT DELETED !!");
                    Stage currentStage = (Stage) deleteBtn.getScene().getWindow();
                    currentStage.close();
                    Parent root = FXMLLoader.load(getClass().getResource("/gui/view/coordinatorView.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    stage.setTitle("Coordinator Page");
                    stage.setScene(scene);
                    stage.show();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
