package gui.controller;

import be.Customer;
import gui.model.ListOfParticipantsModel;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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

    public void backBtnPressed(ActionEvent actionEvent) {

        Stage currentStage = (Stage) listOfParticipantsListView.getScene().getWindow();
        currentStage.close();
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/gui/view/editEventsView.fxml"));
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

    public void closeBtnPressed(ActionEvent actionEvent) {

        Platform.exit();

    }
}
