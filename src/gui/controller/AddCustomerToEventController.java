package gui.controller;

import be.Customer;
import be.Event;
import gui.model.AddCustomerToEventModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class AddCustomerToEventController implements Initializable {

    @FXML
    private Button addCustomerToEventBtn;

    @FXML
    private TextField phoneCustomerTxt;

    @FXML
    private TextField emailCustomerTxt;

    @FXML
    private TextField customerNameTxt;

    @FXML
    private ChoiceBox<?> eventChoiceBox;


    AddCustomerToEventModel addCustomerToEventModel;


    public AddCustomerToEventController(){

        addCustomerToEventModel = new AddCustomerToEventModel();

    }

    private void loadData(){

        eventChoiceBox.getItems().addAll(addCustomerToEventModel.getAllEventsToDo());

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loadData();

    }

    public void addCustomerToEvent(ActionEvent actionEvent) {
        JFrame jFrame = new JFrame();
        String customerEmail = emailCustomerTxt.getText();
        String eventName = ((Event) eventChoiceBox.getSelectionModel().getSelectedItem()).getName();
        int eventID = ((Event) eventChoiceBox.getSelectionModel().getSelectedItem()).getId();
        String customerNEvent = customerEmail + "-" + eventName + "-" + eventID;
        try {
            if (eventChoiceBox.getValue() == null || phoneCustomerTxt.getText().isEmpty() || emailCustomerTxt.getText().isEmpty() || customerNameTxt.getText().isEmpty()) {
                JOptionPane.showMessageDialog(jFrame, "FIELD IS EMPTY !!\nPLEASE TRY AGAIN!!");
            } else {
                addCustomerToEventModel.createCustomer(customerNameTxt.getText(), emailCustomerTxt.getText(), phoneCustomerTxt.getText());
                addCustomerToEventModel.addCustomerToEvent(((Event) eventChoiceBox.getSelectionModel().getSelectedItem()).getId(), emailCustomerTxt.getText());
                JOptionPane.showMessageDialog(jFrame, "CUSTOMER ADDED TO EVENT !!");
                Stage currentStage = (Stage) addCustomerToEventBtn.getScene().getWindow();
                currentStage.close();
                Parent root = FXMLLoader.load(getClass().getResource("/gui/view/ticketView.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(scene);
                stage.setUserData(customerNEvent);
                stage.show();
                scene.setFill(Color.TRANSPARENT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeBtnPressed(ActionEvent actionEvent) {
        Platform.exit();
    }
}
