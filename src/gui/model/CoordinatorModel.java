package gui.model;

import bll.TicketMasterManager;
import dal.dao.UserDAO;

public class CoordinatorModel {

    TicketMasterManager manager;
    UserDAO userDAO;

    public CoordinatorModel(){
        manager = new TicketMasterManager();
        userDAO = new UserDAO();
    }
}
