package gui.controller;

import be.Customer;
import be.Ticket;
import gui.model.PrintTicketModel;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.w3c.dom.Element;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class PrintTicketController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField filterTxt;

    @FXML
    private Button printBtn;

    @FXML
    private TableView<Ticket> searchTicketTV;

    @FXML
    private TableColumn eventNameColumn;

    @FXML
    private TableColumn emailColumn;

    @FXML
    private TableColumn ticketTypeColumn;


    PrintTicketModel printTicketModel;
    //Constructor =  Lego
    public PrintTicketController() {

        printTicketModel = new PrintTicketModel();
    }
    public void updateTicketListTableView(){
        eventNameColumn.setCellValueFactory(new PropertyValueFactory<>("eventId"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        ticketTypeColumn.setCellValueFactory(new PropertyValueFactory<>("ticketType"));
        try{
            searchTicketTV.setItems(printTicketModel.getAllTickets());

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void printTicket(ActionEvent actionEvent) {
        TablePosition pos = searchTicketTV.getSelectionModel().getSelectedCells().get(0);
        int event = (Integer) eventNameColumn.getCellObservableValue(searchTicketTV.getItems().get(pos.getRow())).getValue();
        String email = (String) emailColumn.getCellObservableValue((searchTicketTV.getItems().get(pos.getRow()))).getValue();
        String ticketType = (String) ticketTypeColumn.getCellObservableValue((searchTicketTV.getItems().get(pos.getRow()))).getValue();
        String eventInfo = printTicketModel.getEventById(event);
        String customerInfo = printTicketModel.getCustomerByEmail(email);
        String[] eventData = eventInfo.split("_");
        String[] customerData = customerInfo.split("_");
        String eventName = "\nEvent Name: " + eventData[1];
        String ticketTypeName = "\n\nTicket Type: " + ticketType;
        String eventStartDatenTime = "\n\nStart date and time: " + eventData[2];
        String eventEndDatenTime = "\n\nEnd date and time: " + eventData[3];
        String eventAddress = "\n\nAddress: " + eventData[4];
        String eventAdditionalInfo = "\n\nAdditional Info: " + eventData[6];
        String customerName = "\n\nCustomer Name: " + customerData[0];
        String customerEmail = "\n\nEmail: " + customerData[1];
        String customerPhone = "\n\nPhone: " + customerData[2];
        String ticket = eventName + ticketTypeName + eventStartDatenTime + eventEndDatenTime + eventAddress + eventAdditionalInfo + customerName + customerEmail + customerPhone;
        JFrame jFrame = new JFrame();
        try{
            if (email == null){
                JOptionPane.showMessageDialog(jFrame, "FIELD IS EMPTY !!\nPLEASE TRY AGAIN!!");
            }
            else {
                Stage currentStage = (Stage) printBtn.getScene().getWindow();
                currentStage.close();
                Parent root = FXMLLoader.load(getClass().getResource("/gui/view/customerTicketView.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                //stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(scene);
                stage.setUserData(ticket);
                stage.show();
                //scene.setFill(Color.TRANSPARENT);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateTicketListTableView();
        ObservableList<Ticket> ticketList = printTicketModel.getAllTickets();
        FilteredList<Ticket> filteredData = null;
        try {
            filteredData = new FilteredList<>(ticketList, b -> true);
        } catch (Exception e) {
            e.printStackTrace();
        }



        FilteredList<Ticket> finalFilteredData = filteredData;
        filterTxt.textProperty().addListener((observable, oldValue, newValue) -> {
            finalFilteredData.setPredicate(ticket -> {


                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (ticket.getCustomerId().toLowerCase().contains(lowerCaseFilter) || ticket.getTicketType().toLowerCase().contains(lowerCaseFilter))
                    return true;
                else
                    return false;
            });
        });


        SortedList<Ticket> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(searchTicketTV.comparatorProperty());

        searchTicketTV.setItems(sortedData);

    }
}
