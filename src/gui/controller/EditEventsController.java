package gui.controller;


import be.Event;
import gui.model.EditEventsModel;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.ResourceBundle;

public class EditEventsController implements Initializable {

    @FXML
    private TextField keywordTextField;

    @FXML
    public Button listOfParticipantsBtn;

    @FXML
    private TableView eventTableView;

    @FXML
    private TableColumn eventColumn;

    @FXML
    private Button editBtn;


    EditEventsModel editEventsModel;

    public EditEventsController(){

        editEventsModel = new EditEventsModel();

    }
    public void updatePlayListTableView() {
        eventColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        try {
            eventTableView.setItems(editEventsModel.getAllEvents());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updatePlayListTableView();
        eventTableView.setRowFactory(tr -> new  TableRow<Event>(){
            @Override
            protected void updateItem(Event item, boolean empty) {
                super.updateItem(item, empty);
                if (empty == true){
                    setStyle("-fx-background-color: #DAD5D6");
                    return;
                }
                if(item != null && Date.from(Instant.now()).after(item.getStartDatenTime())) {
                    setStyle("-fx-background-color: #C54B6C;");
                }
                else{
                    setStyle("-fx-background-color: #8DA47E;");
                }

            }
        });
        ObservableList<Event> eventList = editEventsModel.getAllEvents();
        FilteredList<Event> filteredData = null;
        try {
            filteredData = new FilteredList<>(eventList, b -> true);
        } catch (Exception e) {
            e.printStackTrace();
        }



        FilteredList<Event> finalFilteredData = filteredData;
        keywordTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            finalFilteredData.setPredicate(event -> {


                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (event.getName().toLowerCase().contains(lowerCaseFilter))
                    return true;
                else
                    return false;
            });
        });


        SortedList<Event> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(eventTableView.comparatorProperty());

        eventTableView.setItems(sortedData);
    }

    public void editEvents(ActionEvent actionEvent) {
        JFrame jFrame = new JFrame();
        try{
            if (eventTableView.getSelectionModel() == null){
                JOptionPane.showMessageDialog(jFrame, "FIELD IS EMPTY !!\nPLEASE TRY AGAIN!!");
            }
            else {
                Stage currentStage = (Stage) editBtn.getScene().getWindow();
                currentStage.close();
                Parent root = FXMLLoader.load(getClass().getResource("/gui/view/editEventView.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(scene);
                stage.setUserData(eventTableView.getSelectionModel().getSelectedItem());
                stage.show();
                scene.setFill(Color.TRANSPARENT);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void getListOfParticipants(ActionEvent actionEvent) {
        try{
            ObservableList listOfParticipants = editEventsModel.getListOfParticipants(((Event) eventTableView.getSelectionModel().getSelectedItem()).getId());
            Stage currentStage = (Stage) listOfParticipantsBtn.getScene().getWindow();
            currentStage.close();
            Parent root = FXMLLoader.load(getClass().getResource("/gui/view/listOfParticipantsView.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.setUserData(listOfParticipants);
            stage.show();
            scene.setFill(Color.TRANSPARENT);
        } catch(Exception e){
            e.printStackTrace();
        }

    }

    public void closeBtnPressed(ActionEvent actionEvent) {
        Platform.exit();
    }
}
