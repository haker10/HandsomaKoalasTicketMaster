package gui.controller;

import be.Event;
import gui.model.DeleteEventModel;
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
import java.util.Date;
import java.util.ResourceBundle;

public class DeleteEventController implements Initializable {

    @FXML
    private TextField keywordTextField;

    @FXML
    private TableView eventTableView;

    @FXML
    private TableColumn eventColumn;

    @FXML
    private Button deleteBtn;

    DeleteEventModel deleteEventModel;

    String type;

    public DeleteEventController(){

        deleteEventModel = new DeleteEventModel();

    }
    public void updatePlayListTableView() {
        eventColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        try {
            eventTableView.setItems(deleteEventModel.getAllEvents());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadData(){

        Platform.runLater(() -> {
            deleteEventModel = new DeleteEventModel();
            Stage currentStage = (Stage) eventTableView.getScene().getWindow();
            type = (String) currentStage.getUserData();
        });

        updatePlayListTableView();
        eventTableView.setRowFactory(tr -> new  TableRow<Event>(){
            @Override
            protected void updateItem(Event item, boolean empty) {
                super.updateItem(item, empty);
                if (empty == true){
                    setStyle("-fx-background-color: #DAD5D6");
                    return;
                }
                else if(item != null && Date.from(Instant.now()).after(item.getStartDatenTime())) {
                    setStyle("-fx-background-color: #C54B6C;");
                }
                else{
                    setStyle("-fx-background-color: #8DA47E;");
                }

            }
        });
        ObservableList<Event> eventList = deleteEventModel.getAllEvents();
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updatePlayListTableView();
        loadData();

    }

    public void deleteEvent(ActionEvent actionEvent) {
        JFrame jFrame = new JFrame();
        try{

            if (eventTableView.getSelectionModel().getSelectedItem() == null){
                JOptionPane.showMessageDialog(jFrame, "CHOOSE AN EVENT !!\nPLEASE TRY AGAIN!!");
            }
            else {
                if (type == "ADMIN") {
                    deleteEventModel.deleteEvent(((Event) eventTableView.getSelectionModel().getSelectedItem()).getId());
                    JOptionPane.showMessageDialog(jFrame, "EVENT DELETED !!");
                    Stage currentStage = (Stage) deleteBtn.getScene().getWindow();
                    currentStage.close();
                    Parent root = FXMLLoader.load(getClass().getResource("/gui/view/adminView.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    stage.initStyle(StageStyle.TRANSPARENT);
                    scene.setFill(Color.TRANSPARENT);
                    stage.setScene(scene);
                    stage.show();
                }


                else if (type == "COORDINATOR"){
                    deleteEventModel.deleteEvent(((Event) eventTableView.getSelectionModel().getSelectedItem()).getId());
                    JOptionPane.showMessageDialog(jFrame, "EVENT DELETED !!");
                    Stage currentStage = (Stage) deleteBtn.getScene().getWindow();
                    currentStage.close();
                    Parent root = FXMLLoader.load(getClass().getResource("/gui/view/coordinatorView.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    stage.initStyle(StageStyle.TRANSPARENT);
                    scene.setFill(Color.TRANSPARENT);
                    stage.setScene(scene);
                    stage.show();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void closeBtnPressed(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void backBtnPressed(ActionEvent actionEvent) {
        if (type == "ADMIN") {
            Stage currentStage = (Stage) deleteBtn.getScene().getWindow();
            currentStage.close();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/gui/view/adminView.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(scene);
                stage.show();
                scene.setFill(Color.TRANSPARENT);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (type == "COORDINATOR") {
            Stage currentStage = (Stage) deleteBtn.getScene().getWindow();
            currentStage.close();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/gui/view/coordinatorView.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(scene);
                stage.show();
                scene.setFill(Color.TRANSPARENT);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
