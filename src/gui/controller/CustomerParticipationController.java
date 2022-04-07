package gui.controller;

import be.Customer;
import be.Event;
import gui.model.CustomerParticipationModel;
import gui.model.EditEventModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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
            System.out.println(customerEmail);
            List<Integer> eventIDs = customerParticipationModel.getEventByCustomer(customerEmail);
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            startInfoColumn.setCellValueFactory(new PropertyValueFactory<>("startDatenTime"));
            endInfoColumn.setCellValueFactory(new PropertyValueFactory<>("endDatenTime"));
            addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
            addInfoColumn.setCellValueFactory(new PropertyValueFactory<>("additionalInfo"));

            for (Integer eventID : eventIDs) {
                try {
                    customerParticipationTV.setItems(customerParticipationModel.getEventByIdOL(eventID));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loadData();

    }
}
