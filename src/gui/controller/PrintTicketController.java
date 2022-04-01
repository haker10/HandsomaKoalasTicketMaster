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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class PrintTicketController implements Initializable {

    @FXML
    private TextField filterTxt;

    @FXML
    private Button printBtn;

    @FXML
    private TableView searchTicketTV;

    @FXML
    private TableColumn eventNameColumn;

    @FXML
    private TableColumn emailColumn;

    @FXML
    private TableColumn ticketTypeColumn;

    @FXML
    private TableColumn additionalInfoColumn;

    PrintTicketModel printTicketModel;
    //Constructor =  Lego
    public PrintTicketController() {

        printTicketModel = new PrintTicketModel();
    }
    public void updateTicketListTableView(){
        eventNameColumn.setCellValueFactory(new PropertyValueFactory<>("eventId"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        ticketTypeColumn.setCellValueFactory(new PropertyValueFactory<>("ticketType"));
        additionalInfoColumn.setCellValueFactory(new PropertyValueFactory<>("additionalInfo"));
        try{
            searchTicketTV.setItems(printTicketModel.getAllTickets());

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void printTicket(ActionEvent actionEvent) {
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

                if (ticket.getCustomerId().toLowerCase().contains(lowerCaseFilter) || ticket.getTicketType().toLowerCase().contains(lowerCaseFilter) || ticket.getAdditionalInfo().toLowerCase().contains(lowerCaseFilter))
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
