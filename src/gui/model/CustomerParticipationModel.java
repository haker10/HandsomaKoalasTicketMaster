package gui.model;

import be.Customer;
import be.Event;
import bll.TicketMasterManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class CustomerParticipationModel {

    TicketMasterManager manager;

    private ObservableList<Event> allEventsWithID;


    public CustomerParticipationModel() {

        manager = new TicketMasterManager();
    }

    public List<Integer> getEventByCustomer(String customerEmail) {
        return manager.getEventByCustomer(customerEmail);
    }

    public ObservableList<Event> getEventByIdOL(int eventIDs) {
        allEventsWithID = FXCollections.observableArrayList();
        allEventsWithID.addAll(manager.getEventByIdOL(eventIDs));
        return allEventsWithID;
    }
}
