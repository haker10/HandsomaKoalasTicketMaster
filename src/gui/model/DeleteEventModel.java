package gui.model;

import be.Event;
import bll.TicketMasterManager;
import dal.dao.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DeleteEventModel {

    private ObservableList<Event> allEvents;

    TicketMasterManager manager;
    UserDAO userDAO;

    public DeleteEventModel(){
        manager = new TicketMasterManager();
        userDAO = new UserDAO();
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
