package gui.controller;

import be.Event;
import gui.model.CustomerParticipationModel;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerParticipationController implements Initializable {

    @FXML
    private TableView<Event> customerParticipationTV;

    @FXML
    private TableColumn nameColumn;

    @FXML
    private TableColumn startInfoColumn;

    @FXML
    private TableColumn endInfoColumn;

    @FXML
    private TableColumn addressColumn;

    @FXML
    private TableColumn addInfoColumn;


    CustomerParticipationModel customerParticipationModel;

    public CustomerParticipationController() {

        customerParticipationModel = new CustomerParticipationModel();

    }

    public void loadData() {
        Platform.runLater(() -> {
            customerParticipationModel = new CustomerParticipationModel();
            Stage currentStage = (Stage) customerParticipationTV.getScene().getWindow();
            String customerEmail = (String) currentStage.getUserData();
            List<Integer> eventIDs = customerParticipationModel.getEventByCustomer(customerEmail);
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            startInfoColumn.setCellValueFactory(new PropertyValueFactory<>("startDatenTime"));
            endInfoColumn.setCellValueFactory(new PropertyValueFactory<>("endDatenTime"));
            addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
            addInfoColumn.setCellValueFactory(new PropertyValueFactory<>("additionalInfo"));
            ObservableList<Event> eventList = FXCollections.observableArrayList();
            for (Integer eventID : eventIDs) {
                try {
                    eventList.add(customerParticipationModel.getEventByIdOL(eventID));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            customerParticipationTV.setItems(eventList);

        });
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loadData();

    }

    public void closeBtnPressed(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void backBtnPressed(ActionEvent actionEvent) {

        Stage currentStage = (Stage) customerParticipationTV.getScene().getWindow();
        currentStage.close();
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/gui/view/searchCustomersView.fxml"));
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
