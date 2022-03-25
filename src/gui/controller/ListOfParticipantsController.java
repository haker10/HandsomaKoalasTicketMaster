package gui.controller;

import be.Customer;
import gui.model.ListOfParticipantsModel;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ListOfParticipantsController implements Initializable {

    @FXML
    private ListView listOfParticipantsListView;


    ListOfParticipantsModel listOfParticipantsModel;

    ObservableList<Customer> listOfParticipants;

    public ListOfParticipantsController(){

        listOfParticipantsModel = new ListOfParticipantsModel();

    }

    public void loadData(){
        Platform.runLater(() -> {
            listOfParticipantsModel = new ListOfParticipantsModel();
            Stage currentStage = (Stage) listOfParticipantsListView.getScene().getWindow();
            listOfParticipants = (ObservableList<Customer>) currentStage.getUserData();
            listOfParticipantsListView.setItems(listOfParticipants);
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loadData();
    }
}
