package gui.model;

import bll.TicketMasterManager;

import java.util.Date;

public class CreateNewEventModel {

    TicketMasterManager manager;

    public CreateNewEventModel(){

        manager = new TicketMasterManager();
    }

    public void createEvent(String name, Date startDateAndTime, Date endDateAndTime, String address) {
        manager.createEvent(name, startDateAndTime, endDateAndTime, address);
    }
}
