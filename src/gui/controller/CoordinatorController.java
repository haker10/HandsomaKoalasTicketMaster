package gui.controller;

import gui.model.CoordinatorModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CoordinatorController implements Initializable {

    @FXML
    private Button searchCustomersBtn;

    @FXML
    private Button editEventBtn;

    @FXML
    private Button deleteEventBtn;

    @FXML
    private Button addCustomerToEventBtn;

    @FXML
    private Button createEventBtn;

    CoordinatorModel coordinatorModel;

    public CoordinatorController(){

        coordinatorModel = new CoordinatorModel();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void createEvent(ActionEvent actionEvent){
        Stage currentStage = (Stage) createEventBtn.getScene().getWindow();
        currentStage.close();
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/gui/view/createNewEventView.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Create New Event");
            stage.setScene(scene);
            stage.show();
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
            stage.setTitle("Delete Event");
            stage.setScene(scene);
            stage.setUserData("COORDINATOR");
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addCustomerToEvent(ActionEvent actionEvent) {
        Stage currentStage = (Stage) addCustomerToEventBtn.getScene().getWindow();
        currentStage.close();
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/gui/view/addCustomerToEventView.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Add Customer To Event");
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void editEvent(ActionEvent actionEvent) {
        Stage currentStage = (Stage) editEventBtn.getScene().getWindow();
        currentStage.close();
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/gui/view/editEventsView.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Events");
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void searchCustomers(ActionEvent actionEvent) {
        Stage currentStage = (Stage) searchCustomersBtn.getScene().getWindow();
        currentStage.close();
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/gui/view/searchCustomersView.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Search Customers");
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
