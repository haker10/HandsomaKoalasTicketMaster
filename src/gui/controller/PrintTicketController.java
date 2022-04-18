package gui.controller;

import be.Customer;
import be.Event;
import be.Ticket;
import gui.model.PrintTicketModel;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.net.URL;
import java.text.ParseException;
import java.time.Instant;
import java.util.Date;
import java.util.ResourceBundle;
import java.text.SimpleDateFormat;

public class PrintTicketController implements Initializable {

    @FXML
    private Button deleteBtn;

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
        String eventAddressUrl = "\n\nAddress URL: " + eventData[5];
        String eventAdditionalInfo = "\n\nAdditional Info: " + eventData[7];
        String customerName = "\n\nCustomer Name: " + customerData[0];
        String customerEmail = "\n\nEmail: " + customerData[1];
        String customerPhone = "\n\nPhone: " + customerData[2];
        String ticket = eventName + ticketTypeName + eventStartDatenTime + eventEndDatenTime + eventAddress + eventAddressUrl + eventAdditionalInfo + customerName + customerEmail + customerPhone;
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
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(scene);
                stage.setUserData(ticket);
                stage.show();
                scene.setFill(Color.TRANSPARENT);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public Date StartDateNTime(Ticket ticket){
            Date date1;
            String eventInfo = printTicketModel.getEventById(ticket.getEventId());
            String[] eventData = eventInfo.split("_");
            String sDate1 = eventData[3];
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(sDate1);
        }catch(ParseException ex){
            System.err.print(ex.getMessage());
            date1 = null;
        }
        return date1;
        }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateTicketListTableView();
        searchTicketTV.setRowFactory(tr -> new  TableRow<Ticket>(){
            @Override
            protected void updateItem(Ticket item, boolean empty) {
                super.updateItem(item, empty);
                Date date1 = null;

                if( !empty && item != null){
                    date1 = StartDateNTime(item);
                }


                if (empty == true){
                    setStyle("-fx-background-color: #DAD5D6");
                    return;
                }
                if(item != null && date1 != null &&  Date.from(Instant.now()).after(date1)){
                    setStyle("-fx-background-color: #C54B6C;");
                }
                else{
                    setStyle("-fx-background-color: #8DA47E;");
                }

            }
        });
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

    public void closeBtnPressed(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void deleteTicket(ActionEvent actionEvent) {
        JFrame jFrame = new JFrame();
        try{
            if (searchTicketTV.getSelectionModel().getSelectedItem() == null){
                JOptionPane.showMessageDialog(jFrame, "FIELD IS EMPTY !!\nPLEASE TRY AGAIN!!");
            }
            else {
                printTicketModel.deleteTicket(((Ticket) searchTicketTV.getSelectionModel().getSelectedItem()).getId());
                JOptionPane.showMessageDialog(jFrame, "TICKET DELETED !!");
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
