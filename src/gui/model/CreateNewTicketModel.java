package gui.model;

import bll.TicketMasterManager;

public class CreateNewTicketModel {
    TicketMasterManager manager;

    public CreateNewTicketModel() {
        manager = new TicketMasterManager();
    }

    public void createTicket(int eventId, String customer, String choosenTypes, String additionalInfo) {


        manager.createTicket(eventId, customer, choosenTypes, additionalInfo);
    }

    public String getTicketTypes(int eventId) {
        return manager.getTicketTypes(eventId);
    }
}
