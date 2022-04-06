package gui.controller;

import gui.model.CreateNewTicketModel;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.CheckComboBox;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateNewTicketController implements Initializable {


    @FXML
    private Label customerName;

    @FXML
    private Label eventName;

    @FXML
    private Label startTime;

    @FXML
    private CheckComboBox typeChoice;

    @FXML
    private Button createTicketBtn;


    CreateNewTicketModel createNewTicketModel;


    public CreateNewTicketController(){

        createNewTicketModel = new CreateNewTicketModel();
    }
    private ObservableList listTypes;
    String choosenTypes = "";
    String customerNEvent;
    String time = " ";
    String cName = " ";
    String eName = " ";

    @Override
    public void initialize(URL location, ResourceBundle resources){
        loadData();
        }



    private void loadData(){
        Platform.runLater(() -> {
            Stage currentStage = (Stage) typeChoice.getScene().getWindow();
            customerNEvent = (String) currentStage.getUserData();
            String[] parts = customerNEvent.split("-");
            String customer = parts[0];
            String event = parts[1];
            int eventId = Integer.parseInt(parts[2]);
            String types = createNewTicketModel.getTicketTypes(eventId);
            types.replaceAll("\\s+","");
            String str[] = types.split(",");
            customerName.setText(customer);
            eventName.setText(event);

            listTypes = FXCollections.observableArrayList();
            listTypes.addAll(str);
            typeChoice.getItems().addAll(listTypes);
        });
    }

    public void createTicket(ActionEvent actionEvent) {
        String[] parts = customerNEvent.split("-");
        String customer = parts[0];
        String event = parts[1];
        int eventId = Integer.parseInt(parts[2]);
        JFrame jFrame = new JFrame();
        try {
            if (typeChoice.getItems() == null)
                JOptionPane.showMessageDialog(jFrame, "FIELD IS EMPTY !!\nPLEASE TRY AGAIN!!");

            else {
                    ObservableList list = typeChoice.getCheckModel().getCheckedItems();
                    for (int i = 0; i < list.size(); i++)
                    {
                        choosenTypes += list.get(i) + " ";

                    }
                    createNewTicketModel.createTicket(eventId, customer, choosenTypes);

                    JOptionPane.showMessageDialog(jFrame, "TICKET CREATED !!");
                    Stage currentStage = (Stage) createTicketBtn.getScene().getWindow();
                    currentStage.close();
                    Parent root = FXMLLoader.load(getClass().getResource("/gui/view/coordinatorView.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setScene(scene);
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