package gui.model;

import be.Ticket;
import bll.TicketMasterManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class PrintTicketModel {

    TicketMasterManager manager;
    private ObservableList<Ticket> allTickets;

    public PrintTicketModel() {
        manager = new TicketMasterManager();
    }


    public ObservableList getAllTickets() {
        allTickets = FXCollections.observableArrayList();
        allTickets.addAll(manager.getAllTickets());
        return allTickets;
    }

    public String getEventById(int id){ return manager.getEventById(id); }

    public String getCustomerByEmail(String email) {
        return manager.getCustomerByEmail(email);
    }

    public void deleteTicket(int id) {
        manager.deleteTicket(id);
    }
}
