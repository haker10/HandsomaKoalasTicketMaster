package gui.controller;

import gui.model.CreateNewEventModel;
import javafx.application.Platform;
import javafx.collections.ObservableList;
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

public class CreateNewEventController implements Initializable {

    @FXML
    private TextField ticketTypesTxt;

    @FXML
    private TextField additionalInfoTxt;

    @FXML
    private Button createEventBtn;

    @FXML
    private TextField nameTxt;

    @FXML
    private TextField startDateAndTimeTxt;

    @FXML
    private TextField endDateAndTimeTxt;

    @FXML
    private TextField addressTxt;

    @FXML
    private TextField addressURLTxt;

    CreateNewEventModel createNewEventModel;

    public CreateNewEventController(){

        createNewEventModel = new CreateNewEventModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        additionalInfoTxt.setText("No additional info");

    }


    public void createEvent(ActionEvent actionEvent) {
        JFrame jFrame = new JFrame();
        try {
            if (nameTxt.getText().isEmpty() || startDateAndTimeTxt.getText().isEmpty() || endDateAndTimeTxt.getText().isEmpty() || addressTxt.getText().isEmpty() || addressURLTxt.getText().isEmpty() || ticketTypesTxt.getText().isEmpty() || additionalInfoTxt.getText().isEmpty())
                JOptionPane.showMessageDialog(jFrame, "FIELD IS EMPTY !!\nPLEASE TRY AGAIN!!");
            else {
                ObservableList ticketTypeList = createNewEventModel.ticketTypes(ticketTypesTxt.getText());
                Date startDateAndTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(startDateAndTimeTxt.getText());
                Date endDateAndTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(endDateAndTimeTxt.getText());
                createNewEventModel.createEvent(nameTxt.getText(), startDateAndTime, endDateAndTime, addressTxt.getText(), addressURLTxt.getText(), ticketTypesTxt.getText(), additionalInfoTxt.getText());
                JOptionPane.showMessageDialog(jFrame, "EVENT CREATED !!");
                Stage currentStage = (Stage) createEventBtn.getScene().getWindow();
                currentStage.close();
                Parent root = FXMLLoader.load(getClass().getResource("/gui/view/coordinatorView.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(scene);
                stage.show();
                scene.setFill(Color.TRANSPARENT);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void closeBtnPressed(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void backBtnPressed(ActionEvent actionEvent) {

        Stage currentStage = (Stage) createEventBtn.getScene().getWindow();
        currentStage.close();
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/gui/view/coordinatorView.fxml"));
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
