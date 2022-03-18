package gui.model;

import bll.TicketMasterManager;
import dal.dao.UserDAO;

public class AdminModel {

    TicketMasterManager manager;
    UserDAO userDAO;

    public AdminModel(){
        manager = new TicketMasterManager();
        userDAO = new UserDAO();
    }

}
