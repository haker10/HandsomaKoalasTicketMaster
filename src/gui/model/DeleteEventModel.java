package gui.model;

import be.Event;
import bll.TicketMasterManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DeleteEventModel {

    private ObservableList<Event> allEvents;

    TicketMasterManager manager;

    public DeleteEventModel(){
        manager = new TicketMasterManager();
    }

    public ObservableList getAllEvents() {
        allEvents = FXCollections.observableArrayList();
        allEvents.addAll(manager.getAllEvents());
        return allEvents;
    }

    public void deleteEvent(int chosenEventId) {

        manager.deleteEvent(chosenEventId);

    }
}
