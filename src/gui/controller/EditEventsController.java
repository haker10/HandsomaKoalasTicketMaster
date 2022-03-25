package gui.controller;


import be.Event;
import gui.model.EditEventsModel;
import javafx.collections.ObservableList;
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

public class EditEventsController implements Initializable {

    @FXML
    public Button listOfParticipantsBtn;

    @FXML
    private ChoiceBox<?> eventChoiceBox;

    @FXML
    private Button editBtn;

    EditEventsModel editEventsModel;

    public EditEventsController(){

        editEventsModel = new EditEventsModel();

    }

    private void loadData(){

        eventChoiceBox.getItems().addAll(editEventsModel.getAllEvents());

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loadData();

    }

    public void editEvents(ActionEvent actionEvent) {
        JFrame jFrame = new JFrame();
        try{
            if (eventChoiceBox.getValue() == null){
                JOptionPane.showMessageDialog(jFrame, "FIELD IS EMPTY !!\nPLEASE TRY AGAIN!!");
            }
            else {
                Stage currentStage = (Stage) editBtn.getScene().getWindow();
                currentStage.close();
                Parent root = FXMLLoader.load(getClass().getResource("/gui/view/editEventView.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setTitle("Edit Event");
                stage.setScene(scene);
                stage.setUserData(eventChoiceBox.getSelectionModel().getSelectedItem());
                stage.show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getListOfParticipants(ActionEvent actionEvent) {
        try{
            ObservableList listOfParticipants = editEventsModel.getListOfParticipants(((Event) eventChoiceBox.getSelectionModel().getSelectedItem()).getId());
            Stage currentStage = (Stage) listOfParticipantsBtn.getScene().getWindow();
            currentStage.close();
            Parent root = FXMLLoader.load(getClass().getResource("/gui/view/listOfParticipantsView.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("List of Participants");
            stage.setScene(scene);
            stage.setUserData(listOfParticipants);
            stage.show();
        } catch(Exception e){
            e.printStackTrace();
        }

    }
}
