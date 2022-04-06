package gui.model;

import bll.TicketMasterManager;

public class CreateNewTicketModel {
    TicketMasterManager manager;

    public CreateNewTicketModel() {
        manager = new TicketMasterManager();
    }

    public void createTicket(int eventId, String customer, String choosenTypes) {

        manager.createTicket(eventId, customer, choosenTypes);
    }

    public String getTicketTypes(int eventId) {
        return manager.getTicketTypes(eventId);
    }
}
