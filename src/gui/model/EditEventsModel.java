package gui.model;

import be.Customer;
import be.Event;
import bll.TicketMasterManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EditEventsModel {

    private ObservableList<Event> allEvents;
    private ObservableList<Customer> listOfParticipants;

    TicketMasterManager manager;

    public EditEventsModel(){
        manager = new TicketMasterManager();
    }

    public ObservableList getAllEvents() {
        allEvents = FXCollections.observableArrayList();
        allEvents.addAll(manager.getAllEvents());
        return allEvents;
    }

    public ObservableList getListOfParticipants(int eventId) {
        listOfParticipants = FXCollections.observableArrayList();
        listOfParticipants.addAll(manager.getListOfParticipants(eventId));
        return listOfParticipants;
    }
}
