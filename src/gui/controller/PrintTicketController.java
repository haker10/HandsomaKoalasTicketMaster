package gui.controller;

import be.Customer;
import be.Ticket;
import gui.model.PrintTicketModel;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class PrintTicketController implements Initializable {

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
        //send event name instead of event id - to do
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
        String eventInfo = printTicketModel.getEventById(event);
        String customerInfo = printTicketModel.getCustomerByEmail(email);
        System.out.println(eventInfo);
        System.out.println(customerInfo);
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
