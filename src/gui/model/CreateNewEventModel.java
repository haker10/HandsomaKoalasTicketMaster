package gui.model;

import be.Event;
import bll.TicketMasterManager;
import dal.dao.UserDAO;

import java.util.Date;

public class CreateNewEventModel {

    TicketMasterManager manager;
    UserDAO userDAO;

    public CreateNewEventModel(){

        manager = new TicketMasterManager();
        userDAO = new UserDAO();
    }

    public void createEvent(String name, Date startDateAndTime, Date endDateAndTime, String address) {
        Event event = manager.createEvent(name, startDateAndTime, endDateAndTime, address);
    }
}
