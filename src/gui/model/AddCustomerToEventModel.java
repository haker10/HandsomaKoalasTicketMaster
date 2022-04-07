package gui.model;

import be.Customer;
import be.Event;
import bll.TicketMasterManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AddCustomerToEventModel {

    private ObservableList<Event> allEvents;

    TicketMasterManager manager;

    public AddCustomerToEventModel(){

        manager = new TicketMasterManager();
    }

    public ObservableList getAllEvents() {
        allEvents = FXCollections.observableArrayList();
        allEvents.addAll(manager.getAllEvents());
        return allEvents;
    }

    public ObservableList getAllEventsToDo() {
        allEvents = FXCollections.observableArrayList();
        allEvents.addAll(manager.getAllEventsToDo());
        return allEvents;
    }

    public Customer createCustomer(String name, String email, String phone) {

        Customer customer = manager.createCustomer(name, email, phone);
        return customer;

    }

    public void addCustomerToEvent(int eventId, String customerEmail) {

        manager.addCustomerToEvent(eventId, customerEmail);

    }
}
