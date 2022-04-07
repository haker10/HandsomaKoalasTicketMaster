package gui.model;

import be.Customer;
import be.Event;
import bll.TicketMasterManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class CustomerParticipationModel {

    TicketMasterManager manager;

    public CustomerParticipationModel() {

        manager = new TicketMasterManager();
    }

    public List<Integer> getEventByCustomer(String customerEmail) {
        return manager.getEventByCustomer(customerEmail);
    }

    public Event getEventByIdOL(int eventIDs) {
        return manager.getEventByIdOL(eventIDs);
    }
}
