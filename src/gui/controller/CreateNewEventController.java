package gui.controller;

import be.Event;
import gui.model.CreateNewEventModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class CreateNewEventController implements Initializable {

    @FXML
    public Button createEventBtn;

    @FXML
    private TextField nameTxt;

    @FXML
    private TextField startDateAndTimeTxt;

    @FXML
    private TextField endDateAndTimeTxt;

    @FXML
    private TextField addressTxt;

    CreateNewEventModel createNewEventModel;

    public CreateNewEventController(){

        createNewEventModel = new CreateNewEventModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void createEvent(ActionEvent actionEvent) {
        JFrame jFrame = new JFrame();
        try {
            if (nameTxt.getText().isEmpty() || startDateAndTimeTxt.getText().isEmpty() || endDateAndTimeTxt.getText().isEmpty() || addressTxt.getText().isEmpty())
                JOptionPane.showMessageDialog(jFrame, "FIELD IS EMPTY !!\nPLEASE TRY AGAIN!!");
            else {
                Date startDateAndTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(startDateAndTimeTxt.getText());
                Date endDateAndTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(endDateAndTimeTxt.getText());
                createNewEventModel.createEvent(nameTxt.getText(), startDateAndTime, endDateAndTime, addressTxt.getText());
                JOptionPane.showMessageDialog(jFrame, "EVENT CREATED !!");
                Stage currentStage = (Stage) createEventBtn.getScene().getWindow();
                currentStage.close();
                Parent root = FXMLLoader.load(getClass().getResource("/gui/view/coordinatorView.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setTitle("Coordinator Page");
                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
