package gui.model;

import be.Event;
import bll.TicketMasterManager;

import java.util.Date;

public class EditEventModel {

    TicketMasterManager manager;

    public EditEventModel(){

        manager = new TicketMasterManager();

    }

    public void editEvent(int id, String name, Date startDateAndTime, Date endDateAndTime, String address, String addressUrl, String ticketTypes, String additionalInfo) {
        manager.editEvent(id, name, startDateAndTime, endDateAndTime, address, addressUrl, ticketTypes, additionalInfo);
    }
}
