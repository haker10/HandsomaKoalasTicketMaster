package gui.model;

import be.Event;
import bll.TicketMasterManager;

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
