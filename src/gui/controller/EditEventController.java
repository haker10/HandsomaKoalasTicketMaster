package gui.controller;

import be.Event;
import gui.model.EditEventModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class EditEventController implements Initializable {

    @FXML
    private TextField nameTxt;

    @FXML
    private TextField startDateAndTimeTxt;

    @FXML
    private TextField endDateAndTimeTxt;

    @FXML
    private TextField addressTxt;

    @FXML
    private Button saveBtn;

    @FXML
    private TextField ticketTypesTxt;

    EditEventModel editEventModel;

    Event event;

    public EditEventController(){

        editEventModel = new EditEventModel();

    }

    private void loadData(){
        Platform.runLater(() -> {
            editEventModel = new EditEventModel();
            Stage currentStage = (Stage) saveBtn.getScene().getWindow();
            event = (Event)currentStage.getUserData();
            nameTxt.setText(event.getName());
            startDateAndTimeTxt.setText(event.getStartDatenTime().toString());
            endDateAndTimeTxt.setText(event.getEndDatenTime().toString());
            addressTxt.setText(event.getAddress());
            ticketTypesTxt.setText(event.getTicketTypes());
        });


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadData();

    }

    public void saveEdit(ActionEvent actionEvent) {
        JFrame jFrame = new JFrame();
        try{
            if (nameTxt.getText().isEmpty() || startDateAndTimeTxt.getText().isEmpty() || endDateAndTimeTxt.getText().isEmpty() || addressTxt.getText().isEmpty() || ticketTypesTxt.getText().isEmpty()){
                JOptionPane.showMessageDialog(jFrame, "FIELD IS EMPTY !!\nPLEASE TRY AGAIN!!");
            }
            else {
                Date startDateAndTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(startDateAndTimeTxt.getText());
                Date endDateAndTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(endDateAndTimeTxt.getText());
                editEventModel.editEvent(event.getId(), nameTxt.getText(), startDateAndTime, endDateAndTime, addressTxt.getText(),  ticketTypesTxt.getText());
                JOptionPane.showMessageDialog(jFrame, "EVENT EDITED !!");
                Stage currentStage = (Stage) saveBtn.getScene().getWindow();
                currentStage.close();
                Parent root = FXMLLoader.load(getClass().getResource("/gui/view/coordinatorView.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(scene);
                stage.show();
                scene.setFill(Color.TRANSPARENT);
                stage.show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
